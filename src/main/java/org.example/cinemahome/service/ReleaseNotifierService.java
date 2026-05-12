package org.example.cinemahome.service;

import org.example.cinemahome.observer.ReleaseNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseNotifierService {

    @Autowired
    private List<ReleaseNotifier> notifiers;

    public void notifyAboutRelease() {
        notifiers.forEach(ReleaseNotifier::sendNotification);
    }
}
