package com.example.association.Fragments;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        RecyclerView recyclerView = view.findViewById(R.id.rcvMonRecycler);


        return view;
    }


    public class SortieHolder extends RecyclerView.ViewHolder{
        //On declare les widgets
        public final TextView txtNom;
        public final TextView txtDate;
        public final TextView txtPrix;
        public final ImageView imgPhoto;

        //Constructeur du AdherentHolder
        public SortieHolder(@NonNull View itemView) {
            super(itemView);

            //On instencie les widgets qui se trouve dans la View "ItemView"
            txtNom = itemView.findViewById(R.id.txtNom);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPrix = itemView.findViewById(R.id.txtPrix);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
        }

        //On crée un class qui permet de charger notre Adherent dans chaque Item
        public void setSortie (Sortie sortie) {
            txtNom.setText(sortie.getNom());
            txtDate.setText(sortie.getDate());
            txtPrix.setText(""+sortie.getPrix());

        }
    }

    public class SortieAdapter extends RecyclerView.Adapter<SortieHolder>{
        //On declare notre liste en globale
        Sorties _sorties;

        //On instancie dans la liste via un constructeur
        public SortieAdapter(Sorties sorties) {
            this._sorties = sorties;
        }

        @NonNull
        @Override
        public SortieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //On convertit une layoutItem en View pour la passer dans un holder
            View view = LayoutInflater.from(context).inflate(R.layout.item_sortie,parent,false);
            //On retourne un nouveau Holder avec notre View view creer a partir d'un layout Item
            return new SortieHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull SortieHolder holder, int position) {
            //Rechercher un item à la position "position"
            Sortie sortie = this._sorties.get(position);
            //On passe l'objet à notre holder
            holder.setSortie(sortie);
        }

        @Override
        public int getItemCount() {
            return _sorties.size();
        }
    }

}
