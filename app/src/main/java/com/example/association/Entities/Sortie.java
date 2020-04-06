package com.example.association.Entities;

public class Sortie {
    private int idAssociation;
    private String nom;
    private double prix;
    private String photo;
    private String description;
    private String date;
    private boolean statut;

    public Sortie(int idAssociation, String nom, double prix, String photo, String description, String date, boolean statut) {
        this.idAssociation = idAssociation;
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
        this.description = description;
        this.date = date;
        this.statut = statut;
    }

    public Sortie() {
    }

    public int getIdAssociation() {
        return idAssociation;
    }

    public void setIdAssociation(int idAssociation) {
        this.idAssociation = idAssociation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
}
