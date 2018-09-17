public class LegemiddelB extends Legemiddel {
  private int styrke;

  public LegemiddelB(String navn, double pris, double virkestoff, int styrke) {
    super(navn, pris, virkestoff);
    this.styrke = styrke; //hvor vanedannende det er
  }

  public int hentVanedannendeStyrke() {
    return styrke;
  }

  @Override
  public String toString() {
    return super.toString() +
           "\nVanedannende styrke: " + styrke;
  }
}
