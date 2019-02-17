// Singleton with public final field
class Elvis {
  public static final Elvis INSTANCE = new Elvis();
  private Elvis() {}

  public void leaveTheBuilding(){}
}

// Singleton with static favtory
class Elvis2 {
  private static final Elvis2 INSTANCE = new Elvis2();
  private Elvis2(){}

  public static Elvis2 getInstance() {return INSTANCE;}
  public void leaveTheBuilding(){}
}

// Enum singleton - the preffered approach
enum Elvis3 {
  INSTANCE;

  public void leaveTheBuilding() {}
}

public class SingletonExample {
  public static void main(String[] args) {
    Elvis finalField = Elvis.INSTANCE;

    Elvis2 staticFactory = Elvis2.getInstance();

    Elvis3 enumElvis = Elvis3.INSTANCE;
  }
}
