package com.satyajit.evolutionoflife.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.satyajit.evolutionoflife.GetterSetter.Items;
import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.fragments.InfoDialog;
import com.satyajit.evolutionoflife.fragments.PopFragment;

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


public class PastAdapter extends RecyclerView.Adapter<PastAdapter.MyViewHolder> {

    //Extending the Recycler View to use it as the required adapter

    private List<Items> namesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,time;
        ImageView arw;
        CardView crd;
        Context context;

        //Declared all the views from single item xml


        public MyViewHolder(View view) {
            super(view);

            //Init
            name = view.findViewById(R.id.list_item);
            crd = view.findViewById(R.id.card_view);
            context = view.getContext();
            arw = view.findViewById(R.id.arrow);
            time = view.findViewById(R.id.time);

        }
    }


    public PastAdapter(List<Items> namesList) {
        this.namesList = namesList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_view_2, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {



        final Items items = namesList.get(position);

        if (items.getName().length()>20)    holder.name.setTextSize(11);  //Make size smaller to fit in the cards

        holder.name.setText(items.getName());


        holder.name.setVisibility(View.VISIBLE);

        holder.time.setText(items.getTime()+" MA");


        if (items.getId().equals("1")) {  //Hide the arrow icon..for the last item i.e LIFE
            holder.arw.setVisibility(View.GONE);
            holder.time.setVisibility(View.GONE);
        }

        holder.crd.setOnClickListener(v -> {

           if (!items.getId().equals("1")) {
               PopFragment alertDialogFragment = new PopFragment();

               FragmentManager manager = ((AppCompatActivity) holder.context).getSupportFragmentManager();
               Bundle bundle = new Bundle();
               bundle.putString("title", items.getName());
               bundle.putString("id", items.getId());
               alertDialogFragment.setArguments(bundle);
               alertDialogFragment.show(manager, "fragment_pop");
           }
            else {

                //To avoid showing dialog option for the LIFE element
                InfoDialog alertDialogFragment = new InfoDialog();
                FragmentManager manager = ((AppCompatActivity) holder.context).getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString("topic", items.getName());
                bundle.putString("id", items.getId());
                alertDialogFragment.setArguments(bundle);
                alertDialogFragment.show(manager, "fragment_info");

            }
        });


    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }
}