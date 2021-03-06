package MiniJuegos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Jugador;
import sample.Partida;



public class StopMisil extends Application implements  Observador ,Observado
{
    private Pane root ;
    private int puntos;
    private int destruidos;
    private Label puntaje;
    private Label Destruidos;
    private Label velocidadMisil ;
    private Jugador px;
    private float  velocidad;
    private boolean canIproduceMoreMisils;
    private Stage v;
    private Observador eventManager;

   public StopMisil() {
        px= new Jugador();
       canIproduceMoreMisils=true;
       velocidadMisil = new Label();
       Destruidos= new Label();
       puntaje= new Label();
       root = new Pane();
       velocidad=10;
    }

    public void setEventManager(Observador eventManager) {
        this.eventManager = eventManager;
    }

    public void setPx(Jugador px) {
        this.px = px;
    }

    private void destruirMisil(Misil exp)

    {
        /*This destroy a misil when its click on
         *@author Adrián González
         *@Version 06/06/2020
         * @param nothing
         */
        root.getChildren().remove(exp.getImagen());

    }
    public void  cerrarJuego()
        {
            /*This closes the game when a misil falls in the ground
             *@author Adrián González
             *@Version 06/06/2020
             * @param nothing
             */
            notificar();

            Label vic= new Label();vic.setLayoutX(0);vic.setLayoutY(100);vic.setFont(Font.font("Verdana", FontWeight.BOLD, 40));vic.setStyle("-fx-background-color: #8fa1bd");vic.setPrefSize(600,500);

            if(puntos>=100)  {
                Partida.reproducirSonido("win");
                vic.setText("felicidades has ganado la"+"\n"+ "siguiente cantidad"+"\n"+ "de monedas: "+ puntos /100);
            }
            if (puntos<100){
                Partida.reproducirSonido("fail");
                vic.setText("Mal, no ganas nada");


            }
            Platform.runLater(() -> root.getChildren().add(vic));



            px.setMonedas(px.getMonedas()+puntos/100);
            new java.util.Timer().schedule(

                    new java.util.TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            Platform.runLater(() -> v.close());
                        }
                    },
                    3000
            );

        }
    private void crearMisil()

    {  /*This create new misils
     *@author Adrián González
     *@Version 06/06/2020
     * @param nothing
     */


        Partida.reproducirSonido("fall");

        Misil bomba= new Misil();
        bomba.moverse(0.0);
        bomba.getImagen().setLayoutX(Math.random()*350+10);
        bomba.getImagen().toFront();
        bomba.getImagen().setFitHeight(55);
        bomba.getImagen().setFitWidth(55);
        bomba.setVelocidad(velocidad);
        bomba.myObserverIs(StopMisil.this);
        bomba.getImagen().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            bomba.explotar();
            destruidos+=1;
            puntos+= 10;
            velocidad+=0.5;
            puntaje.setText("Puntaje:"+puntos);
            Destruidos.setText("Destruidos:"+destruidos);
            velocidadMisil.setText("Velocidad actual: "+ velocidad);
            new java.util.Timer().schedule(

                    new java.util.TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            Platform.runLater(() -> destruirMisil(bomba));
                        }
                    },
                    30
            );
        });


        root.getChildren().add(bomba.getImagen());
        if( canIproduceMoreMisils) {
            new java.util.Timer().schedule(

                    new java.util.TimerTask() {
                        @Override
                        public void run() {

                            // ...
                            Platform.runLater(() -> {
                                if (canIproduceMoreMisils) {
                                    crearMisil();
                                }
                            });


                        }
                    },
                    1500
            );
        }
    }
    @Override
public void start(Stage stage) {
        /*This function is extended from application it makes a new window and managed it from javafx thread
         *@author Adrián González
         *@Version 06/06/2020
         * @param Stage stage
         */
    v=stage;
    ImageView fondo = new ImageView(new Image("Imagenes/Minijuegos/D3-1.png.png"));
    fondo.setFitWidth(400);
    fondo.setFitHeight(700);
    root.setStyle("-fx-background-color: #202f4a");


    puntaje.setText("Puntaje:");
    puntaje.setLayoutX(400);
    puntaje.setLayoutY(30);
    puntaje.setPrefSize(300,80);
    puntaje.setStyle("-fx-background-color: #535c94;-fx-background-radius: 30");
    puntaje.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Label INDICADOR= new Label();
        INDICADOR.setText("Jugador: "+px.getNombre());
        INDICADOR.setLayoutX(0);
        INDICADOR.setLayoutY(0);
        INDICADOR.setPrefSize(200,50);
        INDICADOR.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    Label indic = new Label();
    indic.setText("Ganas monedas cada 100 puntos");
    indic.setLayoutX(400);
    indic.setLayoutY(170);
    indic.setPrefSize(300,100);
    indic.setStyle("-fx-background-color: #535c94");
    indic.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

    velocidadMisil.setText("Velocidad actual: 10");
    velocidadMisil.setLayoutX(400);
    velocidadMisil.setLayoutY(570);
    velocidadMisil.setPrefSize(300,50);
    velocidadMisil.setStyle("-fx-background-color: #535c94");
    velocidadMisil.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

    Destruidos.setText("Destruidos:");
    Destruidos.setLayoutX(400);
    Destruidos.setLayoutY(300);
    Destruidos.setPrefSize(300,80);
    Destruidos.setStyle("-fx-background-color: #535c94;-fx-background-radius: 30");
    Destruidos.setFont(Font.font("Verdana", FontWeight.BOLD, 20));


    stage.setResizable(false);
    stage.setTitle("Save D3");
    stage.setScene(new Scene(root, 700, 700));
    root.getChildren().addAll(fondo,Destruidos,puntaje,velocidadMisil,INDICADOR,indic);
    crearMisil();

    stage.show();
        stage.setOnCloseRequest(event -> notificar());
}

    @Override
    public void Update() {
        if(canIproduceMoreMisils){
            canIproduceMoreMisils=false;

            cerrarJuego();}
    }

    @Override
    public void Update(int puntaje, Jugador jugador) {

    }

    @Override
    public void notificar() {
        eventManager.Update(puntos,px);
    }
}
