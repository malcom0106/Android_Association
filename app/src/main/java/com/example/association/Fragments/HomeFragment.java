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
import com.example.association.HomeActivity;
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

    EditText edtEmail;
    EditText edtPassword;
    EditText edtTelephone;

    Button btnValider;
    Button btnAnnuler;
    Button btnModifier;
    Button btnCrediter;
    ViewSwitcher viewSwitcher;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        _Adherent = Session.getAdherent();

        //Initiation des Widget
        initWidget(view);

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
                String telephone = edtTelephone.getText().toString().trim();

                //maj adherent
                _Adherent.setEmail(email);
                _Adherent.setTelephone(telephone);

                //maj de la ssesion
                Session.setAdherent(_Adherent);

                //Mise a jour des champs
                setData();

                //Enregistrement dans la BDD
                ((HomeActivity)getActivity()).updateAdherent(email,password,telephone);

                //Switch sur la premiere vue.
                viewSwitcher.showPrevious();
            }
        });

        btnCrediter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        setData();
    }

    private void setData(){
        txtNom.setText("Votre Nom : "+ _Adherent.getNom());
        txtPrenom.setText("Votre Prenom : "+ _Adherent.getPrenom());
        txtEmail.setText("Votre Telephone : "+ _Adherent.getTelephone());
        txtTelephone.setText("Votre Email : "+ _Adherent.getEmail());
        txtSolde.setText(_Adherent.getSolde()+" €");

        edtEmail.setText(_Adherent.getEmail());
        edtTelephone.setText(_Adherent.getTelephone());
    }

    private void initWidget(View view){
        txtNom = view.findViewById(R.id.txtNomAssociation);
        txtPrenom = view.findViewById(R.id.txtPrenom);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtTelephone = view.findViewById(R.id.txtTelephone);
        txtSolde = view.findViewById(R.id.txtSoldeCompte);
        swhCompte = view.findViewById(R.id.swh_EtatCompte);

        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtTelephone =view.findViewById(R.id.edtTelephone);


        viewSwitcher = view.findViewById(R.id.viewSwitcher);
        btnValider = view.findViewById(R.id.btnValiderModif);
        btnAnnuler = view.findViewById(R.id.btnAnnulerModif);
        btnModifier = view.findViewById(R.id.btnModifierCompte);
        btnCrediter = view.findViewById(R.id.btnCrediterCompte);
    }
}
