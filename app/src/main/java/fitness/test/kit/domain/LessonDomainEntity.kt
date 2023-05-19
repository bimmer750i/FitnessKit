package fitness.test.kit.domain

import fitness.test.kit.data.Lessons
import java.time.Duration

data class LessonDomainEntity(val isHeader: Int, val date: String,val duration: String,val lessons: Lessons)