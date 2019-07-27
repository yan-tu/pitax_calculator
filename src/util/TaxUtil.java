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
	
	private static final double THRESHOLD = 5000.0;//起征点
	
	/**
	 * 个税计算方法
	 * @Description:根据工资奖金收入计算个人所得税
	 * @author:YanTu
	 * @date:2017年5月17日上午10:40:53
	 */
	public static IncomeTax getIncomeTaxForSalary(IncomeTax incomeTax){
		//判空
		if(incomeTax == null
				|| incomeTax.getSalaryBeforeTax() == null
				|| incomeTax.getSpecialDeductions() == null
				|| incomeTax.getSpecialAdditionalDes() == null){
			return null;
		}
		//个税
		double taxes = 0.0;
		//税后收入
		double salaryAfterTax = 0.0;
		//应纳税所得额
		double taxableIncome = incomeTax.getSalaryBeforeTax()  - incomeTax.getSpecialDeductions()
				- incomeTax.getSpecialAdditionalDes() - THRESHOLD;
		taxes = countIncomeTaxInChengdu2019(taxableIncome);
		//taxes = new BigDecimal(taxes).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		incomeTax.setTaxes(taxes);
		salaryAfterTax = getSalaryAfterTax(incomeTax);
		incomeTax.setSalaryAfterTax(salaryAfterTax);
		return incomeTax;
	}
	
	/**
	 * 税前工资计算方法
	 * @Description:通过个人所得税计算税前工资
	 * @author:YanTu
	 * @date:2017年5月18日下午2:05:44
	 */
	public static IncomeTax getSalaryByTax(IncomeTax incomeTax){
		//判空
		if(incomeTax == null
				|| incomeTax.getTaxes() == null
				|| incomeTax.getSpecialDeductions() == null
				|| incomeTax.getSpecialAdditionalDes() == null){
			return null;
		}
		//税前收入
		double salaryBeforeTax = 0.0;
		//税后收入
		double salaryAfterTax = 0.0;
		//应纳税所得额
		double taxableIncome = countTaxableIncomeByTaxInChengdu2019(incomeTax.getTaxes());
		salaryBeforeTax = taxableIncome + incomeTax.getSpecialDeductions()
				+ incomeTax.getSpecialAdditionalDes() + THRESHOLD;
		salaryBeforeTax = new BigDecimal(salaryBeforeTax).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		incomeTax.setSalaryBeforeTax(salaryBeforeTax);
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
		double salaryAfterTax = incomeTax.getSalaryBeforeTax() - incomeTax.getSpecialDeductions() - incomeTax.getTaxes();
		salaryAfterTax = new BigDecimal(salaryAfterTax).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return salaryAfterTax;
	}

	/**
	 * 成都2019个人所得税固定计算方法
	 * @Description:根据目前的计算规则，通过应纳税所得额计算个税
	 * @author:YanTu
	 * @date:2017年5月17日上午10:35:00
	 */
	private static double countIncomeTaxInChengdu2019(double taxableIncome){
		//个人所得税计算公式:应纳税额=应纳税所得额*适用税率
		double tax = 0.0;
		if(taxableIncome <= 0){
			return tax;
		}else if(taxableIncome > 0 && taxableIncome <= 3000){
			tax = taxableIncome * 0.03;
		}else if(taxableIncome > 3000 && taxableIncome <= 12000){
			tax = taxableIncome * 0.1 - 210;
		}else if(taxableIncome > 12000 && taxableIncome <= 25000){
			tax = taxableIncome * 0.2 - 1410;
		}else if(taxableIncome > 25000 && taxableIncome <= 35000){
			tax = taxableIncome * 0.25 - 2660;
		}else if(taxableIncome > 35000 && taxableIncome <= 55000){
			tax = taxableIncome * 0.3 - 4410;
		}else if(taxableIncome > 55000 && taxableIncome <= 80000){
			tax = taxableIncome * 0.35 - 7160;
		}else{//超过8万
			tax = taxableIncome * 0.45 - 15160;
		}
		return new BigDecimal(tax).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 成都2019根据个人所得税反算应纳税所得额固定计算方法
	 * @Description:通过个人所得税反算应纳税所得额，规则与个人所得税计算规则相同
	 * @author:YanTu
	 * @date:2017年5月18日下午1:53:14
	 */
	private static double countTaxableIncomeByTaxInChengdu2019(double tax){
		double taxableIncome = 0.0;
		if(tax <= 0){
			return taxableIncome;
		}else if(tax> 0 && tax <= 90){
			taxableIncome = tax / 0.03;
		}else if(tax >90 && tax <= 1200){
			taxableIncome = (tax+210)  * 10;
		}else if(tax > 1200 && tax <= 5000){
			taxableIncome = (tax+1410) * 5;
		}else if(tax > 5000 && tax <= 8750){
			taxableIncome = (tax+2660) * 4;
		}else if(tax > 8750 && tax <= 16500){
			taxableIncome = (tax+4410) / 0.03;
		}else if(tax >16500 && tax <=28000){
			taxableIncome = (tax+7160) / 0.35;
		}else{
			taxableIncome = (tax+15160)  / 0.45;
		}
		return new BigDecimal(taxableIncome).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
