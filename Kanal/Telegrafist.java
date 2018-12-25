public class Telegrafist implements Runnable {
  private Monitor1 m;
  private Kanal kanal;

  public Telegrafist(Kanal kanal, Monitor1 m) {
    this.m = m;
    this.kanal = kanal;
  }

  public void run() {
    try {
      System.out.println("Telegrafisten starter å lytte til sin kanal");
      int kanalId = kanal.hentId();
      String mld = kanal.lytt();

      //siden hver telegrafist lytter til hver kanal trenger jeg ikke å lage
      //en statisk int i Melding-klassen for å inkrementere sekvensnummeret
      int sekvensnr = 0;

      while (mld != null) {
        Melding melding = new Melding(kanalId, sekvensnr, mld);
        sekvensnr++;
        mld = kanal.lytt();
        m.settInnMld(melding);
      }

      //når telegrafisten er ferdig, sendes en melding som signaliserer dette
      Melding slutt = new Melding(kanalId, -1, "Slutt");
      m.settInnMld(slutt);

    } catch (InterruptedException e) {}
    System.out.println("Telegrafisten er ferdig med jobben sin");
  }
}
