package co.io.headytest.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import co.io.headytest.data.model.ProductResponse;
import co.io.headytest.data.rest.RepoService;

public class DetailsViewModel extends ViewModel {

    private CompositeDisposable disposable;

    private final MutableLiveData<ProductResponse.Category> selectedRepoT = new MutableLiveData<>();

    public LiveData<ProductResponse.Category> getSelectedRepo() {
        return selectedRepoT;
    }

    @Inject
    public DetailsViewModel(RepoService repoService) {
        disposable = new CompositeDisposable();
    }

   public void setSelectedRepoT(ProductResponse.Category repo) {
        selectedRepoT.setValue(repo);
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
