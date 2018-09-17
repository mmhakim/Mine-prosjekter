public abstract class Resept {
  protected static int sId = -1;
  protected int id;
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected Pasient p;
  protected int reit;
  protected boolean gyldighet;


  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.p = p;
    this.reit = reit;
    setGyldighet();
    sId++;
    this.id = sId;
  }

  public void setGyldighet() {
    if (reit > 0) {
      gyldighet = true;
    }
    else {
      gyldighet = false;
    }
  }


  public int hentId() {
    return id;
  }

  public Legemiddel hentLegemiddel() {
    return legemiddel;
  }

  public Lege hentLege() {
    return utskrivendeLege;
  }

  public Pasient hentPasient() {
    return p;
  }

  public int hentReit() {
    return reit;
  }

  public boolean bruk() {
    if (gyldighet) {
      reit--;
      setGyldighet();
      return true;
    }
    else {
      return gyldighet;
    }
  }

  abstract public String farge();

  abstract public double prisAaBetale();

  @Override
  public String toString() {
    return  "\n****** Resept ******" +
            "\nID: " + id +
            "\nLegemiddel: " + legemiddel.hentNavn() +
            "\nUtskrivende lege: " + utskrivendeLege.hentNavn() +
            "\nPasient: " + p.hentNavn() +
            "\nNåværende reit: " + reit +
            "\nPris å betale: " + prisAaBetale() +
            "\nFarge: " + farge();

  }
}
