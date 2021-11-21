package com.example.fruitapp.fruits_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fruitapp.R;
import com.example.fruitapp.model.FruitModel;

import java.util.List;

/**
 * Created by Noy davidyan on 02/11/2021.
 */

public class FruitListAdapter  extends RecyclerView.Adapter<FruitListAdapter.MyViewHolder> {

    private Context context;
    private List<FruitModel> fruitList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onFruitClicked(FruitModel fruitModel);
    }

    public FruitListAdapter(Context context, List<FruitModel> fruitList, ItemClickListener clickListener) {
        this.context = context;
        this.fruitList = fruitList;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fruit_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nameTv.setText(this.fruitList.get(position).getNameStr());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onFruitClicked(fruitList.get(position));
            }
        });

        Glide.with(context)
                .load(this.fruitList.get(position).getImageUrlStr())
                .error(R.drawable.ic_no_image)
                .fitCenter()
                .into(holder.imageIv);
    }

    @Override
    public int getItemCount() {
        if(this.fruitList != null) {
            return this.fruitList.size();
        }
        return 0;
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageIv;
        TextView nameTv;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageIv = itemView.findViewById(R.id.fruit_image_iv);
            nameTv = itemView.findViewById(R.id.fruit_name_tv);
        }
    }

    public void setFruitList(List<FruitModel> fruitsList) {
        this.fruitList = fruitsList;
        notifyDataSetChanged();
    }
}
