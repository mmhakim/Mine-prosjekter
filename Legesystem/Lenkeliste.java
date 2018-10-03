import java.util.Iterator;

//FIFO
public class Lenkeliste<T> implements Liste<T> {
//noder legges til i slutten av lenkelisten

  protected Node start;


  protected class Node {
    public Node neste;
    public T verdi;

    public Node(T verdi) {
      this.verdi = verdi;
    }
  } //slutt av intern Node klasse

  @Override
  public int stoerrelse() {
    int teller = 0;
    Node tmp = start;

    while (tmp != null) {
      tmp = tmp.neste;
      teller++;
    }
    return teller;
  }

  //legger til element i en gitt posisjon og skyve neste element ett hakk bak
  @Override
  public void leggTil(int pos, T x) {

    //TestLenkeliste viser at man skal kunne legge til i slutten av en liste,
    // så skal ikke ha exception når pos == stoerrelse()
    if ((pos > stoerrelse() && stoerrelse() != 0) || pos < 0 || pos > stoerrelse()) {
      throw new UgyldigListeIndeks(pos);
    }

    else {
      Node tmp = start;
      Node ny = new Node(x);

      //finner posisjonen til noden før posisjonen den nye noden skal være i
      for (int i = 1; i < pos; i++) {
        tmp = tmp.neste;
      }

      if (tmp == start && stoerrelse() != 0) {
        ny.neste = start;
        start = ny;
      }

      else if (tmp == start) {
        start = ny;
      }

      else {
        //hvis vi ikke befinner oss i enden av lenkelisten
        if (tmp.neste != null) {
          ny.neste = tmp.neste;
          tmp.neste = ny;
        }

        //hvis vi befinner oss i enden av lenkelisten
        else {
          tmp.neste = ny;
        }
      }
    }
  }

  //legger til på slutten av Lenkelisten
  @Override
  public void leggTil(T x) {
    Node ny = new Node(x);

    if (start == null) {
      start = ny;
    }
    else {
      Node tmp = start;

      while(tmp.neste != null) {
        tmp = tmp.neste;
      }

      tmp.neste = ny;
    }
  }

  //setter inn elementet på gitt posisjon og overskriver det som var der fra før
  @Override
  public void sett(int pos, T x) {
    Node tmp = start;

    if (pos >= stoerrelse() || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }

    for (int i = 1; i <= pos; i++) {
      System.out.println(i);
        tmp = tmp.neste;
    }

    tmp.verdi = x;
  }

  //henter ut et element uten å fjerne den fra listen
  @Override
  public T hent(int pos){
    if (pos >= stoerrelse() || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }

    else {
      Node tmp = start;

      for (int i = 0; i < pos; i++) {
        tmp = tmp.neste;
      }
      return tmp.verdi;
    }
  }

  @Override
  public T fjern(int pos) {
    if (pos >= stoerrelse() || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }

    else {
      Node tmp = start;
      Node fjernes = null;

      //finner posisjonen til noden før posisjonen den nye noden skal være i
      for (int i = 1; i < pos; i++) {
        tmp = tmp.neste;
      }

      //hvis listen kun har ett element
      if (stoerrelse() == 1) {
        if (pos == 1) {
          fjernes = start.neste;
          start.neste = null;
        }
        else if (pos == 0) {
          fjernes = start;
          start = start.neste;
        }
      }

      //hvis elementet vi skal fjerne ikke ligger i enden av lenkelisten
      else if (tmp.neste.neste != null) {
        Node tmp2 = tmp.neste.neste;
        fjernes = tmp.neste;
        tmp.neste.neste = null;
        tmp.neste = tmp2;
      }

      //hvis elementet vi skal fjerne ligger i enden av lenkelisten
      else {
        fjernes = tmp.neste;
        tmp.neste = null;
      }
      return fjernes.verdi;
    }
  }

  //fjerner og returnerer elementet på starten av Lenkelisten
  @Override
  public T fjern() {
    if (stoerrelse() == 0) {
      throw new UgyldigListeIndeks(-1);
    }

    else {
      Node tmp = start;
      start = tmp.neste;
      tmp.neste = null;
      return tmp.verdi;
    }
  }

  public void skrivUt() {
    System.out.println("********Skriv ut********");
    Node tmp = start;

    while (tmp != null) {
      System.out.println(tmp.verdi);
      tmp = tmp.neste;
    }
    System.out.println("/*******Skriv ut******/\n");
  }



  public Iterator iterator() {
       return new LenkelisteIterator();
  }

  private class LenkelisteIterator implements Iterator<T> {
       private int indeks = 0;

       public T next() {
           return hent(indeks++);
       }

       public boolean hasNext() {
           return indeks < stoerrelse();
       }
   }

}
