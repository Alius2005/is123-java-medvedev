package org.example.cinemahome.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotifierRegistry {

    @Autowired
    private ApplicationContext context;

    public void notifyAllNotifiers() {
        List<ReleaseNotifier> notifiers = context.getBeansOfType(ReleaseNotifier.class).values().stream()
                .collect(Collectors.toList());
        notifiers.forEach(ReleaseNotifier::sendNotification);
    }
}
