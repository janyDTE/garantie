package com.beaucornu.garantie;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class searchPageController implements Initializable {

    @FXML
    private Button backHome;

    @FXML
    private AutoFillTextBox<Client> clientSearchTextBox;

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
    private void backButtonAction(ActionEvent event){
        getBackHome(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            powerAutocompleteClients();

            // Variable to store the focus on stage load
            final BooleanProperty firstTime = new SimpleBooleanProperty(true);

            backHome.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue && firstTime.get()) {
                    clientSearchTextBox.requestFocus(); // Delegate the focus to container
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
    public Stage openSelectedClientForm(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        //Get id_client

        String cli = clientSearchTextBox.getText();
        ObservableList<Client> clientsList = clientSearchTextBox.getData();
        int id_client = 0;
        for (Client client : clientsList){
            if (client.toString().equals(cli)){
                id_client = client.getId_client();
                System.out.println("client "+id_client+client.toString());
            }
        }
        if (id_client!=0){

            String stmt = "SELECT * FROM clients WHERE id_client="+id_client;
            ResultSet result = DBUtil.dbExecuteQuery(stmt);

            Client client = new Client();

            while (result.next()){
                client.setId_client(result.getInt("id_client"));
                client.setNom_client(result.getString("nom_client"));
                client.setPrenom_client(result.getString("prenom_client"));
                client.setAdresse(result.getString("adresse"));
                client.setVille(result.getString("ville"));
                client.setCode_postal(result.getString("code_postal"));
                client.setTelephone(result.getString("telephone"));
            }

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "clientForm.fxml"
                    )
            );

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(
                    new Scene(
                            (Pane) loader.load(), 700, 600
                    )
            );

            clientFormController controller =
                    loader.<clientFormController>getController();
            controller.initData(client, false, true);

            stage.show();

            return stage;
        }
        return null;
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
        clientSearchTextBox.setData(clientsList);
    }

}
