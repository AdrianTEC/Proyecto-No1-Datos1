package sample;

import Listas.CasillaDoble;
import Listas.CasillaSimple;
import javafx.scene.image.ImageView;

public class Estrella {
    //Atributos de la Estrella
    public float posX;
    public float posY;
    public ImageView imagen;
    public Object ubicacionEnElMapa;
    public int numero;
    public boolean primero;

    public Estrella() {
        posX = 0;
        posY = 0;
        numero = 0;
        primero = false;

    }

    public  void moverseA(CasillaSimple casillita)

    {
        Partida.reproducirSonido("star");

        ubicacionEnElMapa=casillita;

        //System.out.println(casillita.getINDEX() +" "+ casillita.getTipo() );
        moverAcoordenada(casillita.getPosicion()[0],casillita.getPosicion()[1]);


    }

    public  void moverseA(CasillaDoble casillita)

    {


        ubicacionEnElMapa=casillita;
        moverAcoordenada(casillita.getPosicion()[0],casillita.getPosicion()[1]);



    }
    private void moverAcoordenada(float x, float y)
    {
        posY=y;
        posX=x;
        imagen.setLayoutX(posX);
        imagen.setLayoutY(posY);

    }


    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
        imagen.setFitHeight(50);
        imagen.setFitWidth(50);
        imagen.setLayoutY(500);
    }


    public void numeroRandom() {
        numero = (int)(Math.random()*31) + 1;
    }


    public int getNumero(){
        return numero;
    }

    public Object getUbicacionEnElMapa() {
        return ubicacionEnElMapa;
    }

    public void setUbicacionEnElMapa(Object newUbicacion){
        this.ubicacionEnElMapa = newUbicacion;
    }

    public boolean getPrimero(){
        return primero;
    }

    public void setPrimero(boolean newPrimero) {
        this.primero = newPrimero;

    }



}