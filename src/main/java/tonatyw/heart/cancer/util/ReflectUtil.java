package tonatyw.heart.cancer.util;


/**
 * 
 * @ClassName ReflectUtil
 * @Description: 反射相关工具类
 * @date 2019年4月22日 下午1:39:43
 */
public class ReflectUtil {
	public static Object processMethod(Class c,String methodName, Object... parameter)throws Exception{
		Class[] classArray = new Class[parameter.length];
		int len = parameter.length;
		for(int i=0;i<len;i++){
			classArray[i] = parameter[i].getClass();
		}
		return c.getMethod(methodName, classArray).invoke(c.newInstance(), parameter);
	}
	public static final Class<?> getPrimitiveClass(String typeName) {
	    if (typeName.equals("byte"))
	        return byte.class;
	    if (typeName.equals("short"))
	        return short.class;
	    if (typeName.equals("int"))
	        return int.class;
	    if (typeName.equals("long"))
	        return long.class;
	    if (typeName.equals("char"))
	        return char.class;
	    if (typeName.equals("float"))
	        return float.class;
	    if (typeName.equals("double"))
	        return double.class;
	    if (typeName.equals("boolean"))
	        return boolean.class;
	    if (typeName.equals("void"))
	        return void.class;
	    throw new IllegalArgumentException("Not primitive type : " + typeName);
	}
}
