package com.example.composepoc.data.di

import android.app.Application
import android.content.Context
import com.example.composepoc.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + Dispatchers.IO + CoroutineName("DemoApp"))
    }

    @Singleton
    @Provides
    fun providesApplicationNavGraph(): ApplicationNavGraphProvider {
        return ApplicationNavGraphProviderImpl()
    }

}