package fitness.test.kit.domain

import java.text.SimpleDateFormat

fun getDuration(time1: String?,time2: String?): String {
    if (time1.isNullOrEmpty() || time2.isNullOrEmpty()) {
        return ""
    }
    else {
        try {
            val seconds1 = SimpleDateFormat("HH:mm").parse(time1).time
            val seconds2 = SimpleDateFormat("HH:mm").parse(time2).time
            val diff = seconds2-seconds1
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            if (hours == 0L) {
                return "${minutes-hours*60}м"
            }
            else if (minutes-hours*60 == 0L) {
                return "${hours}ч"
            }
            else {
                return "${hours}ч ${minutes-hours*60}м"
            }
        }
        catch (e: Exception) {
            return ""
        }

    }

}