package Listas;

public class CasillaExtraSimple {
    private Object dato;
    private CasillaExtraSimple siguiente;
    private int INDEX;

    public int getINDEX() {
        return INDEX;
    }

    public void setINDEX(int INDEX) {
        this.INDEX = INDEX;
    }

    public CasillaExtraSimple() {
        dato="estoy vacio";
        siguiente= null;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public void setSiguiente(CasillaExtraSimple siguiente) {
        this.siguiente = siguiente;
    }

    public Object getDato() {
        return dato;
    }

    public CasillaExtraSimple getSiguiente() {
        return siguiente;
    }
}
