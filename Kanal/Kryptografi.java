/**
 * Klasse med metode for dekryptering av meldinger.
 */
public class Kryptografi {
    private static int offset = 3;
    private static int VENTETID_PER_BOKSTAV = 10;

    private Kryptografi() {}

    static String krypter(String s) {
        //String s = m.s;
        char[] klartekst = s//.replace(" ", "").toUpperCase()
                            .toCharArray();
        int antallTegn = klartekst.length;
        int n = finnN(antallTegn);
        char[] chiffertekst = new char[antallTegn];

        int initiell, j=0;
        for (int runde=0; runde < n; runde++) {
            initiell = runde;
            for (int i = initiell; i < antallTegn; i += n) {
                chiffertekst[i] = klartekst[j++];
            }
        }
        return new String(chiffertekst);
    }

    /**
     * Dekrypterer innholdet i <tt>s</tt> og returnerer det.
     * @param s     en kryptert streng
     * @return      det dekrypterte innholdet i strengen
     */
    public static String dekrypter(String s) {
        //String s = m.s;
        char[] chiffertekst = s.toCharArray();
        int antallTegn = chiffertekst.length;
        int n = finnN(antallTegn);
        char[] klartekst = new char[antallTegn];
        int initiell, j=0;
        for (int runde=0; runde < n; runde++) {
            initiell = runde;

            for (int i = initiell; i < antallTegn; i += n) {
                klartekst[j++] = chiffertekst[i];
                try {
                    Thread.sleep(VENTETID_PER_BOKSTAV);
                } catch (InterruptedException e) {

                }
                // bør legge inn litt ekstra venting her
            }
        }
        /*
        System.out.printf("Kryptert: '%s'. Dekryptert: '%s'\n",
                          m.s, new String(klartekst));
        */
        return new String(klartekst);
    }

    private static int finnN(int antallTegn) {
        return (int) Math.sqrt(antallTegn);
    }
}
