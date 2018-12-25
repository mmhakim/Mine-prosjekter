import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

public class Monitor1 {
  private int sluttmelding = 0;
  private int antKanaler;
  private ArrayList<Melding> krypterteMld = new ArrayList<>();
  private Lock laas = new ReentrantLock();
  private Condition ikkeTomListe = laas.newCondition();

  public Monitor1(int antKanaler) {
    this.antKanaler = antKanaler;
  }

  public void settInnMld(Melding mld) throws InterruptedException {
    laas.lock();

    try {
      //hvis det er en sluttmelding
      if (mld.hentSekvensnr() == -1) {
        sluttmelding++;
      }
      System.out.println("Telegrafisten setter inn melding i Monitor1");
      krypterteMld.add(mld);
      ikkeTomListe.signalAll();
    }

    finally {
      laas.unlock();
    }
  }

  //returnerer en Melding fra ArrayListen med krypterte meldinger
  public Melding taUtMld() throws InterruptedException {
    laas.lock();

    try {
      while(krypterteMld.isEmpty()) {
        System.out.println("Kryptografen venter på melding");
        ikkeTomListe.await();
      }
      System.out.println("Kryptografen tar ut melding fra Monitor1");
      return krypterteMld.remove(0);
    }

    finally {
      laas.unlock();
    }
  }

  //metode som brukes av kryptografene som viser når telegrafistene har gjort jobben sin
  public boolean teleFerdig() {
    return sluttmelding == antKanaler;
  }

  //metode som brukes av kryptografene
  public boolean erTom() {
    return krypterteMld.isEmpty();
  }
}
