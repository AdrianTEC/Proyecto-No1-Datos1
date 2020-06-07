package MiniJuegos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Jugador;
import sample.Partida;



public class CowBoys extends Application {

    private Jugador px1;
    private Jugador px2;
    private Boolean timeForShoot= false;
    private ImageView flag= new ImageView("Imagenes/Minijuegos/flag.png");
    private Pane root = new Pane();

/*    public CowBoys(Jugador px1, Jugador px2) {
        this.px1 = px1;
        this.px2 = px2;
    }
*/
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
        ImageView fondo= new ImageView("Imagenes/Minijuegos/Dessert.png");

        //creo los vaqueros
        Vaquero v1 = new Vaquero(100, 300,-10,new Image("Imagenes/Minijuegos/C1.png"));
        Vaquero v2 = new Vaquero(1000,300,+10,new Image("Imagenes/Minijuegos/C2.png"));

        ImageView k= new ImageView("Imagenes/Minijuegos/k.png");
        k.setLayoutX(1000);
        ImageView s= new ImageView("Imagenes/Minijuegos/S.png");
        s.setLayoutX(100);



        flag.setLayoutX(500);
        flag.setFitHeight(200);
        flag.setFitWidth(200);


        stage.setResizable(false);
        stage.setTitle("Cowboys");
        stage.setScene(new Scene(root, 1338, 538));
        //AGREGO LAS COSAS
        root.getChildren().addAll(fondo,v1.imagencita(),v2.imagencita(),v1.Sombrero(),v2.Sombrero(),s,k);
        root.setStyle("-fx-background-color: #202f4a");

        stage.show();
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()




        {   @Override
        public void handle(KeyEvent evt)
        {
            Label vic= new Label();
            vic.setLayoutX(300);
            vic.setLayoutY(100);
            vic.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
            if (evt.getCode().equals(KeyCode.S)) {

                if(timeForShoot)
                {                Partida.reproducirSonido("shoot");

                    timeForShoot=false;
                    v2.dead();
                    vic.setText("El jugador :"+ px1.getNombre() +" ha ganado!!");
                    px1.setEstrellas(px1.getMonedas()+1);

                }


            }
            if (evt.getCode().equals(KeyCode.K)) {

                if(timeForShoot)
                {                Partida.reproducirSonido("shoot");

                    timeForShoot=false;

                    v1.dead();
                    vic.setText("El jugador :"+ px2.getNombre() +" ha ganado!!");
                    px2.setEstrellas(px1.getMonedas()+1);

                }


            }


            root.getChildren().add(vic);



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

        }  });


        stage.setOnCloseRequest(event -> { System.exit(1);});
        new java.util.Timer().schedule(

                new java.util.TimerTask() {
                    @Override
                    public void run() {


                    shoot();


                    }
                },
                1000
        );


    }
}