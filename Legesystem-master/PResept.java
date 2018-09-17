public class PResept extends HvitResept {

  public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
    super(legemiddel, utskrivendeLege, p, reit);
    this.reit = 3;
  }

  @Override
  public double prisAaBetale() {
    if (legemiddel.hentPris() < 116) {
      return 0;
    }
    else {
      return legemiddel.hentPris() - 116;
    }
  }
}
