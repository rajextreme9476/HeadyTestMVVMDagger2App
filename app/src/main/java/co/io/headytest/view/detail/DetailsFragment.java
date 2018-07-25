package co.io.headytest.view.detail;

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
import co.io.headytest.util.ViewModelFactory;
import co.io.headytest.view.product.ProductDetailsFragment;
import co.io.headytest.viewmodel.DetailsViewModel;
import co.io.headytest.viewmodel.ProductDetailsViewModel;

public class DetailsFragment extends BaseFragment implements RepoSelectedListener {

   /* @BindView(R.id.tv_repo_name) TextView repoNameTextView;
    @BindView(R.id.tv_repo_description) TextView repoDescriptionTextView;
    @BindView(R.id.tv_forks) TextView forksTextView;
    @BindView(R.id.tv_stars) TextView starsTextView;
*/
    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.title) TextView title;


    @Inject ViewModelFactory viewModelFactory;
    private DetailsViewModel detailsViewModel;

    ProductResponse.Category category;


    @Override
    protected int layoutRes() {
        return R.layout.screen_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(DetailsViewModel.class);
        displayRepo();
        listView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        listView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(4), true));
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setNestedScrollingEnabled(false);

        listView.setAdapter(new ProductListAdapter(getActivity(),detailsViewModel, this,this));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void displayRepo() {
        detailsViewModel.getSelectedRepo().observe(this, repo -> {
        category = repo;

            if(category.getProducts().size()==0){
                listView.setVisibility(View.GONE);
            }else {
                listView.setVisibility(View.VISIBLE);
            }


            title.setText(category.getName());
//            Log.d("***", "displayRepo: "+category.getProducts().get(0).getName());

        });
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public void onRepoSelected(ProductResponse.Category repo) {


    }

    @Override
    public void onProductSelected(ProductResponse.Product product) {
        ProductDetailsViewModel detailsViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(ProductDetailsViewModel.class);
        detailsViewModel.setSelectedRepoT(product);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.screenContainer, new ProductDetailsFragment()).commit();
    }
}

