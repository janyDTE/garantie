package com.beaucornu.garantie;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ClientForm extends Application {


    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException, IOException {

        Parent root = FXMLLoader.load(getClass().getResource("clientForm.fxml"));

        primaryStage.setTitle("Ajouter un client");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
}
