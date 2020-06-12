package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Baraja {

    private String tipoBaraja;




    public void setTipoBaraja(String tipoBaraja) {
        this.tipoBaraja = tipoBaraja;
    }


    Image verde=new Image("Imagenes/Cartas/CVERDE.png");
    Image rojo=new Image("Imagenes/Cartas/CROJA.png");
    Image azul=new Image("Imagenes/Cartas/CAZUL.png");
    Image dorada=new Image("Imagenes/Cartas/CDORADA.png");

    public Carta crearCarta(){
        /*This function creates a card an return it
         *@author Yordan Rojas
         *@Version 02/05/2020
         * @param nothing
         * @return Carta cartita
         */
        Carta cartita = new Carta();

        if (tipoBaraja.equals("V")){ cartita.setCarta(new ImageView(verde));}

        if (tipoBaraja.equals("A")){ cartita.setCarta(new ImageView(azul));}

        if (tipoBaraja.equals("R")){ cartita.setCarta(new ImageView(rojo));}

        if (tipoBaraja.equals("D")){ cartita.setCarta(new ImageView(dorada));}

        cartita.getCarta().setFitWidth(400);
        cartita.getCarta().setFitHeight(400);
        cartita.getCarta().setLayoutX(155);
        cartita.getCarta().setLayoutY(175);


        return cartita;
    }


}
