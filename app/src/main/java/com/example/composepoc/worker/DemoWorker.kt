package com.example.composepoc.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DemoWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    val maxRetryConstantIWant = 3
    override fun doWork(): Result {
        return if (runAttemptCount < maxRetryConstantIWant) {
            try {
                // Simulate some work
                Log.d("MySuccessWorker", "Starting work...")

                // Perform your background task here.
                // If it's successful:
                Log.d("MySuccessWorker", "Work finished successfully!")
                performWork()
                Result.success() // <--- THIS IS THE CORRECT WAY

                // If you need to pass output data:
                // val outputData = workDataOf("output_key" to "some_result")
                // Result.success(outputData)

            } catch (e: Exception) {
                Log.e("MySuccessWorker", "Work failed", e)
                Result.retry() // <--- For failure
                // Or Result.retry() if you want WorkManager to retry
            }
        } else {
            Result.failure()
        }
    }

    fun performWork() {
        Thread.sleep(2000)
        Log.d("Test Worker", "Worker Called")

    }
}