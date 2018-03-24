package com.beaucornu.garantie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SearchPage extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Set up instance instead of using static load() method
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchPage.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Rechercher...");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
}
