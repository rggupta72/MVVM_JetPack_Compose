package com.example.composepoc

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

object PublishSubjectNotifier {
    private val publisher = PublishSubject.create<PublishSubjectEvent>()

    @JvmStatic
    fun gmwPublishEvent(event: PublishSubjectEvent) {
        publisher.onNext(
            event
        )
    }    // Listen should return an Observable and not the publisher    // Using ofType we filter only events that match that class type

    fun listen(eventType: Class<PublishSubjectEvent>): Observable<PublishSubjectEvent> =
        publisher.ofType(eventType)
}