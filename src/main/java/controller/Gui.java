package controller;


import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Entry;
import repository.JdbcConnect;
import services.ITrialRepoServices;
import services.TrialRepoServices;

import java.util.ArrayList;
import java.util.List;

public class Gui extends Application {

    private List<String> entries = new ArrayList<>();

    private static ITrialRepoServices ctrl = new TrialRepoServices();

    public Gui(List<String> entryList) {
        entries = entryList;
    }

    public Gui() {
    }

    public List<String> getEntries() {
        return entries;
    }

    public void setEntries(List<String> entries) {
        this.entries = entries;
    }

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

        btnLogin.setOnAction((xx) -> {
            if (txtUser.getText().isEmpty() || txtPwd.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertizare!");
                alert.setHeaderText(null);
                alert.setContentText("Lipseste numele utilizatorului sau parola!");
                alert.showAndWait();
            } else if (!ctrl.validate(txtUser.getText(), txtPwd.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare!");
                alert.setHeaderText(null);
                alert.setContentText("Utilizatorul sau parola este gresita!");
                alert.showAndWait();
            } else {
                Alert alertLogin = new Alert(Alert.AlertType.INFORMATION);
                alertLogin.setTitle("Inregistrare cu succes!");
                alertLogin.setHeaderText(null);
                alertLogin.setContentText("Utilizatorul " + txtUser.getText() + " s-a logat cu succes!");
                alertLogin.showAndWait();
                stage.close();

                ListView<String> list = new ListView<String>();
                list.setPrefSize(500, 400);

                list.getItems().addAll(ctrl.getEntries());

                Label lbProba = new Label("Proba: ");
                Label lbGrupa = new Label("Grupa: ");
                Label lbNumeCopil = new Label("Nume Copil: ");
                Label lbVarstaCopil = new Label("Varsta Copil: ");
                Label lbProbe = new Label("Lungime Proba: ");

                GridPane grid = new GridPane();
                grid.setPadding(new Insets(10, 10, 10, 10));
                grid.setVgap(5);
                grid.setHgap(5);

                GridPane gridInner = new GridPane();
                gridInner.setPadding(new Insets(10, 10, 10, 10));
                gridInner.setVgap(5);
                gridInner.setHgap(5);

                TextField txtProba = new TextField();
                txtProba.setPromptText("Proba");

                TextField txtGrupa = new TextField();
                txtGrupa.setPromptText("Grupa");

                TextField txtNumeCopil = new TextField();
                txtNumeCopil.setPromptText("ex. Nume Copil");
                TextField txtVarstaCopil = new TextField();
                txtVarstaCopil.setPromptText("ex. Varsta Copil");
                TextField txtLgProba = new TextField();
                txtLgProba.setPromptText("ex. 50");

                Button btnProbeGrupe = new Button("Afisare Probe/Grupe");
                Button btnCauta = new Button("Cauta");
                Button btnAdauga = new Button("Inscriere");
                Button btnLogout = new Button("Logout");

                GridPane.setConstraints(list, 0, 1);
                grid.getChildren().add(list);


                GridPane.setConstraints(gridInner, 1, 1);
                grid.getChildren().add(gridInner);

                GridPane.setConstraints(lbProba, 1, 1);
                gridInner.getChildren().add(lbProba);
                GridPane.setConstraints(txtProba, 2, 1);
                gridInner.getChildren().add(txtProba);

                GridPane.setConstraints(lbGrupa, 1, 2);
                gridInner.getChildren().add(lbGrupa);
                GridPane.setConstraints(txtGrupa, 2, 2);
                gridInner.getChildren().add(txtGrupa);

                GridPane.setConstraints(btnCauta, 4, 2);
                gridInner.getChildren().add(btnCauta);
                GridPane.setConstraints(lbNumeCopil, 1, 4);
                gridInner.getChildren().add(lbNumeCopil);
                GridPane.setConstraints(txtNumeCopil, 2, 4);
                gridInner.getChildren().add(txtNumeCopil);
                GridPane.setConstraints(lbVarstaCopil, 1, 6);
                gridInner.getChildren().add(lbVarstaCopil);
                GridPane.setConstraints(txtVarstaCopil, 2, 6);
                gridInner.getChildren().add(txtVarstaCopil);
                GridPane.setConstraints(lbProbe, 1, 8);
                gridInner.getChildren().add(lbProbe);
                GridPane.setConstraints(txtLgProba, 2, 8);
                gridInner.getChildren().add(txtLgProba);
                GridPane.setConstraints(btnAdauga, 2, 10);
                gridInner.getChildren().add(btnAdauga);

                Scene secondScene = new Scene(grid, 650, 460);

                Stage secondStage = new Stage();
                secondStage.setTitle("Concurs Atletism");
                secondStage.setScene(secondScene);
                secondStage.show();
                GridPane.setConstraints(btnProbeGrupe, 0, 2);
                grid.getChildren().add(btnProbeGrupe);
                GridPane.setConstraints(btnLogout, 1, 2);
                grid.getChildren().add(btnLogout);

                btnLogout.setOnAction((x) -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delogare cu succes!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ati fost delogat cu succes!");
                    alert.showAndWait();
                    JdbcConnect.closeConnection();

                    secondStage.close();
                    stage.show();
                });

                String[] ageEntry = new String[1];
                String[] probaEntry = new String[1];

                btnCauta.setOnAction((x) -> {
                    if (txtGrupa.getText().isEmpty() || txtProba.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Avertizare!");
                        alert.setHeaderText(null);
                        alert.setContentText("Nu ati introdus o data");
                        alert.showAndWait();
                    } else
                        ageEntry[0] = txtGrupa.getText();
                    probaEntry[0] = txtProba.getText();

                    list.setItems(FXCollections.observableArrayList(ctrl.searchEntry(Integer.valueOf(txtProba.getText()),txtGrupa.getText())));
                });


                btnAdauga.setOnAction((x) -> {

                    if(txtVarstaCopil.getText().isEmpty() || txtLgProba.getText().isEmpty() || txtNumeCopil.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Avertizare!");
                        alert.setHeaderText(null);
                        alert.setContentText("Nu ati introdus o data");
                        alert.showAndWait();
                    }
                    else
                        if (

                            ctrl.getTrialCountByAgeAndName(Integer.valueOf(txtVarstaCopil.getText()), txtNumeCopil.getText()) > 1
                    ) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Avertizare!");
                        alert.setHeaderText(null);
                        alert.setContentText("Copil inscris deja de 2 ori");
                        alert.showAndWait();
                    } else {
                        ctrl.addEntry(Integer.valueOf(txtVarstaCopil.getText()),
                                Integer.valueOf(txtLgProba.getText()),
                                txtNumeCopil.getText());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Info!");
                        alert.setHeaderText(null);
                        alert.setContentText("Copil inscris");
                        alert.showAndWait();
                    }

                });
                btnProbeGrupe.setOnAction((x) -> {

                    list.getItems().clear();
                    list.getItems().addAll(ctrl.getEntries());
                });

            }

        });


        Scene scene = new Scene(gridLogin, 300, 150);
        stage.setTitle("Logare");
        stage.setScene(scene);
        stage.show();
    }

    public void startApp(String[] args) {
        launch(args);
    }
}





