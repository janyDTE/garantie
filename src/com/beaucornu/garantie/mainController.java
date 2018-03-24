package com.beaucornu.garantie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class mainController {

    @FXML
    private void openNewAppareilForm(ActionEvent event) {
        AppareilForm appareilForm = new AppareilForm();
        Stage stage = new Stage();
        try {
            appareilForm.start(stage);
            Node source = (Node) event.getSource();
            Stage thisStage  = (Stage) source.getScene().getWindow();
            //if you wanna handle an event OnCloseRequest(), add the following line for it not to be bypassed
            //stage.getOnCloseRequest().handle(null);
            //thisStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openSearchStage(ActionEvent event) {
        SearchPage searchPage = new SearchPage();
        Stage stage = new Stage();
        try {
            searchPage.start(stage);
            Node source = (Node) event.getSource();
            Stage thisStage  = (Stage) source.getScene().getWindow();
            //if you wanna handle an event OnCloseRequest(), add the following line for it not to be bypassed
            //stage.getOnCloseRequest().handle(null);
            //thisStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
