package linda.notification.Monitor;


import android.service.notification.StatusBarNotification;

public class Maps {
    public final static String packageName = "com.google.android.apps.maps";
    public final static String groupKey = "0|com.google.android.apps.maps|g:navigation_status_notification_group";
    private static StatusBarNotification lastNotification = null;

    /**
     * Get the Direction Icon Type
     * @param id String
     * @return iconType String
     */
    public String getIconType(String id){
        String iconType;
        switch (id){
            case "0x7f020515" : iconType = "STRAIGHT"; break;
            default: iconType = null;
        }
        return iconType;
    }

    /**
     * Returns the last notification
     * @return StatusBarNotification
     */
    public static StatusBarNotification getLastNotification() {
        return lastNotification;
    }

    /**
     * Updates the notification
     * @param lastNotification StatusBarNotification
     */
    public static void updateLastNotification(StatusBarNotification lastNotification) {
        Maps.lastNotification = lastNotification;
    }

}
