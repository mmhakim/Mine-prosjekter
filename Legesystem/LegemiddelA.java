public class LegemiddelA extends Legemiddel {
  private int styrke;

  public LegemiddelA (String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke; //hvor narkotisk det er
  }

  public int hentNarkotiskStyrke() {
    return styrke;
  }

  @Override
  public String toString() {
    return super.toString() +
           "\nNarkotisk styrke: " + styrke;
  }
}
