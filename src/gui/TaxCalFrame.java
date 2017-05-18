package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.StringOperateUtil;
import util.TaxUtil;
import entity.IncomeTax;

/**
 * 个税计算器总窗体
 * @Description:展示个税计算器的所有内容
 * @author YanTu
 * @date:2017年5月17日下午2:12:03
 */
public class TaxCalFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JRadioButtonMenuItem countTaxRadio = new JRadioButtonMenuItem("计算个人所得税");
	private JRadioButtonMenuItem countSalaryRadio = new JRadioButtonMenuItem("计算税前收入");
	//输入
	private JLabel salaryLabel = new JLabel("");
	private JLabel socialInsuranceLabel = new JLabel("");
	private JLabel housingFundLabel = new JLabel("");
	private JTextField salaryField = new JTextField("");
	private JTextField socialInsuranceField = new JTextField("");
	private JTextField housingFundField = new JTextField("");
	//输出
	private JLabel taxesLabel = new JLabel("");
	private JLabel salaryAfterTaxLabel = new JLabel("");
	private JTextField taxesField = new JTextField("");
	private JTextField salaryAfterTaxField = new JTextField("");
	public TaxCalFrame() {
		init();
	}
	/**
	 * @Description:页面初始化
	 * @author:YanTu
	 * @date:2017年5月17日下午2:17:09
	 */
	private void init() {
		setTitle("个税计算器");
		setBounds(450,150,280,350);
		setBackground(Color.white);
		setContentPane(createContentPane());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	/**
	 * @Description:添加输入面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:39:18
	 */
	private JPanel createContentPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(createInputContentPane(),"North");
		pane.add(createOutputPane(),"South");
		pane.setBorder(new EmptyBorder(10,10,10,10));//上，左，下，右
		pane.setBackground(Color.WHITE);
		return pane;
	}
	
	/**
	 * @Description:创建输入内容面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:52:13
	 */
	private Component createInputContentPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(createModeSelectPane(),"North");
		pane.add(createInputFieldsPane(),"Center");
		pane.add(createCountButtonPane(),"South");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	/**
	 * 
	 * @Description:创建模式选择面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:54:30
	 */
	private Component createModeSelectPane() {
		JPanel pane = new JPanel(new GridLayout(2,1,3,3));
		ButtonGroup bg = new ButtonGroup();
		bg.add(countTaxRadio);
		bg.add(countSalaryRadio);
		pane.add(countTaxRadio);
		pane.add(countSalaryRadio);
//		countTaxRadio.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(countTaxRadio.equals(e.getSource())){
//					JLabel salaryLabel = new JLabel("税前工资收入");
//					pane.add(salaryLabel,"South");
//				}
//			}
//			private Component countTaxFieldsPane() {
//				JPanel pane = new JPanel(new BorderLayout());
//				JLabel salaryLabel = new JLabel("税前工资收入");
//				JTextField salaryField = new JTextField("税前工资收入");
//				pane.add(salaryLabel,"North");
//				pane.add(salaryField,"Center");
//				return pane;
//			}
//		});
		//JRadioButton radio = new JRadioButton("计算个人所得税");
		//pane.add(radio);
		countTaxRadio.setBackground(Color.WHITE);
		countSalaryRadio.setBackground(Color.WHITE);
		pane.setBackground(Color.WHITE);
		return pane;
	}
	/**
	 * @Description:创建输入值面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:51:58
	 */
	private Component createInputFieldsPane() {
		//JPanel pane = new JPanel(new BorderLayout());
		JPanel pane = new JPanel(new GridLayout(3,1,3,3));
		pane.add(createSalaryPane());
		pane.add(createSocialInsurancePane());
		pane.add(createHousingFundPane());
		pane.setBorder(new EmptyBorder(30,10,30,10));//上，左，下，右
		pane.setBackground(Color.WHITE);
		return pane;
	}
	
	private Component createSalaryPane() {
		JPanel pane = new JPanel(new BorderLayout());
		salaryLabel = new JLabel("税前收入：");
		salaryField = new JTextField("");
		pane.add(salaryLabel,"West");
		pane.add(salaryField,"Center");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	private Component createSocialInsurancePane() {
		JPanel pane = new JPanel(new BorderLayout());
		socialInsuranceLabel = new JLabel("社保：");
		socialInsuranceField = new JTextField("");
		pane.add(socialInsuranceLabel,"West");
		pane.add(socialInsuranceField,"Center");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	private Component createHousingFundPane() {
		JPanel pane = new JPanel(new BorderLayout());
		housingFundLabel = new JLabel("公积金：");
		housingFundField = new JTextField("");
		pane.add(housingFundLabel,"West");
		pane.add(housingFundField,"Center");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	/**
	 * @Description:创建计算按钮面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:51:27
	 */
	private Component createCountButtonPane() {
		JPanel pane = new JPanel();
		//pane.setSize(300, 30);
		JButton count = new JButton("计算");
		pane.add(count);
		count.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String salaryText = salaryField.getText();
				String socialInsuranceText =socialInsuranceField.getText();
				String housingFundText = housingFundField.getText();
				double salary = StringOperateUtil.isExist(salaryText)?Double.parseDouble(salaryText):0.0;
				double socialInsurance = StringOperateUtil.isExist(socialInsuranceText)?Double.parseDouble(socialInsuranceText):0.0;
				double housingFund = StringOperateUtil.isExist(housingFundText)?Double.parseDouble(housingFundText):0.0;
				IncomeTax incomeTax = new IncomeTax(salary,socialInsurance,housingFund);
				IncomeTax resultIncomeTax = TaxUtil.getIncomeTaxForSalary(incomeTax);
				taxesField.setText(String.valueOf(resultIncomeTax.getTaxes()));
				salaryAfterTaxField.setText(String.valueOf(resultIncomeTax.getSalaryAfterTax()));
			}
		});
		pane.setBackground(Color.WHITE);
		return pane;
	}

	/**
	 * @Description:添加右侧输出面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:39:45
	 */
	private Component createOutputPane() {
		JPanel pane = new JPanel(new GridLayout(2,1,3,3));
		pane.add(createTaxesPanel());
		pane.add(createSalaryAfterTaxPanel());
		pane.setBackground(Color.WHITE);
		return pane;
	}
	/**
	 * @Description:应扣个人所得税面板
	 * @author:YanTu
	 * @date:2017年5月17日下午5:26:57
	 */
	private Component createTaxesPanel() {
		JPanel pane = new JPanel(new BorderLayout());
		taxesLabel = new JLabel("你本月应扣个人所得税为：");
		taxesField = new JTextField("");
		pane.add(taxesLabel,"West");
		pane.add(taxesField,"Center");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	/**
	 * @Description:税后收入面板
	 * @author:YanTu
	 * @date:2017年5月17日下午5:27:20
	 */
	private Component createSalaryAfterTaxPanel() {
		JPanel pane = new JPanel(new BorderLayout());
		salaryAfterTaxLabel = new JLabel("税后收入为：");
		salaryAfterTaxField = new JTextField("");
		pane.add(salaryAfterTaxLabel,"West");
		pane.add(salaryAfterTaxField,"Center");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	
}
