package com.example.composepoc.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.composepoc.database.RoomDataModel
import com.example.composepoc.database.db.WorkerDatabase
import kotlin.random.Random

class DemoWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    val maxRetryConstantIWant = 3
    var workerDao = WorkerDatabase.getInstance(applicationContext).workerDao()
    override suspend fun doWork(): Result {
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

    suspend fun performWork() {
        val randomNumber = Random.nextInt()
        workerDao.setData(listOf(RoomDataModel(randomNumber, "Worker")))

        if (workerDao.getWorker().isNotEmpty()) {
            Log.d("Data List", workerDao.getWorker().toString())
        }

    }
}