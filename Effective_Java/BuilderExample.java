// Telescoping constructor pattern
class NutritionFacts {
  private final int servingSize; //required
  private final int servings; // required
  private final int calories; // optional
  private final int fat; // optional
  private final int sodium; // optional
  private final int carbohydrate; // optional

  public NutritionFacts(int servingSize, int servings) {
    this(servingSize, servings, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories) {
    this(servingSize, servings, calories, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat) {
    this(servingSize, servings, calories, fat, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat,
                        int sodium) {
    this(servingSize, servings, calories, fat, sodium, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat,
                        int sodium, int carbohydrate) {
    this.servingSize = servingSize;
    this.servings = servings;
    this.calories = calories;
    this.fat = fat;
    this.sodium = sodium;
    this.carbohydrate = carbohydrate;
  }
}

// JavaBeans Pattern
class NutritionFacts2 {
  private int servingSize = -1; //required
  private int servings = -1; // required
  private int calories = 0; // optional
  private int fat = 0; // optional
  private int sodium = 0; // optional
  private int carbohydrate = 0; // optional

  public NutritionFacts2() {}

  // Setters
  public void setServingSize(int val) {servingSize = val;}
  public void setServings(int val) {servings = val;}
  public void setCalories(int val) {calories = val;}
  public void setFat(int val) {fat = val;}
  public void setSodium(int val) {sodium = val;}
  public void setCarbohydrate(int val) {carbohydrate = val;}
}

// Builder Pattern
class NutritionFacts3 {
  private final int servingSize; //required
  private final int servings; // required
  private final int calories; // optional
  private final int fat; // optional
  private final int sodium; // optional
  private final int carbohydrate; // optional

  public static class Builder {
    // Required parameters
    private final int servingSize;
    private final int servings;

    // Optional parameters - initialized to default values
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      calories = val;
      return this;
    }

    public Builder fat(int val) {
      fat = val;
      return this;
    }

    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    }

    public Builder sodium(int val) {
      sodium = val;
      return this;
    }

    public NutritionFacts3 build() {
      return new NutritionFacts3(this);
    }
  }

  private NutritionFacts3(Builder builder) {
    servingSize = builder.servingSize;
    servings = builder.servings;
    calories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }
}

public class BuilderExample {
  public static void main(String [] args) {
    NutritionFacts telescopincExample = new NutritionFacts(240, 8, 100, 0, 35, 27);

    NutritionFacts2 javaBeansExample = new NutritionFacts2();
    javaBeansExample.setServingSize(240);
    javaBeansExample.setServings(8);
    javaBeansExample.setCalories(100);
    javaBeansExample.setSodium(35);
    javaBeansExample.setCarbohydrate(27);

    NutritionFacts3 builderExample = new NutritionFacts3.Builder(240, 8).
      calories(100).sodium(35).carbohydrate(27).build();

  }
}
