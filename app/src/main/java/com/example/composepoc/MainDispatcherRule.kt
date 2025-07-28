package com.example.composepoc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher

// --- Helper: MainDispatcherRule for JUnit 4 ---
// This rule replaces the Main dispatcher with a TestDispatcher for the duration of the test.
@ExperimentalCoroutinesApi
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() { // TestWatcher is from JUnit
    override fun starting(description: org.junit.runner.Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: org.junit.runner.Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}