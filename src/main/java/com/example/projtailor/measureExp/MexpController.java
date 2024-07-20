package com.example.projtailor.measureExp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.projtailor.enrollmentform.MySqlConnection;
import com.example.projtailor.tabworker.WorkerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MexpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combostat;

    @FXML
    private ComboBox<String> comboworker;

    @FXML
    private TableView<MeasBean> tableview;

    @FXML
    private TextField txtmob;
    String ary[]={"In-Progress","Recieved","Delivered"};

    PreparedStatement stmt;
    void fillcombo(){
        try {
            stmt = con.prepareStatement("select distinct worker from measurements ");
            ResultSet records= stmt.executeQuery();
            ArrayList<String> ary=new ArrayList<String>();
            while(records.next())
            {
                String work=records.getString("worker");//col name
                ary.add(work);

            }
            comboworker.getItems().addAll(ary);

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }


    }
    @FXML
    void getcont(ActionEvent event) {
        cleartable();
        TableColumn<MeasBean, String> hello=new TableColumn<MeasBean, String>("Ord-id");//kuch bhi
        hello.setCellValueFactory(new PropertyValueFactory<>("Order_id"));
        hello.setMinWidth(50);

        TableColumn<MeasBean, String> Cage2=new TableColumn<MeasBean, String>("Dress");//kuch bhi
        Cage2.setCellValueFactory(new PropertyValueFactory<>("dress"));
        Cage2.setMinWidth(80);

        TableColumn<MeasBean, String> Cdt3=new TableColumn<MeasBean, String>("Contact");//kuch bhi
        Cdt3.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        Cdt3.setMinWidth(80);

        TableColumn<MeasBean, String> Cdn3=new TableColumn<MeasBean, String>("Measures");//kuch bhi
        Cdn3.setCellValueFactory(new PropertyValueFactory<>("measurement"));
        Cdn3.setMinWidth(100);


        TableColumn<MeasBean, String> aap=new TableColumn<MeasBean, String>("D.O.del");//kuch bhi
        aap.setCellValueFactory(new PropertyValueFactory<>("dodel"));
        aap.setMinWidth(80);
        TableColumn<MeasBean, String> cong=new TableColumn<MeasBean, String>("W.Name");//kuch bhi
        cong.setCellValueFactory(new PropertyValueFactory<>("worker"));
        cong.setMinWidth(80);

        tableview.getColumns().addAll(hello,Cage2,Cdt3,Cdn3,aap,cong);
        tableview.setItems(showcontl());


    }



    @FXML
    void export(ActionEvent event) {
        try {
            writeExcel();
            //txtPname.setText("Exported to excel..");
            System.out.println("Exported");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    void cleartable(){
        tableview.getColumns().clear();
    }
    @FXML
    void getrecords(ActionEvent event) {
        cleartable();
        TableColumn<MeasBean, String> hello=new TableColumn<MeasBean, String>("Ord-id");//kuch bhi
        hello.setCellValueFactory(new PropertyValueFactory<>("Order_id"));
        hello.setMinWidth(50);

        TableColumn<MeasBean, String> Cage2=new TableColumn<MeasBean, String>("Dress");//kuch bhi
        Cage2.setCellValueFactory(new PropertyValueFactory<>("dress"));
        Cage2.setMinWidth(80);

        TableColumn<MeasBean, String> Cdt3=new TableColumn<MeasBean, String>("Contact");//kuch bhi
        Cdt3.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        Cdt3.setMinWidth(80);

        TableColumn<MeasBean, String> Cdn3=new TableColumn<MeasBean, String>("Measures");//kuch bhi
        Cdn3.setCellValueFactory(new PropertyValueFactory<>("measurement"));
        Cdn3.setMinWidth(100);


        TableColumn<MeasBean, String> aap=new TableColumn<MeasBean, String>("D.O.del");//kuch bhi
        aap.setCellValueFactory(new PropertyValueFactory<>("dodel"));
        aap.setMinWidth(80);
        TableColumn<MeasBean, String> cong=new TableColumn<MeasBean, String>("W.Name");//kuch bhi
        cong.setCellValueFactory(new PropertyValueFactory<>("worker"));
        cong.setMinWidth(80);

        tableview.getColumns().addAll(hello,Cage2,Cdt3,Cdn3,aap,cong);
        tableview.setItems(showonsel());

    }
    @FXML
    void showalll(ActionEvent event) {
        cleartable();
        TableColumn<MeasBean, String> hello=new TableColumn<MeasBean, String>("Ord-id");//kuch bhi
        hello.setCellValueFactory(new PropertyValueFactory<>("Order_id"));
        hello.setMinWidth(50);

        TableColumn<MeasBean, String> Cage2=new TableColumn<MeasBean, String>("Dress");//kuch bhi
        Cage2.setCellValueFactory(new PropertyValueFactory<>("dress"));
        Cage2.setMinWidth(80);

        TableColumn<MeasBean, String> Cdt3=new TableColumn<MeasBean, String>("Contact");//kuch bhi
        Cdt3.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        Cdt3.setMinWidth(80);

        TableColumn<MeasBean, String> Cdn3=new TableColumn<MeasBean, String>("Measures");//kuch bhi
        Cdn3.setCellValueFactory(new PropertyValueFactory<>("measurement"));
        Cdn3.setMinWidth(100);


        TableColumn<MeasBean, String> aap=new TableColumn<MeasBean, String>("D.O.del");//kuch bhi
        aap.setCellValueFactory(new PropertyValueFactory<>("dodel"));
        aap.setMinWidth(80);
        TableColumn<MeasBean, String> cong=new TableColumn<MeasBean, String>("W.Name");//kuch bhi
        cong.setCellValueFactory(new PropertyValueFactory<>("worker"));
        cong.setMinWidth(80);

        tableview.getColumns().addAll(hello,Cage2,Cdt3,Cdn3,aap,cong);
        tableview.setItems(showon());


    }

    ObservableList<MeasBean> showonsel() {
        ObservableList<MeasBean> ary = FXCollections.observableArrayList();
        String namee=comboworker.getSelectionModel().getSelectedItem();
        int statt=combostat.getSelectionModel().getSelectedIndex()+1;


        try {
            stmt = con.prepareStatement("select * from measurements where stat=? AND worker=?");

            stmt.setInt(1,statt);
            stmt.setString(2,namee);

            ResultSet records = stmt.executeQuery();
            while (records.next()) {
                int order = records.getInt("order_id");//col name
                String mobb = records.getString("mobile");//col name
                String drs = records.getString("dress");//col name

                String meas = records.getString("measurement");
//                int qt=records.getInt("qty");//col name
                Date dod=records.getDate("dodel");
//                int billl=records.getInt("bill");
                String work=records.getString("worker");
                ary.add(new MeasBean(order,mobb,drs,dod,meas,work));
//                System.out.println(name+ "  " + add + "  " + mob + "  " + spl );

            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        System.out.println(ary.size());
        return ary;
    }


    ObservableList<MeasBean> showon() {
        ObservableList<MeasBean> ary = FXCollections.observableArrayList();


        try {
            stmt = con.prepareStatement("select * from measurements");


            ResultSet records = stmt.executeQuery();
            while (records.next()) {
                int order = records.getInt("order_id");//col name
                String mobb = records.getString("mobile");//col name
                String drs = records.getString("dress");//col name

                String meas = records.getString("measurement");
//                int qt=records.getInt("qty");//col name
                Date dod=records.getDate("dodel");
//                int billl=records.getInt("bill");
                String work=records.getString("worker");
                ary.add(new MeasBean(order,mobb,drs,dod,meas,work));
//                System.out.println(name+ "  " + add + "  " + mob + "  " + spl );

            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        System.out.println(ary.size());
        return ary;
    }

    ObservableList<MeasBean> showcontl() {
        ObservableList<MeasBean> ary = FXCollections.observableArrayList();
       String cont=txtmob.getText();


        try {
            stmt = con.prepareStatement("select * from measurements where mobile=?");


            stmt.setString(1,cont);

            ResultSet records = stmt.executeQuery();
            while (records.next()) {
                int order = records.getInt("order_id");//col name
                String mobb = records.getString("mobile");//col name
                String drs = records.getString("dress");//col name

                String meas = records.getString("measurement");
//                int qt=records.getInt("qty");//col name
                Date dod=records.getDate("dodel");
//                int billl=records.getInt("bill");
                String work=records.getString("worker");
                ary.add(new MeasBean(order,mobb,drs,dod,meas,work));
//                System.out.println(name+ "  " + add + "  " + mob + "  " + spl );

            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        System.out.println(ary.size());
        return ary;
    }


    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
            File file = new File("Users.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="orderid,mobile,Dress,Measurement,dodel\n";
            writer.write(text);
            for (MeasBean m :showonsel())
            {
                text = m.getOrder_id()+ "," + m.getMobile()+ "," + m.getDress()+ "," + m.getMeasurement()+"," + m.getDodel()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }

    Connection con;
    @FXML
    void initialize() {

        con = MySqlConnection.doconnect();

        if (con == null) {
            System.out.println("connection ni hoya y ");
        } else
            System.out.println("connection jud gyaa");
        combostat.getItems().addAll(ary);
        fillcombo();
        showonsel();
    }

}
