public class Bard extends Adventurer{
  int inspiration, maxInspiration;
  // constructors
  public Bard(String name, int health){
    super(name, health);
    inspiration = 30;
    maxInspiration = 30;
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
  public String attack(Adventurer other){
    return "";
  }

  public String specialAttack(Adventurer other){
    return "";
  }

  public String support(Adventurer other){
    return "";
  }

  public String support(){
    return "";
  }
}
