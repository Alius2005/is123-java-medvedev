package org.example.cinemahome.util;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class MoodAnalyzer {

    private static final String[] MOODS = {
            "Calm", "Romantic", "Thrilling", "Nostalgic", "Chaotic", "Existential"
    };

    public String analyzeMood() {
        return MOODS[new Random().nextInt(MOODS.length)];
    }
}
