package com.shirongbao.bank.utils;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 工具类:可以动态生成DAO的实现类(或者说可以动态生成DAO的代理类)
 */
public class GenerateDaoProxy {

    /**
     * 生成DAO接口实现类,并且将实现类的对象创建出来并返回.
     *
     * @param daoInterface dao接口
     * @return dao接口实现类的实例化对象
     */
    public static Object generate(SqlSession sqlSession, Class daoInterface) {
        // 类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy");
        // 制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());

        // 实现接口
        ctClass.addInterface(ctInterface);
        // 实现接口中所有的方法
        Method[] methods = daoInterface.getDeclaredMethods();
        // 将methods数组转换成流遍历
        Arrays.stream(methods).forEach(method -> {
            // 这里每一个method就是一个抽象方法
            try {
                // 用于拼接方法中代码片段的SB对象
                // public Account selectByActno(String actno) {
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 拼接形参列表
                // 拿到所有的形参的类型
                // 这里其实不会出现很多个参数,也就一两个
                Class<?>[] parameterTypes = method.getParameterTypes();
                // 以fori的形式遍历
                for (int i = 0; i < parameterTypes.length; i++) {
                    // 拿到第i个形参类型
                    Class<?> parameterType = parameterTypes[i];
                    // 先追加这个形参类型的名字
                    methodCode.append(parameterType.getName());
                    // 再追加一个空格
                    methodCode.append(" ");
                    // 再追加形参名,不可重复,以arg开头以i结尾  arg0 arg1 arg2 ...
                    methodCode.append("arg" + i);
                    // 如果不是最后一个形参,那么都需要拼接逗号
                    if (i != parameterTypes.length - 1) {
                        methodCode.append(",");
                    }
                }
                methodCode.append(")");
                methodCode.append("{");
                // 拼接代码片段
                methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = com.shirongbao.bank.utils.SqlSessionUtil.openSession();");
                // 根据规定拼接sqlID
                String sqlId = daoInterface.getName() + "." + method.getName();
                // 拿到SQL标签的类型
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                // 根据类型来写sqlSession.xxx()方法
                if (sqlCommandType == SqlCommandType.INSERT) {

                }
                if (sqlCommandType == SqlCommandType.DELETE) {

                }
                if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return sqlSession.update(\"" + sqlId + "\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.SELECT) {
                    methodCode.append("return (" + method.getReturnType().getName() + ") sqlSession.selectOne(\"" + sqlId + "\", arg0);");
                }
                methodCode.append("}");
                // 此方法是用于创建一个方法CtMethod对象  src中写方法的代码片段  declaring是要在哪个类中创建方法
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                // 将ctMethod作为方法添加到ctClass中
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 创建对象
        Object obj = null;
        try {
            Class<?> aClass = ctClass.toClass();
            obj = aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
