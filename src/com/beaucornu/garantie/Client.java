/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beaucornu.garantie;

/**
 *
 * @author Utilisateur
 */
public class Client {
    private int id_client;
    private String nom_client;
    private String prenom_client;
    private String telephone;
    private String adresse ;
    private String code_postal;
    private String ville;

    public Client() {
    }

    public Client(int id_client, String nom_client, String prenom_client, String telephone, String adresse, String code_postal, String ville) {
        this.id_client = id_client;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.telephone = telephone;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    @Override
    public String toString() {
        return nom_client + " " + prenom_client + " (" + ville + ")";
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
