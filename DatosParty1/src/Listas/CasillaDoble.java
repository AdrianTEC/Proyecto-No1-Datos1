package Listas;

public class CasillaDoble extends CasillaSimple {

    private Object anterior;
    private boolean right = true;

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public Object getAnterior() {
        return anterior;
    }

    public void setAnterior(Object anterior) {
        this.anterior = anterior;
    }
    public void changeDirection()
        {
            Object aux;
            aux= getSiguiente();
            setSiguiente(anterior);
            setAnterior(aux);

        }
}
