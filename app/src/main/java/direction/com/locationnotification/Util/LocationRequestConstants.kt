package direction.com.locationnotification.Util

class LocationRequestConstants {

    companion object {

        val MIN_DISTANCE_DISPLACEMENT: Float = 100F
        val MIN_TIME_DISPLACEMENT: Long = 3000
        val LOCATION_PERMISSION: Int = 1
        val PLACE_PICKER_REQUEST: Int = 2
        val NOTIFICATION_ID: Int = 3
        val START_LOCATION_FOREGROUND_SERVICE: String = "com.locationnotification.action.startforeground"
        val STOP_LOCATION_FOREGROUND_SERVICE: String = "com.locationnotification.action.stopforeground"
    }
}