package sample;


import Listas.CasillaDoble;
import Listas.CasillaSimple;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//Por medio de abstracción se conceptualizó una versión sencilla del jugador


public class    Jugador {   // ATRIBUTOS DEL JUGADOR EL JUGADOR ES MODULAR :IDEPENDIENTE A TABLERO
    private String nombre;
    private float posX;
    private float posY;
    private int estrellas;
    private int monedas;
    private ImageView imagen;//modularidad
    private Object ubicacionEnElMapa;
    private Object ubicacionPasada;

    private boolean direction = true;
    private Label recursos;

    public Jugador() {
        /*This funtion is the constructor of the class
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param nothing
         */
        estrellas = 0;
        monedas = 11;
        nombre = "vacio";
        posY = 0;
        posX = 0;
        recursos= new Label();
        recursos.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        recursos.setStyle("-fx-background-color: rgba(243,236,250,0.63);");


    }

    public Object getUbicacionPasada() {
        return ubicacionPasada;
    }

    public Label getRecursos() {
        return recursos;
    }

    public void actualizarRecursos()
    {
        Platform.runLater(()->{ recursos.setText("P"+nombre +":"  + monedas+ "   "+ estrellas); });

    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        Partida.reproducirSonido("star");
        this.estrellas = estrellas;
        actualizarRecursos();

    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        Partida.reproducirSonido("coin");
        this.monedas = monedas;
        actualizarRecursos();

    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {

        this.imagen = imagen;


    }

    public Object getUbicacionEnElMapa() {
        return ubicacionEnElMapa;
    }

    public void setUbicacionEnElMapa(Object newUbicacion){
        this.ubicacionEnElMapa = newUbicacion;
    }





    public void moverseA(CasillaSimple casillita) {

        /*This funtion calls the funtion moverseAcoordenada
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param and object type of CasillaSimple
         */
        ubicacionPasada=ubicacionEnElMapa;

        ubicacionEnElMapa = casillita;// ahora la nueva casilla donde está el jugador es la introducida

        moverAcoordenada(casillita.getPosicion()[0], casillita.getPosicion()[1]);
        //System.out.println(casillita.getTipo()+ casillita.getINDEX());


    }

    public void moverseA(CasillaDoble casillita) {

        /*This funtion calls the funtion moverseAcoordenada
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param and object type of CasillaDoble
         */
        ubicacionEnElMapa = casillita;// ahora la nueva casilla donde está el jugador es la introducida
        moverAcoordenada(casillita.getPosicion()[0], casillita.getPosicion()[1]);

    }

    private void moverAcoordenada(float x, float y) {
        /*This movees the player to x,y coordinates
         *@author  Adrián González
         *@Version 15/04/2020
         * @param float x, float y
         *
         */
        posY = y;
        posX = x;
        imagen.setLayoutX(posX);
        imagen.setLayoutY(posY);

    }


    public void comprarEstrella() {
        /*This allows player to buy a star
         *@author Andrés Quirós
         *@Version 02/05/2020
         * @param nothing
         */
        setEstrellas(getEstrellas() + 1);
        setMonedas(getMonedas() - 5);

    }



}

