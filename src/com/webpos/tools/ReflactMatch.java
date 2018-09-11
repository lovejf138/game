package com.webpos.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
/**
 * 自动给对象的属性随机赋值的工具类
 * <p>Title: ReflactMatch</p>
 * <p>Description: </p>
 * <p>Company: huagu </p> 
 * @author	tc
 * @date	2015年11月20日上午11:38:11
 * @version 1.0
 */
public class ReflactMatch {
	protected static Logger logger = Logger.getLogger(ReflactMatch.class);
	
	public static void setValue(Object obj) {
//		Map<String, Field> fieldMap = new HashMap<String, Field>();
//		Map<String, Method> methodMap = new HashMap<String, Method>();
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			Field[] parent =  obj.getClass().getSuperclass().getDeclaredFields();
			List<Field> list = new ArrayList<Field>();
			//自身字段赋值
			for(Field f : fields){
			    list.add(f);
			}
			//父类的字段赋值
			for(Field f : parent){
			    list.add(f);
			}
//			Method[] methods = obj.getClass().getDeclaredMethods();
//
//			for (Field field : fields) {
//				String attri = field.getName();
//				fieldMap.put(attri.toLowerCase(), field);
//				for (Method method : methods) {
//					String meth = method.getName();
//					// 匹配set方法
//					if (meth != null
//							&& "set".equals(meth.substring(0, 3))
//							&& Modifier.isPublic(method.getModifiers())
//							&& ("set" + Character.toUpperCase(attri.charAt(0)) + attri
//									.substring(1)).toLowerCase().equals(meth.toLowerCase())) {
//						methodMap.put(attri, method);
//						break;
//					}
//				}
//			}
			String methodName = null;
			String name  = null;
			Method method = null;
			// 2、属性赋值
			for (Field field : list) {
			    name = field.getName();
			    if(name.equals("uOtherPayFee2")|| name.equals("uCarFee2")
			    		|| name.equals("class1")){
			    	continue;
			    }
			    methodName = "set" + (name.substring(0,1)).toUpperCase() + name
                        .substring(1);
			    method = obj.getClass().getMethod(methodName, field.getType());
				fill(obj, field, method);
			}
//			fieldMap = null;
//			methodMap = null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 将字符串值转换为合适的值填充到对象的指定域
	 * @param bean
	 *            被填充的java bean
	 * @param field
	 *            需要填充的域
	 * @param value
	 *            字符串值
	 */
	private static void fill(Object bean, Field field, Method method) {

		try {
			Object[] oo = new Object[1];

			String type = field.getType().getName();
			if ("java.lang.String".equals(type)) {
				oo[0] = "a";
			} else if ("int".equals(type) ||"java.lang.Integer".equals(type)) {
				oo[0] =1;
			} else if ("java.lang.Float".equals(type)) {
				oo[0] = 8.0;
			} else if ("java.lang.Double".equals(type)) {
				oo[0] =8.0;
			} else if ("java.math.BigDecimal".equals(type)) {
				oo[0] = new BigDecimal(8);
			} else if ("java.util.Date".equals(type)) {
				oo[0] = new Date();
			} else if ("java.sql.Timestamp".equals(type)) {
				oo[0] = new Timestamp(0);
			} else if ("java.lang.Boolean".equals(type)) {
				oo[0] = true;
			} else if ("java.lang.Long".equals(type) ||"long".equals(type)) {
				oo[0] = 2L;
			}
			method.invoke(bean, oo);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}
