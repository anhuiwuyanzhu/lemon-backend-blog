package com.lemon.violet.service;

import com.lemon.violet.pojo.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult upload(MultipartFile img);
}
