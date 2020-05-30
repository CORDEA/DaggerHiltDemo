package jp.cordea.daggerhiltdemo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor() {
    private val cache: List<User>? = null

    fun findAll(): Flow<List<User>> = flow {
        emit(cache ?: error("Cache is unavailable"))
    }
}
