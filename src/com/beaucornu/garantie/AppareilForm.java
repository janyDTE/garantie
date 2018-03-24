package com.beaucornu.garantie;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppareilForm extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException, IOException {

        //Set up instance instead of using static load() method
        FXMLLoader loader = new FXMLLoader(getClass().getResource("appareilForm.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Ajouter un appareil vendu");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
}
