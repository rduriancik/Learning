package chapterApps.afterChapterApps;

/**
 * Created by robert on 4.4.2016.
 */
public class ArrayGenericStack<E> {
    private E[] array = (E[]) new Object[1];
    private int length = 0;

    public int getSize() {
        return length;
    }

    public E peek() {
        return array[length - 1];
    }

    public void push(E o) {
        if (length == array.length) {
            raise();
        }

        array[length] = o;
        length++;
    }

    public E pop() {
        if (length == 0) {
            return null;
        }
        E o = array[length - 1];
        array[length - 1] = null;
        length--;

        return o;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        return "stack: " + print();
    }

    private String print() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            str.append(array[i].toString());
            if (i == length - 1) {
                str.append("]\n");
            } else {
                str.append(", ");
            }
        }
        return str.toString();
    }

    private void raise() {
        E[] bigger = (E[]) new Object[array.length * 2];
        System.arraycopy(array, 0, bigger, 0, length);

        array = bigger;
    }

    public static void main(String[] args) {
        ArrayGenericStack<String> stack1 = new ArrayGenericStack<>();

        stack1.push("London");
        stack1.push("Paris");
        stack1.push("Berlin");

        System.out.println(stack1.toString());

        ArrayGenericStack<Integer> stack2 = new ArrayGenericStack<>();

        stack2.push(1);
        stack2.push(2);
        stack2.push(3);

        System.out.println(stack2.toString());
    }
}
