package com.shirongbao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



// 标注注解的解释,叫做元注解.@Target注解用来修饰@Component可以出现的位置
@Target(value = {ElementType.FIELD, ElementType.TYPE})
// 表示注解最后可以在class文件中存在
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value();
}
