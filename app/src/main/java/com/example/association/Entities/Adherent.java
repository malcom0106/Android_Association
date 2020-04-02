package com.example.association.Entities;

public class Adherent {
    private String Nom;
    private String Prenom;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public Adherent() {
    }

    public Adherent(String nom, String prenom) {
        Nom = nom;
        Prenom = prenom;
    }
}
