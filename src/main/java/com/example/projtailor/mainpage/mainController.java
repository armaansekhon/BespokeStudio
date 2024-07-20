package com.example.projtailor.mainpage;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.projtailor.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import animatefx.animation.*;
import javafx.stage.Stage;
import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class mainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnacc;

    @FXML
    private TextField contmail;

    @FXML
    private TextField contmsg;

    @FXML
    private Button getstarted;

    @FXML
    private ImageView goback;

    @FXML
    private Pane paneback;

    @FXML
    private Pane panefirst;

    @FXML
    private TextField txtmail;

    @FXML
    private PasswordField txtpass;
    @FXML
    private Button btnsignin;

    @FXML
    void dogoback(MouseEvent event) {
        if(event.getSource().equals(goback))

        {
            new ZoomIn(panefirst).play();
            panefirst.toFront();
        }

    }

    @FXML
    void dosendadmin(ActionEvent event) {

    }

    @FXML
    void gotonext(ActionEvent event) {
        new ZoomIn(paneback).play();
        paneback.toFront();



    }
//Javafx
//    yzyz qqge ftrp ijbe
    @FXML
    void dosendmail(MouseEvent event) {
        String tooo=txtmail.getText();
        String sub="System Log-in Detected ";
        String msg=" Welcome to The bespoke Studio ,you are currently accessing the system" +
                "Have a good day!! ";
        SendMail(tooo,sub,msg);

    }

    @FXML
    void dosign(ActionEvent event) {
        String pass=txtpass.getText();
        if(pass.equals("Admin123")){
            try {
                Stage stage=new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dash/Dash-View.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
//
                stage.setScene(scene);
                stage.show();
//            Platform.exit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else{
            showMyMsg("Invalid password");

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

    public static void SendMail(String to,String Subj,String emailBody) {
        String from = "armaansekhon6560@gmail.com"; // sender's email
        final String username ="armaansekhon6560@gmail.com"; // your Gmail address
        final String password ="jbxrmsqnlhefroog"; // your app password

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(Subj);
            message.setText(emailBody);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btnacc != null : "fx:id=\"btnacc\" was not injected: check your FXML file 'Main-VIew.fxml'.";
        assert contmail != null : "fx:id=\"contmail\" was not injected: check your FXML file 'Main-VIew.fxml'.";
        assert contmsg != null : "fx:id=\"contmsg\" was not injected: check your FXML file 'Main-VIew.fxml'.";
        assert getstarted != null : "fx:id=\"getstarted\" was not injected: check your FXML file 'Main-VIew.fxml'.";
        assert goback != null : "fx:id=\"goback\" was not injected: check your FXML file 'Main-VIew.fxml'.";
        assert paneback != null : "fx:id=\"paneback\" was not injected: check your FXML file 'Main-VIew.fxml'.";
        assert panefirst != null : "fx:id=\"panefirst\" was not injected: check your FXML file 'Main-VIew.fxml'.";
        txtmail.clear();
        txtpass.clear();
    }

}
