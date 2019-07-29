package com.leyou.upload.serviceImpl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    @Override
    public String upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        //校验文件类型
        String type = file.getContentType();
        if (!CONTENT_TYPES.contains(type)){
            LOGGER.info("文件类型不和法{}",fileName);
            return null;
        }
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null){
                LOGGER.info("文件内容不合法：{}", fileName);
                return null;
            }
            // 保存到服务器
            String ext = StringUtils.substringAfterLast(fileName, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            // 生成url地址，返回
            return "http://image.leyou.com/" + storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", fileName);
            e.printStackTrace();
        }
        return null;
    }

}
