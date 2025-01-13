public class Tester{
  public static void main(String[] args) {
    Text.go(0, 0);
    Text.clear();
    Game.drawBackground();
    Game.TextBox(5, 5, 20, 10, "test text for testing the text box function, this is testing the text box");
    Text.reset();
    Text.showCursor();
    Game.quit();
  }
}
