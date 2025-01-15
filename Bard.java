public class Bard extends Adventurer{
  int inspiration, maxInspiration;
  // constructors
  public Bard(String name, int health){
    super(name, health);
    inspiration = 15;
    maxInspiration = 15;
    setShield(true);
  }

  public Bard(String name){
    this(name, 20);
  }

  public Bard(){
    this("defaultnametextB");
  }
  // abstract accessors
  public String getSpecialName(){
    return "Inspiration";
  }
  public int getSpecial(){
    return inspiration;
  }
  public int getSpecialMax(){
    return maxInspiration;
  }
  public void setSpecial(int n){
    inspiration = n;
  }
  // skills
  /* Do 4-6 damage, regain some inspiration. */
  public String attack(Adventurer other){
    int damage = (int)((Math.random()*3 + 4) * (1 + getStrength()/10));
    other.applyDamage(damage);
    restoreSpecial(6);
    return this + " smacks " + other + " with their lute, dealing " + damage + " damage. They have found some inspiration.";
  }

  /* Consume inspiration. Do 6-10 damage, and debuff enemy attack by 20%. */
  public String specialAttack(Adventurer other){
    return "";
  }

  /* Grant strength to self AND ally. */
  public String support(Adventurer other){
    return "";
  }

  /* Restore some inspiration. */
  public String support(){
    return "";
  }
}
