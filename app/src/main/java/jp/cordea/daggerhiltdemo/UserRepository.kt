package jp.cordea.daggerhiltdemo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) {
    @ExperimentalCoroutinesApi
    fun findAll(forceRefresh: Boolean) =
        if (forceRefresh) {
            remoteDataSource.findAll()
        } else {
            localDataSource.findAll()
                .catch { emitAll(remoteDataSource.findAll()) }
        }
}
