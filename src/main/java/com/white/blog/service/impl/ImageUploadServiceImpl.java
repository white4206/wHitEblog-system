package com.white.blog.service.impl;

import com.white.blog.common.R;
import com.white.blog.service.ImageUploadService;
import com.white.blog.utils.SaveFile;
import com.white.blog.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 图片上传服务层实现类
 * @date 2024/6/26 14:49:51
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    private SaveFile saveFile;

    @Override
    public R<String> imageUpload(MultipartFile file) throws IOException {
        return R.success(saveFile.saveFile("/images", file));
    }
}
