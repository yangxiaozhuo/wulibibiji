package com.yxz.wulibibiji.service.other;

import cn.hutool.core.io.FileUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.yxz.wulibibiji.dto.Result;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

/**
 * 七牛云服务
 *
 * @author yangxiaozhuo
 * @date 2022/12/07
 */
public interface IQiNiuService {
    Result uploadFile(File file) throws QiniuException;

    Result uploadFile(InputStream inputStream) throws QiniuException;

    Result delete(String key) throws QiniuException;

}
