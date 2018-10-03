public class Rack {
  private int nodePerRack;
  private int teller = 0;
  private Node[] noder;

  public Rack(int nodePerRack) {
    this.noder = new Node[nodePerRack];
    this.nodePerRack = nodePerRack;
  }

  public void leggTil(Node node) {
    if (teller < nodePerRack) {
      noder[teller] = node;
      teller++;
    }
  }

  //henter total prosessorantall i alle nodene i racket
  public int antProsessorer() {
    int proAntall = 0;
    for (int i = 0; i < noder.length; i++) {
      if (noder[i] != null) {
        proAntall += noder[i].hentProant();
      }
    }
    return proAntall;
  }

  //returnerer antall noder i racket med nok minne
  public int nokMinne(int paakrevdMinne) {
    int nokMinne = 0;

    for (int i = 0; i < noder.length; i++) {
      if (noder[i] != null){
        if (noder[i].nokMinne(paakrevdMinne)) {
          nokMinne++;
        }
      }
    }

    return nokMinne;
  }

  //nodePerRack skal ikke bli mindre enn teller, men sjekker just in case
  public boolean erFull() {
    return nodePerRack <= teller;
  }

}
