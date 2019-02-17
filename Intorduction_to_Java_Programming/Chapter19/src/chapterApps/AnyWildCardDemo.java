package chapterApps;

/**
 * Created by robert on 25.3.2016.
 */
public class AnyWildCardDemo {
    public static void main(String[] args) {
        GenericStack<Integer> intStack = new GenericStack<>();

        intStack.push(1);
        intStack.push(2);
        intStack.push(-2);

        print(intStack);
    }

    public static void print(GenericStack<?> stack) {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
