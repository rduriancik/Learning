public class StackCloneMethod implements Cloneable {
}

class Stack {
  private Object[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public Stack() {
    this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public void push(Object e) {
    ensureCapacity();
    elements[size++] = e;
  }

  public Object pop() {
    if(size == 0) {
      // throw new EmptyStackException();
    }
    Object result = elements[--size];
    elements[size] = null; // Eliminate obsolete reference
    return result;
  }

  /**
  * Ensure space for at least one more elememt.
  */
  private void ensureCapacity() {
    if(elements.length == size)
      elements = java.util.Arrays.copyOf(elements, 2 * size + 1);
  }

  @Override
  public Stack clone() {
    try {
      Stack result = (Stack) super.clone();
      result.elements = elements.clone();
      return result;
    } catch (CloneNotSupportedException ex) {
      throw new AssertionError();
    }
  }
}
