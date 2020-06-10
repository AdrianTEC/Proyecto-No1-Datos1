package MiniJuegos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaJueg {

    private ImageView imagen;
    private int tipo;


    public CartaJueg() {
        imagen = new ImageView(new Image("Imagenes/Minijuegos/Mcart.png"));
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
