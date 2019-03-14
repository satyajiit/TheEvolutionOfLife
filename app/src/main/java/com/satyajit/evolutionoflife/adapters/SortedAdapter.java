package com.satyajit.evolutionoflife.adapters;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.satyajit.evolutionoflife.GetterSetter.Items;
import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.fragments.InfoDialog;
import com.squareup.picasso.Picasso;
import com.turingtechnologies.materialscrollbar.INameableAdapter;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

public class SortedAdapter extends RecyclerView.Adapter<SortedAdapter.ViewHolder> implements INameableAdapter {

    SortedList<Items> list;

    Items itm;

    public SortedAdapter() {
        list = new SortedList<>(Items.class, new SortedList.Callback<Items>() {
            @Override
            public int compare(Items o1, Items o2) {
                return o1.getName().compareTo(o2.getName());
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Items oldItem, Items newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areItemsTheSame(Items item1, Items item2) {
                return item1.getName().equals(item2.getName());
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }


    @Override
    public Character getCharacterForElement(int element) {

        Items item = list.get(element+8);
        Character c = item.getName().charAt(0);
        if(Character.isDigit(c)) {
            c = '#';
        }
        return c;
    }



    //conversation helpers
    public void addAll(List<Items> items) {
        list.beginBatchedUpdates();
        for (int i = 0; i < items.size(); i++) {
            list.add(items.get(i));
        }
        list.endBatchedUpdates();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Items item = list.get(position);
        holder.textView.setText(item.getName());

        itm = item;

        if (!item.getId().equals("NULL")) {

            String url = "https://webi-satyajit.firebaseapp.com/img/" + item.getId() + ".jpg";

            Picasso.get().load(url).into(holder.img,new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {

                    holder.shim.stopShimmer();
                    holder.img.setVisibility(View.VISIBLE);
                    holder.shim.setVisibility(View.GONE);
                    holder.textView.setVisibility(View.VISIBLE);

                }

                @Override
                public void onError(Exception e) {

                    holder.shim.stopShimmer();
                    holder.img.setVisibility(View.VISIBLE);
                    holder.shim.setVisibility(View.GONE);
                    holder.textView.setVisibility(View.VISIBLE);
                }

            });

        }

        else {

            holder.shim.stopShimmer();
            holder.img.setVisibility(View.GONE);
            holder.shim.setVisibility(View.GONE);
            holder.textView.setVisibility(View.VISIBLE);

        }

        if (!item.getTime().equals("NULL")) holder.time.setText(item.getTime()+" MA");
        else  holder.time.setVisibility(View.GONE);


        holder.crd.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putString("topic", item.getName() );
            bundle.putString("id", item.getKey());
            InfoDialog fragInfo = new InfoDialog();
            fragInfo.setArguments(bundle);
            fragInfo.show(((AppCompatActivity) holder.c).getSupportFragmentManager(),"INFO");


        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView img;
        public TextView time;
        ShimmerFrameLayout shim;
        CardView crd;
        Context c;


        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_item);
            img = itemView.findViewById(R.id.preview);
            shim = itemView.findViewById(R.id.shimmer);
            crd = itemView.findViewById(R.id.card_view);
            time = itemView.findViewById(R.id.time);
            c = itemView.getContext();
        }

    }

}