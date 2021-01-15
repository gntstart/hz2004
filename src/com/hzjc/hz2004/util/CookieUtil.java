package com.hzjc.hz2004.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;

public class CookieUtil {
	// 保存cookie时的域
	private final static String domain = SystemConfig.getSystemConfig("COOKIES.DOMAIN", "");

	// 设置cookie有效期是两个星期
	private final static int cookieMaxAge = 60 * 60 * 24 * 7 * 2;

	/**
	 * 将会话ID写入Cookies
	 * @param u
	 */
	public static void saveCookie(AuthToken u) {
		System.out.println("----------管理员写入cookies----------------");
		
		Cookie c1 = new Cookie(Constants.COOKIE_SID, u.getAuthToken());
		c1.setPath("/");
		// 不能将localhost写到domain中，可为127.0.0.1
		if (!domain.equals("localhost"))
			c1.setDomain(domain);
		c1.setMaxAge(cookieMaxAge);
		
		c1.setValue(u.getAuthToken());
		BaseContext.getContext().getResponse().addCookie(c1);
	}
}
