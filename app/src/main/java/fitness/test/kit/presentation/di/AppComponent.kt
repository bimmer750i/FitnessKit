package fitness.test.kit.presentation.di

import dagger.Component
import fitness.test.kit.presentation.fragments.LessonsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    fun inject(fragment: LessonsFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

    }
}