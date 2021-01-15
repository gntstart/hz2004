package com.hzjc.util;

import java.text.*;
import java.sql.*;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.service.CommonService;

/**
 * <p>
 * Title: EPS Core
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author $Author<br>
 *         $Date
 * @version $Revision
 */

public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;

	/**
	 * Hashes a String using the Md5 algorithm and returns the result as a
	 * String of hexadecimal numbers. This method is synchronized to avoid
	 * excessive MessageDigest object creation. If calling this method becomes a
	 * bottleneck in your code, you may wish to maintain a pool of MessageDigest
	 * objects instead of using this method.
	 * <p>
	 * A hash is a one-way function -- that is, given an input, an output is
	 * easily computed. However, given the output, the input is almost
	 * impossible to compute. This is useful for passwords since we can store
	 * the hash and a hacker will then have a very hard time determining the
	 * original password.
	 * <p>
	 * In System, every time a user logs in, we simply take their plain text
	 * password, compute the hash, and compare the generated hash to the stored
	 * hash. Since it is almost impossible that two passwords will generate the
	 * same hash, we know if the user gave us the correct password or not. The
	 * only negative to this system is that password recovery is basically
	 * impossible. Therefore, a reset password method is used instead.
	 *
	 * @param data
	 *            the String to compute the hash of.
	 * @return a hashed version of the passed-in String
	 */
	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println(
						"Failed to load the MD5 MessageDigest. " + "System will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number.
	 * <p>
	 * Method by Santeri Paavolainen, Helsinki Finland 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 *
	 * @param bytes
	 *            an array of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Turns a hex encoded string into a byte array. It is specifically meant to
	 * "reverse" the toHex(byte[]) method.
	 *
	 * @param hex
	 *            a hex encoded String to transform into a byte array.
	 * @return a byte array representing the hex String[
	 */
	public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * Returns the the byte value of a hexadecmical char (0-f). It's assumed
	 * that the hexidecimal chars are lower case as appropriate.
	 *
	 * @param ch
	 *            a hexedicmal character (0-f)
	 * @return the byte value of the character (0x00-0x0F)
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(java.util.Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
		return newFormat.format(date);
	}

	/**
	 *
	 * @param aStr
	 * @return
	 */
	public static String formatTrim(String aStr) {
		return aStr == null ? "" : aStr.trim();
	}

	/**
	 *
	 * @param aStr
	 * @param iLen
	 * @return
	 */
	public static String addSpaceToLen(String aStr, int iLen) {
		String strRet = formatTrim(aStr);
		int iRet = strRet.length();
		for (int i = 0; i < iLen - iRet; i++) {
			strRet += " ";
		}
		return strRet;
	}

	//////////////////////////////////////////////////////////////////////////////
	// 以下方法为新加
	//////////////////////////////////////////////////////////////////////////////

	private static final String[] LOWERCASES = { "○", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };

	private static final String[] UPPERCASES = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾" };

	/**
	 * 根据传入的格式输出日期（如YYYY年MM月DD日） 年月日时分秒：20040412090909(yyyyMMddHHmmss)
	 * 
	 * @param date
	 * @param sFormat
	 * @return
	 */
	public static String formatDateBy(java.util.Date date, String sFormat) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
		return newFormat.format(date);
	}

	/**
	 *
	 * @return
	 */
	public static String formateDate() {
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMdd");
		return newFormat.format(new java.util.Date());
	}

	/**
	 *
	 * @return
	 */
	public static String formateDateTime() {
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return newFormat.format(new java.util.Date());
	}

	/**
	 * 将格式化的日期字串转化成日期类型(如yyyy年MM月DD日->date)
	 * 
	 * @param strDate
	 * @return
	 */
	public static java.util.Date strToDate(String strDate, String sFormat) {
		if ((null == strDate) || ("".equals(strDate.trim()))) {
			return null;
		}
		SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * 将某日期格式转化成另外的日期格式(例1977-11-8 -> 1977年11月08日 )
	 * 
	 * @param date
	 * @param sFormat
	 * @return
	 */
	public static String changeDateFormat(String date, String bFormat, String eFormat) {
		if ((null == date) || ("".equals(date.trim()))) {
			return "";
		}
		try {
			java.util.Date dDate = strToDate(date, bFormat);
			SimpleDateFormat newFormat = new SimpleDateFormat(eFormat);
			return newFormat.format(dDate);
		} catch (Exception ex) {
			return date;
		}
	}

	/**
	 * 将日期String转化成当前年龄(如1977年11月8日->25)
	 * 
	 * @param date
	 * @param sFormat
	 * @return
	 */
	public static String dateToAge(String date) {
		if ((null == date) || ("".equals(date.trim()))) {
			return "";
		}
		try {
			String sYear = date.trim().substring(0, 4);
			long lYear = Long.parseLong(sYear);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy");
			long curYear = Long.parseLong(newFormat.format(new java.util.Date()));
			return String.valueOf(curYear - lYear + 1);
		} catch (NumberFormatException ex) {
			return date;
		}
	}

	/**
	 * 将日期String转化成当前年龄(如1977年11月8日->25)
	 * 
	 * @param date
	 * @param sFormat
	 * @return
	 */
	public static String dateToAgeDay(String date) {
		if ((null == date) || ("".equals(date.trim()))) {
			return "";
		}
		// try {
		// SimpleDateFormat newFormat = new SimpleDateFormat("yyyymmdd");
		// java.util.Date curDate = newFormat.parse(newFormat.format(new
		// java.util.Date()));
		// java.util.Date dtDate = newFormat.parse(date);
		// float
		// yearNum=(float)((((curDate.getTime()-dtDate.getTime()/1000)/3600)/24)/365);
		// return String.valueOf(yearNum);
		// }
		// catch (java.text.ParseException ex) {
		// return date;
		// }
		// 胡斌 20060607 由于上面的取年龄方法有误，取不到正确的年龄，填加下面取年龄方法，和前台
		// 取年龄方法类似。
		long age = 0;
		long BYear = Long.parseLong(date.trim().substring(0, 4));// 取得出生年份
		long BMonth = Long.parseLong(date.trim().substring(4, 6));// 取得出生月份
		long BDay = Long.parseLong(date.trim().substring(6, 8));// 取得出生日

		SimpleDateFormat nowYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat nowMonth = new SimpleDateFormat("MM");
		SimpleDateFormat nowDay = new SimpleDateFormat("dd");
		long curYear = Long.parseLong(nowYear.format(new java.util.Date()));// 取当前年份
		long curMonth = Long.parseLong(nowMonth.format(new java.util.Date()));// 取当前月份
		long curDay = Long.parseLong(nowDay.format(new java.util.Date()));// 取当前日

		if (curDay < BDay) {
			curMonth = curMonth - 1;
		}
		if (curMonth < BMonth) {
			curYear = curYear - 1;
		}
		age = curYear - BYear;
		if (age < 0) {
			return "";
		}
		return String.valueOf(age);
	}

	/**
	 * 把字串中的数字转换成小写的格式(如:1977年11月8日->一九七七年一一月八日)
	 * 
	 * @param transStr
	 * @return
	 */
	public static String lowerCaseTrans(String transStr) {
		if (null == transStr) {
			return null;
		}
		StringBuffer sbTmp = new StringBuffer();
		for (int i = 0; i < transStr.length(); i++) {
			String stmp = String.valueOf(transStr.charAt(i));
			if ("0123456789".indexOf(stmp) >= 0) {
				int irec = Integer.parseInt(stmp);
				sbTmp.append(LOWERCASES[irec]);
			} else {
				sbTmp.append(stmp);
			}
		}
		return sbTmp.toString();
	}

	/**
	 * 把字串中的数字转换成小写的格式(如:1977年11月8日->壹玖柒柒年壹壹月捌日)
	 * 
	 * @param transStr
	 * @return
	 */
	public static String upperCaseTrans(String transStr) {
		if (null == transStr) {
			return null;
		}
		StringBuffer sbTmp = new StringBuffer();
		for (int i = 0; i < transStr.length(); i++) {
			String stmp = String.valueOf(transStr.charAt(i));
			if ("0123456789".indexOf(stmp) >= 0) {
				int irec = Integer.parseInt(stmp);
				sbTmp.append(UPPERCASES[irec]);
			} else {
				sbTmp.append(stmp);
			}
		}
		return sbTmp.toString();
	}

	/**
	 * 取当前日期的中文小写格式
	 * 
	 * @return
	 */
	public static String lowerCaseDate() {
		StringBuffer sbTmp = new StringBuffer();
		sbTmp.append(lowerCaseTrans(formatDateBy((new java.util.Date()), "yyyy年")));
		sbTmp.append(lowerCaseNumber(Long.parseLong(formatDateBy((new java.util.Date()), "MM"))) + "月");
		sbTmp.append(lowerCaseNumber(Long.parseLong(formatDateBy((new java.util.Date()), "dd"))) + "日");
		return sbTmp.toString();
	}

	/**
	 * 取当前日期的时间格式年，月，日
	 * 
	 * @return
	 */
	public static String curDate() {
		return formatDateBy((new java.util.Date()), "yyyy年MM月dd日");
	}

	/**
	 * 转换成大写的中文数字(902133011->玖亿零贰佰拾叁万叁仟零拾壹)
	 * 
	 * @param ltran
	 * @return
	 */
	public static String supperCaseNumber(long ltran) {
		if (0 == ltran) {
			return "零";
		} else {
			return upperCaseTrans(transChina(ltran, 1));
		}
	}

	/**
	 * 转换成大写的中文数字902133011->(九亿○二百十三万三千○十一)
	 * 
	 * @param ltran
	 * @return
	 */
	public static String lowerCaseNumber(long ltran) {
		if (0 == ltran) {
			return "○";
		} else {
			return lowerCaseTrans(transChina(ltran, 0));
		}
	}

	/**
	 * chinaflag 0时为千,百,十 否则为仟,佰,拾
	 * 
	 * @param ltran
	 * @param chinaflag
	 * @return
	 */
	private static String transChina(long ltran, int chinaflag) {
		StringBuffer sbTmp = new StringBuffer();
		if (ltran / 100000000 >= 1) {
			sbTmp.append(transChina(Math.round(ltran / 100000000), chinaflag));
			sbTmp.append("亿");
			if (((ltran % 100000000) / 10000000 < 1) && ((ltran % 100000000) - (ltran % 10000000) == 0)
					&& ((ltran % 100000000) != 0)) {
				sbTmp.append("0");
			}
			sbTmp.append(transChina(ltran % 100000000, chinaflag));
		} else {
			if ((ltran / 10000) >= 1) {
				sbTmp.append(transChina(Math.round(ltran / 10000), chinaflag));
				sbTmp.append("万");
				if (((ltran % 10000) / 1000 < 1) && ((ltran % 10000) - (ltran % 1000) == 0) && ((ltran % 10000) != 0)) {
					sbTmp.append("0");
				}
				sbTmp.append(transChina(ltran % 10000, chinaflag));
			} else {
				if ((ltran / 1000) >= 1) {
					sbTmp.append(transChina(Math.round(ltran / 1000), chinaflag));
					if (chinaflag == 0) {
						sbTmp.append("千");
					} else {
						sbTmp.append("仟");
					}
					if (((ltran % 1000) / 100 < 1) && ((ltran % 1000) - (ltran % 100) == 0) && ((ltran % 1000) != 0)) {
						sbTmp.append("0");
					}
					sbTmp.append(transChina(ltran % 1000, chinaflag));
				} else {
					if ((ltran / 100) >= 1) {
						sbTmp.append(transChina(Math.round(ltran / 100), chinaflag));
						if (chinaflag == 0) {
							sbTmp.append("百");
						} else {
							sbTmp.append("佰");
						}
						if ((((ltran % 100) / 10) < 1) && (((ltran % 100) - (ltran % 10)) == 0)
								&& ((ltran % 100) != 0)) {
							sbTmp.append("0");
						}
						sbTmp.append(transChina(ltran % 100, chinaflag));
					} else {
						if ((ltran / 10) >= 1) {
							if ((ltran / 10) > 1) {
								sbTmp.append(transChina(Math.round(ltran / 10), chinaflag));
							}
							if (chinaflag == 0) {
								sbTmp.append("十");
							} else {
								sbTmp.append("拾");
							}
							if ((ltran % 10 < 1) && (Math.round((ltran % 10) - ltran) == 0) && ((ltran % 10) != 0)) {
								sbTmp.append("0");
							}
							sbTmp.append(transChina(ltran % 10, chinaflag));
						} else {
							if (ltran > 0) {
								sbTmp.append("" + ltran);
							}
						}
					}
				}
			}
		}
		return sbTmp.toString();
	}

	/**
	 * 将格式化的日期字串转化成日期类型(如yyyy年MM月DD日->date)
	 * 
	 * @param strDate
	 * @return
	 */
	public static java.util.Date strToDate(String strDate) {
		if ((null == strDate) || ("".equals(strDate.trim()))) {
			return null;
		}
		String sFormat = "yyyyMMddHH";
		SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
		}
		sFormat = "yyyy-MM-dd-HH";
		newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
		}
		sFormat = "yyyy/MM/dd/HH";
		newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
		}
		sFormat = "yyyy年MM月dd日HH时";
		newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
		}
		sFormat = "yyyyMMdd";
		newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
		}
		sFormat = "yyyy-MM-dd";
		newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
		}
		sFormat = "yyyy/MM/dd";
		newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
		}
		sFormat = "yyyy年MM月dd日";
		newFormat = new SimpleDateFormat(sFormat);
		try {
			return newFormat.parse(strDate);
		} catch (ParseException ex) {
		}
		return null;
	}

	// (sourceStr为原字串,point 为位置,sep为分隔符)
	// 因jdk1.3不支持String.split方法故采用这种形式
	public static String parseSepStr(String sourceStr, int point, String sep) {
		if (null == sourceStr) {
			return "";
		}
		if ((null == sep) || ("".equals(sep))) {
			return sourceStr;
		}
		int len = sep.length();
		String tmp = sourceStr;
		int ipos = 0;
		try {
			for (int i = 1; i <= point - 1; i++) {
				ipos = tmp.indexOf(sep);
				if (ipos < 0) {
					return "";
				}
				tmp = tmp.substring(ipos + len);
			}
			ipos = tmp.indexOf(sep);
			if (ipos < 0) {
				return tmp;
			} else {
				return tmp.substring(0, ipos);
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 *
	 * @param strDate
	 * @param strFmt
	 * @return
	 */
	public static boolean isDate(String strDate, String strFmt) {
		if (strDate == null
		// || (strDate != null && strDate.length() != 8) ||
		// (strDate != null && !isNumeric(strDate))
		) {
			return false;
		}
		boolean bDate = true;
		SimpleDateFormat sdf = new SimpleDateFormat(strFmt);
		try {
			java.util.Date date = sdf.parse(strDate);
			if (!strDate.equals(formatDateBy(date, strFmt))) {
				bDate = false;
			}
		} catch (ParseException ex) {
			bDate = false;
		}
		return bDate;
	}

	/**
	 *
	 * @param strDate
	 *            - 日期
	 * @param iYearAdd
	 *            - 增加年数
	 * @return
	 */
	public static String addYear(String strDate, int iYearAdd) {
		String strBeforeYear = strDate.substring(0, 4);
		String strBeforeMonth = strDate.substring(4, 6);
		String strBeforeDay = strDate.substring(6, 8);

		int iAfterYear = Integer.parseInt(strBeforeYear) + iYearAdd;
		String strAfterDate = String.valueOf(iAfterYear) + strBeforeMonth + strBeforeDay;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			java.util.Date d = sdf.parse(strAfterDate);

			strAfterDate = sdf.format(d);
		} catch (ParseException ex) {
		}

		return strAfterDate;
	}

	/**
	 *
	 * @param strDate
	 *            - 日期
	 * @param iMonthAdd
	 *            - 增加月数
	 * @return
	 */
	public static String addMonth(String strDate, int iMonthAdd) {
		String strBeforeYear = strDate.substring(0, 4);
		String strBeforeMonth = strDate.substring(4, 6);
		String strBeforeDay = strDate.substring(6, 8);

		int iAfterMonth = Integer.parseInt(strBeforeMonth) + iMonthAdd;
		String strAfterDate = strBeforeYear + String.valueOf(iAfterMonth) + strBeforeDay;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			java.util.Date d = sdf.parse(strAfterDate);

			strAfterDate = sdf.format(d);
		} catch (ParseException ex) {
		}

		return strAfterDate;

	}

	/**
	 *
	 * @param strDate
	 *            － 日期
	 * @param iDayAdd
	 *            - 增加天数
	 * @return
	 */
	public static String addDay(String strDate, int iDayAdd) {
		String strBeforeYear = strDate.substring(0, 4);
		String strBeforeMonth = strDate.substring(4, 6);
		String strBeforeDay = strDate.substring(6, 8);

		int iAfterDay = Integer.parseInt(strBeforeDay) + iDayAdd;
		String strAfterDate = strBeforeYear + strBeforeMonth + String.valueOf(iAfterDay);

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			java.util.Date d = sdf.parse(strAfterDate);

			strAfterDate = sdf.format(d);
		} catch (ParseException ex) {
		}

		return strAfterDate;

	}

	/**
	 * 将含有中文的字符串转换为GB13000字符串（为了Delphi客户端接收处理－－WideString类型）
	 * GB13000对于客户端的delphi接收时，WideString类型对于汉字XML中标识的方式
	 * 用转义标识，例“中国”－－》&#20013;&#22269;
	 * 
	 * @param strIn
	 *            -- 传入的字符串
	 * @return
	 */
	public static String toGB13000(String strIn) {
		String strRet = "";
		if (strIn != null && !strIn.equals("")) {
			char[] chars = strIn.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				int iASCII = (int) chars[i];
				strRet += iASCII <= 127 ? String.valueOf(chars[i]) : ("&#" + (int) chars[i] + ";");
			}
		} else {
			return strIn;
		}
		return strRet;
	}

	/**
	 * GB13000对于客户端的delphi发送过来时，转义编码数据要转换为中文Unicode字符串传给数据库
	 * 用转义标识，例&#20013;&#22269;－－》“中国”
	 * 
	 * @param strIn
	 *            -- 传入字符串
	 * @return
	 */
	public static String fromGB13000(String strIn) {
		String strRet = "";
		return strRet;
	}

	/**
	 * 提取数据库时间--DUAL 格式为 yyyymmddhh24miss
	 * 
	 * @throws ServiceException
	 * @throws DAOException
	 * @return String add hb 20060831
	 */
	public static String getServiceTime() throws ServiceException {
		String serviceTime = null;
		Date serviceDate = null;
		// String strSQL = null;

		// 组织SQL语句
		// strSQL = "select to_char(sysdate,'yyyymmddhh24miss') from dual ";

		try {
			CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
			serviceDate = hqsjDao.getSjksj(); // 得到数据库时间
			serviceTime = formatDateBy(serviceDate, "yyyyMMddHHmmss");// 转换时间格式
			// serviceTime = hqsjDao.selectDual(strSQL);//得到数据库时间

			if (serviceTime == null) {
				throw new ServiceException(WSErrCode.ERR_DB_QUERYEX, "得不到数据库时间，业务失败。", null);
			}

		} catch (Exception ex) {
			throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		}
		// 返回时间
		return serviceTime;
	}

	/**
	 * 提取数据库日期--DUAL 格式为 yyyymmdd
	 * 
	 * @throws ServiceException
	 * @throws DAOException
	 * @return String add hb 20060831
	 */
	public static String getServiceDate() throws ServiceException {
		// String strSQL = null;
		String serviceTime = null;
		Date serviceDate = null;

		// 组织SQL语句
		// strSQL = "select to_char(sysdate,'yyyymmdd') from dual ";

		try {
			CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
			serviceDate = hqsjDao.getSjksj(); // 得到数据库时间
			serviceTime = formatDateBy(serviceDate, "yyyyMMdd");// 转换时间格式
			// serviceTime = hqsjDao.selectDual(strSQL);//得到数据库时间

			if (serviceTime == null) {
				throw new ServiceException(WSErrCode.ERR_DB_QUERYEX, "得不到数据库时间，业务失败。", null);
			}
		} catch (Exception ex) {
			throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		}
		// 返回时间
		return serviceTime;
	}

	/**
	 * 提取数据库日期--DUAL
	 * 传入参数格式为自定义（时间参数格式为JAVA的格式，年：yyyy，月：MM，日：dd，小时：HH，分钟：mm，秒：ss）
	 * 
	 * @throws ServiceException
	 * @throws DAOException
	 * @return String add hb 20060831
	 */
	public static String getServiceDate(String sFormat) throws ServiceException {
		// String strSQL = null;
		String sformat = sFormat;
		String serviceTime = null;
		Date serviceDate = null;

		// 组织SQL语句
		// strSQL = "select to_char(sysdate,'"+ sformat +"') from dual ";

		try {
			CommonService hqsjDao = (CommonService) SpringContextHolder.getBean("commonService");
			serviceDate = hqsjDao.getSjksj(); // 得到数据库时间
			serviceTime = formatDateBy(serviceDate, sFormat);// 转换时间格式
			// serviceTime = hqsjDao.selectDual(strSQL);//得到数据库时间

			if (serviceTime == null) {
				throw new ServiceException(WSErrCode.ERR_DB_QUERYEX, "得不到数据库时间，业务失败。", null);
			}
		} catch (Exception ex) {
			throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		}
		// 返回时间
		return serviceTime;
	}

}
