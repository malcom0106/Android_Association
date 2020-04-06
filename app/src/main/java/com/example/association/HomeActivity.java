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

public class HomeActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    HomeFragment homeFragment;
    SortiesFragment sortiesFragment;
    ArrayList<Fragment> fragments;
    Sorties sorties;
    Context context;
    Adherent adherent;



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

                ParametresOkHttp parametresOkHttp = new ParametresOkHttp();
                parametresOkHttp.add(new ParametreOkHttp("idassociation" , ""+adherent.getIdAssociation()));
                AsyncCallWS asyncCallWS = new AsyncCallWS(Constantes.URL_GETSORTIES,parametresOkHttp);
                asyncCallWS.execute();

                //Instenciation mon premier fragment
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

    public class AssociationHolder extends RecyclerView.ViewHolder{
        public final TextView txtNom;
        public final TextView txtAdresse;
        public final TextView txtCP;
        public final TextView txtVille;
        public final TextView txtCotisation;
        public final TextView txtTelephone;

        public AssociationHolder(@NonNull View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtNomAssociation);
            txtAdresse = itemView.findViewById(R.id.txtAdresseAssociation);
            txtCP = itemView.findViewById(R.id.txtCPAssociation);
            txtVille = itemView.findViewById(R.id.txtVilleAssociation);
            txtCotisation = itemView.findViewById(R.id.txtCotisationAssociation);
            txtTelephone = itemView.findViewById(R.id.txtTelephoneAssociation);
        }
         public void setAssociation(Association association){
            txtNom.setText(association.getNom());
            txtAdresse.setText(association.getAdresse());
            txtCP.setText(association.getCodePostal());
            txtVille.setText(association.getVille());
            txtCotisation.setText(association.getCotisation());
            txtTelephone.setText(association.getTelephone());
         }
    }

    public class AssociationAdapter extends RecyclerView.Adapter<AssociationHolder>{

        Associations mesAssociations;

        public AssociationAdapter(Associations associations) {
            mesAssociations = associations;
        }

        @NonNull
        @Override
        public AssociationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.itemlayout, parent, false);
            return new AssociationHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AssociationHolder holder, int position) {
            Association association = this.mesAssociations.get(position);
            holder.setAssociation(association);
        }

        @Override
        public int getItemCount() {
            return mesAssociations.size();
        }
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
                Functions.replaceFragment(fragmentManager, fragments.get(1),"sortieFragment");
                break;
            case R.id.item_compte:
                Functions.replaceFragment(fragmentManager, fragments.get(0),"sortieFragment");
                break;
        }
        return true;
    }

    public void initFragments(){

        fragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        sortiesFragment = new SortiesFragment();
        fragments.add(homeFragment);
        fragments.add(sortiesFragment);

        fragmentManager = getFragmentManager();
    }

    public void initToolbar(){
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

    private class AsyncCallWS extends AsyncTask<String, Integer,String> {

        private String url;
        private ParametresOkHttp parametresOkHttp;

        public AsyncCallWS(String url,@Nullable ParametresOkHttp parametresOkHttp) {
            this.url = url;
            this.parametresOkHttp = parametresOkHttp;
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

                }
                catch(Exception ex){
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }
}
