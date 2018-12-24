public class SortRute extends Rute {
  public SortRute(Labyrint l, int rad, int kolonne) {
    super(l, rad, kolonne);
  }

  public char tilTegn() {
    return '#';
  }

  @Override
  public void gaa(Rute forrige, String utvei, Liste<String> utveier) {
    return;
  }
}
