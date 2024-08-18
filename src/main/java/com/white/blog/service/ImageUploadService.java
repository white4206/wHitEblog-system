package com.white.blog.service;

import com.white.blog.common.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 图片上传服务层接口
 * @date 2024/6/26 14:49:21
 */
public interface ImageUploadService {
    R<String> imageUpload(MultipartFile file) throws IOException;
}
