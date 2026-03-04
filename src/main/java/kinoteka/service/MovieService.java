package kinoteka.service;

import kinoteka.model.Movie;
import kinoteka.model.MovieDto;
import kinoteka.model.User;
import kinoteka.repository.MovieRepositoryCustom;
import kinoteka.pattern.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepositoryCustom repo;
    private final PricingStrategy baseStrategy = new BasePricingStrategy();
    private final PricingStrategy discountStrategy = new DiscountPricingStrategy();
    private final PricingStrategy seasonalStrategy = new SeasonalPricingStrategy();
    private final ReleaseNotifier notifier = new ReleaseNotifier();

    public MovieService(RepositoryFactory factory) {
        this.repo = factory.getMovieRepository();
        notifier.addObserver(new EmailNotifier());
        notifier.addObserver(new SmsNotifier());
        notifier.addObserver(new PushNotifier());
    }

    public Movie addMovie(MovieDto dto) {
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setYear(dto.getYear());
        movie.setFilePath(dto.getFilePath());
        movie.setCoverPath(dto.getCoverPath());
        movie.setGenres(dto.getGenres());
        movie.setActors(dto.getActors());
        Movie saved = repo.save(movie);
        notifier.notifyAll("New movie released: " + saved.getTitle());
        return saved;
    }

    public Optional<Movie> getById(String id) {
        return repo.findById(id);
    }

    public List<Movie> getAll() {
        return repo.findAll();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public double getPrice(String id, User user) {
        Optional<Movie> m = repo.findById(id);
        if (m.isEmpty()) return -1;
        double price = baseStrategy.calculatePrice(m.get(), user);
        price = discountStrategy.calculatePrice(m.get(), user);
        price = seasonalStrategy.calculatePrice(m.get(), user);
        return price;
    }
}
