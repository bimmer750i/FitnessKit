package fitness.test.kit.domain

import fitness.test.kit.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class GetLessonsUseCase @Inject constructor(val repository: LessonsRepository) {

    suspend operator fun invoke() : Flow<LessonsResult> = repository.getLessons().map {
        when (it) {
            is SuccessRawLessonsResult -> {
                val lessons_list = it.entity.lessons
                val trainers_list = it.entity.trainers
                lessons_list.sortByDescending { it.date + it.startTime }
                lessons_list.reverse()
                return@map SuccessLessonsResult(lessons_list.createLessonsEntityList(),trainers_list)
            }
            else -> {return@map it}
        }

    }

    private fun ArrayList<Lessons>.createLessonsEntityList(): ArrayList<LessonDomainEntity> {
        var currentDate= ""
        val result = ArrayList<LessonDomainEntity>()
        this.forEach {
            if (it.date != currentDate) {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(it.date)
                val date_formatted = SimpleDateFormat("EEEE, dd MMMM", Locale("ru")).format(date)
                result.add(LessonDomainEntity(1, date_formatted, getDuration(it.startTime,it.endTime), it))
                currentDate = it.date.toString()
            }
            result.add(LessonDomainEntity(0,it.date.toString(), getDuration(it.startTime,it.endTime),it))
        }
        return result
    }

}