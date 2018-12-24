import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

abstract class Rute extends Rectangle {

  protected Labyrint l;
  protected int rad, kolonne;
  protected Rute nord, syd, ost, vest;

  public Rute(Labyrint l, int rad, int kolonne) {
    this.setWidth(20);
    this.setHeight(20);
    this.setStroke(Color.BLACK);
    this.l = l;
    this.rad = rad;
    this.kolonne = kolonne;
  }

  public void settNord(Rute n) {
    this.nord = n;
  }

  public void settSyd(Rute s) {
    this.syd = s;
  }

  public void settOst(Rute o) {
    this.ost = o;
  }

  public void settVest(Rute v) {
    this.vest = v;
  }

  public String hentKoordinater() {
    //(x, y) = (kolonne, rad)
    return "(" + kolonne + "," + rad + ")";
  }


  public void gaa(Rute forrige, String utvei, Liste<String> utveier) {
    utvei += hentKoordinater() + " --> ";

    //dead-end
    if (besoktAlle(forrige)) {
      System.out.println("Ingen utveier");
      return;
    }

    if (nord != null && nord != forrige) {
      nord.gaa(this, utvei, utveier);
      // nord.besok();
    }

    if (syd != null && syd != forrige) {
      syd.gaa(this, utvei, utveier);
      // syd.besok();
    }

    if (ost != null && ost != forrige) {
      ost.gaa(this, utvei, utveier);
      // ost.besok();
    }

    if (vest != null && vest != forrige) {
      vest.gaa(this, utvei, utveier);
      // vest.besok();
    }
  }

  //sjekker om alle mulige naboer er besøkt
  public boolean besoktAlle(Rute forrige) {
    if ((nord != null && nord == forrige || nord == null) &&
        (syd != null && syd == forrige || syd == null) &&
        (ost != null && ost == forrige || ost == null) &&
        (vest != null && vest == forrige || vest == null)) {
          return true;
        }
    return false;
  }

  public void finnUtvei(Liste<String> liste) {
    gaa(this, "", liste);
  }

  public int hentRad() {
    return rad;
  }

  public int hentKolonne() {
    return kolonne;
  }

  abstract char tilTegn();
}
