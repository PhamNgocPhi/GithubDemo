package com.systena.githupdemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.systena.githupdemo.R;
import com.systena.githupdemo.databinding.ItemRecipeBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeHolder> {


    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRecipeBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_recipe, parent, false);
        return new RecipeHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RecipeHolder extends RecyclerView.ViewHolder {

        private final ItemRecipeBinding binding;

        public RecipeHolder(ItemRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
