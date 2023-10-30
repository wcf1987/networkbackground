package com.flow.network.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Tools {
    public static String SUCCESS="SUCCESS";
    public static String ERROR="ERROR";

    public static String storeFile(MultipartFile file, String path) throws IllegalStateException, IOException {
        // 获取原始文件名
        String fileName = file.getOriginalFilename();
        // // 获取文件后缀
        // String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
        // //文件的保存重新按照时间戳命名
        // String newFileName = System.currentTimeMillis() + suffixName;
        // 最终保存的文件
        File filePath = new File(path + File.separator + fileName);
        // 判断文件夹是否存在
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        // 保存文件
        file.transferTo(filePath);
        // 如果成功，会返回文件的绝对路径，方便下载
        //logger.info("文件已保存至" +filePath.toString());
        return filePath.toString();
    }
}
