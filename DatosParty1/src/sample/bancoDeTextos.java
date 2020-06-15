package sample;

public class bancoDeTextos {

    private  String[] textos;

    public bancoDeTextos(){
        textos= new String[]{"cohete",
                "programacion", "computadores", "doctor", "abecedario", "hormiga", "datos","mapache","palabra","tecnologico"

        };
    }

    public  String giveMeAText()
        { /*This returns a text from text Bank
         *@author Yordan Rojas
         *@Version 01/06/2020
         * @param nothing
         */
            String mensaje;
                mensaje= textos[(int) (Math.random()*textos.length)];

            return mensaje;

        }

    public String textoDeCartaTipo(int tipo){
        /*This returns a text from text Bank
         *@author Yordan Rojas
         *@Version 01/06/2020
         * @param int
         * @returns String
         */
        String random = new String();
        int x = (int)(Math.random()*3);
        if (tipo == 1){

            random = cartasVERDES [x];

        }
        if (tipo == 3){

            random = cartasAZULES [x];

        }
        if (tipo == 2){

            random = cartasROJAS [x];

        }

        return random;
    }



    private String[] cartasVERDES= new String[]
            {"te encontraste una"+"\n"+ "moneda en el suelo,"+"\n"+"eres 1% más rico"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        +1 moneda",
             "encontraste una"+"\n"+"moneda"+"\n"+"en tu bolsillo,"+"\n"+"eres 1% más rico"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        +1 moneda",
             "recibiste una"+"\n"+"herencia" +"\n"+"de una moneda"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        +1 moneda"
            };

    private String[] cartasAZULES = new String[]
            { "la vida no te sonrie,"+"\n"+ "pero tampoco es"+"\n"+"un mal dia",
              "¿Tu mala suerte"+"\n"+"te trajo aqui?",
              "Cero más cero,"+"\n"+"sigue siendo cero"
            };
    private String[] cartasROJAS = new String[]
            { "Te asaltan"+"\n"+"amablemente,"+"\n"+"pierdes poco dinero"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        -1 moneda",
              "Te da hambre,"+"\n"+"tienes que ir"+"\n"+"a comprar algo"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        -1 moneda",
              "Tu mala suerte"+"\n"+"te trajo aqui"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                    "        -1 moneda"
            };



}