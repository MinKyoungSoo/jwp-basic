package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ReflectionTest  {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());
    }
    
    @Test
    public void newInstanceWithConstructorArgs() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        Arrays.stream(clazz.getDeclaredConstructors()).forEach(constructor -> {
            try {
                logger.debug("{}",constructor.newInstance("ksmin", "1234", "경수", "ksmin@ooo.com"));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
    
    @Test
    public void privateFieldAccess() throws Exception {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
        
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        Student student = new Student();
        nameField.set(student, "민경수");
        logger.debug(student.getName());
    }
}
