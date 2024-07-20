package com.example.projtailor.tabworker;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.projtailor.enrollmentform.MySqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabWorker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboworker;

    @FXML

    private TableView<WorkerBean> tableview;
    void cleartable(){
        tableview.getColumns().clear();
    }

    void fillcombo(){
        try {
            stmt = con.prepareStatement("select wname from workers");
            ResultSet records= stmt.executeQuery();
            ArrayList<String> ary=new ArrayList<String>();
            while(records.next())
            {
                String work=records.getString("wname");//col name
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
    void getrecords(ActionEvent event) {
        cleartable();
        TableColumn<WorkerBean, String> uidC=new TableColumn<WorkerBean, String>("Worker Name");//kuch bhi
        uidC.setCellValueFactory(new PropertyValueFactory<>("wname"));
        uidC.setMinWidth(100);

        TableColumn<WorkerBean, String> Cage=new TableColumn<WorkerBean, String>("Address");//kuch bhi
        Cage.setCellValueFactory(new PropertyValueFactory<>("address"));
        Cage.setMinWidth(100);

        TableColumn<WorkerBean, String> Cdt=new TableColumn<WorkerBean, String>("Contact");//kuch bhi
        Cdt.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        Cdt.setMinWidth(100);

        TableColumn<WorkerBean, String> Cdn=new TableColumn<WorkerBean, String>("specialization");//kuch bhi
        Cdn.setCellValueFactory(new PropertyValueFactory<>("splz"));
        Cdn.setMinWidth(100);

        tableview.getColumns().addAll(uidC,Cage,Cdt,Cdn);
        tableview.setItems(showall());
//        tableview.setItems(showonsel());

    }
    PreparedStatement stmt;
    ObservableList<WorkerBean> showall() {
        ObservableList<WorkerBean> ary = FXCollections.observableArrayList();

        try {
            stmt = con.prepareStatement("select * from workers");
            ResultSet records = stmt.executeQuery();
            while (records.next()) {
                String name = records.getString("wname");//col name
                String add = records.getString("address");//col name
                String mob = records.getString("mobile");//col name

                String spl = records.getString("splz");//col name

                ary.add(new WorkerBean(name,add,mob,spl));
                System.out.println(name+ "  " + add + "  " + mob + "  " + spl );

            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        System.out.println(ary.size());
        return ary;
    }

    ObservableList<WorkerBean> showonsel() {
        ObservableList<WorkerBean> ary = FXCollections.observableArrayList();
        String namee=comboworker.getSelectionModel().getSelectedItem();

        try {
            stmt = con.prepareStatement("select * from workers where wname=?");

            stmt.setString(1,namee);
            ResultSet records = stmt.executeQuery();
            while (records.next()) {
                String name = records.getString("wname");//col name
                String add = records.getString("address");//col name
                String mob = records.getString("mobile");//col name

                String spl = records.getString("splz");//col name

                ary.add(new WorkerBean(name,add,mob,spl));
//                System.out.println(name+ "  " + add + "  " + mob + "  " + spl );

            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        System.out.println(ary.size());
        return ary;
    }
    Connection con;

    @FXML
    void getrec(ActionEvent event) {
        cleartable();
        TableColumn<WorkerBean, String> uidC1=new TableColumn<WorkerBean, String>("Worker Name");//kuch bhi
        uidC1.setCellValueFactory(new PropertyValueFactory<>("wname"));
        uidC1.setMinWidth(100);

        TableColumn<WorkerBean, String> Cage1=new TableColumn<WorkerBean, String>("Address");//kuch bhi
        Cage1.setCellValueFactory(new PropertyValueFactory<>("address"));
        Cage1.setMinWidth(100);

        TableColumn<WorkerBean, String> Cdt1=new TableColumn<WorkerBean, String>("Contact");//kuch bhi
        Cdt1.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        Cdt1.setMinWidth(100);

        TableColumn<WorkerBean, String> Cdn1=new TableColumn<WorkerBean, String>("specialization");//kuch bhi
        Cdn1.setCellValueFactory(new PropertyValueFactory<>("splz"));
        Cdn1.setMinWidth(100);

        tableview.getColumns().addAll(uidC1,Cage1,Cdt1,Cdn1 );
//        tableview.setItems(showall());
        tableview.setItems(showonsel());

    }
    @FXML
    void initialize() {

        con = MySqlConnection.doconnect();

        if (con == null) {
            System.out.println("connection ni hoya y ");
        } else
            System.out.println("connection jud gyaa");

        showall();
        fillcombo();
        showonsel();


    }
}


