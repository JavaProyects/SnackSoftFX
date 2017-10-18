package com.solvesoft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.solvesoft.dao.LoginDao;
import com.solvesoft.model.Usuario;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.data.http.RequestMethodFieldHandler;

/**
 * FXML Controller class
 *
 * @author theo
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnIngresar;

    @FXML
    private ImageView imgProgress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleValidator();
        imgProgress.setVisible(false);
    }

    @FXML
    private void login(ActionEvent ae) {
        imgProgress.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(5));
        pauseTransition.setOnFinished(ev -> {
            doLogin();
        });
        pauseTransition.play();
    }

    private void doLogin() {
        try {
            LoginDao dao = new LoginDao();
            Usuario usuario = dao.login(txtUser.getText(), txtPassword.getText());

            if (usuario.getUsuario().equals(txtUser.getText()) && usuario.getPassword().equals(txtPassword.getText())) {
                btnIngresar.getScene().getWindow().hide();
                imgProgress.setVisible(false);
                Stage principalStage = new Stage();
                principalStage.setTitle("");
                principalStage.setMaximized(false);
                Parent root = FXMLLoader.load(getClass().getResource("/com/solvesoft/view/principal.fxml"));
                Scene scene = new Scene(root);
                principalStage.setScene(scene);
                principalStage.show();
            }
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private void handleValidator() {
        RequiredFieldValidator fieldValidatorUser = new RequiredFieldValidator();
        fieldValidatorUser.setMessage("Campo requerido");
        fieldValidatorUser.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtUser.getValidators().add(fieldValidatorUser);
        txtUser.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                txtUser.validate();
            }
        });

        RequiredFieldValidator fieldValidatorPass = new RequiredFieldValidator();
        fieldValidatorPass.setMessage("Campo requerido");
        fieldValidatorPass.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtPassword.getValidators().add(fieldValidatorUser);
        txtPassword.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                txtPassword.validate();
            }
        });
    }
}
