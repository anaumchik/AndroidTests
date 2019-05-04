package com.example.androidtests

import com.example.androidtests.data.utils.CustomClock
import dagger.BindsInstance
import dagger.Component

@Component
interface TestComponent : ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun build(): TestComponent

        @BindsInstance
        fun customClock(customClock: CustomClock): Builder
    }
}
