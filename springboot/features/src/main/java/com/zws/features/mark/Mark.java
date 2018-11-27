package com.zws.features.mark;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;


/**
 * 标记注解
 * 作用：只要在定义bean时标记该注解，就可以在依赖注入的时候获取这些被标记的bean
 * 用法详解：{@link MarkConfig,MarkController}
 *
 * 必须加上@Qualifier这个注解，才能被spring认为该注解是标记注解
 */

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD }) //注释可能出现在Java程序中的语法位置
@Retention(RetentionPolicy.RUNTIME) //指定注释要保留多长时间
@Documented
@Inherited  //允许子类继承父类的注解
@Qualifier
/**
 * 此注释可以在字段或参数上用作限定符
 * 自动装配时的候选豆。
 * 它也可以用于注释其他自定义注释，
 * 然后可以用作限定符。
 */
public @interface Mark {
}
