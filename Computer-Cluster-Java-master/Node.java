public class Node {
  private int minnestrl;
  private int proAntall;

  public Node (int minnestrl, int proAntall) {
    this.minnestrl = minnestrl;
    this.proAntall = proAntall;
  }

  public int hentProant() {
    return proAntall;
  }

  public boolean nokMinne(int paakrevdMinne) {
    if (minnestrl >= paakrevdMinne) {
      return true;
    }
    else {
      return false;
    }
  }
}
