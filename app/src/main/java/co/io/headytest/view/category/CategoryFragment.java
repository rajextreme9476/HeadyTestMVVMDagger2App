package co.io.headytest.view.category;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import co.io.headytest.R;
import co.io.headytest.base.BaseFragment;
import co.io.headytest.data.model.ProductResponse;
import co.io.headytest.util.GridSpacingItemDecoration;
import co.io.headytest.util.RepoSelectedListener;
import co.io.headytest.viewmodel.DetailsViewModel;
import co.io.headytest.viewmodel.ListViewModel;
import co.io.headytest.viewmodel.RankingViewModel;
import co.io.headytest.util.ViewModelFactory;
import co.io.headytest.view.detail.DetailsFragment;

public class CategoryFragment extends BaseFragment implements RepoSelectedListener {

    @BindView(R.id.recyclerView) RecyclerView listView;
    @BindView(R.id.tv_error) TextView errorTextView;
    @BindView(R.id.loading_view) View loadingView;
    @BindView(R.id.ivSort) View ivSort;

    @Inject ViewModelFactory viewModelFactory;
    private ListViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.categories_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);

        listView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        listView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(4), true));
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setNestedScrollingEnabled(false);

        listView.setAdapter(new CategoryListAdapter(viewModel, this, this));
        observableViewModel();
        setRankings();
    }

    private void setRankings() {

        ivSort.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          RankingViewModel detailsViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(RankingViewModel.class);
                                          detailsViewModel.setSelectedRepoT(viewModel.getRepos().getValue().getRankings());

                                          RankingsBottomSheetFragment bottomSheetFragment = new RankingsBottomSheetFragment();
                                          bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());

                                      }
        }
        );

    }


    private void observableViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if(repos != null) {
                listView.setVisibility(View.VISIBLE);
            }
        });
        viewModel.getError().observe(this, isError -> {
            if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });
        viewModel.getLoading().observe(this, isLoading -> {
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if(isLoading) {
                errorTextView.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
            }
        });
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onRepoSelected(ProductResponse.Category repo) {
        DetailsViewModel detailsViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(DetailsViewModel.class);
        detailsViewModel.setSelectedRepoT(repo);

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.screenContainer, new DetailsFragment()).commit();
    }

    @Override
    public void onProductSelected(ProductResponse.Product product) {

    }
}
