package com.shirongbao.spring6.bean;

/**
 * Bean生命周期按照粗略的五步:
 *      第一步:    实例化Bean(调用无参构造方法)
 *      第二步:    给Bean属性赋值(调用Set方法)
 *      第三步:    初始化Bean(会调用Bean的init方法.这个方法要自己写自己配)
 *      第四步:    使用Bean
 *      第五步:    销毁Bean(会调用Bean的destroy方法.这个方法要自己写自己配)
 */
public class User {
    private String name;

    public void setName(String name) {
        System.out.println("第二步,给对象的属性赋值");
        this.name = name;
    }

    public User() {
        System.out.println("第一步,无参构造方法执行");
    }

    public void initBean(){
        System.out.println("第四步,初始化Bean User");
    }

    public void destroyBean(){
        System.out.println("第七步,销毁Bean User");
    }
}
