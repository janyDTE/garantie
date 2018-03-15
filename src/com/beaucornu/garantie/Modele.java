package com.beaucornu.garantie;

public class Modele {
    private int id_modele;
    private int id_type_appareil;
    private String reference;

    public Modele(int id_modele, int id_type_appareil, String reference) {
        this.id_modele = id_modele;
        this.id_type_appareil = id_type_appareil;
        this.reference = reference;
    }

    public int getId_modele() {
        return id_modele;
    }

    public int getId_type_appareil() {
        return id_type_appareil;
    }

    public void setId_type_appareil(int id_type_appareil) {
        this.id_type_appareil = id_type_appareil;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
