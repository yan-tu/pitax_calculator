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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.deploy.util.StringUtils;
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
	private JTextField specialDeductionsField = new JTextField("");
	private JTextField specialAdditionalDesField = new JTextField("");
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
		setTitle("个税计算器（成都-2019）");
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
		inputPane = new JPanel(inputLayout);
		inputPane.add(createSpecialDeductionsPane());
		inputPane.add(createSpecialAdditionalDesPane());
		inputPane.add(createTaxesInputPane());
		inputPane.add(createSalaryPane());

//		inputPane.add(createInputOrOutPutPane("专项扣除（五险一金等）：",specialDeductionsField,"","（元）"));
//		inputPane.add(createInputOrOutPutPane("专项附加扣除：",specialAdditionalDesField,"","（元）"));
//		taxesInputPane = createInputOrOutPutPane("个人所得税：",taxesInputField,"","（元）");
//		salaryPane = createInputOrOutPutPane("税前收入：",salaryField,"","（元）");
//		inputPane.add(taxesInputPane);
//		inputPane.add(salaryPane);
		inputPane.setBorder(new EmptyBorder(15,10,10,10));//上，左，下，右
		inputPane.setBackground(Color.WHITE);
		return inputPane;
	}

	/**
	*@description: 创建输入输出面板
	*@param prefixLabel 前置文字提示面
	 *@param textField 输出框
	*@param defaultTextField 输入框默认值
	*@param suffixLabel 后置文字提示面板
	*@return: java.awt.Component
	*@author: yan-tu
	*@createTime: 2019/07/27 17:37
	*@version:
	*/
	private JPanel createInputOrOutPutPane(String prefixLabel,JTextField textField,String defaultText,String suffixLabel) {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(new JLabel(prefixLabel),"West");
		textField = new JTextField(defaultText);
		pane.add(textField,"Center");
		pane.add(new JLabel(suffixLabel),"East");
		pane.setBackground(Color.WHITE);
		return pane;
	}
	
	/**
	 * @Description:创建税前收入输入面板
	 * @author:YanTu
	 * @date:2017年5月18日上午9:33:47
	 */
	private Component createSalaryPane() {
		salaryPane = new JPanel(new BorderLayout());
		salaryField = new JTextField("");
		salaryPane.add(new JLabel("税前收入："),"West");
		salaryPane.add(salaryField,"Center");
		salaryPane.add(new JLabel("（元）"),"East");
		salaryPane.setBackground(Color.WHITE);
		return salaryPane;
	}
	
	/**
	 * @Description:创建个人所得税输入面板
	 * @author:YanTu
	 * @date:2017年5月18日上午9:47:36
	 */
	private Component createTaxesInputPane() {
		taxesInputPane = new JPanel(new BorderLayout());
		taxesInputField = new JTextField("");
		taxesInputPane.add(new JLabel("个人所得税："),"West");
		taxesInputPane.add(taxesInputField,"Center");
		taxesInputPane.add(new JLabel("（元）"),"East");
		taxesInputPane.setBackground(Color.WHITE);
		return taxesInputPane;
	}
	
	/**
	*
	*@description: 创建专项扣除输入面板
	No such property: code for class: Script1
	*@return: java.awt.Component
	*@author: yan-tu
	*@createTime: 2019/07/27 17:11
	*@version:  
	*/
	private Component createSpecialDeductionsPane(){
		JPanel pane = new JPanel(new BorderLayout());
		specialDeductionsField = new JTextField("1520");
		pane.add(new JLabel("专项扣除（五险一金）："),"West");
		pane.add(specialDeductionsField,"Center");
		pane.add(new JLabel("（元）"),"East");
		pane.setBackground(Color.white);
		return pane;
	}
	
	/**
	*@description: 创建专项附加扣除输入面板
	*@param
	*@return: java.awt.Component
	*@author: yan-tu
	*@createTime: 2019/07/27 18:09
	*@version:  
	*/
	private Component createSpecialAdditionalDesPane(){
		JPanel pane = new JPanel(new BorderLayout());
		specialAdditionalDesField= new JTextField("1000");
		pane.add(new JLabel("专项附加扣除："),"West");
		pane.add(specialAdditionalDesField,"Center");
		pane.add(new JLabel("（元）"),"East");
		pane.setBackground(Color.white);
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
				String specialDeductionsText =specialDeductionsField.getText().trim();//社保费
				String specialAdditionalDesText = specialAdditionalDesField.getText().trim();//公积金费
				if(!StringOperateUtil.isNumber(specialDeductionsText) && StringOperateUtil.isNotEmpty(specialDeductionsText)){
					JOptionPane.showMessageDialog(null, "请正确输入!");
					return;
				}
				if(!StringOperateUtil.isNumber(specialAdditionalDesText) && StringOperateUtil.isNotEmpty(specialAdditionalDesText)){
					JOptionPane.showMessageDialog(null, "请正确输入!");
					return;
				}
				double specialDeductions = StringOperateUtil.isEmpty(specialDeductionsText)?0.0:Double.parseDouble(specialDeductionsText);
				double specialAdditionalDes = StringOperateUtil.isEmpty(specialAdditionalDesText)?0.0:Double.parseDouble(specialAdditionalDesText);
				specialDeductionsField.setText(String.valueOf(specialDeductions));
				specialAdditionalDesField.setText(String.valueOf(specialAdditionalDes));
				if(countTaxRadio.isSelected()){//计算个税
					String salaryText = salaryField.getText().trim();
					if(!StringOperateUtil.isNumber(salaryText) && StringOperateUtil.isNotEmpty(salaryText)){
						JOptionPane.showMessageDialog(null, "请正确输入!");
						return;
					}
					double salary = StringOperateUtil.isEmpty(salaryText)?0.0:Double.parseDouble(salaryText);
					salaryField.setText(String.valueOf(salary));

					IncomeTax incomeTax = new IncomeTax(salary,specialDeductions,specialAdditionalDes);
					IncomeTax resultIncomeTax = TaxUtil.getIncomeTaxForSalary(incomeTax);

					taxesOutPutField.setText(String.valueOf(resultIncomeTax.getTaxes()));
					salaryAfterTaxField.setText(String.valueOf(resultIncomeTax.getSalaryAfterTax()));
				}else{//计算收入
					String taxesInputText = taxesInputField.getText().trim();//个人所得税
					if(!StringOperateUtil.isNumber(taxesInputText) && StringOperateUtil.isNotEmpty(taxesInputText)){
						JOptionPane.showMessageDialog(null, "请正确输入!");
						return;
					}
					double taxes = StringOperateUtil.isEmpty(taxesInputText)?0.0:Double.parseDouble(taxesInputText);
					taxesInputField.setText(String.valueOf(taxes));

					IncomeTax incomeTax = new IncomeTax(taxes);
					incomeTax.setSpecialDeductions(specialDeductions);
					incomeTax.setSpecialAdditionalDes(specialAdditionalDes);
					IncomeTax resultIncomeTax = TaxUtil.getSalaryByTax(incomeTax);
					salaryBeforeTaxField.setText(String.valueOf(resultIncomeTax.getSalaryBeforeTax()));
					salaryAfterTaxField.setText(String.valueOf(resultIncomeTax.getSalaryAfterTax()));
				}
//				if(isAbnormalInput){//提示输入异常
//					new Thread() {
//						public void run() {
//							try {
//								Thread.sleep(2000);
//							}catch (InterruptedException e1) {
//								e1.printStackTrace();
//							}
//							JOptionPane.getRootFrame().dispose();
//						}
//					}.start();
//					JOptionPane.showMessageDialog(null, "请正确输入!");
//				}
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
		taxesOutPutField = new JTextField("");
		taxesOutputPane.add(new JLabel("应扣个人所得税为："),"West");
		taxesOutputPane.add(taxesOutPutField,"Center");
		taxesOutputPane.add(new JLabel("（元）"),"East");
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
		salaryAfterTaxField = new JTextField("");
		salaryAfterTaxPane.add(new JLabel("税后收入为："),"West");
		salaryAfterTaxPane.add(salaryAfterTaxField,"Center");
		salaryAfterTaxPane.add(new JLabel("（元）"),"East");
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
		salaryBeforeTaxField = new JTextField("");
		salaryBeforeTaxPane.add(new JLabel("税前收入为："),"West");
		salaryBeforeTaxPane.add(salaryBeforeTaxField,"Center");
		salaryBeforeTaxPane.add(new JLabel("（元）"),"Center");
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
		JLabel copyRightLabel = new JLabel("©2019 Yantu. All Rights Reserved.");
		Font font = new Font("Arial",Font.PLAIN,10 ); 
		copyRightLabel.setFont(font);
		pane.add(copyRightLabel,"East");
		return pane;
	}
}
