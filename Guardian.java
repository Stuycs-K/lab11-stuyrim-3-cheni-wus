public class Guardian extends Adventurer{
  int shieldCount, maxShields;
  //constructors
  public Guardian(String name, int health){
    super(name, health);
    shieldCount = 1;
    maxShields = 3;
    setShield(true); // will need to override applydamage to be consistent
  }

  public Guardian(String name){
    this(name, 40);
  }

  public Guardian(){
    this("defaultnametextG");
  }
  //abstract override;
  public String getSpecialName(){
    return "Shields";
  }
  public int getSpecial(){
    return shieldCount;
  }
  public int getSpecialMax(){
    return maxShields;
  }
  public void setSpecial(int n){
    shieldCount = n;
  }

  /*Deals 5-8 damage to a given target, increments turnCount*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*4+5) * (1 * getStrength()/10);
    other.applyDamage(damage);
    setTurns(getTurnCount() + 1);
    return "did " + damage + " to "  + other; 
  }
}
