package com.android.roomdbobserverdemo

import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Robert Duriancik on 3/12/19.
 */
class TaskRepository private constructor(context: Context) {

    private val mTaskDao = Room.databaseBuilder(context, TaskDatabase::class.java, "tasks-database")
        .build()
        .taskDao()

    private val mObserverSubject = PublishSubject.create<DatabaseEvent<Task>>()

    fun addTask(task: Task): Completable {
        return mTaskDao.insertTask(task)
            .doOnComplete { mObserverSubject.onNext(DatabaseEvent(DatabaseEventType.INSERTED, task)) }
    }

    fun deleteTask(task: Task): Completable {
        return mTaskDao.deleteTask(task)
            .doOnComplete { mObserverSubject.onNext(DatabaseEvent(DatabaseEventType.REMOVED, task)) }
    }

    fun updateTask(task: Task): Completable {
        return mTaskDao.updateTask(task)
            .doOnComplete { mObserverSubject.onNext(DatabaseEvent(DatabaseEventType.UPDATED, task)) }
    }

    fun observeTasks(): Observable<DatabaseEvent<Task>> {
        return mTaskDao.getAll()
            .flatMapObservable { Observable.fromIterable(it) }
            .map { DatabaseEvent(DatabaseEventType.INSERTED, it) }
            .concatWith(mObserverSubject)
    }

    companion object {
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(context: Context): TaskRepository {
            return instance ?: synchronized(this) {
                instance ?: TaskRepository(context.applicationContext).also { instance = it }
            }
        }
    }
}