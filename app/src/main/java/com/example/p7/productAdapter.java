package com.example.p7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.analytics.ecommerce.Product;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<product> productList;


    public productAdapter(Context mCtx, List<product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view= inflater.inflate(R.layout.listlayout,null);
        ProductViewHolder holder= new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        product products= productList.get(i);

        productViewHolder.name.setText(products.getName());
        productViewHolder.price.setText(String.valueOf(products.getPrice()));



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
        }
    }
}
