package fitness.test.kit.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fitness.test.kit.domain.GetLessonsUseCase
import javax.inject.Inject

class LessonsViewModelFactory @Inject constructor(val useCase: GetLessonsUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LessonsViewModel(useCase) as T
    }
}