package org.example.cinemahome.observer;

import org.springframework.stereotype.Component;

@Component
public class InAppNotifier implements ReleaseNotifier {
    @Override
    public void sendNotification() {
        System.out.println("In-app notification: 'A movie was added. You didn't notice.'");
    }
}
