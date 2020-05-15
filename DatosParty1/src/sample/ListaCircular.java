package sample;

//NOTA ESTE CODIGO ES INSPIRADO EN EL QUE SE ENSEÑA A HACER EN EL SIGUIENTE TUTORIAL https://www.youtube.com/watch?v=J1T5VJ93SAk

public class ListaCircular
    {
        Casilla primero;
        Casilla ultimo;
        public  int tamaño;
        public ListaCircular()

            {
                primero= null;      //es necesario que empiecen como un valor nulo, mas adelante se les brindará un valor
                ultimo=null;
                tamaño=0;
            }
        public Casilla giveMe(int ind)
            /*This funtion returns the Casilla with the inserted index
             *@author Adrián González Jiménez
             *@Version 02/05/2020
             * @param Casilla
             */
            {   Casilla casillaActual =primero;
                if(ind <= tamaño) {
                    while (casillaActual.INDEX != ind) {

                        casillaActual = casillaActual.siguiente;
                    }
                    return casillaActual;
                }
                else
                {
                    System.out.println("ERROR: INDEX DE LA LISTA SOBREPASAD");
                }

                return casillaActual;
            }
        public void ingresarNodo(Casilla x)
            {

                    /*This funtion adds new component to a list
                     *@author Adrián González Jiménez
                     *@Version 02/05/2020
                     * @param Casilla
                     */
                System.out.println(tamaño);
                if (primero==null)//si no hay valores en la lista
                    {
                        primero= x;//el primer valor será el agregado
                        primero.INDEX=tamaño;
                        tamaño+=1;
                        ultimo=primero;//y a su vez este será el último
                        ultimo.INDEX=tamaño;
                        primero.siguiente=ultimo; //por lo tanto el primero apuntará al último
                    }

                else
                    {
                        ultimo.siguiente=x;
                        ultimo.INDEX=tamaño;
                        tamaño+=1;
                        x.siguiente=primero;// el ultimo volverá a apuntar al primero
                        ultimo=x;  //el ultimo ahora será el agregado
                    }

            }
















    }
