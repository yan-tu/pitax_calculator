package test;

import util.TaxUtil;
import entity.IncomeTax;

public class TaxUtilTest {
	public static void main(String[] args) {
		IncomeTax incomeTax = new IncomeTax(8400,230.34,710);
		IncomeTax resultIncomeTax = TaxUtil.getIncomeTaxForSalary(incomeTax);
		System.out.println("你本月应扣个人所得税为:"+resultIncomeTax.getTaxes()+"，税后收入为："+resultIncomeTax.getSalaryAfterTax());
	}
}
