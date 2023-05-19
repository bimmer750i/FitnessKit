package fitness.test.kit.data

import fitness.test.kit.domain.LessonDomainEntity

open class LessonsResult

class PendingLessonsResult: LessonsResult()

class FailureLessonsResult: LessonsResult()

class SuccessRawLessonsResult(val entity: RawLessonsEntity): LessonsResult()

class SuccessLessonsResult(val lessons: ArrayList<LessonDomainEntity>, val trainers: ArrayList<Trainers>): LessonsResult()