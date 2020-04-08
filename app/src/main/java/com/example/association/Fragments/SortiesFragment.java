package com.example.association.Fragments;

import android.content.Context;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.association.Entities.Sortie;
import com.example.association.Entities.Sorties;

import com.example.association.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SortiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortiesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Context _context;
    RecyclerView rcwSorties;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SortiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SortiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SortiesFragment newInstance(String param1, String param2) {
        SortiesFragment fragment = new SortiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sorties, container, false);

        rcwSorties = view.findViewById(R.id.rcvRecycler);

        return view;
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<SortieHolder> {
        //Liste d'adhérents
        Sorties sorties;

        public RecyclerViewAdapter(Sorties sorties) {
            //Initialisation de la liste d'adhérents via le constructeur
            this.sorties = sorties;
        }

        @NonNull
        @Override
        public SortieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Récupération du fichier xml "graphique" dans les layouts
            View view = LayoutInflater.from(_context).inflate(R.layout.item_sortie,parent,false);
            //Passage de notre objet(view) à notre viewholder
            return new SortieHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SortieHolder sortieholder, int position) {
            //Récupération d'un adhérent parmi la liste des adhérents en fonction de sa position
            Sortie sortie = this.sorties.get(position);
            //Passage de l'objet adhérent à notre viewholder
            sortieholder.setSortie(sortie);
        }

        @Override
        public int getItemCount() {
            return sorties.size();
        }
    }

    public class SortieHolder extends RecyclerView.ViewHolder {

        public final TextView txtNom;
        public final TextView txtPrix;
        public final TextView txtDate;
        public final ImageView imgPhoto;

        public SortieHolder(@NonNull View itemView) {
            super(itemView);

            txtNom = itemView.findViewById(R.id.txtNomAssociation);
            txtPrix = itemView.findViewById(R.id.txtPrix);
            txtDate = itemView.findViewById(R.id.txtDate);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
        }

        public void setSortie(Sortie sortie) {
            //Matching des données avec les widgets
            txtNom.setText(sortie.getNom());
            txtPrix.setText("" + sortie.getPrix() + "€");
            txtDate.setText("");
            //imgPhoto.setImageDrawable();
        }
    }

    public void loadSorties(Sorties sorties, Context context){
        _context = context;
        //Instanciation de l'adapter
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(sorties);

        //Manière d'afficher les adhérents (verticalement, horizontalement, gridview)
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false);

        //Version Gridview
        //RecyclerView.LayoutManager layoutManagerGrid = new GridLayoutManager(context, 3);

        rcwSorties.setLayoutManager(layoutManager);

        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        rcwSorties.setItemAnimator(defaultItemAnimator);

        //Passage de notre adapter à notre recyclerview
        rcwSorties.setAdapter(recyclerViewAdapter);
    }
}