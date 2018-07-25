package co.io.headytest.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import co.io.headytest.view.main.MainActivity;
import co.io.headytest.view.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
