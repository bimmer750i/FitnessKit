package fitness.test.kit.data

import retrofit2.Response
import retrofit2.http.GET

interface RemoteService {

    @GET("schedule/get_v3/?club_id=2")
    suspend fun getLessons(): Response<RawLessonsEntity>

}