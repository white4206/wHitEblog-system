package com.white.blog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 文件保存工具类
 * @date 2024/7/6 14:20:00
 */
@Component
public class SaveFile {

    @Value("${wHitEblog.uploadPath}")
    private String uploadPath;

    public String saveFile(String path, MultipartFile file) throws IOException {
        // 文件创建成功返回值
        boolean dr;
        // 创建封面图片上传目录
        File directory = new File(uploadPath + path);
        // 判断目录是否存在
        if (!directory.exists())
            dr = directory.mkdirs();

        // 获取上传文件的文件名
        String originalFilename = file.getOriginalFilename();
        // 获取上传文件的原拓展名
        // 通过String工具类来获取这个文件名的扩展名
        String ext = StringUtils.getFilenameExtension(originalFilename);

        // 生成一个新的文件
        File destDirectory = new File(uploadPath + path + "/" + UUID.randomUUID() + "." + ext);
        // 获取要上传文件的内容, 返回是MultipartFile对象
        // MultipartFile对象有一个方法 transferTo 可以将上传文件的内容写入到指定的目录destDirectory中。
        file.transferTo(destDirectory);
        return "/resource" + path + "/" + destDirectory.getName();
    }
}
