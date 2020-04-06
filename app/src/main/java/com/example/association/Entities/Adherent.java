package com.example.association.Entities;

import androidx.annotation.Nullable;

public class Adherent {
    private String nom;
    private String prenom;
    @Nullable
    private String matricule;
    @Nullable
    private String email;
    @Nullable
    private String telephone;
    @Nullable
    private String solde;
    @Nullable
    private String statut;
    @Nullable
    private String idAssociation;


    public Adherent() {
    }

    public Adherent(String nom, String prenom, String matricule, String email, String telephone, String solde, String statut, String idassociation) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.email = email;
        this.telephone = telephone;
        this.solde = solde;
        this.statut = statut;
        this.idAssociation = idassociation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Nullable
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(@Nullable String matricule) {
        this.matricule = matricule;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(@Nullable String telephone) {
        this.telephone = telephone;
    }

    @Nullable
    public String getSolde() {
        return solde;
    }

    public void setSolde(@Nullable String solde) {
        this.solde = solde;
    }

    @Nullable
    public String getStatut() {
        return statut;
    }

    public void setStatut(@Nullable String statut) {
        this.statut = statut;
    }

    @Nullable
    public String getIdAssociation() {
        return idAssociation;
    }

    public void setIdAssociation(@Nullable String idAssociation) {
        this.idAssociation = idAssociation;
    }



}
