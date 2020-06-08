package sample;


import Listas.CasillaDoble;
import Listas.CasillaSimple;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class    Jugador {   // ATRIBUTOS DEL JUGADOR
    private String nombre;
    private float posX;  //estos valores se consideran que deben de ser publicos para luego utilizarlos en eventos (QUIZA)
    private float posY;
    private int estrellas;
    private int monedas;
    private ImageView imagen;
    private Object ubicacionEnElMapa;
    private boolean direction = true;
    private int numero;
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

    public Label getRecursos() {
        return recursos;
    }

    public void actualizarRecursos()
    {
        recursos.setText("P"+nombre +":"  + monedas+ "   "+ estrellas);

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
        this.estrellas = estrellas;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
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

        ubicacionEnElMapa = casillita;// ahora la nueva casilla donde está el jugador es la introducida

        System.out.println(casillita.getINDEX() + " " + casillita.getTipo());
        moverAcoordenada(casillita.getPosicion()[0], casillita.getPosicion()[1]);


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
        setMonedas(getMonedas() - 2);

    }



}

