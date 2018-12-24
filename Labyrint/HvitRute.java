import javafx.scene.paint.Color;

public class HvitRute extends Rute {
  public HvitRute(Labyrint l, int rad, int kolonne) {
    super(l, rad, kolonne);
    this.setFill(Color.WHITE);

  }

  @Override
  public char tilTegn() {
    return '.';
  }
}
