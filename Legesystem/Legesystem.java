import java.util.Scanner;
import java.util.InputMismatchException;

public class Legesystem {
//skal holde styr på flere lister med info om legemidler, resepter, leger
//og pasienter
  public static Scanner inn = new Scanner(System.in);


  // //SortertLenkeliste for leger fordi de skal være sortert alfabetisk
  public static SortertLenkeliste<Lege> leger = new SortertLenkeliste<>();

  //Lenkeliste for Pasienter fordi den første pasienten i køen blir behandlet først
  public static Lenkeliste<Pasient> pasienter = new Lenkeliste<>();

  //legemidler sorteres etter id
  public static SortertLenkeliste<Legemiddel> legemidler = new SortertLenkeliste<>();

  public static Lenkeliste<Resept> resepter = new Lenkeliste<>();

  //gjør variabelen global slik at man kan forlate kommandoløkken hvor enn man
  //er i legesystemet
  public static String input = " ";


  public static void main(String[] args) {
    kommandolokke();
  }


  public static void kommandolokke() {
    System.out.println("\n*********** Legesystem ***********");

    while (!input.equals("q")) {
      System.out.println("\n\nHovedmeny:");
      System.out.println("For å:\n\t- skrive ut oversikter, tast \"o\"");
      System.out.println("\t- Opprette og legge til nye elementer, tast \"l\"");
      System.out.println("\t- Bruke en gitt resept fra listen til en pasient, tast \"r\"");
      System.out.println("\t- Skrive ut forskjellige former for statistikk, tast \"s\"");
      System.out.println("\t- Skrive all data til fil, tast \"d\"");
      System.out.println("\t- Avslutte legesystemet, tast \"q\"");

      input = inn.nextLine();

      if (input.equals("o")) {
        oversikter();
      }

      if (input.equals("l")) {
        leggeTil();
      }

      if (input.equals("r")) {
        brukResept();
      }

      if (input.equals("s")) {
      statistikk();
      }

    }

//uh ias
  }

  public static void oversikter() {
    System.out.println("************ OVERSIKT OVER LEGENE: ************");
    for (Lege l : leger) {
      System.out.println(l);
      System.out.println("\n--- Legens reseptliste: ---");
      for (Resept r : l.hentReseptliste()) {
        System.out.println(r);
      }
      System.out.println("\n\n");
    }

    System.out.println("************ OVERSIKT OVER PASIENTENE: ************");
    for (Pasient p : pasienter) {
      System.out.println(p);
      System.out.println("\n--- Pasientens reseptliste: ---");
      for (Resept r : p.hentReseptliste()) {
        System.out.println(r);
      }
      System.out.println("\n\n");
    }

    System.out.println("************ OVERSIKT OVER LEGEMIDLENE: ************");
    for (Legemiddel l : legemidler) {
      System.out.println(l);
    }
  }

