package kinoteka.pattern;

import kinoteka.model.Movie;
import kinoteka.model.User;

public class BasePricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Movie movie, User user) {
        return 5.0;
    }
}
