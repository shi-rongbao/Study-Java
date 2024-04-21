package com.shirongbao.spring6.bean;

import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFactory implements FactoryBean<Date> {
    private String strDate;

    public DateFactory(String strDate) {
        this.strDate = strDate;
    }

    @Override
    public Date getObject() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strDate);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
