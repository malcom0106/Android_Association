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

        Sorties sorties;

        public RecyclerViewAdapter(Sorties sorties) {
            this.sorties = sorties;
        }
        @NonNull
        @Override
        public SortieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sortie, parent, false);
            return new SortieHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull SortieHolder sortieHolder, int position) {
            Sortie sortie = this.sorties.get(position);
            sortieHolder.setSortie(sortie);
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

            txtNom = itemView.findViewById(R.id.txtNom);
            txtPrix = itemView.findViewById(R.id.txtPrix);
            txtDate = itemView.findViewById(R.id.txtDate);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
        }

        public void setSortie(Sortie sortie) {
            txtNom.setText(sortie.getNom());
            txtPrix.setText("" + sortie.getPrix() + " €");
            txtDate.setText("");
            //imgPhoto.setImageDrawable();
        }
    }

    public void loadSorties(Sorties sorties, Context context) {
        _context = context;
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(sorties);

        // la manière dont les adherents doivent s'afficher
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false);

        // version GridView
        //RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(context, 3);

        // Effet sur le RecyclerView
        //rcvAdherents.setItemAnimator(new DefaultItemAnimator());

        rcwSorties.setLayoutManager(layoutManager);
        rcwSorties.setAdapter(recyclerViewAdapter);
    }
}