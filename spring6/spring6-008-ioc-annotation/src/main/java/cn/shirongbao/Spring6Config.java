package cn.shirongbao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 全注解开发

/**
 * 专门写一个类
 * @Configuration 注解表示指定为配置类
 * @ComponentScan 参数指定包
 */
@Configuration
@ComponentScan({"cn.shirongbao.dao", "cn.shirongbao.service"})
public class Spring6Config {
}
