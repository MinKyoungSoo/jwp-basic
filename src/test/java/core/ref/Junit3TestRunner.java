package core.ref;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Junit3TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;
        Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
            if(method.getName().startsWith("test")) {
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
