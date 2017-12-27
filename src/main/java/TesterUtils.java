import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sommeiller 2017/12/27 23:45
 * @version v1.0
 */
public class TesterUtils {
    public static <T> T getParObject(JavaSamplerContext context,Class<T> clazz,String... ignore){
        T object = null;
        try{
            object= clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        Iterator<String> argsName = context.getParameterNamesIterator();
        List<String> ignoreList = new ArrayList<String>();
        Collections.addAll(ignoreList,ignore);
        while (argsName.hasNext()){
            String arg = argsName.next();
            if (ignoreList.contains(arg)||context.getParameter(arg).length()==0){
                continue;
            }
            try {
                Field field = object.getClass().getDeclaredField(arg);
                field.setAccessible(true);
                Class fieldClazz = field.getType();

                if (fieldClazz==String.class) {
                    String value = context.getParameter(arg);
                    field.set(object, value);
                }else if (fieldClazz == Integer.class||fieldClazz==int.class){
                    int value = context.getIntParameter(arg);
                    field.set(object, value);
                }else {
                    System.out.println(arg+"-----这个参数添加失败了，请自行补充");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return object;
    }

    public static void main(String args[]){
        System.out.println("dunboTest");
    }
}
