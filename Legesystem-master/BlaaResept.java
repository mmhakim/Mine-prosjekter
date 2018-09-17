class BlaaResept extends Resept{

  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
    super(legemiddel, utskrivendeLege, p, reit);
  }

  @Override
  public String farge() {
    return "blaa";
  }

  @Override
  public double prisAaBetale() {
    return legemiddel.hentPris() * 0.25;
  }

}
