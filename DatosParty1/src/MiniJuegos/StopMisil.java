package MiniJuegos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StopMisil extends Application
{
    private ImageView fondo;

    public void setFondo(ImageView fondo) {
        this.fondo = fondo;
    }


    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        fondo= new ImageView(new Image("Imagenes/Minijuegos/D3-1.png.png"));
        fondo.setFitWidth(400);
        fondo.setFitHeight(700);
        root.setStyle("-fx-background-color: #202f4a");






        stage.setResizable(false);
            stage.setTitle("Datos Party 1");
            stage.setScene(new Scene(root, 700, 700));
            root.getChildren().addAll(fondo);
            stage.show();

    }
}
