package util;

import java.math.BigDecimal;

import entity.IncomeTax;

/**
 * 税收计算工具类
 * @Description:用于计算个税，和根据个税计算工资奖金
 * @author YanTu
 * @date:2017年5月17日上午10:32:28
 */
public class TaxUtil {
	
	/**
	 * 个税计算方法
	 * @Description:根据工资奖金收入计算个人所得税
	 * @author:YanTu
	 * @date:2017年5月17日上午10:40:53
	 */
	public static IncomeTax getIncomeTaxForSalary(IncomeTax incomeTax){
		//起征点
		double threshold = 3500.0;
		//个税
		double taxes = 0.0;
		//税后收入
		double salaryAfterTax = 0.0;
		//应纳税所得额
		double taxableIncome = incomeTax.getSalaryBeforeTax()  - incomeTax.getSocialInsurance() 
				- incomeTax.getHousingFund() - threshold;
		taxes = countIncomeTaxInChengdu2017(taxableIncome);
		taxes = new BigDecimal(taxes).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		incomeTax.setTaxes(taxes);
		salaryAfterTax = getSalaryAfterTax(incomeTax);
		incomeTax.setSalaryAfterTax(salaryAfterTax);
		return incomeTax;
	}
	
	/**
	 * 计算税后收入
	 * @Description:TODO
	 * @author:YanTu
	 * @date:2017年5月17日上午10:59:08
	 */
	private static double getSalaryAfterTax(IncomeTax incomeTax) {
		double salaryAfterTax = incomeTax.getSalaryBeforeTax() - incomeTax.getSocialInsurance() 
				- incomeTax.getHousingFund() - incomeTax.getTaxes();
		salaryAfterTax = new BigDecimal(salaryAfterTax).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return salaryAfterTax;
	}

	/**
	 * 成都2017个人所得税固定计算方法
	 * @Description:根据目前的计算规则，通过应纳税所得额计算个税
	 * @author:YanTu
	 * @date:2017年5月17日上午10:35:00
	 */
	private static double countIncomeTaxInChengdu2017(double taxableIncome){
		//个人所得税计算公式:应纳税额=应纳税所得额*适用税率-速算扣除数
		double tax = 0.0;
		if(taxableIncome <= 1500){
			tax = taxableIncome * 0.03;
		}else if(taxableIncome > 1500 && taxableIncome <= 4500){
			tax = taxableIncome * 0.1 - 105;
		}else if(taxableIncome > 4500 && taxableIncome <= 9000){
			tax = taxableIncome * 0.2 - 555;
		}else if(taxableIncome > 9000 && taxableIncome <= 35000){
			tax = taxableIncome * 0.25 - 1005;
		}else if(taxableIncome > 35000 && taxableIncome <= 55000){
			tax = taxableIncome * 0.3 - 2755;
		}else if(taxableIncome > 55000 && taxableIncome <= 80000){
			tax = taxableIncome * 0.35 - 5505;
		}else{//超过8万
			tax = taxableIncome * 0.45 - 13505;
		}
		return tax;
	}
}
