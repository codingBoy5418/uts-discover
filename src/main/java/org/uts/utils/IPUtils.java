package org.uts.utils;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description IP地址请求类
 * @Author codBoy
 * @Date 2024/7/31 21:00
 */
@Slf4j
public class IPUtils {

    private static final String UNKNOWN = "UNKNOWN";

    /*
      获取客户端IP地址
     */
    public static String getIpAddress(HttpServletRequest servletRequest)
    {
        return servletRequest.getHeader("Real-IP");
    }

}
