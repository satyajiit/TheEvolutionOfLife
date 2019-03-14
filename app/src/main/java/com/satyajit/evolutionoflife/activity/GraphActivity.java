package com.satyajit.evolutionoflife.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.Utils.ServiceManager;
import com.satyajit.evolutionoflife.fragments.InfoDialog;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import de.blox.graphview.BaseGraphAdapter;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphAdapter;
import de.blox.graphview.GraphView;
import de.blox.graphview.ViewHolder;



public abstract class GraphActivity extends AppCompatActivity {
    private int nodeCount = 1;
    protected BaseGraphAdapter<ViewHolder> adapter;
    public Graph graph;
    Typeface Cav;
    ServiceManager sm = new ServiceManager(this);  //Connection Manager

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Cav =  Typeface.createFromAsset(getAssets(), "fonts/cav.ttf"); //Init the Typeface


        setContentView(R.layout.activity_graph);


        if ( sm.isConnectionAvailable() ) { //checks the internet available and calls the dialog fragment

            graph = createGraph();


            setCustomTitlebar(); //Sets Actionbar

            new Thread(() -> runOnUiThread(() -> setupAdapter(graph))).start();  //Run on UI Thread to prevent lag upto some extent


        }




    }

    void setCustomTitlebar(){

        //Set Title bar with Custom Typeface
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);  //Get the textView for the action bar
        tv.setLayoutParams(lp);
        tv.setText("Graph View");
        tv.setTextSize(24);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTypeface(Cav);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Set the up button in action bar

    }

    @Override
    public boolean onSupportNavigateUp() {  //onClick of up
        finish();
        return true;
    }

    private void setupAdapter(Graph graph) {
        final GraphView graphView = findViewById(R.id.graph);

        adapter = new BaseGraphAdapter<ViewHolder>(graph) {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {


                String label = "";
                JSONObject obj = null;

                try {
                    obj = new JSONObject(loadJSONFromAsset());

                    label = obj.getJSONObject(String.valueOf(position + 1)).getString("name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String image = "";

                try {

                    if (obj.getJSONObject(String.valueOf(position + 1)).has("d"))
                        image = obj.getJSONObject(String.valueOf(position + 1)).getString("d");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (image != "") {

                    ((SimpleViewHolder) viewHolder).textView.setVisibility(View.GONE);
                    ((SimpleViewHolder) viewHolder).bg.setVisibility(View.VISIBLE);

                    String url = "https://webi-satyajit.firebaseapp.com/img/" + image;

                    url = url + ".jpg";

                    Picasso.get().load(url).into(((SimpleViewHolder) viewHolder).bg,new com.squareup.picasso.Callback() {

                        @Override
                        public void onSuccess() {

                            ((SimpleViewHolder) viewHolder).shim.stopShimmer();
                            ((SimpleViewHolder) viewHolder).shim.setVisibility(View.GONE);
                            ((SimpleViewHolder) viewHolder).textView.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });

                    ((SimpleViewHolder) viewHolder).textView2.setVisibility(View.VISIBLE);


                    ((SimpleViewHolder) viewHolder).textView2.setText(label);


                } else {

                    ((SimpleViewHolder) viewHolder).shim.stopShimmer();
                    ((SimpleViewHolder) viewHolder).shim.setVisibility(View.GONE);
                    ((SimpleViewHolder) viewHolder).textView.setText(label);
                    ((SimpleViewHolder) viewHolder).textView.setVisibility(View.VISIBLE);

                }

                final String label2 = label;
                final String key = String.valueOf(position + 1);
                ((SimpleViewHolder) viewHolder).crd.setOnClickListener(v -> {

                    if ( sm.isConnectionAvailable() )  //check the internet available and calls the dialog fragment
                    sendData(label2,key);
                });



            }

            class SimpleViewHolder extends ViewHolder {
                TextView textView, textView2;
                ImageView bg;
                CardView crd;
                ShimmerFrameLayout shim;
                LinearLayout lin;


                SimpleViewHolder(View itemView) {
                    super(itemView);
                    textView = itemView.findViewById(R.id.text);
                    textView2 = itemView.findViewById(R.id.text2);
                    bg = itemView.findViewById(R.id.img);
                    crd = itemView.findViewById(R.id.card_view);
                    shim = itemView.findViewById(R.id.shimmer);
                    lin = itemView.findViewById(R.id.lin66);
                }
            }
        };

        setEvolution(adapter);

        graphView.setAdapter(adapter);
    }


    public String loadJSONFromAsset() {

        //Load from the Assets the file.json

        String json;
        try {
            InputStream is = this.getAssets().open("file.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public abstract Graph createGraph();

    public abstract void setEvolution(GraphAdapter adapter);

    protected String getNodeText() {
        return "Node " + nodeCount++;
    }

    void sendData(String data,String key){

        //Sends the data / String data to Info Dialog to show the WIKI
        Bundle bundle = new Bundle();
        bundle.putString("topic", data );
        bundle.putString("id", key );
        InfoDialog fragInfo = new InfoDialog();
        fragInfo.setArguments(bundle);
        fragInfo.show(getSupportFragmentManager(),"INFO");



    }



}