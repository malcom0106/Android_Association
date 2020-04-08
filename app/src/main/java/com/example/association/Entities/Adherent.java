package com.example.association.Entities;

import androidx.annotation.Nullable;

public class Adherent {
    private int idAdherent;
    private String nom;
    private String prenom;
    @Nullable
    private String matricule;
    @Nullable
    private String email;
    @Nullable
    private String telephone;
    @Nullable
    private double solde;
    @Nullable
    private boolean statut;
    @Nullable
    private int idAssociation;


    public Adherent() {
    }

    public Adherent(int idAdherent, String nom, String prenom, @Nullable String matricule, @Nullable String email, @Nullable String telephone, double solde, boolean statut, int idAssociation) {
        this.idAdherent = idAdherent;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.email = email;
        this.telephone = telephone;
        this.solde = solde;
        this.statut = statut;
        this.idAssociation = idAssociation;
    }

    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
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

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public int getIdAssociation() {
        return idAssociation;
    }

    public void setIdAssociation(int idAssociation) {
        this.idAssociation = idAssociation;
    }
}
