package com.satyajit.evolutionoflife.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.satyajit.evolutionoflife.GetterSetter.Items;
import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.fragments.InfoDialog;
import com.satyajit.evolutionoflife.fragments.PopFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Scripted by Satyajit
 * Updated 29th Jan 19
 *
 */


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    //Extending the Recycler View to use it as the required adapter

    private List<Items> namesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,time;
        ImageView img;
        ShimmerFrameLayout shim;
        CardView crd;
        Context context;

        //Declared all the views from single item xml


        public MyViewHolder(View view) {
            super(view);

            //Init
            name = view.findViewById(R.id.list_item);
            img = view.findViewById(R.id.preview);
            shim = view.findViewById(R.id.shimmer);
            crd = view.findViewById(R.id.card_view);
            context = view.getContext();
            time = view.findViewById(R.id.time);

        }
    }


    public ItemsAdapter(List<Items> namesList) {
        this.namesList = namesList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {



        final Items items = namesList.get(position);

        if (items.getName().length()>20)    holder.name.setTextSize(11);

        holder.name.setText(items.getName());

        String url = "https://webi-satyajit.firebaseapp.com/img/" + items.getName();

        url = url + ".jpg";


        Picasso.get().load(url).into(holder.img,new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

                    holder.shim.stopShimmer();
                    holder.img.setVisibility(View.VISIBLE);
                    holder.shim.setVisibility(View.GONE);
                    holder.name.setVisibility(View.VISIBLE);

            }

            @Override
            public void onError(Exception e) {

                holder.shim.stopShimmer();
                holder.img.setVisibility(View.VISIBLE);
                holder.shim.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }

        });


                holder.crd.setOnClickListener(v -> {

                        PopFragment alertDialogFragment = new PopFragment();

                        FragmentManager manager = ((AppCompatActivity) holder.context).getSupportFragmentManager();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", items.getName());
                        bundle.putString("id", items.getId());
                        alertDialogFragment.setArguments(bundle);
                        alertDialogFragment.show(manager, "fragment_pop");




                    });


        holder.time.setText(items.getTime()+" MA");

    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }
}