/**
 * Klasse som oppretter <tt>Kanal</tt>-objektene og lar dem bli hentet ut..
 */
public class Operasjonssentral {
    private static int MAKS_KANALER = Tekster.ANTALL_TEKSTER;
    private int antallKanaler;

    /**
     * Oppretter en ny operasjonssentral med inntil <tt>maksKanaler</tt>
     * kanaler.
     * @param maksKanaler maksimalt antall kanaler
     */
    public Operasjonssentral(int maksKanaler) {
        if (maksKanaler <= Tekster.ANTALL_TEKSTER) {
            antallKanaler = maksKanaler;
        } else {
            System.out.printf("Kan ikke lytte på mer enn %d kanaler\n",
                              MAKS_KANALER);
            antallKanaler = MAKS_KANALER;
        }
    }

    /**
     * Returnerer antall kanaler
     * @return antall kanaler
     */
    public int hentAntallKanaler() {
        return antallKanaler;
    }

    /**
     * Returnerer et array med kanalene det lyttes på.
     * @return array med kanalene
     */
    public Kanal[] hentKanalArray() {
        Kanal[] kanalene = new Kanal[antallKanaler];
        for (int i = 0; i < antallKanaler; i++) {
            kanalene[i] = new Kanal(Tekster.tekster[i], i+1);
        }
        return kanalene;
    }
}
