package fitness.test.kit.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Model @Inject constructor(val service: RemoteService) {

    suspend fun getLessons(): Flow<LessonsResult> = channelFlow {
        send(PendingLessonsResult())
        try {
            val response = service.getLessons()
            if (!response.isSuccessful) {
                send(FailureLessonsResult())
            }
            else {
                response.body()?.let {
                    val lessons = it
                    send(SuccessRawLessonsResult(lessons))
                }
            }
        }
        catch (e: java.lang.Exception) {
            send(FailureLessonsResult())
        }
    }.flowOn(Dispatchers.IO)

}