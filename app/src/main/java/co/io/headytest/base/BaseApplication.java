package co.io.headytest.base;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import co.io.headytest.di.component.ApplicationComponent;
import co.io.headytest.di.component.DaggerApplicationComponent;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
