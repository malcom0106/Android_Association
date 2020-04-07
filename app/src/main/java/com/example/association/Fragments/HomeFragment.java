package com.example.association.Fragments;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.association.Entities.Adherent;
import com.example.association.R;

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

    EditText edtEmail;
    EditText edtPassword;
    EditText edtTelephone;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        txtNom = view.findViewById(R.id.txtNomAssociation);
        txtPrenom = view.findViewById(R.id.txtPrenom);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtTelephone = view.findViewById(R.id.txtTelephone);
        txtSolde = view.findViewById(R.id.txtSoldeCompte);
        swhCompte = view.findViewById(R.id.swh_EtatCompte);

        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtTelephone =view.findViewById(R.id.edtTelephone);


        final ViewSwitcher viewSwitcher = view.findViewById(R.id.viewSwitcher);
        Button btnValider = view.findViewById(R.id.btnValiderModif);
        Button btnAnnuler = view.findViewById(R.id.btnAnnulerModif);
        Button btnModifier = view.findViewById(R.id.btnModifierCompte);

        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcher.showNext();
            }
        });
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcher.showPrevious();
            }
        });
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String email2 = edtEmail.getText().toString().trim();

                viewSwitcher.showPrevious();
            }
        });


        return view;
    }

    public void SetAdherent(Adherent adherent){
        this._Adherent = adherent;
    }

    @Override
    public void onResume(){
        super.onResume();

        txtNom.setText("Votre Nom : "+ _Adherent.getNom());
        txtPrenom.setText("Votre Prenom : "+ _Adherent.getPrenom());
        txtEmail.setText("Votre Telephone : "+ _Adherent.getTelephone());
        txtTelephone.setText("Votre Email : "+ _Adherent.getEmail());
        txtSolde.setText(_Adherent.getSolde()+" €");

        edtEmail.setText(_Adherent.getEmail());
        edtTelephone.setText(_Adherent.getTelephone());

    }
}
