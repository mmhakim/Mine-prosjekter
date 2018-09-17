class Militaerresept extends HvitResept {
  public Militaerresept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
    super(legemiddel, utskrivendeLege, p, reit);
  }

  @Override
  public double prisAaBetale() {
    return legemiddel.hentPris() * 0; //100% rabatt
  }
}
