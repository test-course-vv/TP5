package vv.tdd.factory;

/**
 * Created by Simon on 14/10/14.
 */
public class StringToIntegerFactory implements Factory<Integer, String> {

    @Override
    public Integer transform(String object) {
        try {
            return Integer.parseInt(object);
        } catch (Exception e)
        {}
        return create();
    }

    @Override
    public Integer create() {
        return 1;
    }
}
