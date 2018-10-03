class Lege implements Comparable<Lege>{
  private String navn;
  private Lenkeliste<Resept> reseptliste = new Lenkeliste<>();

  public Lege(String navn) {
    this.navn = navn;
  }

  public String hentNavn() {
    return navn;
  }

  @Override
  public String toString() {
    return  "\n****** Lege ******" +
            "\nNavn: " + navn;
  }

  @Override
  public int compareTo(Lege andreLege) {
    return this.navn.compareTo(andreLege.hentNavn());
  }

  public void leggTilResept(Resept r) {
    reseptliste.leggTil(r);
  }

  public Lenkeliste<Resept> hentReseptliste() {
    return reseptliste;
  }

}
