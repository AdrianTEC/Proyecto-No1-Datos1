package MiniJuegos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Carta {

    private ImageView imagen;
    private int tipo;


    public Carta() {
        imagen = new ImageView(new Image("Imagenes/Minijuegos/bomb.png"));
        imagen.setFitHeight(50);
        imagen.setFitWidth(50);
        tipo = 0;
    }

    public void setTipo (int tipox){
        tipo = tipox;
    }


    public int getTipo () {
        return tipo;
    }

    public ImageView getImagen () {
        return imagen;
    }




}
