package kinoteka.pattern;

import kinoteka.model.Movie;
import kinoteka.model.User;

public class DiscountPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Movie movie, User user) {
        if (user.getRoles().contains("VIP")) {
            return 3.0;
        }
        return 5.0;
    }
}
