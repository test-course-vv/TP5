package vv.tdd.factory;

/**
 * Created by Simon on 14/10/14.
 */
public class IntegerToStringFactory implements Factory<String,Integer> {
    @Override
    public String transform(Integer object) {
        try {
            return object.toString();
        } catch (Exception e) {}
        return create();
    }

    @Override
    public String create() {
        return "string";
    }
}
