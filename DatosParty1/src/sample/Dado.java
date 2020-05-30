package sample;


import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

public class Dado {
    private  int numero;
    public ImageView cara;

    //IMAGEN LADOS DEL DADO
    private Image D1=new Image("Imagenes/Dados-1.png.png");
    private Image D2=new Image("Imagenes/Dados-2.png.png");
    private Image D3=new Image("Imagenes/Dados-3.png.png");
    private Image D4=new Image("Imagenes/Dados-4.png.png");
    private Image D5=new Image("Imagenes/Dados-5.png.png");
    private Image D6=new Image("Imagenes/Dados-6.png.png");


    public Dado(){
        cara= new ImageView(D1);
    }
    public  void tirar(){
        //Función para "tirar" el dado
        Partida.reproducirSonido("dado");
        numero = (int)(Math.random()*6) + 1;





        if (numero == 1) {
            cara= new ImageView(D1); }
        if (numero == 2) {
            cara= new ImageView(D2); }
        if (numero == 3) {
            cara=new ImageView(D3); }
        if (numero == 4) {
            cara=new ImageView(D4); }
        if (numero== 5) {
            cara=new ImageView(D5); }
        if (numero== 6) {
            cara=new ImageView(D6); }


        cara.setFitHeight(100);
        cara.setFitWidth(100);
        cara.setLayoutY(600);

    }

    public  int getNumero() {
        return numero;
    }



}
