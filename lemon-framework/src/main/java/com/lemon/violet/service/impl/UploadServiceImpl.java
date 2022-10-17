package com.lemon.violet.service.impl;

import com.google.gson.Gson;
import com.lemon.violet.constant.KeyConstant;
import com.lemon.violet.constants.SystemConstants;
import com.lemon.violet.pojo.dto.QiniuOss;
import com.lemon.violet.pojo.enums.CodeEnum;
import com.lemon.violet.pojo.enums.DatePattern;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.UploadService;
import com.lemon.violet.utils.FileUtil;
import com.lemon.violet.utils.RandomUtil;
import com.lemon.violet.utils.StringUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {
    @Resource
    private QiniuOss qiniuOss;

    @Override
    public ResponseResult upload(MultipartFile img) {
        try {
            //图片不匹配
            String suffix = FileUtil.getSuffix(img.getOriginalFilename());
            if(!SystemConstants.JPG.equals(suffix) && !SystemConstants.PNG.equals(suffix)){
                return ResponseResult.fail(CodeEnum.IMG_TYPE_NOT_MATCHING);
            }
            String url = uploadOss(img);
            return ResponseResult.success(url);
        } catch (Exception e) {
            return ResponseResult.fail(CodeEnum.IMG_UPLOAD_FAIL);
        }
    }

    private String uploadOss(MultipartFile img) throws IOException {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        //...生成上传凭证，然后准备上传
        String accessKey = qiniuOss.getAccessKey();
        String secretKey = qiniuOss.getSecretKey();
        String bucket = qiniuOss.getBucket();
        String domain = qiniuOss.getDomain();

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = FileUtil.generateFileName(img.getOriginalFilename(), DatePattern.DATE_PATTERN_P7);

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(img.getInputStream(), key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = StringUtil.getUrl(KeyConstant.PROTOCOL_HTTP,domain,key);
            return url;

        } catch (Exception ex) {
            log.error("图片上传失败:{}", ex.getMessage());
            throw ex;
        }
    }
}
