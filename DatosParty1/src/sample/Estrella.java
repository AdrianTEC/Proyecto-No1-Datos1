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

    {  /*this moves the Start to Casilla Simple casillita
     *@author Andrés Quirós Guzman
     *@Version 01/05/2020
     * @param and object type of CasillaSimple
     */
        Partida.reproducirSonido("star");

        ubicacionEnElMapa=casillita;

        System.out.println(casillita.getINDEX() +" "+ casillita.getTipo() );
        moverAcoordenada(casillita.getPosicion()[0],casillita.getPosicion()[1]);


    }

    public  void moverseA(CasillaDoble casillita)
        /*this moves the Start to Casilla Doble casillita
         *@author Andrés Quirós Guzman
         *@Version 01/05/2020
         * @param and object type of CasillaSimple
         */
    {


        ubicacionEnElMapa=casillita;
        moverAcoordenada(casillita.getPosicion()[0],casillita.getPosicion()[1]);



    }
    private void moverAcoordenada(float x, float y)
    { /*this moves the Start to specific coordinates (x,y)
     *@author Andrés Quirós Guzman
     *@Version 01/05/2020
     * @param
     */
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
        numero = (int)(Math.random()*4)+1;
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