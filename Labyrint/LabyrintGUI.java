import java.io.FileNotFoundException;
import java.io.File;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class LabyrintGUI extends Application{

  public void start(Stage stage) {

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Velg labyrint-fil");
    fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter(".in Files", "*.in"),
         new ExtensionFilter("All Files", "*.*"));

    File fil = fileChooser.showOpenDialog(stage);

    if (fil == null) {
      System.exit(1);
    }

    GridPane brettet = new GridPane();

    Labyrint l = Labyrint.lesFraFil(fil);
    Rute[][] brett = l.hentArray();

    for (int rad = 0; rad < l.hentRader(); rad++) {
      for (int kol = 0; kol < l.hentKolonner(); kol++) {
        final Rute denneRuten = brett[rad][kol];

        if (denneRuten instanceof HvitRute) {

          denneRuten.setOnMouseClicked(new EventHandler<MouseEvent>()
          {
              @Override
              public void handle(MouseEvent t) {

                  //resetter brettet til at alle hvite ruter skal være hvite og
                  //ikke gule
                  for (int r = 0; r < brett.length; r++) {
                    for (int k = 0; k < brett[r].length; k++) {
                      if (brett[r][k] instanceof HvitRute) {
                        brett[r][k].setFill(Color.WHITE);
                      }
                    }
                  }

                  Liste<String> utveier = new Lenkeliste<String>();
                  utveier = l.finnUtveiFra(denneRuten.hentKolonne(), denneRuten.hentRad());
                  denneRuten.setFill(Color.YELLOW);

                  //for å ikke få Nullpointer for å hente første element i listen
                  //hvis den er tom (aka ingen utveier)
                  if(utveier.stoerrelse() > 0) {

                    boolean[][] losning = l.losningStringTilTabell(utveier.hent(0),
                                          l.hentKolonner(), l.hentRader());

                    for (int r = 0; r < l.hentRader(); r++) {
                      for (int k = 0; k < l.hentKolonner(); k++) {
                        if (losning[r][k]) {
                          brett[r][k].setFill(Color.YELLOW);
                        }
                      }
                    }
                  }

              }
          });
        }

        StackPane rute = new StackPane(brett[rad][kol]);
        brettet.add(rute, kol, rad);
      }
    }

    Scene scene = new Scene(brettet);
    stage.setScene(scene);
    stage.setTitle("Labyrint-programmet");
    stage.show();
  }

}
