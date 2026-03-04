package kinoteka.pattern;

public class SmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}
