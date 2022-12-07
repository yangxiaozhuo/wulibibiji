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
    public Result uploadFile(File file) throws QiniuException {
        String uploadToken = getUploadToken();
        String key = "2022/12/07/b";
        Response response = this.uploadManager.put(file, key,uploadToken);
        int retry = 0;
        //重复三次
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, key, getUploadToken());
            retry++;
        }
        System.out.println(uploadToken);
        return Result.ok(response.bodyString());
    }

    @Override
    public Result uploadFile(InputStream inputStream) throws QiniuException {
        return null;
    }

    @Override
    public Result delete(String key) throws QiniuException {
        return null;
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
