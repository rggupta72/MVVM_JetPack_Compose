package com.example.composepoc

sealed class PublishSubjectEvent {
    data class AppointmentsEvent(val status: Boolean = false) : PublishSubjectEvent()

}