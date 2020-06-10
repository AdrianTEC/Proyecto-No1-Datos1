package Listas;


public class Pila {
    public CasillaExtraSimple ultimo; // LAST IN FIRST OUT LIFO
    private int size;

    public Pila() {
        ultimo= null;
        size=0;
    }

    public void ShowPila()
        {
            Pila x= this;
            String b="[";

            for(int i=size; i>0 ;i--)
                {
                    b+= x.pop().getDato() + ", ";
                }
            b+="]";
            System.out.println(b);
        }
    public void push(CasillaExtraSimple casillita)
        {/*This adds a CasillaExtraSimple to the top of the pila
         *@author Adrián González
         *@Version 06/06/2020
         * @param CasilaExtraSimple
         */
            size++;
          casillita.setSiguiente(ultimo);
          ultimo= casillita;

        }
    public void especialPush(String[] cadena)
        {/*This converts a String[] int a pila
         *@author Adrián González
         *@Version 07/06/2020
         * @param String[] cadena
         */
            for(String i: cadena)
            { CasillaExtraSimple x= new CasillaExtraSimple();
            x.setDato(i);
            push(x);

            }
        }
    public CasillaExtraSimple pop()
    {  /*This funtion deletes the last CasillaExtraSimple from pila
     *@author Adrián González
     *@Version 06/06/2020
     * @param nothing
     */
        CasillaExtraSimple x= new CasillaExtraSimple();
        CasillaExtraSimple aux= ultimo;
        x=  ultimo.getSiguiente();
        ultimo.setSiguiente(null);
        ultimo=x;
        size--;
        return aux;
    }
    public CasillaExtraSimple peek()
    {/*This funtion returns the last CasillaExtraSimple from pila
     *@author Adrián González
     *@Version 06/06/2020
     * @param nothing
     *@returns CasillaExtraSimple ultimo
     */
        CasillaExtraSimple x= ultimo;

        return x;


    }





}
