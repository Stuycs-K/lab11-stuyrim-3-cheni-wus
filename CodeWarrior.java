public class CodeWarrior extends Adventurer{
  int caffeine, caffeineMax;
  String preferredLanguage;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public CodeWarrior(String name, int hp, String language){
    super(name,hp);
    caffeineMax = 12;
    caffeine = caffeineMax/2;
    preferredLanguage = language;
  }

  public CodeWarrior(String name, int hp){
    this(name,hp,"c++");
  }

  public CodeWarrior(String name){
    this(name,24);
  }

  public CodeWarrior(){
    this("Carmack");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Caffeine";
  }

  public int getSpecial(){
    return caffeine;
  }

  public void setSpecial(int n){
    caffeine = n;
  }

  public int getSpecialMax(){
    return caffeineMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 caffeine*/
  public String attack(Adventurer other){
    int damage = (int)(((Math.random()*6)+2) * (1 + getStrength()/10)); // strength boosts dmg by 10% per level
    setTurns(getTurns()-1); // lose 1 strength turn per turn
    restoreSpecial(2);
    if (other.isShielded()){ // special line of text if damage is blocked
      other.applyDamage(damage); // shields blocking damage is handled by Adventurer
      return this + " attacked "+ other +", but dealt no damage due to their shield. They then take a ship of their coffee.";
    }
    other.applyDamage(damage);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. They then take a sip of their coffee.";
  }

  /*Deal 3-12 damage to opponent, only if caffeine is high enough.
  *Reduces caffeine by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)((Math.random()*5+Math.random()*5)+3) * (1 + getStrength()/10);
      setTurns(getTurns()-1);
      if (other.isShielded()){
        other.applyDamage(damage);
        return this + " used their "+preferredLanguage+
        " skills to hack the matrix. "+
        " This glitched out "+other+", but the damage was blocked by their shield.";
      }
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough caffeine to use the ultimate code. Instead "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    setTurns(getTurns()-1);
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/ // its now  4 health since 1 hp is meaningless
  public String support(){
    int hp = 4;
    setHP(getHP()+hp);
    setTurns(getTurns()-1);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
