package com.example.studaid;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    private Connection connect = DatabaseService.instance.getConnection();
    private PreparedStatement prepare;
    private ResultSet result;
    public void LoginAdmin(){
        String sql = "SELECT email, password FROM admin Where email = ? and password = ?";
//        connect = database.connectionDb();
        try {
            Alert alert;
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();
            if (username.getText().isEmpty() || password.getText().isEmpty())
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the Blank Fields");
                alert.showAndWait();
            }
            else{
                if(result.next())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();


                    loginBtn.getScene().getWindow().hide();


                    Parent root = FXMLLoader.load(getClass().getResource("fxmls/dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Email or Password");
                    alert.showAndWait();

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void close(){
        System.exit(0);
    }
    @Override
    public void initialize(URL url , ResourceBundle rb){}


}
