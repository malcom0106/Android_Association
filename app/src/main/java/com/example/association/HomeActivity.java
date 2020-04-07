package com.example.association;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Fragment;
import android.app.FragmentManager;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.association.Entities.Adherent;
import com.example.association.Entities.Association;
import com.example.association.Entities.Associations;
import com.example.association.Entities.ParametreOkHttp;
import com.example.association.Entities.ParametresOkHttp;
import com.example.association.Entities.Sortie;
import com.example.association.Entities.Sorties;
import com.example.association.Fragments.HomeFragment;
import com.example.association.Fragments.SortiesFragment;
import com.example.association.Utilities.CallServiceWeb;
import com.example.association.Utilities.Constantes;
import com.example.association.Utilities.Functions;
import com.example.association.Utilities.Session;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    Context context;

    FragmentManager fragmentManager;

    HomeFragment homeFragment;
    SortiesFragment sortiesFragment;
    ArrayList<Fragment> fragments;
    int index;


    Sorties sorties;

    Adherent adherent;

    TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;

        adherent = Session.getAdherent();
        //Toast.makeText(context, adherent.getNom(), Toast.LENGTH_SHORT).show();
        if(adherent == null){

            this.finish();

        }else{
            try{
                initToolbar();
                initFragments();

                //Instenciation mon premier fragment
                index = 0;
                Functions.replaceFragment(fragmentManager, homeFragment,"homeFragment");
            }
            catch (Exception ex){
                String message = ex.getMessage();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        homeFragment.SetAdherent(adherent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //On associe un menu à notre activité
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.item_Quitter:
                finish();
                break;
            case R.id.itm_Sortie:
                index = 1;
                Functions.replaceFragment(fragmentManager, fragments.get(1),"sortieFragment");
                AsyncCallWS asyncCallWS = new AsyncCallWS();
                break;
            case R.id.item_compte:
                homeFragment = (HomeFragment)fragments.get(0);
                homeFragment.SetAdherent(adherent);
                Functions.replaceFragment(fragmentManager, homeFragment,"sortieFragment");
                break;
        }
        return true;
    }

    public void updateAdherent(String email, String password, String Telephone){

    }

    private void initFragments(){

        fragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        sortiesFragment = new SortiesFragment();
        fragments.add(homeFragment);
        fragments.add(sortiesFragment);

        fragmentManager = getFragmentManager();
    }

    private void initToolbar(){
        //On va chercher notre toolbar dans layout
        Toolbar toolbar = findViewById(R.id.tlb_main);
        //On remplace l'actionBar par notre toolbar
        setSupportActionBar(toolbar);
        //Supprimer le titre par default
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Definission de titre
        toolbar.setTitle("Bjr "+ adherent.getNom());
        //Evenement Click sur la toolbar
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "toolbar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class AsyncCallWS2 extends AsyncTask<String, Integer,String> {

        private String url;
        private ParametresOkHttp parametresOkHttp;
        String _email;
        String _password;
        String _Telephone;

        public AsyncCallWS2(String url,@Nullable ParametresOkHttp parametresOkHttp) {
            this.url = url;
            this.parametresOkHttp = parametresOkHttp;
        }

        public AsyncCallWS2(String email, String password, String telephone) {
            this._email = email;
            this._password = password;
            this._Telephone = telephone;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {

            return CallServiceWeb.CallServiceWeb(this.url, this.parametresOkHttp);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.isEmpty()){
                try{
                    Gson gson = new Gson();
                    sorties = gson.fromJson(s, Sorties.class);
                    sortiesFragment.loadSorties(sorties,context);
                }
                catch(Exception ex){
                    txtError = findViewById(R.id.txtError);
                    txtError.setText(ex.getMessage());
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Integer, String>
    {
        //Variables globales
        int _idAssociation = 0;
        String _email;
        String _password;
        String _telephone;
        String _methode;

        //Constructeur
        public AsyncCallWS(int idAssociation) {
            //on passe nos 2 valeurs aux variables globales
            this._idAssociation = idAssociation;
        }

        public AsyncCallWS(String email,String password,String telephone) {
            //on passe nos 2 valeurs aux variables globales
            this._email = email;
            this._password = password;
            this._telephone= telephone;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            //on passe nos variables globales au service web
            HashMap<String,String> parameters = new HashMap<>();
            if(this._idAssociation > 0){
                _methode = "getSorties";
                parameters.put("idAssociation","" +this._idAssociation);

            }else{
                _methode = "updateAdherent";
                parameters.put("email",this._email);
                parameters.put("password",this._password);
                parameters.put("telephone",this._telephone);
            }
            return Functions.callServiceWeb(parameters,_methode);
        }

        @Override
        protected void onPostExecute(String retourServiceWeb) {
            super.onPostExecute(retourServiceWeb);

            //Retour du service web
            if(!retourServiceWeb.isEmpty())
            {
                try {
                    Gson gson = new Gson();

                    if(_methode == "getSorties"){
                        //Matching du flux json qui vient du service web en objet Sorties
                        Sorties sorties = gson.fromJson(retourServiceWeb,Sorties.class);
                        sortiesFragment.loadSorties(sorties,context);
                    } else if(_methode == "updateAdherent"){

                    }
                }
                catch (Exception ex)
                {
                    String erreur = ex.getMessage();
                    Log.e("erreurFragment",erreur);
                }
            }
        }
    }
}
