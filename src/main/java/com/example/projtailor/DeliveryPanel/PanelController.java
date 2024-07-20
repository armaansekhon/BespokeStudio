package com.example.projtailor.DeliveryPanel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.projtailor.enrollmentform.MySqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Integer> lstbill;

    @FXML
    private ListView<String> lstdress;

    @FXML
    private ListView<Integer> lstorder;

    @FXML
    private ListView<String> lststat;

    @FXML
    private TextField txtbill;

    @FXML
    private TextField txtmob;



    @FXML
    void dodeliver(ActionEvent event) {


        String selectedItem = txtmob.getText();
        try {
            stmt = con.prepareStatement("update  measurements set stat=3  where stat=2 AND mobile=?" );
            stmt.setString(1,selectedItem);
            stmt.executeUpdate();
            showMyMsg("Orders Delivered Succesfully");
            lstorder.getItems().clear();
            lstdress.getItems().clear();
            lststat.getItems().clear();
            lstbill.getItems().clear();
            txtbill.clear();




        }
        catch(Exception exp)
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
PreparedStatement stmt;
    int billl=0;
    @FXML
    void dofind(ActionEvent event) {
        billl=0;

        lstorder.getItems().clear();
        lstdress.getItems().clear();
        lststat.getItems().clear();
        lstbill.getItems().clear();




        String selectedItem = txtmob.getText();
        try {
            stmt = con.prepareStatement("select  stat,order_id,bill,dress from measurements where (stat=1 OR stat=2) AND mobile=?" );
            stmt.setString(1,selectedItem);
            ResultSet records= stmt.executeQuery();

            while(records.next())
            {
                String drs=records.getString("dress");
                lstdress.getItems().add("drs");
                int order=records.getInt("order_id");
                lstorder.getItems().add(order);
                int bi=records.getInt("bill");
                lstbill.getItems().add(bi);
                int sta=records.getInt("stat");
                if(sta==1){
                    lststat.getItems().add("In-Progress");
                }
                else if(sta==2){
                    lststat.getItems().add("Recieved");
                     billl+=bi;
                     txtbill.setText(""+billl);
                }
                else{
                    lststat.getItems().add("delivered");
                }




            }


        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }



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

    }

}
