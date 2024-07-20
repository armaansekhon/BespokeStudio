package com.example.projtailor.measurement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.projtailor.enrollmentform.MySqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class MControlller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField orderid;


    @FXML
    private ComboBox<String> combodress;

    @FXML
    private ComboBox<Integer> comboquan;

    @FXML
    private ComboBox<String> comboworker;

    @FXML
    private DatePicker dod;

    @FXML
    private ImageView imgprev;

    @FXML
    private TextField txtbill;

    @FXML
    private TextArea txtmes;

    @FXML
    private TextField txtmobb;

    @FXML
    private TextField txtprice;
    String Dresss[]={"pants","jacket","kurta pajama","suit","blazer","three piece","shirt","jeans",};


    @FXML
    void dosearch(ActionEvent event) {
        try {
            stmt = con.prepareStatement("select * from measurements where order_id=?");
            stmt.setString(1,orderid.getText());
            ResultSet records = stmt.executeQuery();
            while (records.next()) {
                String mob= records.getString("mobile");//col name
                String dres = records.getString("dress");//col name

                String picc= records.getString("pic");//col name

                Date dt = records.getDate("dodel");
                int qt=records.getInt("qty");
                int bil=records.getInt("bill");
                String meas = records.getString("measurement");//col name
                String work = records.getString("worker");//col name
//                System.out.println(em + "  " + pwd + "  " + age + "  " + bal + "  " + path + "  " + dt);

                combodress.getEditor().setText(dres);
                comboquan.getEditor().setText(String.valueOf(qt));
                txtbill.setText(String.valueOf(bil));
                txtmobb.setText(mob);
                txtmes.setText(meas);
                comboworker.getEditor().setText(work);
                dod.setValue(((java.sql.Date) dt).toLocalDate());

                if(!picc.equals("null")) {
                    filePath = picc;
                    imgprev.setImage(new Image(new FileInputStream(filePath)));
                }



            }
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }

    }

    void dobill(){
        txtbill.clear();
        String selectedquan= String.valueOf(comboquan.getSelectionModel().getSelectedItem());
        int quan=Integer.parseInt(selectedquan);
        int pri= Integer.parseInt(txtprice.getText());

        int billl=quan*pri;


        txtbill.setText(""+billl);
        System.out.println(""+billl);


    }

    @FXML
    void donew(ActionEvent event) {
        txtmobb.clear();
        combodress.getEditor().clear();
        filePath = null;
        imgprev.setImage(null);
        dod.getEditor().clear();
        comboquan.getEditor().clear();

        txtbill.clear();
        txtmes.clear();
        comboworker.getEditor().clear();
        orderid.clear();

    }

    @FXML
    void dosave(ActionEvent event) {
        String query=("insert into measurements(mobile,dress,pic,dodel,qty,bill,measurement,worker,doorder,stat) values (?,?,?,?,?,?,?,?,current_date(),1)");
        try{
            stmt=con.prepareStatement(query);
            stmt.setString(1,txtmobb.getText());
            stmt.setString(2,combodress.getSelectionModel().getSelectedItem());
            stmt.setString(3,filePath);
            LocalDate local=dod.getValue();
            java.sql.Date date=java.sql.Date.valueOf(local);
            stmt.setDate(4, date);
            stmt.setString(5, String.valueOf((comboquan.getSelectionModel().getSelectedItem())));
            stmt.setString(6,txtbill.getText());
            stmt.setString(7,txtmes.getText());
            stmt.setString(8,(comboworker.getSelectionModel().getSelectedItem()));

            stmt.executeUpdate();
            showMyMsg("Data saved Successsssfull");;

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
    void doupdate(ActionEvent event) {

        String query=("update measurements set mobile=?,dress=?,pic=?,dodel=?,qty=?,bill=?,measurement=?,worker=? where order_id=?" );
        try{
            stmt=con.prepareStatement(query);
            stmt.setString(1,txtmobb.getText());
            stmt.setString(2,combodress.getSelectionModel().getSelectedItem());
            stmt.setString(3,filePath);
            LocalDate local=dod.getValue();
            java.sql.Date date=java.sql.Date.valueOf(local);
            stmt.setDate(4, date);
            stmt.setString(5, String.valueOf((comboquan.getSelectionModel().getSelectedItem())));
            stmt.setString(6,txtbill.getText());
            stmt.setString(7,txtmes.getText());
            stmt.setString(8,(comboworker.getSelectionModel().getSelectedItem()));
            stmt.setString(9,orderid.getText());

            stmt.executeUpdate();
            showMyMsg("record updated Successsssfull");;

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }




    }
    String filePath="nopic.jpg";
    @FXML
    void doupload(ActionEvent event) {

        FileChooser chooser=new FileChooser();
        chooser.setTitle("Select Profile Pic:");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
        );
        File file=chooser.showOpenDialog(null);
        filePath=file.getAbsolutePath();


        try {
            imgprev.setImage(new Image(new FileInputStream(file)));
        }
        catch (FileNotFoundException e)
        {	e.printStackTrace();}

    }
   PreparedStatement stmt;

    void dofillworkers() {
        comboworker.getItems().clear();
        String selecteddress=combodress.getSelectionModel().getSelectedItem();
        if(selecteddress!=null){
            try {
                stmt = con.prepareStatement("select wname from workers where splz LIKE ?");
                stmt.setString(1,"%"+ selecteddress + "%");
                ResultSet records= stmt.executeQuery();
//                ArrayList<String> ary=new ArrayList<String>();
                while(records.next())
                {
                    String worker=records.getString("wname");//col name
                    comboworker.getItems().addAll(worker);

                }


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
            combodress.getItems().addAll(Dresss);
            for(int i=0;i<=100;i++){
                comboquan.getItems().add(i);
            }
            combodress.setOnAction(event -> dofillworkers());
            txtprice.setOnAction(event -> dobill() );


    }

}
