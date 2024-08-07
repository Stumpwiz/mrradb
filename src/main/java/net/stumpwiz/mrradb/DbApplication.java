package net.stumpwiz.mrradb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class DbApplication extends Application
{
    private static final Logger logger = LoggerFactory.getLogger(DbApplication.class);

    //TODO Don't include expired terms in reports (substitute "vacant?")
    //TODO Backup doesn't work.  File is zero length.
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(@NotNull Stage stage)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DbApplication.class.getResource("/net/stumpwiz/mrradb/db-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 550);
            stage.setTitle("MRRA Database Management");
            stage.setResizable(false);
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/logo.png"))));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("Failed to load FXML", e);
        }
    }
}
