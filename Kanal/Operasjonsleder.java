import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Operasjonsleder implements Runnable {
  Monitor2 m;
  ArrayList<SortertLenkeliste<Melding>> kanalmeldinger = new ArrayList<>();

  public Operasjonsleder(Monitor2 m) {
    this.m = m;

  }

  //kun 1 operasjonsleder = 1 tråd
  //kalles etter at alle trådene blir ferdige
  public void run() {
    try {
      System.out.println("Operasjonslederen starter jobben");
      Melding mld;

      while(!(m.erTom())) {
        mld = m.taUtMld();

        //for å ikke få OutOfBoundException
        if (mld.hentKanalId() != -1) {

          //for å få en SortertLenkeliste per kanal
          while(mld.hentKanalId() >= kanalmeldinger.size()) {
            kanalmeldinger.add(new SortertLenkeliste<Melding>());
          }

          kanalmeldinger.get(mld.hentKanalId()).leggTil(mld);
        }
      }

      //etter at alle medlinger er hentet og sortert, skrives meldingene til fil
      skrivTilFil();

    } catch (InterruptedException e) {}
    System.out.println("Operasjonslederen er ferdig med jobben sin");
  }

  //kalles etter at alle trådene blir ferdige
  public void skrivTilFil() {
    try {

      for (SortertLenkeliste<Melding> s : kanalmeldinger) {
        if (s.stoerrelse() != 0) {
          File utfil = new File("kanal" + s.hent(0).hentKanalId() + ".txt");
          PrintWriter writer = new PrintWriter(utfil, "utf-8");

          for(Melding m : s) {
            //System.out.println(m);
            writer.println(m.hentMld());
          }

          writer.close();
        }
      }
    }
    catch (FileNotFoundException|UnsupportedEncodingException e) {}
  }

}
