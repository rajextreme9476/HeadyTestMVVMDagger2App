package co.io.headytest.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.io.headytest.data.model.ProductResponse;
import co.io.headytest.data.rest.RepoService;

public class RankingViewModel extends ViewModel {


    private final MutableLiveData<List<ProductResponse.Ranking>> selectedRepoT = new MutableLiveData<>();

    public LiveData<List<ProductResponse.Ranking>> getSelectedRepo() {
        return selectedRepoT;
    }

    @Inject
    public RankingViewModel(RepoService repoService) {
    }

    public void setSelectedRepoT(List<ProductResponse.Ranking> repo) {
        selectedRepoT.setValue(repo);
    }


}
