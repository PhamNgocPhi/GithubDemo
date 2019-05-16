package com.systena.githupdemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.systena.githupdemo.R;
import com.systena.githupdemo.data.model.Repo;
import com.systena.githupdemo.databinding.ItemRepoBinding;
import com.systena.githupdemo.util.common.CommonUtils;

public class RepoAdapter extends PagedListAdapter<Repo, RepoAdapter.RepoHolder> {

    private OnItemClick onItemClick;

    public RepoAdapter() {
        super(Repo.DIFF_CALLBACK);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public RepoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRepoBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_repo, parent, false);
        return new RepoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoHolder holder, int position) {
        Repo repo = getItem(position);
        holder.binding.tvName.setText(repo.getFullName() == null ? "" : repo.getFullName());
        holder.binding.tvDescription.setText(repo.getDescription() == null ? "" : repo.getDescription());
        holder.binding.ivLanguage.setImageResource(CommonUtils.getIconByLanguage(repo.getLanguage()));
        holder.binding.tvLanguage.setText(repo.getLanguage() == null ? "" : repo.getLanguage());
        holder.binding.tvStar.setText(repo.getStargazersCount() + "");
        holder.binding.tvUpdate.setText("updated at " + repo.getUpdatedAt());
        holder.binding.cvRepo.setOnClickListener(v -> onItemClick.onClickListener());
    }

    class RepoHolder extends RecyclerView.ViewHolder {

        private final ItemRepoBinding binding;

        public RepoHolder(ItemRepoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClick {
        void onClickListener();
    }
}
