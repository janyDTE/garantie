package com.beaucornu.garantie;

public class Intervention {
    private int id_intervention;
    private int libelle_intervention;
    private String date_inetrvention;
    private int id_appareil;

    public Intervention(int id_intervention, int libelle_intervention, String date_inetrvention, int id_appareil) {
        this.id_intervention = id_intervention;
        this.libelle_intervention = libelle_intervention;
        this.date_inetrvention = date_inetrvention;
        this.id_appareil = id_appareil;
    }

    public int getId_intervention() {
        return id_intervention;
    }

    public int getLibelle_intervention() {
        return libelle_intervention;
    }

    public void setLibelle_intervention(int libelle_intervention) {
        this.libelle_intervention = libelle_intervention;
    }

    public String getDate_inetrvention() {
        return date_inetrvention;
    }

    public void setDate_inetrvention(String date_inetrvention) {
        this.date_inetrvention = date_inetrvention;
    }

    public int getId_appareil() {
        return id_appareil;
    }

    public void setId_appareil(int id_appareil) {
        this.id_appareil = id_appareil;
    }
}
