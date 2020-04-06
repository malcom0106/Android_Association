package com.example.association.Fragments;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.association.Entities.Adherent;
import com.example.association.R;
import com.example.association.Utilities.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private Adherent _Adherent;
    TextView txtNom;
    TextView txtPrenom;
    TextView txtEmail;
    TextView txtTelephone;
    TextView txtSolde;
    Switch swhCompte;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        txtNom = view.findViewById(R.id.txtNom);
        txtPrenom = view.findViewById(R.id.txtPrenom);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtTelephone = view.findViewById(R.id.txtTelephone);
        txtSolde = view.findViewById(R.id.txtSoldeCompte);
        swhCompte = view.findViewById(R.id.swh_EtatCompte);
        return view;
    }

    public void SetAdherent(Adherent adherent){
        txtNom.setText("Votre Nom : "+ adherent.getNom());
        txtPrenom.setText("Votre Prenom : "+ adherent.getPrenom());
        txtEmail.setText("Votre Telephone : "+ adherent.getTelephone());
        txtTelephone.setText("Votre Email : "+ adherent.getEmail());
        txtSolde.setText(adherent.getSolde()+" €");
    }
}
