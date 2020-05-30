package sample;


public class Dado {
    private static int numero;

    public static void tirar(){
        //Función para "tirar" el dado
        numero = (int)(Math.random()*6) + 1;
    }

    public static int getNumero() {
        return numero;
    }



}
