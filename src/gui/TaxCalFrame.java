package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	
	//计算模式选择
	private JRadioButton countTaxRadio = new JRadioButton("根据税前收入计算个人所得税");
	private JRadioButton countSalaryRadio = new JRadioButton("根据个人所得税计算税前收入");
	//输入
	private JTextField salaryField = new JTextField("");
	private JTextField socialInsuranceField = new JTextField("");
	private JTextField housingFundField = new JTextField("");
	private JTextField taxesInputField = new JTextField("");
	
	private JPanel inputPane = new JPanel();
	private JPanel taxesInputPane = new JPanel();
	private JPanel salaryPane = new JPanel();
	//输出
	private JTextField taxesOutPutField = new JTextField("");
	private JTextField salaryAfterTaxField = new JTextField("");
	private JTextField salaryBeforeTaxField = new JTextField("");
	
	private JPanel outPutPane = new JPanel();
	private JPanel taxesOutputPane = new JPanel();
	private JPanel salaryAfterTaxPane = new JPanel();
	private JPanel salaryBeforeTaxPane = new JPanel();
	
	public TaxCalFrame() {
		init();
	}
	/**
	 * @Description:页面初始化
	 * @author:YanTu
	 * @date:2017年5月17日下午2:17:09
	 */
	private void init() {
		setTitle("个税计算器（成都-2017）");
		setSize(280,390);
		setLocationRelativeTo(null);//让窗口居中
		this.setResizable(false);//不可改变大小
		setBackground(Color.white);
		setContentPane(createContentPane());
		initContentPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	/**
	 * @Description:初始化容器
	 * @author:YanTu
	 * @date:2017年5月18日上午10:33:12
	 */
	private void initContentPane() {
		countTaxRadio.setSelected(true);
		inputPane.remove(taxesInputPane);//隐藏个税输入面板
		outPutPane.remove(salaryBeforeTaxPane);//隐藏税前收入输出面板
	}
	/**
	 * @Description:添加内容面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:39:18
	 */
	private JPanel createContentPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(createHeadPane(),"North");
		pane.add(createBottomPane(),"South");
		//pane.add(createOutputPane(),"South");
		pane.setBorder(new EmptyBorder(10,10,10,10));//上，左，下，右
		pane.setBackground(Color.WHITE);
		return pane;
	}
	
	/**
	 * @Description:创建头部面板，包括模式选择、内容输入和计算按钮面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:52:13
	 */
	private Component createHeadPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(createModeSelectPane(),"North");
		pane.add(createInputFieldsPane(),"Center");
		pane.add(createCountButtonAreaPane(),"South");
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
		JPanel pane = new JPanel(new GridLayout(0,1,3,3));
		ButtonGroup bg = new ButtonGroup();
		bg.add(countTaxRadio);
		bg.add(countSalaryRadio);
		pane.add(countTaxRadio);
		pane.add(countSalaryRadio);
		pane.add(createPartingLine());
		countTaxRadio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				taxesInputPane.setVisible(false);
				inputPane.remove(taxesInputPane);
				salaryPane.setVisible(true);
				inputPane.add(salaryPane);
				
				salaryBeforeTaxPane.setVisible(false);
				outPutPane.remove(salaryBeforeTaxPane);
				taxesOutputPane.setVisible(true);
				outPutPane.add(taxesOutputPane);
			}
		});
		countSalaryRadio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				salaryPane.setVisible(false);
				inputPane.remove(salaryPane);
				taxesInputPane.setVisible(true);
				inputPane.add(taxesInputPane);
				
				taxesOutputPane.setVisible(false);
				outPutPane.remove(taxesOutputPane);
				salaryBeforeTaxPane.setVisible(true);
				outPutPane.add(salaryBeforeTaxPane);
			}
		});
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
		GridLayout inputLayout = new GridLayout(0,1,3,3);
		//JPanel pane = new JPanel(new BorderLayout());
		inputPane = new JPanel(inputLayout);
