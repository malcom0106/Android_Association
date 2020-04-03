package com.example.association.Utilities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import com.example.association.R;

public class Functions {
    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, String NomFragment){
        //Nouvelle Transaction pour la gestion des fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //On associe le fragment mainFragment à notre frmMain
        fragmentTransaction.replace(R.id.frm_main, fragment);
        //On ajoute notre fragment à notre liste de fragments
        fragmentTransaction.addToBackStack(NomFragment);
        //On valide la transaction
        fragmentTransaction.commit();
    }
}
