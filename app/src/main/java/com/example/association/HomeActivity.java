package com.example.association;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.association.Entities.Adherent;
import com.example.association.Entities.Association;
import com.example.association.Entities.Associations;
import com.example.association.Utilities.Session;

public class HomeActivity extends AppCompatActivity {
    Context context;
    Adherent adherent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;


        adherent = Session.getAdherent();
        if(adherent == null){
            this.finish();
        }
        try{
            //On recupere notre recyclerView
            RecyclerView recyclerView = findViewById(R.id.rcvMonRecycler);

            //On recupere les association
            Associations associations = new Associations();

            AssociationAdapter associationAdapter = new AssociationAdapter(associations);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(associationAdapter);
        }
        catch (Exception ex){
            String message = ex.getMessage();
        }
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
}
