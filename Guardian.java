public class Guardian extends Adventurer{
  int shieldCount, maxShields;
  int turnCount;
  //constructors
  public Guardian(String name, int health){
    super(name, health);
    shieldCount = 1;
    maxShields = 3;
    turnCount = 0;
    setShield(true);
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
    if (other.isShielded()){
      damage = 0;
    }
    other.applyDamage(damage);
    turnCount++;
    if (turnCount >= 3){
      if (shieldCount <= maxShields){
        restoreSpecial(1);
        turnCount %= 3;
        return this + " attacked " + other + "and dealt " + damage + "damage. They have gained an additional shield.";
      }
      else{
        turnCount = 3;
      }
    }
    return this + " attacked " + other + "and dealt " + damage + "damage. They have " + (3 - turnCount) + "turns left until they gain another shield.";
  }

  /*Consumes all shields. Deal 3-4 damage, and increase damage by 6 for each shield consumed.*/
  public String specialAttack(Adventurer other){
    if (shieldCount > 0){
      int damage = ((int)(Math.random()+3) + (shieldCount * 6)) * (1 * getStrength()/10);
      setSpecial(0);
      setShield(false);
      other.applyDamage(damage);
      return this + " crashed their shields into " + other + ", dealing " + damage + "damage. " + this + " has lost all their shields.";
    }
    else{
      return "No shields available. Instead " + attack(other);
    }
  }

  /* Gives a shield to an ally. */
  public String support(Adventurer other){
    if (shieldCount > 0 && !other.isShielded()){
      other.setShield(true);
      shieldCount--;
      if (shieldCount == 0){
        setShield(false);
      }
      return this + " gave a shield to " + other + ".";
    }
    if (other.isShielded()){
      return other + "already has a shield. Instead " + support();
    }
    else{
      return "Not enough shields to support ally. Instead " + support();
    }
  }

  public String support(){
    return ""; //temp
  }
  @Override // need to override so that multiple shields work.
  public void applyDamage(int amount){
    setShield(shieldCount > 0);
    super.applyDamage(amount);
    setShield(shieldCount > 0);
  }
}
