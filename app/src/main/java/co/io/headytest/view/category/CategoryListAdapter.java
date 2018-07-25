package co.io.headytest.view.category;

import android.arch.lifecycle.LifecycleOwner;
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
import co.io.headytest.viewmodel.ListViewModel;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.RepoViewHolder>{

    private RepoSelectedListener repoSelectedListener;
    private final List<ProductResponse.Category> data = new ArrayList<>();

    CategoryListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, RepoSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos.getCategories());
               notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category_list_item, parent, false);
        return new RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCategory) TextView tvCategory;

        private ProductResponse.Category repo;

        RepoViewHolder(View itemView, RepoSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(repo != null) {
                    repoSelectedListener.onRepoSelected(repo);
                }
            });
        }

        void bind(ProductResponse.Category repo) {
            this.repo = repo;
            tvCategory.setText(repo.getName());
        }
    }
}
