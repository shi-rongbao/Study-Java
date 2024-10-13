package com.shirongbao.boot3.features;

import com.shirongbao.boot3.features.bean.Cat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@Slf4j
@SpringBootApplication
public class Boot306FeaturesApplication {

    public static void main(String[] args) {
        // SpringApplication 是Boot应用的核心API入口
        ConfigurableApplicationContext context = SpringApplication.run(Boot306FeaturesApplication.class, args);
        Cat cat = context.getBean(Cat.class);

        log.info("组件: {}",cat);
    }

}