//		JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		inputPane.add(createSocialInsurancePane());
		inputPane.add(createHousingFundPane());
		inputPane.add(createSalaryPane());
		inputPane.add(createTaxesInputPane());
		inputPane.setBorder(new EmptyBorder(15,10,10,10));//上，左，下，右
		inputPane.setBackground(Color.WHITE);
		return inputPane;
	}
	/**
	 * @Description:创建税前收入输入面板
	 * @author:YanTu
	 * @date:2017年5月18日上午9:33:47
	 */
	private Component createSalaryPane() {
		//salaryPane = new JPanel(new GridLayout(1, 2));
		salaryPane = new JPanel(new BorderLayout());
		JLabel salaryLabel = new JLabel("税前收入：");
		salaryField = new JTextField("");
		salaryPane.add(salaryLabel,"West");
		salaryPane.add(salaryField,"Center");
		salaryPane.setBackground(Color.WHITE);
		//salaryPane.setSize(250, 320);
		return salaryPane;
	}
	
	/**
	 * @Description:创建个人所得税输入面板
	 * @author:YanTu
	 * @date:2017年5月18日上午9:47:36
	 */
	private Component createTaxesInputPane() {
		taxesInputPane = new JPanel(new BorderLayout());
		JLabel taxesInputLabel = new JLabel("个人所得税：");
		taxesInputField = new JTextField("");
		taxesInputPane.add(taxesInputLabel,"West");
		taxesInputPane.add(taxesInputField,"Center");
		taxesInputPane.setBackground(Color.WHITE);
		return taxesInputPane;
	}
	
	/**
	 * @Description:创建社保输入面板
	 * @author:YanTu
	 * @date:2017年5月18日上午9:34:03
	 */
	private Component createSocialInsurancePane() {
		JPanel pane = new JPanel(new BorderLayout());
		JLabel socialInsuranceLabel = new JLabel("个人缴纳社保费：");
		socialInsuranceField = new JTextField("");
		pane.add(socialInsuranceLabel,"West");
		pane.add(socialInsuranceField,"Center");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	/**
	 * @Description:创建公积金输入面板
	 * @author:YanTu
	 * @date:2017年5月18日上午9:34:30
	 */
	private Component createHousingFundPane() {
		JPanel pane = new JPanel(new BorderLayout());
		JLabel housingFundLabel = new JLabel("个人缴纳公积金费：");
		housingFundField = new JTextField("");
		pane.add(housingFundLabel,"West");
		pane.add(housingFundField,"Center");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	/**
	 * @Description:创建计算按钮区域面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:51:27
	 */
	private Component createCountButtonAreaPane() {
		JPanel pane = new JPanel(new GridLayout(0,1,3,3));
		pane.add(createCountButtonPane());
		pane.add(createPartingLine());
		pane.setBackground(Color.WHITE);
		return pane;
	}

	/**
	 * @Description:创建计算按钮面板
	 * @author:YanTu
	 * @date:2017年5月18日上午11:19:43
	 */
	private Component createCountButtonPane() {
		JPanel pane = new JPanel();
		JButton count = new JButton("计算");
		getRootPane().setDefaultButton(count);
		pane.add(count);
		count.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String socialInsuranceText =socialInsuranceField.getText();//社保费
				String housingFundText = housingFundField.getText();//公积金费
				double socialInsurance = StringOperateUtil.isNumber(socialInsuranceText)?Double.parseDouble(socialInsuranceText):0.0;
				double housingFund = StringOperateUtil.isNumber(housingFundText)?Double.parseDouble(housingFundText):0.0;
				if(countTaxRadio.isSelected()){//计算个税
					String salaryText = salaryField.getText();
					double salary = StringOperateUtil.isNumber(salaryText)?Double.parseDouble(salaryText):0.0;
					IncomeTax incomeTax = new IncomeTax(salary,socialInsurance,housingFund);
					IncomeTax resultIncomeTax = TaxUtil.getIncomeTaxForSalary(incomeTax);
					if(resultIncomeTax != null){
						taxesOutPutField.setText(String.valueOf(resultIncomeTax.getTaxes()));
						salaryAfterTaxField.setText(String.valueOf(resultIncomeTax.getSalaryAfterTax()));
					}
				}else{//计算收入
					String taxesInputText = taxesInputField.getText();//个人所得税
					double taxes = StringOperateUtil.isNumber(taxesInputText)?Double.parseDouble(taxesInputText):0.0;
					IncomeTax incomeTax = new IncomeTax(taxes);
					incomeTax.setSocialInsurance(socialInsurance);
					incomeTax.setHousingFund(housingFund);
					IncomeTax resultIncomeTax = TaxUtil.getSalaryByTax(incomeTax);
					if(resultIncomeTax != null){
						salaryBeforeTaxField.setText(String.valueOf(resultIncomeTax.getSalaryBeforeTax()));
						salaryAfterTaxField.setText(String.valueOf(resultIncomeTax.getSalaryAfterTax()));
					}
				}
			}
		});
		pane.setBackground(Color.WHITE);
		return pane;
	}
	/**
	 * @Description:创建底部面板，包括输出面板和版权面板
	 * @author:YanTu
	 * @date:2017年5月18日下午2:58:14
	 */
	private Component createBottomPane(){
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(createOutputPane(),"Center");
		pane.add(createCopyRightPane(),"South");
		return pane;
	}
	
	/**
	 * @Description:创建输出面板
	 * @author:YanTu
	 * @date:2017年5月17日下午2:39:45
	 */
	private Component createOutputPane() {
		outPutPane = new JPanel(new GridLayout(0,1,3,3));
		outPutPane.add(createSalaryAfterTaxPanel());
		outPutPane.add(createTaxesPanel());
		outPutPane.add(createSalaryBeforeTaxPanel());
		outPutPane.setBorder(new EmptyBorder(15,10,10,10));//上，左，下，右
		outPutPane.setBackground(Color.WHITE);
		return outPutPane;
	}
	/**
	 * @Description:应扣个人所得税结果面板
	 * @author:YanTu
	 * @date:2017年5月17日下午5:26:57
	 */
	private Component createTaxesPanel() {
		taxesOutputPane = new JPanel(new BorderLayout());
		JLabel taxesLabel = new JLabel("应扣个人所得税为：");
		taxesOutPutField = new JTextField("");
		taxesOutputPane.add(taxesLabel,"West");
		taxesOutputPane.add(taxesOutPutField,"Center");
		taxesOutputPane.setBackground(Color.WHITE);
		return taxesOutputPane;
	}
	/**
	 * @Description:税后收入结果面板
	 * @author:YanTu
	 * @date:2017年5月17日下午5:27:20
	 */
	private Component createSalaryAfterTaxPanel() {
		salaryAfterTaxPane = new JPanel(new BorderLayout());
		JLabel salaryAfterTaxLabel = new JLabel("税后收入为：");
		salaryAfterTaxField = new JTextField("");
		salaryAfterTaxPane.add(salaryAfterTaxLabel,"West");
		salaryAfterTaxPane.add(salaryAfterTaxField,"Center");
		salaryAfterTaxPane.setBackground(Color.WHITE);
		return salaryAfterTaxPane;
	}
	/**
	 * @Description:税前收入结果面板
	 * @author:YanTu
	 * @date:2017年5月18日上午10:50:43
	 */
	private Component createSalaryBeforeTaxPanel() {
		salaryBeforeTaxPane = new JPanel(new BorderLayout());
		JLabel salaryBeforeTaxLabel = new JLabel("税前收入为：");
		salaryBeforeTaxField = new JTextField("");
		salaryBeforeTaxPane.add(salaryBeforeTaxLabel,"West");
		salaryBeforeTaxPane.add(salaryBeforeTaxField,"Center");
		salaryBeforeTaxPane.setBackground(Color.WHITE);
		return salaryBeforeTaxPane;
	}
	
	/**
	 * @Description:创建分割线
	 * @author:YanTu
	 * @date:2017年5月18日上午11:09:56
	 */
	private Component createPartingLine() {
		JLabel partingLine = new JLabel();
		partingLine.setText("_________________________________");
		partingLine.setBorder(new EmptyBorder(0,10,5,10));//上，左，下，右
		return partingLine;
	}
	
	/**
	 * @Description:创建版权信息面板
	 * @author:YanTu
	 * @date:2017年5月18日下午3:02:08
	 */
	private Component createCopyRightPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.setBorder(new EmptyBorder(10,10,0,10));//上，左，下，右
		pane.setBackground(Color.WHITE);
		JLabel copyRightLabel = new JLabel("©2017 Yantu. All Rights Reserved.");
		Font font = new Font("Arial",Font.PLAIN,10 ); 
		copyRightLabel.setFont(font);
		pane.add(copyRightLabel,"East");
		return pane;
	}
}
