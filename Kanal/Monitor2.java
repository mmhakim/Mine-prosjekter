import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

public class Monitor2 {
  private ArrayList<Melding> dekrypterteMld = new ArrayList<>();
  private Lock laas = new ReentrantLock();
  private Condition ikkeTomListe = laas.newCondition();

  public void settInnMld(Melding mld) throws InterruptedException {
    laas.lock();

    try {
      System.out.println("Kryptografen setter inn melding i Monitor2");
      dekrypterteMld.add(mld);
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

      if(dekrypterteMld.isEmpty()) {
        return null;
      }
      
      System.out.println("Operasjonslederen tar ut melding fra Monitor2");
      return dekrypterteMld.remove(0);
    }

    finally {
      laas.unlock();
    }
  }

  //metode som brukes av operasjonslederen
  public boolean erTom() {
    return dekrypterteMld.isEmpty();
  }

  public void printMeldinger() {
    for (Melding m : dekrypterteMld) {
      System.out.println(m);
    }
  }
}
