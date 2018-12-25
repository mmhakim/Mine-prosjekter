//implementerer Comparable for å senere kunne sortere etter sekvensnummer
public class Melding implements Comparable<Melding> {
  private int kanalId;
  private int sekvensnr;
  private String mld;

  public Melding(int kanalId, int sekvensnr, String mld) {
    this.kanalId = kanalId;
    this.sekvensnr = sekvensnr;
    this.mld = mld;
  }

  public int hentSekvensnr() {
    return sekvensnr;
  }

  public int hentKanalId() {
    return kanalId;
  }

  public String hentMld() {
    return mld;
  }

  //brukes av kryptografen når en melding blir dekryptert
  public void endreMld(String nyMld) {
    mld = nyMld;
  }

  @Override
  public int compareTo(Melding andreMld) {
    int b = andreMld.hentSekvensnr();

    if (sekvensnr < b) {
      return -1;
    }

    if (sekvensnr > b) {
      return 1;
    }

    return 0;
  }

  @Override
  public String toString() {
    return "kanalid: " + kanalId + ", sekvens: " + sekvensnr;
  }

}
