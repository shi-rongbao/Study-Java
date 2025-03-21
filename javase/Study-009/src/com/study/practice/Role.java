package com.study.practice;

import java.util.Random;

public class Role {
    private String name;
    private int hp;
    private char gender;
    private String face;
    String[] boyFace = {"风流俊雅", "气宇轩昂", "相貌英俊", "五官端正", "相貌平平", "一塌糊涂", "面目狰狞"};
    String[] girlFace = {"美丽绝伦", "沉鱼落雁", "亭亭玉立", "身材姣好", "相貌平平", "相貌简陋", "惨不忍睹"};

    //attack 攻击描述：
    String[] attacks_desc = {
            "%s使出了一招【背心钉】，转到对方的身后，一掌向%s背心的灵台穴拍去。",
            "%s使出了一招【游空探爪】，飞起身形自半空中变掌为抓锁向%s。",
            "%s大喝一声，身形下伏，一招【劈雷坠地】，捶向%s双腿。",
            "%s运气于掌，一瞬间掌心变得血红，一式【掌心雷】，推向%s。",
            "%s阴手翻起阳手跟进，一招【没遮拦】，结结实实的捶向%s。",
            "%s上步抢身，招中套招，一招【劈挂连环】，连环攻向%s。"
    };

    //injured 受伤描述：
    String[] injureds_desc = {
            "结果%s退了半步，毫发无损",
            "结果给%s造成一处瘀伤",
            "结果一击命中，%s痛得弯下腰",
            "结果%s痛苦地闷哼了一声，显然受了点内伤",
            "结果%s摇摇晃晃，一跤摔倒在地",
            "结果%s脸色一下变得惨白，连退了好几步",
            "结果『轰』的一声，%s口中鲜血狂喷而出",
            "结果%s一声惨叫，像滩软泥般塌了下去"
    };


    public Role(String name, int hp, char gender) {
        this.name = name;
        this.hp = hp;
        this.gender = gender;
        setFace(gender);
    }

    public void attack(Role role) {
        Random r = new Random();
        int index = r.nextInt(attacks_desc.length);
        String kungFu = attacks_desc[index];
        System.out.printf(kungFu, this.getName(), role.name);
        System.out.println();
        int hurt = r.nextInt(20) + 1;  // 1 - 20
        int remainHp = role.getHp() - hurt;  // hp: 3   hurt: 10   remainHp: 0
        if (remainHp < 0) {
            remainHp = 0;
        }
        role.setHp(remainHp);

        if (remainHp >= 90 && remainHp <= 100) {
            System.out.printf(injureds_desc[0], role.name);
        } else if (remainHp >= 80) {
            System.out.printf(injureds_desc[1], role.name);
        } else if (remainHp >= 70) {
            System.out.printf(injureds_desc[2], role.name);
        } else if (remainHp >= 60) {
            System.out.printf(injureds_desc[3], role.name);
        } else if (remainHp >= 40) {
            System.out.printf(injureds_desc[4], role.name);
        } else if (remainHp >= 30) {
            System.out.printf(injureds_desc[5], role.name);
        } else if (remainHp >= 10) {
            System.out.printf(injureds_desc[6], role.name);
        } else{
            System.out.printf(injureds_desc[7], role.name);
        }
        System.out.println();
    }

    public void showRoleInfo() {
        System.out.println("姓名为:" + getName());
        System.out.println("血量为:" + getHp());
        System.out.println("性别为:" + getGender());
        System.out.println("长相为:" + getFace());
    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * 设置
     *
     * @param hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * 获取
     *
     * @return gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * 设置
     *
     * @param gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * 获取
     *
     * @return face
     */
    public String getFace() {
        return face;
    }

    /**
     * 设置
     */
    public void setFace(char gender) {
        Random r = new Random();
        if (gender == '男') {
            int index = r.nextInt(boyFace.length);
            this.face = boyFace[index];
        } else if (gender == '女') {
            int index = r.nextInt(girlFace.length);
            this.face = girlFace[index];
        } else {
            this.face = "不男不女大傻逼";
        }
    }

    /**
     * 获取
     *
     * @return boyFace
     */
    public String[] getBoyFace() {
        return boyFace;
    }

    /**
     * 设置
     *
     * @param boyFace
     */
    public void setBoyFace(String[] boyFace) {
        this.boyFace = boyFace;
    }

    /**
     * 获取
     *
     * @return girlFace
     */
    public String[] getGirlFace() {
        return girlFace;
    }

    /**
     * 设置
     *
     * @param girlFace
     */
    public void setGirlFace(String[] girlFace) {
        this.girlFace = girlFace;
    }
}
