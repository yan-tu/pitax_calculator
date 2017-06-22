package test;

import util.TaxUtil;
import entity.IncomeTax;

public class TaxUtilTest {
	public static void main(String[] args) {
		IncomeTax incomeTax = new IncomeTax(7350,230.34,120);
		IncomeTax resultIncomeTax = TaxUtil.getIncomeTaxForSalary(incomeTax);
		System.out.println("你本月应扣个人所得税为:"+resultIncomeTax.getTaxes()+"，税后收入为："+resultIncomeTax.getSalaryAfterTax());

//		IncomeTax incomeTax = new IncomeTax(1200);//通过个税构造对象
//		incomeTax.setSocialInsurance(300.0);//社保费
//		incomeTax.setHousingFund(600.0);//公积金费用
//		IncomeTax resultIncomeTax = TaxUtil.getSalaryByTax(incomeTax);
//		System.out.println("你本月税前收入为:"+resultIncomeTax.getSalaryBeforeTax()+"，税后收入为："+resultIncomeTax.getSalaryAfterTax());
	}
}
