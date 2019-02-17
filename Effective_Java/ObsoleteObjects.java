public class ObsoleteObjects {
  private Object[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public ObsoleteObjects() {
    elements = new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public void push(Object e) {
    ensureCapacity();
    elements[size++] = e;
  }

  // INCORRECT
  // public Object pop() {
  //   if (size == 0) {
  //     // throw new EmptyStackException();
  //   }
  //
  //   // obsolete references
  //   return elemets[--size];
  // }

  public Object pop() {
    if (size == 0) {
        // throw new EmptyStackException();
    }

    Object result = elements[--size];
    elements[size] = null;
    return result; // Eliminate obsolete reference
  }

  /**
  * Ensure space for at least one more element, roughly
  * doubling the capacity each time the array needs to grow.
  */
  private void ensureCapacity() {
    if (elements.length == size)
      elements = java.util.Arrays.copyOf(elements, 2 * size + 1);
  }
}
