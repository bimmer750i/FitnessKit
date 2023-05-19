package fitness.test.kit.data

import fitness.test.kit.domain.LessonsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LessonsRepositoryImpl @Inject constructor(val model: Model): LessonsRepository {

    override suspend fun getLessons(): Flow<LessonsResult> {
        return model.getLessons()
    }

}