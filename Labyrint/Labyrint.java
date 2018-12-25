import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Labyrint {
  private Rute[][] ruter;
  private int antRader;
  private int antKolonner;
  private Lenkeliste<String> utveier = new Lenkeliste<>();

  private Labyrint(Rute[][] ruter, int antRader, int antKolonner) {
    this.ruter = ruter;
    this.antRader = antRader;
    this.antKolonner = antKolonner;
  }


  public static Labyrint lesFraFil(File fil) {

    try {
      Scanner sc = new Scanner(fil);
      int antRader = sc.nextInt();
      int antKolonner = sc.nextInt();
      System.out.println(sc.nextLine());

      //for å gjøre livet enklere når jeg skal tilordne koordinater til rutene
      Rute[][] r1 = new Rute[antRader+2][antKolonner+2];

      //den faktiske labyrinten:
      Rute[][] ruter = new Rute[antRader][antKolonner];

      Labyrint l = new Labyrint(ruter, antRader, antKolonner);

      char[] linje;

      //lager rutene og legger dem til i r1-2Darrayet
      for (int r = 0; r < antRader; r++) {
        linje = sc.nextLine().toCharArray();
        for (int k = 0; k < antKolonner; k++) {
          char denne = linje[k];

          //hvis den hvite ruten befinner seg i kanten av labyrinten, lager vi Aapning
          if (denne == '.' && (k == 0 || k == antKolonner-1 || r == 0 || r == antRader-1)) {
            //Labyrint l, int rad, int kolonne
            Aapning a = new Aapning(l, r, k);
            r1[r+1][k+1] = a;
          }

          //hvis det ikke er i kanten av labyrinten
          else {
            if (denne == '.') {
              HvitRute h = new HvitRute(l, r, k);
              r1[r+1][k+1] = h;
            }

            else if (denne == '#') {
              SortRute s = new SortRute(l, r, k);
              r1[r+1][k+1] = s;
            }
          }
        }
      }

      //gir rutene deres koordinater og legger rutene til i den ordentlige labyrinten:
      for (int r = 1; r < antRader+1; r++) {
        for (int k = 1; k < antKolonner+1; k++) {
          ruter[r-1][k-1] = r1[r][k];

          if (r1[r-1][k] != null) {
            r1[r][k].settNord(r1[r-1][k]);
          }

          if (r1[r+1][k] != null) {
            r1[r][k].settSyd(r1[r+1][k]);
          }

          if (r1[r][k+1] != null) {
            r1[r][k].settOst(r1[r][k+1]);
          }

          if (r1[r][k-1] != null) {
            r1[r][k].settVest(r1[r][k-1]);
          }
        }
      }
      return l;
    }
    catch (FileNotFoundException e) {
      return null;
    }
  }



  @Override
  public String toString() {
    String s = "";

    for(int r = 0; r < ruter.length; r++) {
      s = s + "\n";
      for (int k = 0; k < ruter[r].length; k++) {
        if (ruter[r][k] != null)
          s = s + ruter[r][k].tilTegn();

        else {
          s = s + "n";
        }
      }
    }
    return s;
  }


  public Liste<String> finnUtveiFra(int kol, int rad) {
      //ser at listen blir nullstillt i eksempelkallet i oppgaven, så gjør det her
      utveier = new Lenkeliste<String>();

      ruter[rad][kol].finnUtvei(utveier);
      return utveier;
  }

  //ekstra hjelpemetode
  public static String arrayTilString(Rute[][] arr) {
    String s = "";

    for(int r = 0; r < arr.length; r++) {
      s = s + "\n";
      for (int k = 0; k < arr[r].length; k++) {
        if (arr[r][k] != null)
          s = s + arr[r][k].tilTegn();

        else {
          s = s + "n";
        }
      }
    }
    return s;
  }

  public int hentRader() {
    return antRader;
  }

  public int hentKolonner() {
    return antKolonner;
  }

  public Rute[][] hentArray() {
    return ruter;
  }

  /**
 * Konverterer losning-String til en boolean[][]-representasjon
 * av losningstien.
 * @param losningString String-representasjon av utveien
 * @param bredde        bredde til labyrinten
 * @param hoyde         hoyde til labyrinten
 * @return              2D-representasjon av rutene der true indikerer at
 *                      ruten er en del av utveien.
 */
  static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
      boolean[][] losning = new boolean[hoyde][bredde];
      java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
      java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
      while (m.find()) {
          int x = Integer.parseInt(m.group(1));
          int y = Integer.parseInt(m.group(2));
          losning[y][x] = true;
      }
      return losning;
  }

}
