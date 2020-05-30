package jp.cordea.daggerhiltdemo

import io.bloco.faker.Faker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor() {
    private val faker = Faker()

    @ExperimentalCoroutinesApi
    fun findAll(): Flow<List<User>> = flowOf(
        (0 until 30).map {
            User(
                faker.name.firstName(),
                faker.name.lastName(),
                faker.phoneNumber.phoneNumber(),
                faker.internet.safeEmail(),
                faker.company.name()
            )
        }
    )
}
