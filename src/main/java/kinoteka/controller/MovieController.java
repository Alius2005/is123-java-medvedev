package kinoteka.controller;

import kinoteka.model.Movie;
import kinoteka.model.MovieDto;
import kinoteka.model.User;
import kinoteka.service.MovieService;
import kinoteka.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final UserService userService;

    public MovieController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Movie> add(@RequestBody MovieDto dto) {
        return ResponseEntity.ok(movieService.addMovie(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> get(@PathVariable String id) {
        return movieService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Movie>> all() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<Double> price(@PathVariable String id, Authentication auth) {
        User user = userService.findByUsername(auth.getName()).orElse(null);
        if (user == null) return ResponseEntity.status(401).build();
        double price = movieService.getPrice(id, user);
        return ResponseEntity.ok(price);
    }
}
