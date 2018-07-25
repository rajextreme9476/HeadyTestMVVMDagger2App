package co.io.headytest.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import co.io.headytest.data.model.ProductResponse;
import co.io.headytest.data.rest.RepoService;

public class ProductDetailsViewModel extends ViewModel {

    private final MutableLiveData<ProductResponse.Product> selectedRepoT = new MutableLiveData<>();

    public LiveData<ProductResponse.Product> getSelectedRepo() {
        return selectedRepoT;
    }

    @Inject
    public ProductDetailsViewModel(RepoService repoService) {
    }

    public void setSelectedRepoT(ProductResponse.Product repo) {
        selectedRepoT.setValue(repo);
    }


   }
