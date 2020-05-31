package jp.cordea.daggerhiltdemo

class Notifiable<T>(private val event: T) {
    private var notified = false

    fun poll(): T? {
        if (notified) {
            return null
        }
        notified = true
        return event
    }
}
