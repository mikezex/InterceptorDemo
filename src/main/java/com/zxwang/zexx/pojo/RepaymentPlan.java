package com.zxwang.zexx.pojo;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class RepaymentPlan {

    // 贷款本金
    private BigDecimal principal;
    // 贷款年利率
    private BigDecimal annualRate;
    // 贷款期数（月）
    private int periods;
    // 每月还款金额
    private BigDecimal monthlyPayment;
    // 还款计划列表
    private List<Repayment> repayments;

    public RepaymentPlan(BigDecimal principal, BigDecimal annualRate, int periods) {
        this.principal = principal;
        this.annualRate = annualRate;
        this.periods = periods;
        this.repayments = new ArrayList<>();
        this.calculate();
    }

    // 计算还款计划
    private void calculate() {
        // 计算每月还款本金
        BigDecimal monthlyPrincipal = principal.divide(new BigDecimal(periods), 2, BigDecimal.ROUND_HALF_UP);
        // 计算每月利率
        BigDecimal monthlyRate = annualRate.divide(new BigDecimal(12), 6, BigDecimal.ROUND_HALF_UP);
        // 计算每月还款金额
        monthlyPayment = monthlyPrincipal.add(principal.multiply(monthlyRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
        // 计算每期的还款情况
        for (int i = 1; i <= periods; i++) {
            Repayment repayment = new Repayment();
            repayment.setPeriod(i); // 设置期数
            repayment.setMonthlyPrincipal(monthlyPrincipal); // 设置每月还款本金
            // 计算剩余本金
            BigDecimal remainingPrincipal = principal.subtract(monthlyPrincipal.multiply(new BigDecimal(i - 1)));
            repayment.setRemainingPrincipal(remainingPrincipal); // 设置剩余本金
            // 计算每月还款利息
            BigDecimal monthlyInterest = remainingPrincipal.multiply(monthlyRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            repayment.setMonthlyInterest(monthlyInterest); // 设置每月还款利息
            // 计算每月还款金额（最后一期可能有微小误差）
            if (i == periods) {
                monthlyPayment = monthlyPrincipal.add(monthlyInterest).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            repayment.setMonthlyPayment(monthlyPayment); // 设置每月还款金额
            repayments.add(repayment); // 添加到还款计划列表中
        }
    }

    // 打印还款计划表格
    public void print() {
        System.out.println("贷款本金：" + principal + "元，贷款年利率：" + annualRate + "%，贷款期数：" + periods + "个月");
        System.out.println("期数\t\t本金（元）\t\t利息（元）\t\t剩余本金（元）\t\t月供（元）");
        for (Repayment repayment : repayments) {
            System.out.println(repayment);
        }
    }

    public static void main(String[] args) {
        RepaymentPlan plan = new RepaymentPlan(new BigDecimal(10000), new BigDecimal(4.35), 24);
        plan.print();
    }
}

