package co.io.headytest.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import co.io.headytest.data.model.ProductResponse;
import co.io.headytest.data.rest.RepoService;

public class ListViewModel extends ViewModel {

    private final RepoService repoService;
    private CompositeDisposable disposable;

    private final MutableLiveData<ProductResponse> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private final MutableLiveData<ProductResponse.Product> getProduct = new MutableLiveData<>();

    @Inject
    public ListViewModel(RepoService repoService) {
        this.repoService = repoService;
        disposable = new CompositeDisposable();
        fetchProducts();
    }

    public LiveData<ProductResponse> getRepos() {
        return repos;
    }
    public LiveData<Boolean> getError() {
        return repoLoadError;
    }
    public LiveData<Boolean> getLoading() {
        return loading;
    }


    public LiveData<ProductResponse.Product> getSelectedRepo(int pos) {
        getProduct.setValue(repos.getValue().getCategories().get(0).getProducts().get(pos));
        return getProduct;
    }

    private void fetchProducts() {
        loading.setValue(true);
        disposable.add(repoService.getProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ProductResponse>() {
                    @Override
                    public void onSuccess(ProductResponse value) {
                        repoLoadError.setValue(false);
                        repos.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
