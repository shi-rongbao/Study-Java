package com.shirongbao.bank.pojo;

public class Account {
    private Long id;
    private String actno;
    private Double balance;

    public Account() {
    }

    public Account(Long id, String actno, Double balance) {
        this.id = id;
        this.actno = actno;
        this.balance = balance;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return actno
     */
    public String getActno() {
        return actno;
    }

    /**
     * 设置
     * @param actno
     */
    public void setActno(String actno) {
        this.actno = actno;
    }

    /**
     * 获取
     * @return balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置
     * @param balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Account{id = " + id + ", actno = " + actno + ", balance = " + balance + "}";
    }
}
