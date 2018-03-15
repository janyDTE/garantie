package com.beaucornu.garantie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainController {
    @FXML
    private void handleButtonAction1(ActionEvent event) {
        AppareilForm appareilForm = new AppareilForm();
        Stage stage = new Stage();
        try {
            appareilForm.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
