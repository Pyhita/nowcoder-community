package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: pyhita
 * @Date: 2022/2/23
 * @Descrption: com.nowcoder.community.annotation
 * @Version: 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {



}
