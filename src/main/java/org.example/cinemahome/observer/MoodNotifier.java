package org.example.cinemahome.observer;

import org.example.cinemahome.util.MoodAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoodNotifier implements ReleaseNotifier {
    @Autowired
    private MoodAnalyzer moodAnalyzer;

    @Override
    public void sendNotification() {
        System.out.println("Mood-based alert: 'This movie fits your current vibe: " + moodAnalyzer.analyzeMood() + "'");
    }
}
