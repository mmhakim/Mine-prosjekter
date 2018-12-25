import java.util.Random;

/**
 * Klasse som representerer en kanal/telegraflinje hvor det overføres krypterte
 * meldinger.
 */
public final class Kanal {
    private Random r;
    private int antall;
    private int total;
    private String[] meldinger;
    private int id;

    Kanal(String[] meldinger, int id) {
        this.r = new Random();
        this.meldinger = meldinger;
        this.total = meldinger.length;
        this.id = id;
    }

    /**
     * Returnerer kanalens id: et unikt, positivt heltall
     * @return kanalens id
     */
    public int hentId() {
        return id;
    }

    /**
     * <p>Returnerer neste melding. Hvis kanalen er stengt for videre
     * kommunikasjon, returneres <tt>null</tt>.</p>
     *
     * <p><b>NB:</b> Denne metoden er ikke <i>thread-safe</i>!</p>
     * @return neste melding
     */
    public String lytt() {
        if (antall == total) {
            return null;
        }
        try {
            int tid = r.nextInt(100);
            Thread.currentThread().sleep(tid);
        } catch (InterruptedException e) {}

        return Kryptografi.krypter(meldinger[antall++]);
    }
}
