
//dekrypterer krypterte meldinger som blir hentet av Monitor1
public class Kryptograf implements Runnable{
  private Monitor1 m1;
  private Monitor2 m2;

  public Kryptograf(Monitor1 m1, Monitor2 m2) {
    this.m1 = m1;
    this.m2 = m2;
  }

  //en kryptograf krypterer 1 melding og sender den til Monitor2
  public void run() {
    System.out.println("Kryptografen starter å jobbe");

    try {

      //henter meldinger inntil alle meldinger er mottat fra telegrafistene
      while (!(m1.erTom() && m1.teleFerdig())) {
        Melding hentetMld = m1.taUtMld();
        if(hentetMld.hentSekvensnr() != -1) {
          hentetMld.endreMld(Kryptografi.dekrypter(hentetMld.hentMld()));
          m2.settInnMld(hentetMld);
          System.out.println("Kryptografen har dekyptert en melding og sendt den til Monitor2");
        }
      }

    } catch (InterruptedException e) {}
    System.out.println("Kryptografen er ferdig med jobben sin");
  }
}
