package com.study.mextends2;

public class Manager extends Person{
    private int bonus;

    public Manager() {
    }

    public Manager(String id,String name, int salary ,int managerSalary) {
        super(id,name,salary);
        this.bonus = managerSalary;
    }

    public Manager(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public void work() {
        System.out.println("管理其他人");
    }

    /**
     * 获取
     * @return bonus
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * 设置
     * @param bonus
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String toString() {
        return "Manager{bonus = " + bonus + "}";
    }
}
