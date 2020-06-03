package Listas;

public class Pila {
    private CasillaSimple ultimo; // LAST IN FIRST OUT LIFO
    //push pop peek
    public void push(CasillaSimple casillita)
        {
          casillita.setSiguiente(ultimo);
          ultimo= casillita;
        }
    public void pop()
    {
        CasillaSimple x= new CasillaSimple();
        x= (CasillaSimple) ultimo.getSiguiente();
        ultimo.setSiguiente(null);
        ultimo=x;

    }
    public CasillaSimple peek()
    {

        return ultimo;
    }





}
