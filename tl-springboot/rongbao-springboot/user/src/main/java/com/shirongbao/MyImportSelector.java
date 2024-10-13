package com.shirongbao;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ShiRongbao
 * @create 2024/10/8
 * @description:
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "com.shirongbao.webserver.autoconfig.WebServerAutoConfig"
        };
    }
}
