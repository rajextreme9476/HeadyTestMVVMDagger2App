package co.io.headytest.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import co.io.headytest.viewmodel.ProductDetailsViewModel;
import co.io.headytest.viewmodel.RankingViewModel;
import co.io.headytest.viewmodel.ListViewModel;
import co.io.headytest.viewmodel.DetailsViewModel;
import co.io.headytest.di.util.ViewModelKey;
import co.io.headytest.util.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RankingViewModel.class)
    abstract ViewModel bindRankingViewModel(RankingViewModel rankingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel.class)
    abstract ViewModel bindProductDetailsViewModel(ProductDetailsViewModel productDetailsViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
