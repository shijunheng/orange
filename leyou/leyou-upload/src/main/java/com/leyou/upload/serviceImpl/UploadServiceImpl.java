package com.leyou.upload.serviceImpl;

import com.leyou.upload.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

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
            file.transferTo(new File("C:\\Users\\wuhui\\Desktop\\上传图片库\\" + fileName));
            // 生成url地址，返回
            return "http://image.leyou.com/" + fileName;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", fileName);
            e.printStackTrace();
        }
        return null;
    }

}
