package fitness.test.kit.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fitness.test.kit.data.LessonsResult
import fitness.test.kit.domain.GetLessonsUseCase
import javax.inject.Inject

class LessonsViewModel @Inject constructor(val useCase: GetLessonsUseCase): ViewModel() {

    private val _lessons = MutableLiveData<LessonsResult>()
    val lessons: LiveData<LessonsResult> = _lessons

    suspend fun getLessons() {
        useCase().collect {
            _lessons.postValue(it)
        }
    }


}