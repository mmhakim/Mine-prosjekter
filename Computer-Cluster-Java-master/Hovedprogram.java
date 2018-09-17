class Hovedprogram {
  public static void main(String[] args) {

    Regneklynge abel = new Regneklynge("regneklynge.txt");

    System.out.println("Noder med minst 32 GB: " + abel.noderMedNokMinne(32));
    System.out.println("Noder med minst 64 GB: " + abel.noderMedNokMinne(64));
    System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));
    System.out.println("Antall prosessorer: " + abel.antProsessorer());
    System.out.println("Antall rack: " + abel.hentAntRack());


/*
FASIT: 
Noder med minst 32 GB: 666
Noder med minst 64 GB: 666
Noder med minst 128 GB: 16
Antall prosessorer: 682
Antall rack: 56
*/

  }
}
