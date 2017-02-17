package com.cydercode.devtoolkit.ui;

import eu.hansolo.enzo.notification.Notification;

public class NotificationFacade {

    public void showInformation(String header, String content) {
        Notification.Notifier.INSTANCE.notify(new Notification(header, content));
    }
}
