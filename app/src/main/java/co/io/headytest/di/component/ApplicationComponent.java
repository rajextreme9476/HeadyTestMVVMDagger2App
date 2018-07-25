package co.io.headytest.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import co.io.headytest.base.BaseApplication;
import co.io.headytest.di.module.ActivityBindingModule;
import co.io.headytest.di.module.ViewModelModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBindingModule.class, ViewModelModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}