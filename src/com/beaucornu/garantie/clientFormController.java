package com.beaucornu.garantie;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class clientFormController implements Initializable {

    private Boolean fromSearch;

    private Client client;

    @FXML
    private Label sceneTitle;

    @FXML
    private Button backHome;

    @FXML
    private TextField nom_text;

    @FXML
    private TextField prenom_text;

    @FXML
    private TextField telephone_text;

    @FXML
    private TextField adresse_text;

    @FXML
    private TextField code_postal_text;

    @FXML
    private TextField ville_text;

    @FXML
    private Button backBtn;

    @FXML
    private Button validateBtn;

    @FXML
    private Button modifyBtn;

    @FXML
    private Label okText;

    @FXML
    private void getBackHome(ActionEvent event){
        try {
            Node source = (Node) event.getSource();
            Stage thisStage  = (Stage) source.getScene().getWindow();
            //if you wanna handle an event OnCloseRequest(), add the following line for it not to be bypassed
            //stage.getOnCloseRequest().handle(null);
            thisStage.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event){
        try {
            if (fromSearch==null) {
                AppareilForm appareilForm = new AppareilForm();
                Stage stage = new Stage();
                appareilForm.start(stage);
            }

            Node source = (Node) event.getSource();
            Stage thisStage  = (Stage) source.getScene().getWindow();
            //if you wanna handle an event OnCloseRequest(), add the following line for it not to be bypassed
            //stage.getOnCloseRequest().handle(null);
            thisStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @FXML
    private void modifyButtonAction(ActionEvent event){
        initData(client,true, true);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        //Get nom

        String nom = nom_text.getText();

        //Get prenom

        String prenom = prenom_text.getText();

        //Get telephone

        String telephone = telephone_text.getText();

        //Get adresse

        String adresse = adresse_text.getText();

        //Get code_postal

        String code_postal = code_postal_text.getText();

        //Get ville

        String ville = ville_text.getText();

        if (fromSearch==true){
            //Update the client in the database
            String stmt = "UPDATE clients SET nom_client='"+nom+"', prenom_client='"+prenom+"', telephone='"+telephone+
                    "',adresse='"+adresse+"',code_postal='"+code_postal+"', ville='"+ville+"' " +
                    "WHERE id_client=" + client.getId_client();

            if (!nom.equals("")){
//            DBUtil.dbExecuteUpdate(stmt);
                okText.setVisible(true);
                okText.setText("Ce client a été modifié.");
                initData(client,false,true);
            } else {
                okText.setVisible(true);
                okText.setText("Remplir les champs manquants.");
            }
        } else {
            //Insert in the database
            String stmt = "INSERT INTO clients (nom_client, prenom_client, telephone, adresse, code_postal, ville) " +
                    "VALUES ("+nom + ", " + prenom + ", " + telephone + ", " + adresse + ", " + code_postal + ", " + ville + ")" ;
            if (!nom.equals("")){
//            DBUtil.dbExecuteUpdate(stmt);
                okText.setVisible(true);
                okText.setText("Ce client a été ajouté.");
                nom_text.clear();
                prenom_text.clear();
                telephone_text.clear();
                adresse_text.clear();
                code_postal_text.clear();
                ville_text.clear();
            } else {
                okText.setVisible(true);
                okText.setText("Remplir les champs manquants.");
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb){    }

    void initData(Client client, Boolean modif, Boolean fromSearch) {

        this.fromSearch=fromSearch;

        sceneTitle.setText("Fiche client");

        this.client=client;
        nom_text.setText(client.getNom_client());
        prenom_text.setText(client.getPrenom_client());
        telephone_text.setText(client.getTelephone());
        adresse_text.setText(client.getAdresse());
        code_postal_text.setText(client.getCode_postal());
        ville_text.setText(client.getVille());

        if (modif==false){
            nom_text.setDisable(true);
            prenom_text.setDisable(true);
            telephone_text.setDisable(true);
            adresse_text.setDisable(true);
            code_postal_text.setDisable(true);
            ville_text.setDisable(true);

            validateBtn.setDisable(true);
            modifyBtn.setDisable(false);

        } else if (modif==true){
            nom_text.setDisable(false);
            prenom_text.setDisable(false);
            telephone_text.setDisable(false);
            adresse_text.setDisable(false);
            code_postal_text.setDisable(false);
            ville_text.setDisable(false);

            validateBtn.setDisable(false);
            modifyBtn.setDisable(true);
            okText.setVisible(false);
        }

    }



}
