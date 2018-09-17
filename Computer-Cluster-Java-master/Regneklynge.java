import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Regneklynge {
  private ArrayList<Rack> rackliste = new ArrayList<Rack>();
  private int nodePerRack;

  public Regneklynge(String filnavn) {

    File fil = new File(filnavn);

    try {
      Scanner sc = new Scanner(fil);
      nodePerRack = sc.nextInt();
      sc.nextLine();

      int antNoder = 0;
      int minne = 0;
      int proAnt = 0;

      while (sc.hasNextLine()) {

        String[] linje = sc.nextLine().split(" ");
        //linje = linje.split(" ");
        antNoder = Integer.parseInt(linje[0]);
        minne = Integer.parseInt(linje[1]);
        proAnt = Integer.parseInt(linje[2]);

        for (int i = 0; i < antNoder; i++) {
          Node n = new Node(minne, proAnt);
          leggTil(n);
        }
      }
    }

    catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
  }


  public void leggTil(Node node) {
    for (Rack r : rackliste) {
      if (!r.erFull()) {
        r.leggTil(node);
        return;
      }
    }

    //hvis det ikke finnes et ledig rack
    Rack r = new Rack(nodePerRack);
    r.leggTil(node);
    rackliste.add(r);
  }

  public int antProsessorer() {
    int antPro = 0;
    for (Rack r : rackliste) {
      antPro += r.antProsessorer();
    }

    return antPro;
  }

  public int noderMedNokMinne(int paakrevdMinne) {
    int nokMinne = 0;

    for (Rack r : rackliste) {
      nokMinne += r.nokMinne(paakrevdMinne);
    }

    return nokMinne;

  }


  public int hentAntRack() {
    return rackliste.size();
  }


}
