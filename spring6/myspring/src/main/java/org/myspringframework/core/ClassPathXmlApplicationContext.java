package org.myspringframework.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {


    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 解析MySpring的配置文件,然后初始化所有的Bean对象
     *
     * @param configLocation spring配置文件的路径.注意:使用ClassPathXmlApplicationContext,配置文件应当放到类路径下
     */
    public ClassPathXmlApplicationContext(String configLocation) {

        try {
            // 解析MySpring.xml文件,然后实例化Bean,将Bean存放到singletonObjects集合当中
            // 这是dom4j解析XML文件的核心对象
            SAXReader reader = new SAXReader();
            // 获取一个指向配置文件的输入流.
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(configLocation);
            // 读到xml文件
            Document document = reader.read(in);
            List<Node> beans = document.selectNodes("//bean");
            beans.forEach(node -> {
                try {
                    // 向下转型的目的是为了使用Element接口里更丰富的方法
                    Element beanElt = (Element) node;
                    // 获取id属性
                    String id = beanElt.attributeValue("id");
                    // 获取class属性
                    String className = beanElt.attributeValue("class");

                    // 通过全类名拿到类对象
                    Class<?> aClass = Class.forName(className);
                    // 拿到类的默认无参构造方法
                    Constructor<?> defaultCon = aClass.getDeclaredConstructor();
                    // 通过无参构造方法的newInstance()方法创建bean对象
                    Object bean = defaultCon.newInstance();
                    // 将创建的bean对象与对应的id放入map集合中
                    singletonObjects.put(id, bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            beans.forEach(node -> {
                try {
                    Element beanElt = (Element) node;
                    String id = beanElt.attributeValue("id");
                    String className = beanElt.attributeValue("class");
                    Class<?> aClass = Class.forName(className);
                    // 获取bean标签下的所有property标签
                    List<Element> properties = beanElt.elements("property");
                    properties.forEach(property -> {
                        try {
                            // 拿到property标签中的name属性值
                            String propertyName = property.attributeValue("name");
                            Field field = aClass.getDeclaredField(propertyName);
                            // 获取set方法名
                            // 前面加上set property第一个字母大写剩下的小写
                            String setMethodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                            // 获取到set方法
                            Method setMethod = aClass.getDeclaredMethod(setMethodName, field.getType());

                            // 拿到property标签中的value或是ref
                            String value = property.attributeValue("value");
                            String ref = property.attributeValue("ref");
                            Object actualValue = null;
                            if (value != null) {
                                // 说明这个值是简单类型
                                // 调用set方法
                                // 获取属性类型名
                                String propertyTypeSimpleName = field.getType().getSimpleName();
                                switch (propertyTypeSimpleName){
                                    case "byte":
                                        actualValue = Byte.parseByte(value);
                                        break;
                                    case "short":
                                        actualValue = Short.parseShort(value);
                                        break;
                                    case "int":
                                        actualValue = Integer.parseInt(value);
                                        break;
                                    case "long":
                                        actualValue = Long.parseLong(value);
                                        break;
                                    case "float":
                                        actualValue = Float.parseFloat(value);
                                        break;
                                    case "double":
                                        actualValue = Double.parseDouble(value);
                                        break;
                                    case "boolean":
                                        actualValue = Boolean.parseBoolean(value);
                                        break;
                                    case "char":
                                        actualValue = value.charAt(0);
                                        break;
                                    case "Byte":
                                        actualValue = Byte.valueOf(value);
                                        break;
                                    case "Short":
                                        actualValue = Short.valueOf(value);
                                        break;
                                    case "Integer":
                                        actualValue = Integer.valueOf(value);
                                        break;
                                    case "Long":
                                        actualValue = Long.valueOf(value);
                                        break;
                                    case "Float":
                                        actualValue = Float.valueOf(value);
                                        break;
                                    case "Double":
                                        actualValue = Double.valueOf(value);
                                        break;
                                    case "Boolean":
                                        actualValue = Boolean.valueOf(value);
                                        break;
                                    case "Character":
                                        actualValue = value.charAt(0);
                                        break;
                                    case "String":
                                        actualValue = value;
                                        break;
                                }
                                setMethod.invoke(singletonObjects.get(id),actualValue);
                            }
                            if (ref != null) {
                                // 说明这个值是非简单类型
                                // 调用set方法
                                setMethod.invoke(singletonObjects.get(id),singletonObjects.get(ref));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
