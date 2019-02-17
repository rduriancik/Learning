import java.awt.*;

public class EqualsDemo{
  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(1, 2);
    System.out.println(p1.equals(p2)); // True

    System.out.println("\n");

    ColorPoint cp1 = new ColorPoint(1, 2, Color.RED);
    System.out.println(p1.equals(cp1)); // True
    System.out.println(cp1.equals(p1)); // False

    System.out.println("\n");

    ColorPoint cp2 = new ColorPoint(1, 2, Color.BLUE);
    System.out.println(cp1.equals2(p2)); // True
    System.out.println(p2.equals(cp2)); // True
    System.out.println(cp1.equals2(cp2)); // false

    System.out.println("\n");

    ColorPoint2 cp1_2 = new ColorPoint2(1, 2, Color.RED);
    ColorPoint2 cp2_2 = new ColorPoint2(1, 2, Color.BLUE);
    ColorPoint2 cp3_2 = new ColorPoint2(1, 2, Color.RED);
    System.out.println(cp1_2.equals(cp3_2)); // True
    System.out.println(cp1_2.equals(cp2_2)); // False
    System.out.println(cp1_2.equals(p1)); // False
    System.out.println(cp1_2.asPoint().equals(p1)); // True
    System.out.println(p1.equals(cp1_2)); // False
    System.out.println(p1.equals(cp1_2.asPoint())); // True
  }
}

class Point {
  private final int x;
  private final int y;
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Point)) {
      return false;
    }

    Point p = (Point) o;
    return p.x == x && p.y == y;
  }
}

// INCORRECT EQUALS METHODS
class ColorPoint extends Point {
  private final Color color;

  public ColorPoint(int x, int y, Color color) {
    super(x,y);
    this.color = color;
  }

  // VIOLATES SYMETRY!
  @Override
  public boolean equals(Object o) {
    if(!(o instanceof ColorPoint)) {
      return false;
    }
    return super.equals(o) && ((ColorPoint) o).color == color;
  }

  // VIOLATES TRANSITIVITY!
  public boolean equals2(Object o) {
    if(!(o instanceof Point))
      return false;

    // If o is a normal Point, do a color-blind comparison
    if(!(o instanceof ColorPoint)) {
      return o.equals(this);
    }

    // o is a ColorPoint; do a full comparison
    return super.equals(o) && ((ColorPoint) o).color == color;
  }
}

// CORRECT EQUALS METHOD
class ColorPoint2 {
  private final Point point;
  private final Color color;

  public ColorPoint2(int x, int y, Color color) {
    if (color == null) {
      throw new NullPointerException();
    }
    point = new Point(x, y);
    this.color = color;
  }

  /**
  * Returns the point-view of this color point
  */
  public Point asPoint() {
    return point;
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof ColorPoint2)) {
      return false;
    }

    ColorPoint2 cp = (ColorPoint2) o;
    return cp.point.equals(point) && cp.color.equals(color);
  }
}
