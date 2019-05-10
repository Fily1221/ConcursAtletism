package controller;




import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.ITrialRepoServices;
import services.TrialRepoServices;

public class Gui extends Application{

    private static ITrialRepoServices ctrl = new TrialRepoServices();

    @Override
    public void start(Stage stage) throws Exception {

        GridPane gridLogin;
        gridLogin = new GridPane();
        gridLogin.setPadding(new Insets(10, 10, 10, 10));
        gridLogin.setVgap(5);
        gridLogin.setHgap(5);

        Label lbUser = new Label("Utilizator: ");
        Label lbPwd = new Label("Parola: ");

        TextField txtUser = new TextField();
        txtUser.setPromptText("nume utilizator");
        TextField txtPwd = new TextField();
        txtPwd.setPromptText("parola");

        Button btnLogin = new Button("Login");

        GridPane.setConstraints(lbUser, 0, 1);
        gridLogin.getChildren().add(lbUser);
        GridPane.setConstraints(txtUser, 1, 1);
        gridLogin.getChildren().add(txtUser);
        GridPane.setConstraints(lbPwd, 0, 2);
        gridLogin.getChildren().add(lbPwd);
        GridPane.setConstraints(txtPwd, 1, 2);
        gridLogin.getChildren().add(txtPwd);
        GridPane.setConstraints(btnLogin, 1, 3);
        gridLogin.getChildren().add(btnLogin);

        Scene scene = new Scene(gridLogin, 300, 150);
        stage.setTitle("Logare");
        stage.setScene(scene);
        stage.show();
    }

    public void startApp(String[] args){
        launch(args);
    }

}

