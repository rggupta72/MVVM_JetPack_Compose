package com.example.composepoc.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers.computation
import io.reactivex.rxjava3.schedulers.Schedulers.io

/** * Sets the subscribeOn and observeOn schedulers for a Single. * This function subscribes on the IO scheduler and observes on the main thread. * * @return a Single with the specified schedulers applied. */
fun <T : Any> Single<T>.iOSubscribeMainThreadObserve(): Single<T> =
    compose<T> { it.subscribeOn(io()).observeOn(mainThread()) }

/** * Sets the subscribeOn and observeOn schedulers for an Observable. * This function subscribes on the IO scheduler and observes on the main thread. * * @return an Observable with the specified schedulers applied. */
fun <T : Any> Observable<T>.iOSubscribeMainThreadObserve(): Observable<T> =
    compose<T> { it.subscribeOn(io()).observeOn(mainThread()) }

/** * Sets the subscribeOn and observeOn schedulers for an Observable. * This function subscribes on the IO scheduler and observes on the Computation scheduler. * * @return an Observable with the specified schedulers applied. */
fun <T : Any> Observable<T>.iOSubscribeComputationObserve(): Observable<T> =
    compose<T> { it.subscribeOn(io()).observeOn(computation()) }

/** * Sets the subscribeOn and observeOn schedulers for a Completable. * This function subscribes on the IO scheduler and observes on the main thread. * * @return a Completable with the specified schedulers applied. */
fun Completable.iOSubscribeMainThreadObserve(): Completable =
    compose { it.subscribeOn(io()).observeOn(mainThread()) }

/** * Sets the subscribeOn and observeOn schedulers for a Flowable. * This function subscribes on the IO scheduler and observes on the main thread. * * @return a Flowable with the specified schedulers applied. */
fun <T : Any> Flowable<T>.iOSubscribeMainThreadObserve(): Flowable<T> =
    compose { it.subscribeOn(io()).observeOn(mainThread()) }

/** * Sets the subscribeOn and observeOn schedulers for a Maybe. * This function subscribes on the IO scheduler and observes on the main thread. * * @return a Maybe with the specified schedulers applied. */
fun <T> Maybe<T>.iOSubscribeMainThreadObserve(): Maybe<T> =
    compose<T> { it.subscribeOn(io()).observeOn(mainThread()) }