package sample;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Carta {

    private Label descripcion = new Label();
    private ImageView carta;
    private bancoDeTextos banco = new bancoDeTextos();

    public Label getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(int tipoCarta){

        descripcion.setLayoutX(280);
        descripcion.setLayoutY(270);
        descripcion.setText(banco.textoDeCartaTipo(tipoCarta));
        descripcion.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

    }
    public void setDescripcion(String tipoCarta){

        descripcion.setLayoutX(280);
        descripcion.setLayoutY(270);
        descripcion.setText(tipoCarta);
        descripcion.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

    }

    public ImageView getCarta() {
        return carta;
    }

    public void setCarta(ImageView carta) {
        this.carta = carta;
    }




}