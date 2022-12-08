package com.yxz.wulibibiji.utils;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author yangxiaozhuo
 * @date 2022/12/09
 */
@Slf4j
public class MyFileUtil extends FileTypeUtil {

    static HashSet<String> imgset = new HashSet<>(4);

    static {
        imgset.add("jpg");
        imgset.add("png");
        imgset.add("webp");
        imgset.add("jpeg");
    }

    public static boolean isImg(File file) {
        String type = getType(file);
        return imgset.contains(type);
    }

    public static boolean isImg(InputStream inputStream) {
        String type = getType(inputStream);
        return imgset.contains(type);
    }
    public static boolean isImg(MultipartFile file){
        try {
            return isImg(file.getInputStream());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public static boolean sizeCheck(File file, int mb){
        double length = file.length();
        double size = length / 1024 / 1024;
        return size <= mb;
    }
    public static boolean sizeCheck(MultipartFile file, int mb){
        double size = Double.parseDouble(String.valueOf(file.getSize())) / 1024 / 1024;
        return size <= mb;
    }
}
