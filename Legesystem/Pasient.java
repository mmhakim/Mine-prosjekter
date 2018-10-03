class Pasient {

  public static int sId = 0;
  private int id;
  private String navn;
  private String fnr;
  private Stabel<Resept> reseptliste = new Stabel<>();

  public Pasient(String navn, String fnr) {
    this.navn = navn;
    this.fnr = fnr;
  }

  public void leggTilResept(Resept r) {
    reseptliste.leggPaa(r);
  }

  public String toString() {
    return  "\n****** Pasient ******" +
            "\nNavn: " + navn +
            "\nId:" + id +
            "\nFødselsnummer: " + fnr;
  }

  public int hentId() {
    return id;
  }

  public String hentNavn() {
    return navn;
  }

  public String hentFnr() {
    return fnr;
  }

  public Stabel<Resept> hentReseptliste() {
    return reseptliste;
  }
}
