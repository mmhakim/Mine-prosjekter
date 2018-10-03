class HvitResept extends Resept {

  public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
    super(legemiddel, utskrivendeLege, p, reit);
  }

  @Override
  public String farge() {
    return "hvit";
  }

  @Override
  public double prisAaBetale() {
    return legemiddel.hentPris();
  }
}
