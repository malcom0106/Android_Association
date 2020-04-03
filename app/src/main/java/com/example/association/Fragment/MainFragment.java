package com.example.association.Fragment;

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
public class MainFragment extends Fragment {
    public TextView textView;
    private Adherent _Adherent;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textView = view.findViewById(R.id.txt_mainfrag);
        textView.setText(Session.getAdherent().getNom()+ "" + Session.getAdherent().getNom());
        return view;
    }
}
