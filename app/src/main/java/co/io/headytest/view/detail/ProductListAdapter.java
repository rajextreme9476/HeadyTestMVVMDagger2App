package co.io.headytest.view.detail;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.io.headytest.R;
import co.io.headytest.data.model.ProductResponse;
import co.io.headytest.util.RepoSelectedListener;
import co.io.headytest.viewmodel.DetailsViewModel;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.RepoViewHolder>{

    private RepoSelectedListener repoSelectedListener;
    private final List<ProductResponse.Product> data = new ArrayList<>();
    private Context context;

    ProductListAdapter(Context context, DetailsViewModel viewModel, LifecycleOwner lifecycleOwner, RepoSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
        this.context = context;
        viewModel.getSelectedRepo().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos.getProducts());
               notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_products_list_item, parent, false);
        return new RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(data.get(position),context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCategory) TextView tvCategory;
        @BindView(R.id.tvPrice) TextView tvPrice;


        private ProductResponse.Product repo;

        RepoViewHolder(View itemView, RepoSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(repo != null) {
                    repoSelectedListener.onProductSelected(repo);
                }
            });
        }

        void bind(ProductResponse.Product repo,Context context) {
            this.repo = repo;
            tvCategory.setText(repo.getName());
            tvPrice.setText(context.getResources().getString(R.string.Rs) +" "+repo.getVariants().get(0).getPrice());
        }
    }
}
