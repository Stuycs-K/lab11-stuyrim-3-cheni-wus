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
    int damage = (int)((Math.random()*4+5) * (1 * getStrength()/10));
    setTurns(getTurns()-1); // lose 1 strength turn per turn
    if (other.isShielded()){
      damage = 0;
    }
    other.applyDamage(damage);
    turnCount++;
    String str1 = this + " attacked " + other + "and dealt " + damage + "damage.";
    if (turnCount >= 3){
      if (shieldCount <= maxShields){
        restoreSpecial(1);
        turnCount %= 3;
        return str1 + " They have gained an additional shield.";
      }
      else{
        turnCount = 3;
        return str1 + " They are at the maximum amount of shields.";
      }
    }
    return str1 + "They have " + (3 - turnCount) + "turns left until they gain another shield.";
  }

  /*Consumes all shields. Deal 1-5 damage, and increase damage by 6 for each shield consumed.*/
  public String specialAttack(Adventurer other){
    if (shieldCount > 0){
      int damage = (int)(((Math.random()*5 + 1) + (shieldCount * 6)) * (1 * getStrength()/10));
      setTurns(getTurns()-1); // lose 1 strength turn per turn
      setSpecial(0);
      setShield(false);
      other.applyDamage(damage);
      turnCount++;
      return this + " crashed their shields into " + other + ", dealing " + damage + "damage. " + this + " has lost all their shields.";
    }
    else{
      return "No shields available. Instead " + attack(other);
    }
  }

  /* Gives a shield to an ally. */
  public String support(Adventurer other){
    if (shieldCount > 0 && !other.isShielded()){
      setTurns(getTurns()-1);
      other.setShield(true);
      shieldCount--;
      turnCount++;
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

  /* Heal 3 HP to self + 4 HP per shield. */
  public String support(){;
    setTurns(getTurns()-1);
    int heal = 3 + (4 * shieldCount);
    setHP(getHP() + heal);
    String str1 = this + " focused themselves, and regained " + heal + "HP. ";
    turnCount += 2;
    if (turnCount >= 3){
      turnCount %= 3;
      restoreSpecial(1);
      return str1 + " They have gained an additional shield.";
    }
    return str1 + " They have " + (3 - turnCount) + " turns left until they gain another shield.";
  }

  @Override // need to override so that multiple shields work.
  public void applyDamage(int amount){
    setShield(shieldCount > 0);
    super.applyDamage(amount);
    if (shieldCount > 0){
      shieldCount--;
    }
    setShield(shieldCount > 0);
  }
}
