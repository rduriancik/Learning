package chapterApps.genericMatrix;

/**
 * Created by robert on 3.4.2016.
 */
public class IntegerMatrix extends GenericMatrix<Integer> {
    @Override
    protected Integer add(Integer o1, Integer o2) {
        return o1 + o2;
    }

    @Override
    protected Integer multiply(Integer o1, Integer o2) {
        return o1 * o2;
    }

    @Override
    protected Integer zero() {
        return 0;
    }
}
