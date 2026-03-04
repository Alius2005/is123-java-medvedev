package kinoteka.pattern;

import kinoteka.model.Movie;
import kinoteka.model.User;

public interface PricingStrategy {
    double calculatePrice(Movie movie, User user);
}
