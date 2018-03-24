package com.beaucornu.garantie;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class modeleFormController implements Initializable {

    @FXML
    private Button backHome;

    @FXML
    private AutoFillTextBox<AppareilType> appareilTypeTextBox;

    @FXML
    private TextField reference_text;

    @FXML
    private Label okText;

    @FXML
    private void getBackHome(ActionEvent event){
        //Main main = new Main();
        //Stage stage = new Stage();
        try {
            //main.start(stage);
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

        //Get id_type_appareil
        String typ = appareilTypeTextBox.getText();
        ObservableList<AppareilType> appareilTypesList = appareilTypeTextBox.getData();
        int id_type_appareil = 0;
        for (AppareilType appareilType : appareilTypesList){
            if (appareilType.toString().equals(typ)){
                id_type_appareil = appareilType.getId_type_appareil();
            }
        }

        //Get reference

        String reference = reference_text.getText();

        //Insert in the database
        String stmt = "INSERT INTO modeles (id_type_appareil, reference) " +
                "VALUES ("+id_type_appareil + ", " + reference + ")" ;
        if (id_type_appareil!=0 && !reference.equals("")){
//            DBUtil.dbExecuteUpdate(stmt);
            okText.setVisible(true);
            okText.setText("Cet appareil vendu a été ajouté.");
            appareilTypeTextBox.getTextbox().clear();
            reference_text.clear();
        } else {
            okText.setVisible(true);
            okText.setText("Remplir les champs manquants.");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            powerAutocompleteAppareilTypes();

            // Variable to store the focus on stage load
            final BooleanProperty firstTime = new SimpleBooleanProperty(true);

            backHome.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
                if(newValue && firstTime.get()){
                    appareilTypeTextBox.requestFocus(); // Delegate the focus to container
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
    private void openNewAppareilTypeForm(ActionEvent event){
        AppareilTypeForm appareilTypeForm = new AppareilTypeForm();
        Stage stage = new Stage();
        try {
            appareilTypeForm.start(stage);
            Node source = (Node) event.getSource();
            Stage thisStage  = (Stage) source.getScene().getWindow();
            //if you wanna handle an event OnCloseRequest(), add the following line for it not to be bypassed
            //stage.getOnCloseRequest().handle(null);
            thisStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event){
        AppareilForm appareilForm = new AppareilForm();
        Stage stage = new Stage();
        try {
            appareilForm.start(stage);
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
    private void powerAutocompleteAppareilTypes () throws SQLException, ClassNotFoundException {
        String stmt = "SELECT id_type_appareil, libelle_appareil "
                + "FROM appareil_types "
                + "ORDER BY libelle_appareil";
        ResultSet result = DBUtil.dbExecuteQuery(stmt);
        ObservableList<AppareilType> appareilTypesList = FXCollections.observableArrayList();

        while(result.next()){
            AppareilType appareilType = new AppareilType();
            appareilType.setId_type_appareil(result.getInt("id_type_appareil"));
            appareilType.setLibelle_appareil(result.getString("libelle_appareil"));
            appareilTypesList.add(appareilType);
        }
        appareilTypeTextBox.setMinSize(150,25);
        appareilTypeTextBox.setData(appareilTypesList);
    }

}
