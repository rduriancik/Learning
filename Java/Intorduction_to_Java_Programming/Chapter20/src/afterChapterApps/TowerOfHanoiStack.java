package afterChapterApps;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by robert on 9.8.2016.
 */
public class TowerOfHanoiStack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of disks: ");
        int n = input.nextInt();

        System.out.println("The moves are: ");
        moveDisks(n, 'F', 'T', 'A');
    }

    /**
     * The method for finding the solution to move n disks
     * from fromTower to toTower with auxTower
     */
    private static void moveDisks(int n, char from, char to, char aux) {

        Stack<Tower> stack = new Stack<>();
        stack.push(null);

        char f = from;
        char t = to;
        char a = aux;

        int count = n;
        char temp;
        Tower tower;

        do {
            while (count != 1) {
                stack.push(new Tower(count, f, t, a));
                count--;
                temp = t;
                t = a;
                a = temp;
            }

            System.out.println("Move disc 1 from " + f + " to " + t);

            tower = stack.pop();
            if (tower == null) {
                break;
            }

            f = tower.getFrom();
            t = tower.getTo();
            a = tower.getAux();
            count = tower.getNum();

            System.out.println("Move disc " + count + " from " + f + " to " + t);

            count--;
            temp = f;
            f = a;
            a = temp;
        } while (!stack.isEmpty());
    }
}

class Tower {
    private char from;
    private char to;
    private char aux;
    private int num;

    public Tower(int num, char from, char to, char aux) {
        this.num = num;
        this.from = from;
        this.to = to;
        this.aux = aux;
    }

    public int getNum() {
        return num;
    }

    public char getFrom() {
        return from;
    }

    public char getTo() {
        return to;
    }

    public char getAux() {
        return aux;
    }
}
