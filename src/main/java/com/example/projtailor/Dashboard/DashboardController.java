package com.example.projtailor.Dashboard;

import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.projtailor.HelloApplication;
import com.example.projtailor.enrollmentform.MySqlConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class DashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnen;

    @FXML
    private ImageView btnlog;

    @FXML
    private Button docon;

    @FXML
    private Button dodata;

    @FXML
    private Button dodel;

    @FXML
    private Button dodress;

    @FXML
    private Button doexp;

    @FXML
    private Button domes;

    @FXML
    private Label lblinprog;

    @FXML
    private Label lblrec;

    @FXML
    private Label lblrec1;

    @FXML
    private Label r;

    @FXML
    private Label r1;

    @FXML
    private Label r11;

    @FXML
    private Label r111;

    @FXML
    private PieChart piechart;

    @FXML
    void gobacktomain(MouseEvent event) {
        if(event.getSource().equals(btnlog))
        {
            try {
//                Stage stage=new Stage();
//                FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("Landpage/Main-View.fxml"));
//                Scene scene = new Scene(fxmlLoader.load());
////
//                stage.setScene(scene);
//                stage.show();
                Platform.exit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    @FXML
    void opendel(ActionEvent event) {
        try {
                Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("panell/Panel-View.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
//
                stage.setScene(scene);
                stage.show();
//            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openenroll(ActionEvent event) {
        try {
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("enrolll/Enroll-View.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
//
            stage.setScene(scene);
            stage.show();
//            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openexplorer(ActionEvent event) {
        try {
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("explore/Mexp-View.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
//
            stage.setScene(scene);
            stage.show();
//            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void opengetd(ActionEvent event) {
        try {
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("getD/get-View.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
//
            stage.setScene(scene);
            stage.show();
//            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openmeas(ActionEvent event) {
        try {
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("measure/Mview.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
//
            stage.setScene(scene);
            stage.show();
//            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void opentabworker(ActionEvent event) {
        try {
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tabW/tab-View.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
//
            stage.setScene(scene);
            stage.show();
//            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openworker(ActionEvent event) {
        try {
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("WorkersCon/Worker-View.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
//
            stage.setScene(scene);
            stage.show();
//            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

PreparedStatement stmt;


        private void loadPieChart() {
            try {
                // Connect to MySQL database (replace with your database details)


                // Execute SQL query
                String sql = "SELECT dress, COUNT(*) as count FROM measurements GROUP BY dress";
                 stmt = con.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery();

                // Populate data into pie chart
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    String dressType = resultSet.getString("dress"
                    );
                    int count = resultSet.getInt("count");
                    PieChart.Data data = new PieChart.Data(dressType, count);
                    pieChartData.add(data);
                }

                piechart.setData( pieChartData );




            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private void fetchstat(){
            try {
                // Connect to MySQL database (replace with your database details)


                // Execute SQL query
                String sql = "SELECT stat, COUNT(*) as count FROM measurements GROUP BY stat";
                stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                // Populate data into pie chart

                while (rs.next()) {
                    while (rs.next()) {
                        int stat = rs.getInt("stat");
                        int count = rs.getInt("count");
                        if (stat == 1) {
                            lblinprog.setText("" + count);
                        } else if (stat == 2) {
                            lblrec.setText("" + count);
                        } else if (stat == 3) {
                            lblrec1.setText("" + count);
                        }
                    }
                }





                // Close connections

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


  Connection con;
    @FXML
    void initialize() {
        assert piechart != null : "fx:id=\"piechart\" was not injected: check your FXML file 'Dash-View.fxml'.";
        con = MySqlConnection.doconnect();

        if (con == null) {
            System.out.println("connection ni hoya y ");
        } else
            System.out.println("connection jud gyaa");
        loadPieChart();
        fetchstat();


    }

}
