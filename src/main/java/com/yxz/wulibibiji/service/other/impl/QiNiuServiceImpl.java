package com.yxz.wulibibiji.service.other.impl;

import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.service.other.IQiNiuService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangxiaozhuo
 * @date 2022/12/07
 */
@Service
public class QiNiuServiceImpl implements IQiNiuService, InitializingBean {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.Bucket}")
    private String bucket;

    StringMap putPolicy;


    @Override
    public Result uploadFile(File file, String key) throws QiniuException {
        String uploadToken = getUploadToken();
        Response response = this.uploadManager.put(file, key,uploadToken);
        int retry = 0;
        //重复三次
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, key, getUploadToken());
            retry++;
        }
        if (response.isOK()) {
            return Result.ok(response.bodyString());
        } else {
            return Result.fail("上传失败");
        }
    }

    @Override
    public Result uploadFile(InputStream inputStream, String key) throws IOException {
        String uploadToken = getUploadToken();
        Response response = this.uploadManager.put(inputStream, key,uploadToken,null,null);
        int retry = 0;
        //重复三次
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, key,uploadToken,null,null);
            retry++;
        }
        inputStream.close();
        if (response.isOK()) {
            return Result.ok(response.bodyString());
        } else {
            return Result.fail("上传失败");
        }
    }

    @Override
    public Result delete(String key) throws QiniuException {
        Response response = this.bucketManager.delete(this.bucket, key);
        int retry = 0;
        //重复三次
        while (response.needRetry() && retry < 3) {
            response = this.bucketManager.delete(this.bucket, key);
            retry++;
        }
        if (response.isOK()) {
            return Result.ok(response.bodyString());
        } else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody",
                "{\"key\":\"$(key)\"," +
                        "\"hash\":\"$(etag)\"," +
                        "\"bucket\":\"$(bucket)\"," +
                        "\"fsize\":$(fsize)," +
                        "\"width\":$(image.Info.width)," +
                        "\"height\":$(image.Info.height)}");
    }

    /**
     * 获取上传token
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }
}
