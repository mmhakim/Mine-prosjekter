import java.util.ArrayList;

public class Hovedprogram {
  public static void main(String[] args) {

    int maksAntallTelegrafister = 3;
    Operasjonssentral ops = new Operasjonssentral(maksAntallTelegrafister);
    Kanal[] kanaler = ops.hentKanalArray();

    int antallTelegrafisterViTrenger = kanaler.length;

    Monitor1 m1 = new Monitor1(antallTelegrafisterViTrenger);
    Monitor2 m2 = new Monitor2();

    ArrayList<Thread> traader = new ArrayList<>();

    //lager telegrafister
    for(int i= 0; i<kanaler.length; i++) {
      Thread t = new Thread(new Telegrafist(kanaler[i], m1));
      traader.add(t);
      t.start();
    }

    //lager kryptografer
    for (int i = 0; i < 20; i++) {
      Thread t1 = new Thread(new Kryptograf(m1, m2));
      t1.start();
      traader.add(t1);
    }

    //sjekker at telegrafistene og kryptografene er ferdige
    for (Thread t : traader) {
      try {
        t.join();
      }
      catch (InterruptedException e) {}
    }
    System.out.println("Trådene er ferdige");

    //operasjonslederen starter jobben sin etter at kryptografene og telegrafistene er ferdige
    Thread tt = new Thread(new Operasjonsleder(m2));
    tt.start();

    try {
      tt.join();
    }
    catch (InterruptedException e) {}

    System.out.println("Meldingene er skrevet til de forskjellige kanalfilene.");
  }
}
