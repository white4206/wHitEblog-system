package com.white.blog.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description ip地址工具类
 * @date 2024/5/4 15:54:59
 */
@Log4j2
public class IPUtils {
    public static String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress.split(",")[0];
    }

    /**
     * 获取ip属地(缓存整个 xdb 数据)
     *
     * @param ip IP地址
     * @return 返回地址
     */
    public static String getCityInfoByMemorySearch(String ip) {
        // 离线数据的路径
        String dbPath = "ip/ip2region.xdb";
        // 执行查询的对象
        Searcher searcher;
        try {
            // 根据路径读入数据流
            InputStream inputStream = new ClassPathResource(dbPath).getInputStream();
            // 数据流存入位数组
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(inputStream);
            // 根据离线数据初始化构建查询对象缓存
            searcher = Searcher.newWithBuffer(dbBinStr);
        } catch (Exception e) {
            // 捕获异常抛出并结束运行
            log.error("failed to create content cached searcher: ", e);
            return null;
        }
        try {
            long startTime = System.nanoTime();
            // 查询IP
            String region = searcher.search(ip);
            long costTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
            log.info("{} 的IP属地: {}, 花费时间: {}us,IO次数: {}次", ip, region, costTime, searcher.getIOCount());
            // 按格式切分拼接后输出
            return region.split("\\|")[0] + ' ' + region.split("\\|")[2] + ' ' + region.split("\\|")[3];
        } catch (Exception e) {
            log.error("failed to search({}): ", ip, e);
            return "未知";
        }
    }

}
