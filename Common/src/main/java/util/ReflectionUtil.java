package util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectionUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ClassGenericity getGenericity(Class clazz) {
        if (clazz == null) {
            return null;
        }
        Type type = clazz.getGenericSuperclass();
        return doGetGenericity(type);
    }

    public static ClassGenericity doGetGenericity(Type type) {
        if (type == null) {
            return null;
        }
        ClassGenericity classGenericity = new ClassGenericity();
        List<Genericity> genericityList = new ArrayList<>();
        classGenericity.setGenericityList(genericityList);
        // 嵌套泛型
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type rawType = parameterizedType.getRawType();
            if (rawType instanceof Class) {
                Class superClass = (Class)rawType;
                if (!Object.class.equals(superClass)) {
                    TypeVariable[] superTypeVariables = superClass.getTypeParameters();
                    ClassGenericity superClassGenericity = new ClassGenericity();
                    List<Genericity> superGenericityList = new ArrayList<>();
                    superClassGenericity.setGenericityList(superGenericityList);
                    for (TypeVariable superTypeVariable: superTypeVariables) {
                        Genericity superGenericity = new Genericity();
                        superGenericity.setType(superTypeVariable.getName());
                        superGenericityList.add(superGenericity);
                    }
                    classGenericity.setParent(superClassGenericity);
                    Type superSuperClass = superClass.getSuperclass();
                    if (!Object.class.equals(superSuperClass)) {
                        Type superSuperType = superClass.getGenericSuperclass();
                        superClassGenericity.setParent(doGetGenericity(superSuperType));
                    }
                }
            }
            //加载父类泛型
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type t: types) {
                Genericity genericity = new Genericity();
                if (t instanceof ParameterizedType) {
                    genericity.setType(((ParameterizedType)t).getRawType().getTypeName());
                    genericity.setEmbedClassGenericity(doGetGenericity(t));
                }
                else if (t instanceof TypeVariable){
                    genericity.setType(((TypeVariable) t).getName());
                }
                else if (t instanceof Class) {
                    genericity.setType(((Class) t).getName());
                }
                else {
                    System.out.println("[warn] run in else");
                }
                genericityList.add(genericity);
            }

        // 处理泛型擦拭对象
        } else if (type instanceof TypeVariable) {
            Genericity genericity = new Genericity();
            genericity.setType(((TypeVariable) type).getName());
            genericityList.add(genericity);
        // class本身也是type，强制转型
        } else if (type instanceof Class){// class本身也是type，强制转型
            Genericity genericity = new Genericity();
            genericity.setType(((Class) type).getName());
            genericityList.add(genericity);
        }
        else {
            System.out.println("[warn] run in else");
        }
        return classGenericity;
    }

    private static Class getClass(Type type, int i) {
        if (type instanceof ParameterizedType) { // 处理泛型类型
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            return (Class) getClass(((TypeVariable) type).getBounds()[0], 0); // 处理泛型擦拭对象
        } else {// class本身也是type，强制转型
            return (Class) type;
        }
    }

    private static Class getGenericClass(ParameterizedType parameterizedType, int i) {
        Object genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
            return (Class) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) { // 处理泛型擦拭对象
            return (Class) getClass(((TypeVariable) genericClass).getBounds()[0], 0);
        } else {
            return (Class) genericClass;
        }
    }

    public static void main(String[] args) throws Exception{
//        System.out.println(getGenericity(StudentServiceImpl.class).toJsonString());
//        System.out.println(getGenericity(ArrayList.class).toJsonString());
        System.out.println(getGenericity(SubParent.class).toJsonString());
    }

    private static class ClassGenericity{
        //父类泛型
        private ClassGenericity parent;
        //泛型列表
        private List<Genericity> genericityList;

        public ClassGenericity getParent() {
            return parent;
        }

        public ClassGenericity setParent(ClassGenericity parent) {
            this.parent = parent;
            return this;
        }

        public List<Genericity> getGenericityList() {
            return genericityList;
        }

        public ClassGenericity setGenericityList(List<Genericity> genericityList) {
            this.genericityList = genericityList;
            return this;
        }

        public String toJsonString() throws JsonProcessingException {
            return mapper.writeValueAsString(this);
        }

        @Override
        public String toString() {
            return "ClassGenericity{" +
                    "parent=" + parent +
                    ", genericityList=" + genericityList +
                    '}';
        }
    }

    public static class Genericity {
        private String type;
        private ClassGenericity embedClassGenericity;

        public String getType() {
            return type;
        }

        public Genericity setType(String type) {
            this.type = type;
            return this;
        }

        public ClassGenericity getEmbedClassGenericity() {
            return embedClassGenericity;
        }

        public Genericity setEmbedClassGenericity(ClassGenericity embedClassGenericity) {
            this.embedClassGenericity = embedClassGenericity;
            return this;
        }

        public String toJsonString() throws JsonProcessingException {
            return mapper.writeValueAsString(this);
        }

        @Override
        public String toString() {
            return "Genericity{" +
                    "type='" + type + '\'' +
                    ", embedClassGenericity=" + embedClassGenericity +
                    '}';
        }
    }

    private static class GenSuper<S> {

    }

    private static class GenParent<T, X> extends GenSuper<Double>{

    }
    private static class SubParent extends GenParent<List<Map<String, Map<String, Long>>>, Set<Map<String, Integer>>> {

    }
}
