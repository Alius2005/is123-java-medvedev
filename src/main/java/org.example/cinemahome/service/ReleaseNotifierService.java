package org.example.cinemahome.service;

import org.example.cinemahome.observer.ReleaseNotifier;
import org.example.cinemahome.observer.EmailNotifier;
import org.example.cinemahome.observer.InAppNotifier;
import org.example.cinemahome.observer.MoodNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseNotifierService {
    @Autowired
    private ReleaseNotifier notifier;

    public void notifyAboutRelease() {
        notifier.notify();
    }
}
