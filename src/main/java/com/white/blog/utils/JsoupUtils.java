package com.white.blog.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description Jsoup工具类
 * @date 2024/7/24 18:20:45
 */
public class JsoupUtils {
    public static Elements getElementsBySelector(String html, String selector) {
        // 解析传入的html文本
        Document document = Jsoup.parse(html);
        // 提取标签
        return document.select(selector);
    }
}
