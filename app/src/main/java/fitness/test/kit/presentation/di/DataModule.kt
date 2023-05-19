package fitness.test.kit.presentation.di

import dagger.Module
import dagger.Provides
import fitness.test.kit.data.LessonsRepositoryImpl
import fitness.test.kit.data.Model
import fitness.test.kit.data.RemoteService
import fitness.test.kit.domain.LessonsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideCurrencyV2RemoteService(): RemoteService {
        val retrofit2 = Retrofit.Builder()
            .baseUrl("https://olimpia.fitnesskit-admin.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit2.create(RemoteService::class.java)
    }

    @Provides
    fun provideRepository(model: Model): LessonsRepository {
        return LessonsRepositoryImpl(model)
    }


}