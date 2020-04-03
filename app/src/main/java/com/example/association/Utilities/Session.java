package com.example.association.Utilities;

import com.example.association.Entities.Adherent;

public class Session {
    private static String Id;
    private static Adherent adherent;

    public static String getId() {
        return Id;
    }

    public static void setId(String id) {
        Id = id;
    }

    public static Adherent getAdherent() {
        return adherent;
    }

    public static void setAdherent(Adherent adherent) {
        Session.adherent = adherent;
    }
}
