package com.beaucornu.garantie;

import java.time.LocalDate;

public class Appareil {
    private int id_appareil;
    private int id_modele ;
    private int id_client;
    private String no_serie;
    private String no_produit;
    private LocalDate date_facturation;

    public Appareil(int id_appareil, int id_modele, int id_client, String no_serie, String no_produit, LocalDate date_facturation) {
        this.id_appareil = id_appareil;
        this.id_modele = id_modele;
        this.id_client = id_client;
        this.no_serie = no_serie;
        this.no_produit = no_produit;
        this.date_facturation = date_facturation;
    }

    public Appareil() {
    }

    public int getId_appareil() {
        return id_appareil;
    }

    public void setId_appareil(int id_appareil) {
        this.id_appareil = id_appareil;
    }

    public int getId_modele() {
        return id_modele;
    }

    public void setId_modele(int id_modele) {
        this.id_modele = id_modele;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNo_serie() {
        return no_serie;
    }

    public void setNo_serie(String no_serie) {
        this.no_serie = no_serie;
    }

    public String getNo_produit() {
        return no_produit;
    }

    public void setNo_produit(String no_produit) {
        this.no_produit = no_produit;
    }

    public LocalDate getDate_facturation() {
        return date_facturation;
    }

    public void setDate_facturation(LocalDate date_facturation) {
        this.date_facturation = date_facturation;
    }
}
