package com.sing.ren.common;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.JSONParser;

public class CommonTools {
	
	public static String[] dateFormat = {
			"yyyy/MM/dd",
			"yyyyMMddHHmmss",
			"yyyy/MM/dd HH:mm:ss",
			"yyyy/MM/dd HH",
			"0.00"
	};
	
	private static DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	private static DateFormat dms = new SimpleDateFormat("yyyyMMddHHmmss");
	private static DateFormat dft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static DateFormat dh = new SimpleDateFormat("yyyy/MM/dd HH");
	private static NumberFormat nf = new DecimalFormat("0.00");
	
	private static String SUN = "Sun";
	private static String MON = "Mon";
	private static String TUE = "Tue";
	private static String WED = "Wed";
	private static String THUS = "Thus";
	private static String FRI = "Fri";
	private static String SAT = "Sat";
	
	/**
	 * 取得傳入的日期和當時相差幾分
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static long countDifferenceDate(String strDate , String dateFormat) throws ParseException {
		long longDate = CommonTools.getMillisecondForString(strDate,dateFormat);
		long longCurrent = CommonTools.getMillisecondForDate(new Date(),dateFormat);
		int diffMinute = (int)((longCurrent - longDate)/1000/60);
		return diffMinute;
	}
	
	/**
	 * 取得傳入的日期和當時相差幾分
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static int countNowDifferenceDay(String strDate) throws ParseException {
		long compareTime = CommonTools.getMillisecondForString(strDate,"yyyy/MM/dd");
		long nowTime = CommonTools.getMillisecondForDate(new Date(),"yyyy/MM/dd");
		int diffDay = (int)((nowTime - compareTime)/1000/60/60/24);
		return diffDay;
	}
	
	/**
	 * 取得以傳入的日期增加n小時的日期-格式:yyyy
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static String getAddHourAfterDate(String srcDate, int hour, String dateFormat) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(CommonTools.getMillisecondForString(srcDate, dateFormat)));
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		Date date = calendar.getTime();
		String strDate = new SimpleDateFormat(dateFormat).format(date);
		return strDate;
	}
	/**
	 * 取得以傳入的日期增加n月的日期-格式:yyyy
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static String getAddMonthAfterDate(String srcDate, int month, String dateFormat) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(CommonTools.getMillisecondForString(srcDate, dateFormat)));
		calendar.add(Calendar.MONTH, month);
		Date date = calendar.getTime();
		String strDate = new SimpleDateFormat(dateFormat).format(date);
		return strDate;
	}

	/**
	 * 取得以傳入的日期增加n日的日期-格式
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static String getAddDayAfterDate(String srcDate, int day, String dateFormat) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(CommonTools.getMillisecondForString(srcDate, dateFormat)));
		calendar.add(Calendar.DATE, day);
		Date date = calendar.getTime();
		String strDate = new SimpleDateFormat(dateFormat).format(date);
		return strDate;
	}
	
	/**
	 * 取得以傳入的日期增加n日的日期-格式
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static Date countAddDayAfterDate(String srcDate, int day, String dateFormat) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(CommonTools.getMillisecondForString(srcDate, dateFormat)));
		calendar.add(Calendar.DATE, day);
		
		return calendar.getTime();
	}
	
	/**
	 * 將字串日期轉換成Date
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static Date getStrDateChangeDate(String srcDate, String dateFormat) throws ParseException {
		return new Date(CommonTools.getMillisecondForString(srcDate, dateFormat));
	}
	
	public static Date getStrDateChangeDate(String date,String time, String dateFormat) throws ParseException {
		return new Date(CommonTools.getMillisecondForString(date.concat(" ").concat(time), dateFormat));
	}

	/**
	 * 取現在的前N年為西元幾年-格式:yyyy
	 * 
	 * @return String
	 */
	public static String getAgoNYear(int agoN) {
		int year = Integer.parseInt(CommonTools.getCurrentYear());
		year -= agoN;
		return year + "";
	}

