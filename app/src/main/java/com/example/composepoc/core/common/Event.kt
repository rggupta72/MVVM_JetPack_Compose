package com.example.composepoc.core.common

/** * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * * This class is designed to hold content that should only be handled once.
 * * It is useful in situations where you want to expose events to observers,
 * * such as navigation actions or one-time UI events.
 * * * @param T The type of the content wrapped by this Event.
 * * @property content The data contained in this event. */
open class Event<out T>(private val content: T) {
    /**     * Flag indicating whether the content has been handled.
     *  * Allows external read access but prevents modification.     */
    var hasBeenHandled = false
        private set // Allow external read but not write
//    /**     * Returns the content and prevents its use again.
//    *     * If the content has already been handled, this method returns null.
//    * Otherwise, it marks the content as handled and returns the content.
//    *     * @return The content if it has not been handled, or null if it has been handled.

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**     * This method simplifies the use of getContentIfNotHandled() function.
     *  * Instead of calling event.getContentIfNotHandled()?.let { },
     *  * you can replace it with event.handleEvent { }.
     *  *     * @param block The action to perform with the content if it has not been handled.
     *  * @return The result of the block if the content has not been handled, or null if it has.
     *  */
    inline fun <R> handleEvent(block: (T) -> R): R? {
        return getContentIfNotHandled()?.let {
            block(it)
        }
    }

    /**     * Returns the current event value.
     *  *     * This method provides access to the raw content without checking
     *  * if it has been handled. Use with caution.
     *  *     * @return The current content of the event.
     *  */
    fun eventValue() = content
}