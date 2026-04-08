package org.example.cinemahome.observer;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

@Component
@Primary
public class EmailNotifier implements ReleaseNotifier {
    @Override
    public void sendNotification() {
        System.out.println("Email sent: 'New movie alert! (Probably just a demo)'");
    }
}
