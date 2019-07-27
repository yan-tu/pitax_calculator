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
//	private Double socialInsurance;//社会保险费
//	private Double housingFund;//住房公积金
	private Double salaryAfterTax;//税后收入
	private Double specialDeductions;//专项扣除部分（含社保、公积金等）
	private Double specialAdditionalDes;//专项附加扣除部分
	private Double taxableIncome;//应纳税所得额
	
	public IncomeTax() {
	}
	
	public IncomeTax(double taxes) {
		this.taxes = taxes;
	}
	
	public IncomeTax(double salaryBeforeTax,double specialDeductions,double specialAdditionalDes) {
		this.salaryBeforeTax = salaryBeforeTax;
		this.specialDeductions = specialDeductions;
		this.specialAdditionalDes = specialAdditionalDes;
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

	public Double getSalaryAfterTax() {
		return salaryAfterTax;
	}

	public void setSalaryAfterTax(Double salaryAfterTax) {
		this.salaryAfterTax = salaryAfterTax;
	}

	public Double getSpecialDeductions() {
		return specialDeductions;
	}

	public void setSpecialDeductions(Double specialDeductions) {
		this.specialDeductions = specialDeductions;
	}

	public Double getSpecialAdditionalDes() {
		return specialAdditionalDes;
	}

	public void setSpecialAdditionalDes(Double specialAdditionalDes) {
		this.specialAdditionalDes = specialAdditionalDes;
	}
}
