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
	
	private Double taxes;//税费
	private Double salaryBeforeTax;//税前工资奖金总收入
	private Double socialInsurance;//社会保险费
	private Double housingFund;//住房公积金
	private Double salaryAfterTax;//税后收入
	
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

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getSalaryBeforeTax() {
		return salaryBeforeTax;
	}

	public void setSalaryBeforeTax(Double salaryBeforeTax) {
		this.salaryBeforeTax = salaryBeforeTax;
	}

	public Double getSocialInsurance() {
		return socialInsurance;
	}

	public void setSocialInsurance(Double socialInsurance) {
		this.socialInsurance = socialInsurance;
	}

	public Double getHousingFund() {
		return housingFund;
	}

	public void setHousingFund(Double housingFund) {
		this.housingFund = housingFund;
	}

	public Double getSalaryAfterTax() {
		return salaryAfterTax;
	}

	public void setSalaryAfterTax(Double salaryAfterTax) {
		this.salaryAfterTax = salaryAfterTax;
	}
	
}
