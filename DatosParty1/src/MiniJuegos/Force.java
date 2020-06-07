package MiniJuegos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Jugador;
import sample.Partida;



public class Force extends Application {

    private Jugador px1;
    private Jugador px2;
    private  int key1;
    private int key2;
    private Boolean timeForShoot;
    private ImageView flag;
    private Pane root ;

    public Force() {
        px1= new Jugador();
        px2= new Jugador();
        key1=0;
        key2=0;
        timeForShoot=false;
        flag= new ImageView("Imagenes/Minijuegos/flag.png");
        root = new Pane();
    }

    private  void shoot()
    {
        timeForShoot=true;
        Partida.reproducirSonido("alert");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.getChildren().add(flag);
            }
        });

        new java.util.Timer().schedule(

                new java.util.TimerTask() {
                    @Override
                    public void run() {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                root.getChildren().remove(flag);
                            }
                        });

                    }
                },
                2000
        );

    }

    @Override
    public void start(Stage stage) throws Exception {
        ImageView fondo= new ImageView("Imagenes/Minijuegos/Construction.png");

        //creo los vaqueros

        ImageView k= new ImageView("Imagenes/Minijuegos/k.png");
        k.setLayoutX(1000);
        ImageView s= new ImageView("Imagenes/Minijuegos/S.png");
        s.setLayoutX(100);

        ImageView flecha= new ImageView("Imagenes/Minijuegos/Flecha.png");
            flecha.setLayoutX(600);
            flecha.setLayoutY(300);
        ImageView workers = new ImageView(("Imagenes/Minijuegos/Workers.png"));
            workers.setLayoutX(400);
            workers.setLayoutY(330);
        flag.setLayoutX(500);
        flag.setFitHeight(200);
        flag.setFitWidth(200);


        stage.setResizable(false);
        stage.setTitle("Force");
        stage.setScene(new Scene(root, 1338, 538));
        //AGREGO LAS COSAS
        root.getChildren().addAll(fondo,s,k,workers,flecha);
        root.setStyle("-fx-background-color: #202f4a");

        stage.show();
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()




        {   @Override
        public void handle(KeyEvent evt)
        {

            if(timeForShoot) {

                if (evt.getCode().equals(KeyCode.S)) {

                    Partida.reproducirSonido("bip");
                    key1 += 1;
                    workers.setLayoutX(workers.getLayoutX() - key1);


                }
                if (evt.getCode().equals(KeyCode.K)) {

                    Partida.reproducirSonido("bip");
                    key2 += 1;
                    workers.setLayoutX(workers.getLayoutX() + key2);

                }

            }

            if(workers.getLayoutX()<229 || workers.getLayoutX()>561) {
                Partida.reproducirSonido("win");
                Label vic= new Label();
                vic.setLayoutX(10);
                vic.setLayoutY(100);
                vic.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
                vic.setStyle("-fx-background-color: #8fb8c9");
                vic.setPrefSize(1300,200);
                if (workers.getLayoutX() < 229) {
                    timeForShoot = false;
                    vic.setText("px1.getNombre()" +"  Ha ganado el duelo!");
                    px1.setMonedas(px1.getMonedas()+1);
                    px2.setMonedas(px2.getMonedas()-1);

                }
                if (workers.getLayoutX() > 561) {
                    timeForShoot = false;
                    vic.setText("px2.getNombre()" +"  Ha ganado el duelo!");
                   // px2.setMonedas(px2.getMonedas()+1);
                    //px1.setMonedas(px1.getMonedas()-1);

                }
                Platform.runLater(new Runnable() {@Override public void run() { root.getChildren().add(vic); }});

                new java.util.Timer().schedule(

                        new java.util.TimerTask() {
                            @Override
                            public void run() {

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.exit(1);
                                    }
                                });

                            }
                        },
                        2000
                );



            }



        }  });


        stage.setOnCloseRequest(event -> { System.exit(1);});
        new java.util.Timer().schedule(

                new java.util.TimerTask() {
                    @Override
                    public void run() {


                        shoot();


                    }
                },
                (long) (1000*Math.random()*5)
        );


    }
}
