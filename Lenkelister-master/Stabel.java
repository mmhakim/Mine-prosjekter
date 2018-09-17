//LIFO
public class Stabel<T> extends Lenkeliste<T> {

  //legger til element i slutten av listen (som legg til i Lenkeliste)
  public void leggPaa(T x) {
    leggTil(x);
  }

  //fjerner et element fra slutten av listen (motsatt av Lenkeliste)
  public T taAv() {
    return fjern(stoerrelse()-1);
  }
}
