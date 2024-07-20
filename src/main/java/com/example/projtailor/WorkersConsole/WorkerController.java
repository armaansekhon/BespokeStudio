package com.example.projtailor.WorkersConsole;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.projtailor.enrollmentform.MySqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class WorkerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listspl;

    @FXML
    private TextArea txtadd;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtname;

    @FXML
    private TextArea txtspl;

    String Dresss[]={"pants","jacket","kurta pajama","suit","blazer","three piece","shirt","jeans",};

    @FXML
    void donew(ActionEvent event) {

        txtname.clear();
        txtmob.clear();
        txtspl.clear();
        txtadd.clear();

    }

    @FXML
    void dofill(MouseEvent event) {
        if(event.getClickCount()==2){

            String selectedItem = listspl.getSelectionModel().getSelectedItem();
            txtspl.setText(selectedItem);


        }


    }
   PreparedStatement stmt;
    @FXML
    void dosave(ActionEvent event) {
        try{
            stmt=con.prepareStatement("insert into workers values(?,?,?,?)");
            stmt.setString(1, txtname.getText());
            stmt.setString(2,txtadd.getText());
            stmt.setString(3,txtmob.getText());
            stmt.setString(4,txtspl.getText());

            stmt.executeUpdate();
            showMyMsg("  data saved Successsssfull");;

        } catch(Exception exp)
        {
            exp.printStackTrace();
        }



    }

    void showMyMsg(String msg)
    {
        //Alert alert = new Alert(AlertType.INFORMATION);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle("Information Dialog");



        alert.setHeaderText("Its Header");
        alert.setContentText(msg);

        alert.showAndWait();
    }
Connection con;
    @FXML
    void initialize() {
        con= MySqlConnection.doconnect();

        if(con==null)
        {
            System.out.println("connection ni hoya y ");
        }
        else
            System.out.println("connection jud gyaa");
        listspl.getItems().addAll(Dresss);

    }

}
