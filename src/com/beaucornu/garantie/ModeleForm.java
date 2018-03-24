package com.beaucornu.garantie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ModeleForm extends Application {


    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException, IOException {

        Parent root = FXMLLoader.load(getClass().getResource("modeleForm.fxml"));

        primaryStage.setTitle("Ajouter un modèle d'appareil");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
}
