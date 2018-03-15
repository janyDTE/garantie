package com.beaucornu.garantie;

public class AppareilType {
    private int id_type_appareil;
    private String libelle_appareil;
    private String code;

    public AppareilType(int id_type_appareil, String libelle_appareil, String code) {
        this.id_type_appareil = id_type_appareil;
        this.libelle_appareil = libelle_appareil;
        this.code = code;
    }

    public int getId_type_appareil() {
        return id_type_appareil;
    }

    public String getLibelle_appareil() {
        return libelle_appareil;
    }

    public void setLibelle_appareil(String libelle_appareil) {
        this.libelle_appareil = libelle_appareil;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
