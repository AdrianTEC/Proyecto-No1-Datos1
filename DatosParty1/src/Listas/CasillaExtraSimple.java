package Listas;

public class CasillaExtraSimple {
    private String dato;
    private CasillaExtraSimple siguiente;

    public CasillaExtraSimple() {
        dato="estoy vacio";
        siguiente= null;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setSiguiente(CasillaExtraSimple siguiente) {
        this.siguiente = siguiente;
    }

    public String getDato() {
        return dato;
    }

    public CasillaExtraSimple getSiguiente() {
        return siguiente;
    }
}
