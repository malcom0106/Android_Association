package com.example.association.Fragments;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView txtTelephne;

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
        txtTelephne = view.findViewById(R.id.txtTelephone);

        return view;
    }

    public void SetAdherent(Adherent adherent){
        txtNom.setText("Votre Nom : "+ adherent.getNom());
        txtNom.setText("Votre Prenom : "+ adherent.getPrenom());
        txtNom.setText("Votre Telephone : "+ adherent.getTelephone());
        txtNom.setText("Votre Email : "+ adherent.getEmail());
    }
}