	/**
	 * 取現在到前N年清單-格式:yyyy
	 * 
	 * @return String
	 */
	public static List<String> getAgoNYearList(int agoN) {
		List<String> yearList = new ArrayList<String>();
		int year = Integer.parseInt(CommonTools.getCurrentYear());
		for (int i = 0; i < agoN; i++) {
			yearList.add(String.valueOf(year--));
		}

		return yearList;
	}
	
	/**
	 * 取第幾年開始到前N年清單-格式:yyyy
	 * 
	 * @return String
	 */
	public static List<String> getAgoNYearList(int year,int agoN) {
		List<String> yearList = new ArrayList<String>();
		for (int i = 0; i < agoN; i++) {
			yearList.add(String.valueOf(year--));
		}

		return yearList;
	}

	/**
	 * 取月份清單
	 * 
	 * @return String
	 */
	public static List<String> getMonthList() {
		List<String> monthList = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				monthList.add("0" + i);
			} else {
				monthList.add("" + i);
			}
		}
		return monthList;
	}

	/**
	 * 取當前日子-格式:yyyy/MM/dd
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {

		return df.format(new Date());
	}

	/**
	 * 取當前日子時間-格式:yyyy/MM/dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getCurrentDateTime() {

		return dft.format(new Date());
	}

	/**
	 * 取當前日子-格式:yyyy-MM-dd HH
	 * 
	 * @return String
	 */
	public static String getCurrentHours() {

		return dh.format(new Date());
	}

	/**
	 * 取當前日子-格式:yyyyMMddHH
	 * 
	 * @return String
	 */
	public static String getMillisecond() {

		return dms.format(new Date());
	}

	/**
	 * 取當前年度-格式:yyyy
	 * 
	 * @return String
	 */
	public static String getCurrentYear() {
		SimpleDateFormat dy = new SimpleDateFormat("yyyy");
		return dy.format(new Date());
	}

	/**
	 * 取當前月份-格式:MM
	 * 
	 * @return String
	 */
	public static String getCrurrentMonth() {
		SimpleDateFormat dm = new SimpleDateFormat("MM");
		return dm.format(new Date());
	}

	/**
	 * 月份清單-格式:1~12
	 * 
	 * @return String
	 */
//	public static List<SelectVO> monSelect() {
//		List<SelectVO> month = new ArrayList<SelectVO>();
//		for (int i = 1; i < 13; i++) {
//			SelectVO m = new SelectVO();
//			if (i < 10) {
//				m.setKey("0" + String.valueOf(i));
//				m.setValue("0" + String.valueOf(i));
//			} else {
//				m.setKey(String.valueOf(i));
//				m.setValue(String.valueOf(i));
//			}
//			month.add(m);
//		}
//		return month;
//	}

	/**
	 * 前十年-格式:1~10
	 * 
	 * @return String
	 */
//	public static List<SelectVO> getTenyearsAgo(String year) {
//
//		List<SelectVO> twYearList = new ArrayList<SelectVO>();
//		for (int i = 0; i < 10; i++) {
//			int yearSub = Integer.parseInt(year) - i;
//			int keySub = 10 - i;
//			SelectVO thisYear = new SelectVO();
//			thisYear.setKey(String.valueOf(yearSub));
//			thisYear.setValue(String.valueOf(yearSub));
//			twYearList.add(thisYear);
//		}
//		return twYearList;
//	}

	/**
	 * 今日的前n天日期
	 * 
	 * @return String
	 */
	public static String getCurrentDayAgo(int day) {
		Calendar   c   =   Calendar.getInstance();
		c.add(Calendar.DATE, 0-day);
		return df.format(c.getTime());
	}
	
	/**
	 * 今日的前n天日期有格式化日期
	 * 
	 * @return String
	 */
	public static String getCurrentDayAgoForFormat(int day,String style) {
		Calendar   c   =   Calendar.getInstance();
		c.add(Calendar.DATE, 0-day);
		return CommonTools.getDateForFormat(c.getTime(), style);
	}

	/**
	 * 取得時段清單-格式:00~23
	 * 
	 * @return String
	 */
//	public static List<SelectVO> getHourList() {
//		return makeNumber(0, 24);
//	}

	/**
	 * 取得秒清單-格式:00~60
	 * 
	 * @return String
	 */
