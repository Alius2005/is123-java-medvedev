package kinoteka.pattern;

import java.util.ArrayList;
import java.util.List;

public class ReleaseNotifier {

    private final List<Notifier> observers = new ArrayList<>();

    public void addObserver(Notifier n) {
        observers.add(n);
    }

    public void removeObserver(Notifier n) {
        observers.remove(n);
    }

    public void notifyAll(String message) {
        for (Notifier n : observers) {
            n.send(message);
        }
    }
}
