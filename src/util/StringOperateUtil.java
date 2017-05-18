package util;
/**
 * 字符串操作工具类
 * @Description:TODO
 * @author YanTu
 * @date:2017年5月17日下午5:07:03
 */
public class StringOperateUtil {
	/**
	 * 判断字符串是否存在
	 * @Description:TODO
	 * @author:YanTu
	 * @date:2017年5月17日下午5:07:58
	 */
	public static boolean isExist(String str) {
		if(str != null && !str.equals("")){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否不存在
	 * @Description:TODO
	 * @author:YanTu
	 * @date:2017年5月17日下午5:07:38
	 */
	public static boolean isNotExist(String str){
		if(str == null || "".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串中是否为纯数字
	 * @Description:TODO
	 * @author:YanTu
	 * @date:2017年5月17日下午5:08:10
	 */
	public static boolean isNumber(String inputStr) {
		String regStr = "^[+-]?[1-9][0-9]*$|^0$";
		return inputStr.matches(regStr);
	}
}
