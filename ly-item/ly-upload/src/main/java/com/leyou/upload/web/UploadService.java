package com.leyou.upload.web;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * @ProjectName: leyou
 * @Author: Beryl
 * @Date: 2019/6/21 9:25 PM
 * @Version: 1.0
 * @Description: TODO
 */
@Slf4j
@Service
public class UploadService {

    private static final String IMAGE_DIR= "/usr/local/var/www";

   private static final String IMAGE_URL = "http://image.leyou.com/";

   private static final List<String> ALLOW_IMAGE_TYPE= Arrays.asList("image/png","image/jpeg","image/jpg","image/bmp");
    public String uploadImage(MultipartFile file) {
        //校验图片 根据名称判断文件类型，根据
        String contentType = file.getContentType();
        if(!ALLOW_IMAGE_TYPE.contains(contentType)){
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        //校验内容判断文件合法

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image == null){
                throw new IOException("图片内容有误");
            }

        } catch (IOException e) {
           log.error("【上传服务】图片类型有误，原因：{}",e.getMessage());
           throw new LyException(ExceptionEnum.INVALID_FILE_TYPE,e);
        }

            //文件地址
            File dir = new File(IMAGE_DIR);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                String filename= file.getOriginalFilename();
            File filePath = new File(dir,file.getOriginalFilename());

                //保存数据到指定位置
            try {
                file.transferTo(filePath);
            } catch (IOException e) {
                log.error("【上传服务】图片保存失败原因");
                throw new LyException(ExceptionEnum.FILE_UPLOAD_ERROR,e);
            }
            //返回可访问的图片地址

            return IMAGE_URL+filename ;
        }
    }

