package com.satyajit.evolutionoflife.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.Utils.ServiceManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;



public class InfoDialog extends DialogFragment {

    String topic, key="None";
    ImageView anim;
    ImageView preview;
    RequestQueue rQueue;
    TextView wiki;
    int flag=0,flag2 = 0;
    JSONObject obj;
    String image="";
    String data2="";

    ShimmerFrameLayout shim;
    ScrollView sv;

    private Toolbar toolbar;



    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_info, container, false);


        ServiceManager sm = new ServiceManager(getActivity());

        if ( sm.isConnectionAvailable() ) {

            InitUI(view);

            LoadPreview();

        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle(topic.toUpperCase());
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });


        GetWiki(topic);


    }

    void GetWiki(String keyword){

        if(keyword=="") keyword="qwer123";


        keyword=keyword.toLowerCase();

        if(keyword.charAt(keyword.length()-1)=='s') keyword = keyword.substring(0,keyword.length()-1)+"|"+keyword;

        StringRequest request = new StringRequest("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="+keyword,
                string -> parseJsonData(string),
                volleyError -> {
            Toast.makeText(getActivity(), "Some error occurred!!", Toast.LENGTH_SHORT).show();

            volleyError.printStackTrace();
        });

        rQueue = Volley.newRequestQueue(getActivity());
        rQueue.add(request);



    }


     void parseJsonData(String jsonString) {



     if (flag <=2)

        try {
            JSONObject object = new JSONObject(jsonString);


            String temp= "";


            String temp_key = object.getJSONObject("query").getJSONObject("pages").keys().next();

            Log.d("Original String",jsonString);
            Log.d("Key",temp_key);

            if(!object.getJSONObject("query").getJSONObject("pages").getJSONObject(temp_key).toString().contains("\"extract\":\"\"")&&!object.getJSONObject("query").getJSONObject("pages").getJSONObject(temp_key).toString().contains("\"missing\":\"\"")&&!object.getJSONObject("query").getJSONObject("pages").getJSONObject(temp_key).toString().contains("may refer to:\"}"))

            {


             temp = object.getJSONObject("query").getJSONObject("pages").getJSONObject(temp_key).getString("extract").replace("[\"", "").replace("\"]", "").replace("\\\"", "\"").replace("()", "");
                Log.d("Selection",temp);

            }

            Iterator<String> tempo = object.getJSONObject("query").getJSONObject("pages").keys();

            tempo.next();

            if(temp==""&&(object.getJSONObject("query").getJSONObject("pages").toString().contains("-1")||tempo.hasNext())){




                Iterator<String> temp_key2 = object.getJSONObject("query").getJSONObject("pages").keys();
                temp_key2.next();

                String s ="";

                if (temp_key2.hasNext())
                s = temp_key2.next();

if (object.getJSONObject("query").getJSONObject("pages").has(s))
                if(!object.getJSONObject("query").getJSONObject("pages").getJSONObject(s).toString().contains("\"extract\":\"\"")&&!object.getJSONObject("query").getJSONObject("pages").getJSONObject(s).toString().contains("\"missing\":\"\""))
                {


                    temp = object.getJSONObject("query").getJSONObject("pages").getJSONObject(s).getString("extract").replace("[\"", "").replace("\"]", "").replace("\\\"", "\"").replace("()", "");


                }

            }



            if (temp!=""&&temp!=null) {
                wiki.setText(temp);
                shim.setVisibility(View.GONE);
                sv.setVisibility(View.VISIBLE);
            }
            else if(flag == 0)
            {


                GetWiki(image);
                flag++;
            }
            else {


                GetWiki(data2);
                flag++;

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

     else {


         wiki.setText("Ops...its too hard to find!!");

         GetWiki2(topic);

     }


    }



    void InitUI(View v){

        preview = v.findViewById(R.id.preview);
        toolbar = v.findViewById(R.id.toolbar);
        topic = getArguments().getString("topic");
        key = getArguments().getString("id","EMPTY");
        anim = v.findViewById(R.id.anim);
        wiki = v.findViewById(R.id.wiki);
        sv = v.findViewById(R.id.sv);
        shim = v.findViewById(R.id.shimmer);

    }

    void LoadPreview(){




        try {
            obj = new JSONObject(loadJSONFromAsset());



            if (obj.getJSONObject(key).has("d"))
                image = obj.getJSONObject(key).getString("d");


            if (obj.getJSONObject(key).has("d2"))
                data2 = obj.getJSONObject(key).getString("d2");

        } catch (JSONException e) {
            e.printStackTrace();
        }



        if(!image.equals("")){

            String url = "https://webi-satyajit.firebaseapp.com/img/" + image;

            url = url + ".jpg";

            Picasso.get().load(url).into(preview);



        }

        else {


            if (key.equals("66")||key.equals("76")||key.equals("80")||key.equals("113")||key.equals("85")||key.equals("106"))
            {
                image = topic;

               String url = "https://webi-satyajit.firebaseapp.com/img/" + image;

                url = url + ".jpg";

                Picasso.get().load(url).into(preview,new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                            preview.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.no_image));
                    }

            });
            }
            else {
                preview.setVisibility(View.GONE);
                anim.setVisibility(View.VISIBLE);
            }


        }


    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getActivity().getAssets().open("file.json");
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



    void GetWiki2(String keyword){

        if(keyword=="") keyword="This is non sense to seTT";

        StringRequest request = new StringRequest("https://en.wikipedia.org/w/api.php?action=opensearch&search="+keyword+"&limit=1&format=json&&formatcode=2",
                string -> parseJsonData2(string),
                volleyError -> {
                    Toast.makeText(getActivity(), "Some error occurred!!", Toast.LENGTH_SHORT).show();

                    volleyError.printStackTrace();
                });

        rQueue = Volley.newRequestQueue(getActivity());
        rQueue.add(request);

    }


    void parseJsonData2(String jsonString) {

        Log.d("Hey",jsonString);

        if (flag2 <=2)

            try {
                JSONArray object = new JSONArray(jsonString);


                String temp= "";


                    temp = object.getString(2).replace("[\"","").replace("\"]","").replace("\\\"","\"").replace("()","");

                if (temp!=""&&temp!="[]"&&temp!=null&&!temp.contains("[]")&&!temp.contains("may refer to:")) {
                    wiki.setText(temp);
                    shim.setVisibility(View.GONE);
                    sv.setVisibility(View.VISIBLE);

                }
                else if(flag2 == 0)
                {

                    GetWiki2(image);
                    flag2++;
                }
                else {


                    GetWiki2(data2);
                    flag2++;

                }




            } catch (JSONException e) {
                e.printStackTrace();
            }

        else {


            wiki.setText("Sorry Nothing Found!!");
            shim.setVisibility(View.GONE);
            sv.setVisibility(View.VISIBLE);

        }


    }






}