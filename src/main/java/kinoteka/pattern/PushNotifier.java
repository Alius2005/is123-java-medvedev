package kinoteka.pattern;

public class PushNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Push notification: " + message);
    }
}
