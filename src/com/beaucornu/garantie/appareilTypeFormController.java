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

public class appareilTypeFormController implements Initializable {

    @FXML
    private Button backHome;

    @FXML
    private TextField libelle_text;

    @FXML
    private TextField code_text;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        //Get libelle
        String libelle = libelle_text.getText();

        //Get code

        String code = code_text.getText();

        //Insert in the database
        String stmt = "INSERT INTO appareil_types (libelle_appareil, code) " +
                "VALUES ("+ libelle + ", " + code + ")" ;
        System.out.println(libelle);
        if (!libelle.equals("")){
//            DBUtil.dbExecuteUpdate(stmt);
            okText.setVisible(true);
            okText.setText("Cette catégorie d'appareil a été ajoutée.");
            libelle_text.clear();
            code_text.clear();
        } else {
            okText.setVisible(true);
            okText.setText("Remplir les champs manquants.");
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event){
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


    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Variable to store the focus on stage load
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);

        backHome.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                libelle_text.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
    }

}