  public static void leggeTil() {
    System.out.println("Ønkser du å legge til:\n\t- lege, tast \"l\"");
    System.out.println("\t- pasient, tast \"p\"\n\t- legemiddel, tast \"lm\"");
    System.out.println("\t- resept, tast \"r\"");
    String ny = inn.nextLine();

    if (ny.equals("l")) {
      System.out.println("Hva er legens navn?");
      Lege nylege = new Lege(inn.nextLine());
      leger.leggTil(nylege);
    }

    else if (ny.equals("p")) {
      System.out.println("Hva er pasientens navn?");
      String navn = inn.nextLine();
      System.out.println("Hva er pasientens fødselsnummer?");
      String fnr = inn.nextLine();

      Pasient nypasient = new Pasient(navn, fnr);
      pasienter.leggTil(nypasient);
    }

    else if (ny.equals("lm")) {

      try {
        System.out.println("Hva slags type legemiddel er det?");
        System.out.println("Er det:\n\t- LegemiddelA: tast \"a\"");
        System.out.println("\t- LegemiddelB: tast \"b\"");
        System.out.println("\t- LegemiddelC: tast \"c\"");

        String legemiddel = inn.nextLine();

        if (legemiddel.equals("a")) {
          //String navn, double pris, double virkestoff, int styrke

          System.out.println("Hva heter legemidlet?");
          String navn = inn.nextLine();

          System.out.println("Hva er dens pris?");
          double pris = inn.nextDouble();

          System.out.println("Hvor mange mg inneholder den?");
          double virkestoff = inn.nextDouble();

          System.out.println("Hva er dens narkotiske styrke?");
          int styrke = inn.nextInt();

          LegemiddelA la = new LegemiddelA(navn, pris, virkestoff, styrke);
          legemidler.leggTil(la);

        }

        else if (legemiddel.equals("b")) {
          //String navn, double pris, double virkestoff, int styrke

          System.out.println("Hva heter legemidlet?");
          String navn = inn.nextLine();

          System.out.println("Hva er dens pris?");
          double pris = inn.nextDouble();

          System.out.println("Hvor mange mg inneholder den?");
          double virkestoff = inn.nextDouble();

          System.out.println("Hva er dens vanedannende styrke?");
          int styrke = inn.nextInt();

          LegemiddelB lb = new LegemiddelB(navn, pris, virkestoff, styrke);
          legemidler.leggTil(lb);


        }

        else if (legemiddel.equals("c")) {
          //String navn, double pris, double virkestoff

          System.out.println("Hva heter legemidlet?");
          String navn = inn.nextLine();

          System.out.println("Hva er dens pris?");
          double pris = inn.nextDouble();

          System.out.println("Hvor mange mg inneholder den?");
          double virkestoff = inn.nextDouble();

          LegemiddelC lc = new LegemiddelC(navn, pris, virkestoff);
          legemidler.leggTil(lc);

        }
      }

      catch (NumberFormatException|InputMismatchException e) {
        System.out.println("Du skrev ikke inn et tall");
      }
    }

    else if (ny.equals("r")) {
      //Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit
      System.out.println("Hvilket legemiddel ønsker du å lage resept til?");
      String navn = inn.nextLine();

      Legemiddel legemiddel = null;
      Lege utskrivendeLege = null;
      Pasient p = null;

      for (Legemiddel lm : legemidler) {
        if (lm.hentNavn().equals(navn)) {
          legemiddel = lm;
        }
      }

      System.out.println("Hva er navnet til legen som skriver ut resepten?");
      navn = inn.nextLine();

      for (Lege l : leger) {
        if (l.hentNavn().equals(navn)) {
          utskrivendeLege = l;
        }
      }

      System.out.println("Hva heter pasienten resepten skrives til?");
      navn = inn.nextLine();

      for (Pasient pa : pasienter) {
        if (pa.hentNavn().equals(navn)) {
          p = pa;
        }
      }

      if (legemiddel == null) System.out.println("Legemiddelet du la inn finnes ikke i systemet");
      if (utskrivendeLege == null) System.out.println("Legen du la inn finnes ikke i systemet");
      if (p == null) System.out.println("Pasienten du la inn finnes ikke i systemet");

      if (legemiddel != null && utskrivendeLege != null && p != null) {
        System.out.println("Hva er reiten til resepten?");

        // try-catch for hvis brukeren setter inn noe annet enn et heltall for reit
        try {
          int reit = inn.nextInt();
          System.out.println("Reiten: " + reit);
          System.out.println("Hvilke type er resepten? For:\n\t- Blå-resept, tast \"b\"");
          System.out.println("\t- Hvit-resept, tast \"h\"\n\t- P-resept, tast \"p\"");
          inn.nextLine();
          String rtype = inn.nextLine();
          System.out.println("Rtypeee: " + rtype);

          if (rtype.equals("b")) {
            BlaaResept br = new BlaaResept(legemiddel, utskrivendeLege, p, reit);
            utskrivendeLege.leggTilResept(br);
            p.leggTilResept(br);
            resepter.leggTil(br);
          }

          else if (rtype.equals("h")) {
            HvitResept hr = new HvitResept(legemiddel, utskrivendeLege, p, reit);
            utskrivendeLege.leggTilResept(hr);
            p.leggTilResept(hr);
            resepter.leggTil(hr);
          }

          else if (rtype.equals("p")) {
            PResept pr = new PResept(legemiddel, utskrivendeLege, p, reit);
            utskrivendeLege.leggTilResept(pr);
            p.leggTilResept(pr);
            resepter.leggTil(pr);
          }

          else {
            System.out.println("Du skrev inn feil bokstav");
          }
        }

        catch (NumberFormatException|InputMismatchException e) {
          System.out.println("Du skrev inn feil type input");
        }
      }
    }

    else if (ny.equals("q")) {
      input = "q";
    }


    else {
      System.out.println("Du tastet inn noe feil.");
    }
  }


