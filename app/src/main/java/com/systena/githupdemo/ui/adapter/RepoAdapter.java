package com.systena.githupdemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.systena.githupdemo.R;
import com.systena.githupdemo.data.model.Repo;
import com.systena.githupdemo.databinding.ItemRepoBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {

    private List<Repo> repos;

    public RepoAdapter() {
        this.repos = new ArrayList<>();
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
        notifyDataSetChanged();
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
        Repo repo = repos.get(position);
        holder.binding.tvName.setText(repo.getFullName());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class RepoHolder extends RecyclerView.ViewHolder {

        private final ItemRepoBinding binding;

        public RepoHolder(ItemRepoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