//	public static List<SelectVO> getSecondList() {
//		return makeNumber(0, 60);
//	}

	/**
	 * 取得分清單-格式:00~60
	 * 
	 * @return String
	 */
//	public static List<SelectVO> getMinuteList() {
//		return makeNumber(0, 60);
//	}

	/**
	 * 雙位數補0
	 * 
	 * @return String
	 */
	public static String markZero(String value, int valueLength) {
		while (value.length() < valueLength) {
			value = "0" + value;
		}
		return value;
	}

	// 共用
//	public static List<SelectVO> makeNumber(int startNum, int number) {
//		List<SelectVO> list = new ArrayList<SelectVO>();
//		SelectVO vo = null;
//		for (int i = startNum; i < number; i++) {
//			vo = new SelectVO();
//			vo.setKey(markZero(i + "", 2));
//			vo.setValue(markZero(i + "", 2));
//			list.add(vo);
//		}
//		return list;
//	}

	// 組檔案名稱(有data)
	public static String chartName(String name, String formatDate, String dataType) {
		String chartName = name + formatDate + "." + dataType;
		return chartName;
	}

	public static String chartName(String name, String dataType) {
		String chartName = name + "." + dataType;
		return chartName;
	}

	/**
	 * 以傳入格式回傳當日的日期格式
	 * 
	 * @return String
	 */
	public static String getDateForFormat(String style) {
		return new SimpleDateFormat(style).format(new Date());
	}

	/**
	 * 以傳入日期和格式回傳該日期格式
	 * 
	 * @return String
	 */
	public static String getDateForFormat(Date date, String style) {
		return new SimpleDateFormat(style).format(date);
	}

	/**
	 * 以傳入日期和格式回傳該日期格式
	 * 
	 * @return String
	 */
	public static String getDateForFormat(Date date, String style, String timeZoneID) {
		DateFormat df = new SimpleDateFormat(style);
		df.setTimeZone(TimeZone.getTimeZone(timeZoneID));
		return df.format(date);
	}

	/**
	 * 以傳入日期和格式取出從1970/01/01到該日期的毫秒
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static long getMillisecondForDate(Date date, String style) throws ParseException {
		DateFormat df = new SimpleDateFormat(style);
		// df.setTimeZone(TimeZone.getTimeZone("008C"));
		String strDate = df.format(date);
		return df.parse(strDate).getTime();

	}

	/**
	 * 以傳入日期和格式和時區取出從1970/01/01到該日期的毫秒
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static long getMillisecondForDate(Date date, String style, String timeZone) throws ParseException {
		DateFormat df = new SimpleDateFormat(style);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		String strDate = df.format(date);
		return df.parse(strDate).getTime();

	}

	/**
	 * 以傳入字串日期和格式取出從1970/01/01到該日期的毫秒
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static long getMillisecondForString(String strDate, String style) throws ParseException {
		DateFormat df = new SimpleDateFormat(style);
		return df.parse(strDate).getTime();

	}
	
	/**
	 * 以傳入字串日期轉型date
	 * 
	 * @return date
	 * @throws ParseException
	 */
	public static Date getDateForString(String strDate, String style) throws ParseException {
		DateFormat df = new SimpleDateFormat(style);
		return df.parse(strDate);
	}

	/**
	 * 取得四捨五入
	 * 
	 * @return String
	 */
	public static String getRound(String dividend, String divisor){
		if(divisor.trim().equals("0")){
			return "0";
		}
		return nf.format(new BigDecimal((Double.parseDouble(dividend)/Double.parseDouble(divisor))+"").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	/**
	 * 取得比率(四捨五入)
	 * 
	 * @return String
	 */
	public static String getRateForRound(String dividend, String divisor){
		if(divisor.trim().equals("0")){
			return "0";
		}
		return nf.format(new BigDecimal((Double.parseDouble(dividend)/Double.parseDouble(divisor)*100)+"").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	/**
	 * 取得比率(四捨五入)
	 * 
	 * @return String
	 */
	public static String getAvgForRound(String dividend, String divisor){
		if(divisor.trim().equals("0")){
			return "0";
		}
		return nf.format(new BigDecimal((Double.parseDouble(dividend)/Double.parseDouble(divisor))+"").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

	}
	
	/**
	 * 取得比率(四捨五入)傳入值:被除數,除數,四捨五入位置,回傳值格式化
	 * 
	 * @return String
	 */
	public static String getRateForRound(String dividend, String divisor,int roundIndex,String numberFormat){
		if(divisor.trim().equals("0")){
			return "0";
		}
		NumberFormat nf = new DecimalFormat(numberFormat);
		return nf.format(new BigDecimal((Double.parseDouble(dividend)/Double.parseDouble(divisor)*100)+"").setScale(roundIndex, BigDecimal.ROUND_HALF_UP).doubleValue());

	}
	/**
	 * 取得成長比率(四捨五入)
	 * 
	 * @return String
	 * 被除數-除數/除數*100
	 * 如果除數等於0時:被除數-0/1*100
	 */
	public static String getGrowRateForRound(String dividend, String divisor){
		if(divisor.trim().equals("0")){
			//return nf.format(new BigDecimal((((Double.parseDouble(dividend)-Double.parseDouble(divisor))/Double.parseDouble("1"))*100)+"").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			return "--";
		}else{
			return nf.format(new BigDecimal((((Double.parseDouble(dividend)-Double.parseDouble(divisor))/Double.parseDouble(divisor))*100)+"").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}

	}
	
	/**
	 * 移除前面為0;
	 * 
	 */
	public static String removeForeZero(String value) {
		if (value != null && value.trim().length() > 0) {
			value = value.trim();
			int startIndex = 0;
			for (; startIndex < value.length(); startIndex++) {

				if (!value.substring(startIndex, startIndex + 1).equals("0")) {
					break;
				}
			}
			return value.substring(startIndex);
		} else {
			return value;
		}
	}
	
	public static String whatDayIsTheDate (String date) {
		if (StringUtils.isBlank(date) || date.indexOf("/") < 0) {
			return "";
		}
		String yy = date.split("/")[0];
		String mm = date.split("/")[1];
		String dd = date.split("/")[2];
		String result = "";
		Calendar startDate = Calendar.getInstance();
		startDate.set(Integer.parseInt(yy), Integer.parseInt(mm) - 1, Integer.parseInt(dd));
		switch (startDate.get(Calendar.DAY_OF_WEEK)) {
			case 1:
				result = SUN;
				break;
			case 2:
				result = MON;
				break;
			case 3:
				result = TUE;
				break;
			case 4:
				result = WED;
				break;
			case 5:
				result = THUS;
				break;
			case 6:
				result = FRI;
				break;
			case 7:
				result = SAT;
				break;
		}
		return result;
	}
	
	/**
	 * JSONParser ;
	 * json to map;
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> jsonToMap(String json) throws org.json.simple.parser.ParseException {
		Map<String,Object> map = null;
		if(StringUtils.isNotBlank(json)) {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json.replaceAll("\\[","").replaceAll("\\]",""));  
			map = (HashMap<String, Object>)obj;
		}else {
			map = new HashMap<String,Object>();
		}
		return map;
	}
	
	/**
	 * JSONParser ;
	 * json to List<map>;
	 */
	public List<Map<String,Object>> jsonToList(String json) throws org.json.simple.parser.ParseException {
		List<Map<String,Object>> list=null;
		if(StringUtils.isNotBlank(json)) {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);  
			list = (List<Map<String, Object>>)obj;
		}
		return list;
	}
	
	//學號加上最末位  驗證碼
	public String verificationNumber (String s){
		int [] arr={9,8,7,6,5,4,3,2,1};
		int a []=new int[s.length()];
		int sum = 0;
		for(int i=0;i<arr.length;i++){
			a[i]=Integer.parseInt(s.substring(i,i+1));
				sum=sum+(arr[i]*a[i]%10);
		}
		for(int i=1;i>0;){
			if(sum>=10){
				String sumS=Integer.toString(sum);
				sum=0;
				int len=sumS.length();
				for(int j=0;j<len;j++){
					sum=sum+Integer.parseInt(sumS.substring(j, j+1));
				}
			}else{
				break;
			}
		}
		return s+sum;
	}
}