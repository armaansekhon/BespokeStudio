package com.example.projtailor.getdresses;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.projtailor.enrollmentform.MySqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class GetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Date> lstdod;

    @FXML
    private ListView<String> lstdress;

    @FXML
    private ListView<Integer> lstorder;

    @FXML
    private ComboBox<String> txtwrkr;

    @FXML
    void dofilllists() {


            String selectedItem = txtwrkr.getSelectionModel().getSelectedItem();
            try {
                stmt = con.prepareStatement("select order_id,dress,dodel from measurements where stat=1 AND worker=?");
                stmt.setString(1,selectedItem);
                ResultSet records= stmt.executeQuery();
                ArrayList<Integer> aryofordr= new ArrayList<>();
                ArrayList<String> aryofdress=new ArrayList<String>();
                ArrayList<Date> aryofdate=new ArrayList<Date>();
                while(records.next())
                {
                    String dres=records.getString("dress");//col name
                    aryofdress.add(dres);
                    int ord=records.getInt("order_id");//col name
                    aryofordr.add(ord);
                    Date dt=records.getDate("dodel");//col name
                    aryofdate.add(dt);

                }
                lstorder.getItems().addAll(aryofordr);
                lstdress.getItems().addAll(aryofdress);
                lstdod.getItems().addAll(aryofdate);

            }
            catch(Exception exp)
            {
                exp.printStackTrace();
            }


    }

    @FXML
    void getrecived(ActionEvent event) {

        String query=("update measurements set stat=2" );
        try{
            stmt=con.prepareStatement(query);
            stmt.executeUpdate();

            showMyMsg("orders Recieved Successsssfull");
            lstorder.getItems().clear();
            lstdod.getItems().clear();
            lstdress.getItems().clear();

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }





    }
    PreparedStatement stmt;
    void fillcombo(){
        try {
            stmt = con.prepareStatement("select distinct worker from measurements where stat=1");
            ResultSet records= stmt.executeQuery();
            ArrayList<String> ary=new ArrayList<String>();
            while(records.next())
            {
                String work=records.getString("worker");//col name
                ary.add(work);

            }
            txtwrkr.getItems().addAll(ary);

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
    @FXML
    void doAddToCart(MouseEvent event)
    {
        if(event.getClickCount()==2)
        {
            String query=("update measurements set stat=2" );
            try{
                stmt=con.prepareStatement(query);
                stmt.executeUpdate();

                showMyMsg("orders Recieved Successsssfull");
                lstorder.getItems().clear();
                lstdod.getItems().clear();
                lstdress.getItems().clear();

            }
            catch(Exception exp)
            {
                exp.printStackTrace();
            }
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
        fillcombo();

    }


}
