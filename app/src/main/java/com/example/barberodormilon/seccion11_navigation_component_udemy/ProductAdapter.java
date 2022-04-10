package com.example.barberodormilon.seccion11_navigation_component_udemy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final List<ProductPOJO> productPOJOS;
    private final OnClickListener onClickListener;
    private Context context;

    public ProductAdapter(List<ProductPOJO> productPOJOS, OnClickListener onClickListener) {
        this.productPOJOS = productPOJOS;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductPOJO productPOJO = productPOJOS.get(position);

        holder.setListener(productPOJO, onClickListener);
        holder.tvName.setText(productPOJO.getName());

        Glide.with(context)
                .load(productPOJO.getUrl())
                .apply(new RequestOptions().centerCrop())
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return productPOJOS.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPhoto;
        TextView tvName;
        MaterialCardView viewContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            viewContainer = (MaterialCardView) itemView;
        }

        protected void setListener(ProductPOJO productPOJO, OnClickListener listener){
            viewContainer.setOnClickListener(view -> {
                productPOJO.setSelected(!productPOJO.isSelected());
                viewContainer.setChecked(productPOJO.isSelected());
                listener.onClick(productPOJO);
            });
        }
    }
}
