package neo.uap.util;

import neo.uap.exception.PrerequisiteNotSatisfiedException;
import neo.uap.exception.ResourceNotAccessibleException;
import neo.uap.exception.ResourceNotFoundException;

public class Preconditions {
    public static void checkResourceAccessible(boolean expression) {
        if (expression == false) {
            throw new ResourceNotAccessibleException();
        }
    }

    public static void checkResourceNotFound(boolean expression) {
        if (expression == false) {
            throw new ResourceNotFoundException();
        }
    }

    public static void checkResourceNotFound(Object obj) {
        checkResourceNotFound(obj != null);
    }

    public static void checkResourceNotFound(int rowNum) {
        checkResourceNotFound(rowNum != 0);
    }

    public static void checkPrerequisite(boolean expression) {
        if (expression == false) {
            throw new PrerequisiteNotSatisfiedException();
        }
    }

    public static void checkPrerequisite(boolean expression, Class<? extends RuntimeException> clazz) {
        if (expression == false) {
            try {
                throw clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
