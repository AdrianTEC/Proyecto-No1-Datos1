package Listas;


//NOTA ESTE CODIGO ES INSPIRADO EN EL QUE SE ENSEÑA A HACER EN EL SIGUIENTE TUTORIAL https://www.youtube.com/watch?v=J1T5VJ93SAk
public class CasillaSimple {
       private int INDEX;         //AGREGADO QUE ME PERMITE SABER QUÉ CASILLA ES
       private float[] posicion  ; //contiene una ubicacion X,Y
       private String tipo;        //Sin desarrollar
       private Object siguiente;  //Indica cual es la casilla siguiente

    public CasillaSimple()
        {
            INDEX=0;
            posicion=new float[]{0,0};
            tipo="sin tipo";
            siguiente= null;

        }

    public int getINDEX() {
        return INDEX;
    }

    public void setINDEX(int INDEX) {
        this.INDEX = INDEX;
    }

    public float[] getPosicion() {
        return posicion;
    }

    public void setPosicion(float[] posicion) {
        this.posicion = posicion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Object siguiente) {
        this.siguiente = siguiente;
    }
}
