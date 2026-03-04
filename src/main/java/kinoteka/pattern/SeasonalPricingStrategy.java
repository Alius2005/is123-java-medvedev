package kinoteka.pattern;

import kinoteka.model.Movie;
import kinoteka.model.User;
import java.time.Month;
import java.time.LocalDate;

public class SeasonalPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Movie movie, User user) {
        Month now = LocalDate.now().getMonth();
        if (now == Month.DECEMBER) {
            return 4.0;
        }
        return 5.0;
    }
}
