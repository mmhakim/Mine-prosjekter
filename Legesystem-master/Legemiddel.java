public class Legemiddel implements Comparable<Legemiddel>{
  protected static int sId = -1;
  protected int id;
  protected String navn;
  protected double pris;
  protected double virkestoff;


  public Legemiddel(String navn, double pris, double virkestoff) {
    sId++;
    this.id = sId;
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }

  public int hentId() {
    return id;
  }

  public String hentNavn() {
    return navn;
  }

  public double hentPris() {
    return pris;
  }

  public double hentVirkestoff() {
    return virkestoff;
  }

  public void settNyPris(double nyPris) {
    pris = nyPris;
  }

  @Override
  public String toString() {
    return "\n****** Legemiddel ******" +
           "\nID: " + id +
           "\nNavn: " + navn +
           "\nPris: " + pris + "kr" +
           "\nVirkestoff: " + virkestoff + "mg";
  }

  //sorteres etter id
  @Override
  public int compareTo(Legemiddel andreLegemiddel) {
    if (id > andreLegemiddel.hentId()) return 1;
    else if (id < andreLegemiddel.hentId()) return -1;
    else return 0;
  }

}
