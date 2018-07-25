package co.io.headytest.view.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import co.io.headytest.di.module.NetworkModule;
import co.io.headytest.view.detail.DetailsFragment;
import co.io.headytest.view.product.ProductDetailsFragment;
import co.io.headytest.view.category.CategoryFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector(modules = {NetworkModule.class})
    abstract CategoryFragment provideListFragment();

    @ContributesAndroidInjector(modules = {NetworkModule.class})
    abstract DetailsFragment provideDetailsFragment();

    @ContributesAndroidInjector(modules = {NetworkModule.class})
    abstract ProductDetailsFragment provideProductDetailsFragment();
}
