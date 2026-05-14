package cinemahome.observer;

import org.example.cinemahome.observer.MoodNotifier;
import org.example.cinemahome.util.MoodAnalyzer;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MoodNotifierTest {

    @Test
    void sendNotification_callsMoodAnalyzer() {
        MoodAnalyzer analyzer = mock(MoodAnalyzer.class);
        when(analyzer.analyzeMood()).thenReturn("Calm");

        MoodNotifier notifier = new MoodNotifier();
        injectField(notifier, "moodAnalyzer", analyzer);

        notifier.sendNotification();

        verify(analyzer, times(1)).analyzeMood();
    }

    private static void injectField(Object target, String fieldName, Object value) {
        try {
            var f = target.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
