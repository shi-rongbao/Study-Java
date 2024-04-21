package com.shirongbao.spring6.biz;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.shirongbao.spring6.biz"})
@EnableAspectJAutoProxy
public class Spring6Config {
}
