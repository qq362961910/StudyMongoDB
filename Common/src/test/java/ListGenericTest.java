import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListGenericTest {
    public static void main(String[] args) throws Exception{
        List<String> list = new ArrayList<>();
        Type type = list.getClass().getGenericSuperclass();
        System.out.println(type);
        TypeVariable<?>[] typeVariable = list.getClass().getTypeParameters();
        ParameterizedType parameterizedType = (ParameterizedType) list.getClass().getGenericSuperclass();
        Method m = list.getClass().getDeclaredMethod("get", int.class);
        m.getReturnType();
        System.out.println(int.class.isPrimitive());
        System.out.println(Date.class.isPrimitive());
    }
}
