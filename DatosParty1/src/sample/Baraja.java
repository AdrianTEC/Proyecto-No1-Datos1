package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Baraja {

    private String tipoBaraja;
    private ImageView baraja;


    public String getTipoBaraja() {
        return tipoBaraja;
    }

    public void setTipoBaraja(String tipoBaraja) {
        this.tipoBaraja = tipoBaraja;
    }

    public ImageView getBaraja() {
        return baraja;
    }

    public void setBaraja(ImageView baraja) {
        this.baraja = baraja;
    }

    Image verde=new Image("Imagenes/Cartas/CVERDE.png");
    Image rojo=new Image("Imagenes/Cartas/CROJA.png");
    Image azul=new Image("Imagenes/Cartas/CAZUL.png");

    public Carta crearCarta(){
        Carta cartita = new Carta();

        if (tipoBaraja == "V"){ cartita.setCarta(new ImageView(verde));}

        if (tipoBaraja == "A"){ cartita.setCarta(new ImageView(azul));}

        if (tipoBaraja == "R"){ cartita.setCarta(new ImageView(rojo));}

        cartita.getCarta().setFitWidth(400);
        cartita.getCarta().setFitHeight(400);
        cartita.getCarta().setLayoutX(155);
        cartita.getCarta().setLayoutY(175);


        return cartita;
    }


}
