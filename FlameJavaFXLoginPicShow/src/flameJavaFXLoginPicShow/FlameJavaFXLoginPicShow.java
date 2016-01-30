/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flameJavaFXLoginPicShow;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import flameJavaFXLoginPicShow.model.User;
import flameJavaFXLoginPicShow.security.Authenticator;

/**
 *
 * @author Flame
 */
public class FlameJavaFXLoginPicShow extends Application {

    private Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 100.0;
    private final double MINIMUM_WINDOW_HEIGHT = 300.0;

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Login Window");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            gotoLogin();
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(FlameJavaFXLoginPicShow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(FlameJavaFXLoginPicShow.class, (java.lang.String[]) null);
    }

    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(FlameJavaFXLoginPicShow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean userLogging(String userId, String password) {
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoMainWindow();
            return true;
        } else {
            return false;
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = FlameJavaFXLoginPicShow.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(FlameJavaFXLoginPicShow.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 560, 420);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    private void gotoMainWindow() {
        try {
            Circle circ = new Circle(40, 40, 30);
            Group root = new Group(circ);
            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("Main Window");
            stage.setScene(scene);

        } catch (Exception ex) {
            Logger.getLogger(FlameJavaFXLoginPicShow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
