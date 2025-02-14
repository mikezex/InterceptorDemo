package com.zxwang.zexx.pojo;

import java.math.BigDecimal;

// 还款类
class Repayment {

    // 期数
    private int period;
    // 每月还款本金
    private BigDecimal monthlyPrincipal;
    // 每月还款利息
    private BigDecimal monthlyInterest;
    // 剩余本金
    private BigDecimal remainingPrincipal;
    // 每月还款金额
    private BigDecimal monthlyPayment;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public BigDecimal getMonthlyPrincipal() {
        return monthlyPrincipal;
    }

    public void setMonthlyPrincipal(BigDecimal monthlyPrincipal) {
        this.monthlyPrincipal = monthlyPrincipal;
    }

    public BigDecimal getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(BigDecimal monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public BigDecimal getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public void setRemainingPrincipal(BigDecimal remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String toString() {
        return period + "\t\t" + monthlyPrincipal + "\t\t" + monthlyInterest + "\t\t" + remainingPrincipal + "\t\t" + monthlyPayment;
    }
}