  public static void brukResept() {
    System.out.println("Hva heter pasienten som ønsker å bruke resept?");
    String navn = inn.nextLine();

    System.out.println("Hva er pasientens fødselsnummer?");
    String fnr = inn.nextLine();

    Pasient pasient = null;

    for (Pasient p : pasienter) {
      if (p.hentNavn().equals(navn) && p.hentFnr().equals(fnr)) {
        pasient = p;
      }
    }

    if (pasient != null) {
      System.out.println("Hvilken resept ønsker du å bruke?");

      for (Resept r : pasient.hentReseptliste()) {
        System.out.println(r.hentId() + ": " + r.hentLegemiddel().hentNavn() +
                            " (reit: " + r.hentReit() + ")");
      }

      //hvis brukeren skriver inn noe annet enn et tall for id
      try {
        int id = inn.nextInt();

        for (Resept r : pasient.hentReseptliste()) {
          if (r.hentId() == id) {
            r.bruk();
          }
        }
      }
      catch (NumberFormatException|InputMismatchException e) {
        System.out.println("Du må skrive inn et tall");
      }
    }

    else {
      System.out.println("Pasienten finnes ikke i systemet");
    }
  }


  public static void statistikk() {

    //utskrevne resepter på vanedannende legemidler
    int vl = 0;

    //utskrevne til militære
    int ml = 0;

    for (Resept r : resepter) {
      if (r.hentLegemiddel() instanceof LegemiddelB) {
         vl++;
         if (r instanceof PResept) ml++;
      }
    }

    System.out.println("\n\n**** Statistikk ****");
    System.out.println("Antall utskrevene resepter på vanedannende legemidler: " + vl);
    System.out.println("Antall vanedannende resepter utskrevne til militære: " + ml);
    System.out.println("\nStatistikk om mulig misbruk av narkotika: ");
    System.out.println("\tLeger som har skrevet ut minst en narkotisk resept:");

    for (Lege l : leger) {
      int antR = 0;
      for (Resept r : l.hentReseptliste()) {
        if (r.hentLegemiddel() instanceof LegemiddelB) antR++;
      }

      if (antR > 0) {
        System.out.println("\t\t"+l.hentNavn() + " (antall utskrevne narkotiske resepter: "
                                          + antR + ")");
      }
    }

    System.out.println("\n\tPasienter som har minst en gyldig resept på narkotiske legemidler:");

    for (Pasient p : pasienter) {
      int antR = 0;
      for (Resept r : p.hentReseptliste()) {
        if (r.hentLegemiddel() instanceof LegemiddelB && r.hentReit() > 0) {
          antR++;
        }
      }

      if (antR > 0) {
        System.out.println("\t\t" +p.hentNavn() + " (gyldige resepter på narkotiske legemidler: )"
                                            + antR);
      }
    }

  }
}
