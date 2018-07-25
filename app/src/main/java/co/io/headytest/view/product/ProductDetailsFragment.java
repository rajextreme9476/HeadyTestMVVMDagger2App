package co.io.headytest.view.product;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import co.io.headytest.R;
import co.io.headytest.base.BaseFragment;
import co.io.headytest.data.model.ProductResponse;
import co.io.headytest.util.ViewModelFactory;
import co.io.headytest.viewmodel.ProductDetailsViewModel;

public class ProductDetailsFragment extends BaseFragment {

 /*   @BindView(R.id.tv_repo_name) TextView repoNameTextView;
    @BindView(R.id.tv_repo_description) TextView repoDescriptionTextView;
    @BindView(R.id.tv_forks) TextView forksTextView;
    @BindView(R.id.tv_stars) TextView starsTextView;*/

    @BindView(R.id.tvCategory) TextView tvProductName;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject ViewModelFactory viewModelFactory;
    private ProductDetailsViewModel productDetailsViewModel;

    private ProductResponse.Product product;

    @Override
    protected int layoutRes() {
        return R.layout.view_detail_item;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        productDetailsViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(ProductDetailsViewModel.class);
        getProductDetails();
        setSize();
     //   displayRepo();
        //addSize();
    }

    private void setSize() {
    }

    private void getProductDetails() {
        product = productDetailsViewModel.getSelectedRepo().getValue();
        tvProductName.setText(""+product.getName());
        Log.d("product", "getProductDetails: "+product.getName());

        SizeAdapter adapter=new SizeAdapter(getActivity(), product.getVariants());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("ResourceType")
    private void addSize() {

        LinearLayout linearLayout =  getActivity().findViewById(R.id.info);
        //LinearLayout layout = (LinearLayout) findViewById(R.id.info);

        TextView valueTV = new TextView(getActivity());
        valueTV.setText("M");
        valueTV.setId(0);
        valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(valueTV);

        TextView valueTV1 = new TextView(getActivity());
        valueTV.setText("L");
        valueTV.setId(1);
        valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(valueTV1);

        TextView valueTV3 = new TextView(getActivity());
        valueTV.setText("XL");
        valueTV.setId(2);
        valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(valueTV3);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}
