package core.ref;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;
        Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
            if(method.isAnnotationPresent(MyTest.class)) {
                try {
                    method.invoke(clazz.newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
