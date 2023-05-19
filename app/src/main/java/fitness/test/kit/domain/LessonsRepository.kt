package fitness.test.kit.domain

import fitness.test.kit.data.LessonsResult
import kotlinx.coroutines.flow.Flow


interface LessonsRepository {
    suspend fun getLessons(): Flow<LessonsResult>
}