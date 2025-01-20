import java.util.*;

public class Tester{
  public static void main(String[] args) {
    Text.go(0, 0);
    Text.clear();
    // Game.drawBackground();
    Text.reset();
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Adventurer enem = Game.createRandomAdventurer();
    enemies.add(enem);
    Adventurer enem2 = Game.createRandomAdventurer();
    enemies.add(enem2);
    Adventurer enem3 = Game.createRandomAdventurer();
    enemies.add(enem3);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> heroes = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Adventurer hero = new CodeWarrior();
    heroes.add(hero);
    Adventurer hero2 = new Guardian("guardian");
    heroes.add(hero2);
    Adventurer hero3 = new Bard("bard");
    heroes.add(hero3);
    Game.drawScreen(heroes, enemies);
    // Game.TextBox(1, 1, 18, 1, "HP: "+Game.colorByPercent(hero.getHP(), hero.getmaxHP())); // Current HP
    // Text.showCursor();
    Game.quit();
    // Game.run();
  }
}
