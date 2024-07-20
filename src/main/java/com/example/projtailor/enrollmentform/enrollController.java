package com.example.projtailor.enrollmentform;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;

public class enrollController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combogen;

    @FXML
    private DatePicker dobb;

    @FXML
    private TextArea txtadd;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtname;

    String gen[]={"Male","Female","Not Specified"};

//    mobile, cname,address,city,gender,dob,doenroll

    @FXML
    void doclearall(ActionEvent event) {
        txtname.clear();
        txtmob.clear();
        txtcity.clear();
        txtadd.clear();
        dobb.getEditor().clear();
        combogen.getItems().clear();


    }

    @FXML
    void doedit(ActionEvent event) {
        try{
            stmt=con.prepareStatement("update enroll set cname=?,address=?,city=?, gender=?,dob=? where mobile=?");


            stmt.setString(1,txtname.getText());
            stmt.setString(2,txtadd.getText());
            stmt.setString(3,txtcity.getText());
            stmt.setString(4,(combogen.getValue()));
            LocalDate local=dobb.getValue();
            java.sql.Date date=java.sql.Date.valueOf(local);
            stmt.setDate(5, date);
            stmt.setString(6, txtmob.getText());
            stmt.executeUpdate();
            showMyMsg(" Data Updation Complete ");;

        } catch(Exception exp)
        {
            exp.printStackTrace();
        }



    }

    PreparedStatement stmt;
    @FXML
    void doenroll(ActionEvent event) {
        try{
            stmt=con.prepareStatement("insert into enroll values(?,?,?,?,?,?,current_date())");
            stmt.setString(1, txtmob.getText());
            stmt.setString(2,txtname.getText());
            stmt.setString(3,txtadd.getText());
            stmt.setString(4,txtcity.getText());
            stmt.setString(5,(combogen.getSelectionModel().getSelectedItem()));
            LocalDate local=dobb.getValue();
            java.sql.Date date=java.sql.Date.valueOf(local);
            stmt.setDate(6, date);
            stmt.executeUpdate();
            showMyMsg("  Enrollment Successsssfull");;

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
    @FXML
    void dofetch(ActionEvent event) {

        try {
            stmt = con.prepareStatement("select * from enroll where mobile=?");
            stmt.setString(1,txtmob.getText());
            ResultSet records = stmt.executeQuery();
            while (records.next()) {
                String mo = records.getString("mobile");//col name
                String name = records.getString("cname");//col name
                String addd = records.getString("address");//col name
                String cityy = records.getString("city");//col name
                String g = records.getString("gender");//col name
                Date dt = records.getDate("dob");//col name
//                System.out.println(em + "  " + pwd + "  " + age + "  " + bal + "  " + path + "  " + dt);

                combogen.getEditor().setText(g);
                txtadd.setText(addd);
                txtcity.setText((cityy));
                txtname.setText((name));

                dobb.setValue(((java.sql.Date) dt).toLocalDate());




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
        combogen.getItems().addAll(gen);


    }

}
