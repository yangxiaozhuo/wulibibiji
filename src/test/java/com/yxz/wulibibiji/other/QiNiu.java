package com.yxz.wulibibiji.other;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.service.other.impl.QiNiuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class QiNiu {

    @Test
    void contextLoads() {
        File file = FileUtil.file("C:/Users/Yang/Desktop/南湖航拍.png");
        if (!file.exists() || !file.isFile()) {
            System.out.println("文件不存在");
        }
        float fileKB = file.length() / 1024F;
        float factor = 200 / fileKB;
        ImgUtil.compress(file, FileUtil.file("C:/Users/Yang/Desktop/南湖航拍.jpg"), factor);
//        Img.from(FileUtil.file("C:/Users/Yang/Desktop/校历.jpg"))
//                .setQuality(0.5)//压缩比率
//                .write(FileUtil.file("C:/Users/Yang/Desktop/校历2.jpg"));
    }

    @Autowired
    private QiNiuServiceImpl qiNiuService;

    @Test
    void qniu() throws QiniuException {
        File file = FileUtil.file("C:/Users/Yang/Desktop/dafultAvatar.webp");
        String name = file.getName();
        String key = "avatar/" + name;
        Result result = qiNiuService.uploadFile(file, key);
        System.out.println(JSONUtil.toJsonStr(result));
    }

}
