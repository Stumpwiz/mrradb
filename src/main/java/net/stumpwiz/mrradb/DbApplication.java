package net.stumpwiz.mrradb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class DbApplication extends Application
{
    //TODO Don't include expired terms in reports (substitute "vacant?")
    //TODO Backup doesn't work.  File is zero length.
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(@NotNull Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(DbApplication.class.getResource("/net/stumpwiz/mrradb/db-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("MRRA Database Management");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/logo.png"))));
        stage.setScene(scene);
        stage.show();
    }
}
