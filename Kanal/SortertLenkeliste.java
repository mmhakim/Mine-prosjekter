public class SortertLenkeliste <T extends Comparable <T> > extends Lenkeliste <T> {

  //setter inn elementer i rekkefølge (minst til størst)
  @Override
  public void leggTil(T x) {
    Node ny = new Node(x);

    //hvis listen er tom, settes den nye noden i starten av listen
    if (stoerrelse() == 0) {
      start = ny;
    }

    else if (stoerrelse() == 1) {
      if (x.compareTo(start.verdi) >= 0) {
        start.neste = ny;
      }
      else {
        ny.neste = start;
        start = ny;
      }
    }

    else {
      Node tmp = start;

      while(tmp != null) {


        //hvis vi har kommet til enden av listen og verdien er størst
        if (tmp.neste == null) {
          tmp.neste = ny;
          break;
        }

        //hvis elementets verdi er større enn tmps og mindre enn tmps nestes
        else if (x.compareTo(tmp.verdi) >= 0 && x.compareTo(tmp.neste.verdi) < 0) {
            Node tmp2 = tmp.neste;
            tmp.neste = ny;
            ny.neste = tmp2;
            break;
        }
        tmp = tmp.neste;
      } //slutt av while(tmp != null)
    }
  }

  //fjerner største element
  @Override
  public T fjern() {
    return fjern(stoerrelse()-1);
  }

  @Override
  public void sett(int pos, T x) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void leggTil(int pos, T x) {
    throw new UnsupportedOperationException();
  }

}
