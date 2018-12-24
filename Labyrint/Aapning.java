public class Aapning extends HvitRute {
  public Aapning(Labyrint l, int rad, int kolonne) {
    super(l, rad, kolonne);
  }


  // hvis det er en åpning så skal rekursjonen avsluttes
  @Override
  public void gaa(Rute forrige, String utvei, Liste<String> utveier) {
    utvei += hentKoordinater();

    //for å ikke legge til samme utvei flere ganger
    for (String s : utveier) {
      if (s.equals(utvei)){
        return;
       }
    }

    utveier.leggTil(utvei);
  }
}
