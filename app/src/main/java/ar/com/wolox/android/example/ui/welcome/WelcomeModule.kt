package ar.com.wolox.android.example.ui.welcome

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WelcomeModule {
    @ContributesAndroidInjector
    internal abstract fun welcomeActivity(): WelcomeActivity
}