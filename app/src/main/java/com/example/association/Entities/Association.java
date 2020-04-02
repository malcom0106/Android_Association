package com.example.association.Entities;

public class Association {
    private String Nom;
    private String Adresse;
    private String CodePostal;
    private String Ville;
    private String Telephone;
    private String Cotisation;

    public Association() {
    }

    public Association(String nom, String adresse, String codePostal, String ville, String telephone, String cotisation) {
        Nom = nom;
        Adresse = adresse;
        CodePostal = codePostal;
        Ville = ville;
        Telephone = telephone;
        Cotisation = cotisation;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(String codePostal) {
        CodePostal = codePostal;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getCotisation() {
        return Cotisation;
    }

    public void setCotisation(String cotisation) {
        Cotisation = cotisation;
    }
}
