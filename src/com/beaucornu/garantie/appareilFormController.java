package com.beaucornu.garantie;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ResourceBundle;

public class appareilFormController implements Initializable {

    @FXML
    private Button backHome;

    @FXML
    private AutoFillTextBox<Modele> modeleTextBox;

    @FXML
    private AutoFillTextBox<Client> clientTextBox;

    @FXML
    private Button addModele;

    @FXML
    private Button addClient;

    @FXML
    private TextField no_serie_text;

    @FXML
    private TextField no_produit_text;

    @FXML
    private DatePicker date_facturationPicker;

    @FXML
    private Label okText;

    private static String modeleSelected = "";

    @FXML
    private void getBackHome(ActionEvent event){
        //Main main = new Main();
        //Stage stage = new Stage();
        try {
            //main.start(stage);
            modeleSelected= "";
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
    private void handleButtonAction(ActionEvent event) throws Exception {

        //Get id_modele
        String mod = modeleTextBox.getText();
        ObservableList<Modele> modelesList = modeleTextBox.getData();
        int id_modele = 0;
        for (Modele modele : modelesList){
            if (modele.toString().equals(mod)){
                id_modele = modele.getId_modele();
            }
        }

        //Get id_client

        String cli = clientTextBox.getText();
        ObservableList<Client> clientsList = clientTextBox.getData();
        int id_client = 0;
        for (Client client : clientsList){
            if (client.toString().equals(cli)){
                id_client = client.getId_client();
                System.out.println("client "+id_client+client.toString());
            }
        }

        //Get no_serie

        String no_serie = no_serie_text.getText();

        //Get no_produit

        String no_produit = no_produit_text.getText();

        //Get date_facturation
        LocalDate date = date_facturationPicker.getValue();
        System.out.println("date"+date);

        //Insert in the database
        String stmt = "INSERT INTO appareils (id_modele, id_client, no_serie, no_produit, date_facturation) " +
                "VALUES ("+id_modele + ", " + id_client + ", " + no_serie + ", " + no_produit + ", " + date + ")" ;
        if (id_modele!=0 && id_client !=0 && date!=null){
//            DBUtil.dbExecuteUpdate(stmt);
            okText.setVisible(true);
            okText.setText("Cet appareil vendu a été ajouté.");
            modeleTextBox.getTextbox().clear();
            modeleSelected= "";
            clientTextBox.getTextbox().clear();
            no_serie_text.clear();
            no_produit_text.clear();
            date_facturationPicker.getEditor().clear();
            date_facturationPicker.setValue(null);
        } else {
            okText.setVisible(true);
            okText.setText("Remplir les champs manquants.");
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event){
        getBackHome(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        try {
            powerAutocompleteModeles();

            if (!modeleSelected.equals("")){
                modeleTextBox.getTextbox().setText(modeleSelected);
            }

            powerAutocompleteClients();

            // Variable to store the focus on stage load
            final BooleanProperty firstTime = new SimpleBooleanProperty(true);

            backHome.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
                if(newValue && firstTime.get()){
                    modeleTextBox.requestFocus(); // Delegate the focus to container
                    firstTime.setValue(false); // Variable value changed for future references
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openNewModeleForm(ActionEvent event){
        ModeleForm modeleForm = new ModeleForm();
        Stage stage = new Stage();
        try {
            modeleForm.start(stage);
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
    private void openNewClientForm(ActionEvent event){
        ClientForm clientForm = new ClientForm();
        Stage stage = new Stage();
        try {
            if (modeleTextBox.getText() != null){

                modeleSelected = modeleTextBox.getText();
            }
            clientForm.start(stage);
            //TODO to save the already chosen modele in a global variable
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
    private void powerAutocompleteModeles () throws SQLException, ClassNotFoundException {
        String stmt = "SELECT id_modele, code || ' ' || reference as codeRef "
                + "FROM modeles JOIN appareil_types "
                + "ON modeles.id_type_appareil=appareil_types.id_type_appareil "
                + "ORDER BY codeRef";
        ResultSet result = DBUtil.dbExecuteQuery(stmt);
        ObservableList<Modele> modelesList = FXCollections.observableArrayList();

        while(result.next()){
            Modele modele = new Modele();
            modele.setId_modele(result.getInt("id_modele"));
            modele.setCode_ref(result.getString("codeRef"));
            modelesList.add(modele);
        }
        modeleTextBox.setMinSize(150,25);
        modeleTextBox.setData(modelesList);
    }

    @FXML
    private void powerAutocompleteClients () throws SQLException, ClassNotFoundException {
        String stmt = "SELECT id_client, nom_client, prenom_client, ville "
                + "FROM clients ORDER BY nom_client, prenom_client, ville ";
        ResultSet result = DBUtil.dbExecuteQuery(stmt);
        ObservableList<Client> clientsList = FXCollections.observableArrayList();

        while(result.next()){
            Client client = new Client();
            client.setId_client(result.getInt("id_client"));
            client.setNom_client(result.getString("nom_client"));
            client.setPrenom_client(result.getString("prenom_client"));
            client.setVille(result.getString("ville"));
            clientsList.add(client);
        }
        clientTextBox.setData(clientsList);
    }

}
