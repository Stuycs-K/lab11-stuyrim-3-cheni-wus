import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    for (int col = 0; col < HEIGHT; col++){
      for (int row = 0; row < WIDTH; row++){
        if (row == 0 || col == 0 || row == WIDTH || col == HEIGHT){
          System.out.print(Text.colorize(" ", BORDER_COLOR));
          Text.reset();
        }else{
          System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
          Text.reset();
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startRow, startCol);
    System.out.print(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    int length = 0; int heightTrack = 0; // tracks text progress
    String word = "";
    String[] speech = text.split(" "); //splits the text by words
    for (int i = 0; i < speech.length; i++){
      word = speech[i]; // goes through each word in "text" and then draws them
      if (length + word.length() > width){
        for (int x = length; x < width; x++){
          drawText(" ", row + heightTrack, col + x); // fills in extra spaces each row
        }
        length = 0;
        heightTrack++;
        if (heightTrack > height){
          break;
        }
      }
      drawText(word, row + heightTrack, col + length);  // draws the text
      length += word.length();
      if (length + 1 < width){ // adds an extra space if possible to fit it in
        drawText(" ", row + heightTrack, col + length);
        length++;
      }
    }
    for (int i = heightTrack; i < height; i++){
      for (int x = length; x < width; x++){
        drawText(" ", row + i, col + x);
      }
      length = 0;
    }
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int select = (int)Math.random()*3;
      if (select == 0){
        return new CodeWarrior("Bob"+(int)(Math.random()*100));
      }
      if (select == 1){
        return new Guardian("John" + (int)(Math.random()*100));
      }
      else{
        return new Bard("Kevin" + (int)(Math.random()*100));
      }
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow, int startCol){
      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      int width = 15; // set width of each item
      for (int j = 0; j < party.size(); j++){
        Adventurer currentAdv = party.get(j);
        TextBox(startRow, startCol, width, 1, currentAdv.getName()); // Name
        TextBox(startRow + 1, startCol, width, 1, "HP: "+currentAdv.getHP()); // Current HP
        TextBox(startRow + 2, startCol, width, 1, currentAdv.getSpecialName() + ": "+ currentAdv.getSpecial()); // Current Special amount
        TextBox(startRow + 3, startCol, width, 1, ""); // Empty Line
        startCol += width; // Shift to the right, go to next Adventurer
      }
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    if ((double)hp / maxHP < 0.25){
      output = Text.colorize(output, Text.RED);
    }
    // under 75% : yellow
    else if ((double)hp / maxHP > 0.75){
      output = Text.colorize(output, Text.YELLOW);
    }
    // otherwise : white
    else{
      output = Text.colorize(output, Text.WHITE);
    }
    return output;
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> heroes, ArrayList<Adventurer> enemies){

    drawBackground();

    //draw player party
    TextBox(24,20,11,1,"Hero Party:");
    drawParty(heroes, 25, 20);
    //draw enemy party
    TextBox(1,20,12,1,"Enemy Party:");
    drawParty(enemies, 2, 20);

  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(30,7);
      //show cursor
      Text.reset();
      Text.showCursor();
      String input = in.nextLine();

      //clear the text that was written

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();
    Text.go(0, 0);


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Adventurer enem = createRandomAdventurer();
    enemies.add(enem);
    Adventurer enem2 = createRandomAdventurer();
    enemies.add(enem2);
    Adventurer enem3 = createRandomAdventurer();
    enemies.add(enem3);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Adventurer hero = new CodeWarrior();
    party.add(hero);
    Adventurer hero2 = createRandomAdventurer();
    party.add(hero2);
    Adventurer hero3 = createRandomAdventurer();
    party.add(hero3);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    TextBox(29,5,500,78,preprompt);
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      ;

      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";

          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }

      //display the updated screen after input has been processed.
      drawScreen(party, enemies);



    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
