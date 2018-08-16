package util;

import java.math.BigDecimal;

import entity.IncomeTax;

/**
 * ˰�ռ��㹤����
 * @Description:���ڼ����˰���͸��ݸ�˰���㹤�ʽ���
 * @author YanTu
 * @date:2017��5��17������10:32:28
 */
public class TaxUtil {
	
	private static final double THRESHOLD = 3500.0;//������
	
	/**
	 * ��˰���㷽��
	 * @Description:���ݹ��ʽ�����������������˰
	 * @author:YanTu
	 * @date:2017��5��17������10:40:53
	 */
	public static IncomeTax getIncomeTaxForSalary(IncomeTax incomeTax){
		//�п�
		if(incomeTax == null || incomeTax.getSalaryBeforeTax() == null ||
				incomeTax.getSocialInsurance() == null || incomeTax.getHousingFund() == null){
			return null;
		}
		//��˰
		double taxes = 0.0;
		//˰������
		double salaryAfterTax = 0.0;
		//Ӧ��˰���ö�
		double taxableIncome = incomeTax.getSalaryBeforeTax()  - incomeTax.getSocialInsurance() 
				- incomeTax.getHousingFund() - THRESHOLD;
		taxes = countIncomeTaxInChengdu2017(taxableIncome);
		//taxes = new BigDecimal(taxes).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		incomeTax.setTaxes(taxes);
		salaryAfterTax = getSalaryAfterTax(incomeTax);
		incomeTax.setSalaryAfterTax(salaryAfterTax);
		return incomeTax;
	}
	
	/**
	 * ˰ǰ���ʼ��㷽��
	 * @Description:ͨ����������˰����˰ǰ����
	 * @author:YanTu
	 * @date:2017��5��18������2:05:44
	 */
	public static IncomeTax getSalaryByTax(IncomeTax incomeTax){
		//�п�
		if(incomeTax == null || incomeTax.getTaxes() == null ||
				incomeTax.getSocialInsurance() == null || incomeTax.getHousingFund() == null){
			return null;
		}
		//˰ǰ����
		double salaryBeforeTax = 0.0;
		//˰������
		double salaryAfterTax = 0.0;
		//Ӧ��˰���ö�
		double taxableIncome = countTaxableIncomeByTaxInChengdu2017(incomeTax.getTaxes());
		salaryBeforeTax = taxableIncome + incomeTax.getSocialInsurance() 
				+ incomeTax.getHousingFund() + THRESHOLD;
		salaryBeforeTax = new BigDecimal(salaryBeforeTax).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		incomeTax.setSalaryBeforeTax(salaryBeforeTax);
		salaryAfterTax = getSalaryAfterTax(incomeTax);
		incomeTax.setSalaryAfterTax(salaryAfterTax);
		return incomeTax;
	}
	
	/**
	 * ����˰������
	 * @Description:TODO
	 * @author:YanTu
	 * @date:2017��5��17������10:59:08
	 */
	private static double getSalaryAfterTax(IncomeTax incomeTax) {
		double salaryAfterTax = incomeTax.getSalaryBeforeTax() - incomeTax.getSocialInsurance() 
				- incomeTax.getHousingFund() - incomeTax.getTaxes();
		salaryAfterTax = new BigDecimal(salaryAfterTax).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return salaryAfterTax;
	}

	/**
	 * �ɶ�2017��������˰�̶����㷽��
	 * @Description:����Ŀǰ�ļ������ͨ��Ӧ��˰���ö�����˰
	 * @author:YanTu
	 * @date:2017��5��17������10:35:00
	 */
	private static double countIncomeTaxInChengdu2017(double taxableIncome){
		//��������˰���㹫ʽ:Ӧ��˰��=Ӧ��˰���ö�*����˰��-����۳���
		double tax = 0.0;
		if(taxableIncome <= 0){
			return tax;
		}else if(taxableIncome > 0 && taxableIncome <= 1500){
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
		}else{//����8��
			tax = taxableIncome * 0.45 - 13505;
		}
		return new BigDecimal(tax).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * �ɶ�2017���ݸ�������˰����Ӧ��˰���ö�̶����㷽��
	 * @Description:ͨ����������˰����Ӧ��˰���ö�������������˰���������ͬ
	 * @author:YanTu
	 * @date:2017��5��18������1:53:14
	 */
	private static double countTaxableIncomeByTaxInChengdu2017(double tax){
		double taxableIncome = 0.0;
		if(tax <= 0){
			return taxableIncome;
		}else if(tax> 0 && tax <= 45){
			taxableIncome = tax / 0.03;
		}else if(tax >45 && tax <= 345){
			taxableIncome = (tax + 105) * 10;
		}else if(tax > 345 && tax <= 1245){
			taxableIncome = (tax + 555) * 5;
		}else if(tax > 1245 && tax <= 7745){
			taxableIncome = (tax + 1005) * 4;
		}else if(tax > 7745 && tax <= 13745){
			taxableIncome = (tax + 2755) / 0.03;
		}else if(tax >13745 && tax <=22495){
			taxableIncome = (tax + 5505) / 0.35;
		}else{
			taxableIncome = (tax + 13505) / 0.45;
		}
		return new BigDecimal(taxableIncome).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
