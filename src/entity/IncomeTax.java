package entity;

import java.io.Serializable;

/**
 * 个人所得税实体
 * @Description:TODO
 * @author YanTu
 * @date:2017年5月17日上午10:29:26
 */
public class IncomeTax implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private double taxes;//税费
	private double salaryBeforeTax;//税前工资奖金总收入
	private double socialInsurance;//社会保险费
	private double housingFund;//住房公积金
	private double salaryAfterTax;//税后收入
	
	public IncomeTax() {
	}
	
	public IncomeTax(double taxes) {
		this.taxes = taxes;
	}
	
	public IncomeTax(double salaryBeforeTax,double socialInsurance,double housingFund) {
		this.salaryBeforeTax = salaryBeforeTax;
		this.socialInsurance = socialInsurance;
		this.housingFund = housingFund;
	}
	
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getSocialInsurance() {
		return socialInsurance;
	}
	public void setSocialInsurance(double socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	public double getHousingFund() {
		return housingFund;
	}
	public void setHousingFund(double housingFund) {
		this.housingFund = housingFund;
	}

	public double getSalaryBeforeTax() {
		return salaryBeforeTax;
	}

	public void setSalaryBeforeTax(double salaryBeforeTax) {
		this.salaryBeforeTax = salaryBeforeTax;
	}

	public double getSalaryAfterTax() {
		return salaryAfterTax;
	}

	public void setSalaryAfterTax(double salaryAfterTax) {
		this.salaryAfterTax = salaryAfterTax;
	}
	
	
	
}
