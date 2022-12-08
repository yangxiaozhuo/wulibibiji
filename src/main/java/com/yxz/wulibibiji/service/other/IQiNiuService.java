package com.yxz.wulibibiji.service.other;

import com.qiniu.common.QiniuException;
import com.yxz.wulibibiji.dto.Result;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云服务
 *
 * @author yangxiaozhuo
 * @date 2022/12/07
 */
public interface IQiNiuService {
    Result uploadFile(File file, String key) throws QiniuException;

    Result uploadFile(InputStream inputStream, String key) throws IOException;

    Result delete(String key) throws QiniuException;

}
