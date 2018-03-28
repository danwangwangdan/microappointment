package com.showaye.microappointment.service.impl;

import com.showaye.microappointment.constant.ResultConstant;
import com.showaye.microappointment.dao.CategoryMapper;
import com.showaye.microappointment.dao.UserMapper;
import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.dto.ImageResp;
import com.showaye.microappointment.model.entity.CategoryDetail;
import com.showaye.microappointment.model.entity.Comment;
import com.showaye.microappointment.model.entity.UploadImage;
import com.showaye.microappointment.service.AttachmentService;
import com.showaye.microappointment.util.PictureUtil;
import com.showaye.microappointment.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/2/6
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private CategoryMapper categoryMapper;
    private static final Logger log = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    //@Cacheable("AttachmentServiceImpl.getAllTypes")
    @Override
    public BaseResult getAllTypes() {

        BaseResult baseResult = new BaseResult();
        try {
            List<CategoryDetail> allTypes = categoryMapper.findAllTypes();
            baseResult.setData(allTypes);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getAllTypes错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult uploadImage(List<MultipartFile> multipartFiles, String serverUrl) {

        // 缩略图缩放比例
        int scale = PropertiesUtil.getInstance().getInt("imageScale");
        // 上传地址
        String path = PropertiesUtil.getInstance().get("imageDir");
        // Tomcat映射地址
        String mappedPath = PropertiesUtil.getInstance().get("mappedDir");
        long maxSize = PropertiesUtil.getInstance().getLong("imageMaxSize");
        // 图片压缩质量
        Double imageQuality = PropertiesUtil.getInstance().getDouble("imageQuality");
        BaseResult<List<ImageResp>> baseResult = new BaseResult<List<ImageResp>>();
        List<ImageResp> uploadImageList = new ArrayList<ImageResp>();
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                // 检查图片后缀
                if (!PictureUtil.checkFormat(multipartFile.getOriginalFilename())) {
                    log.info("图片格式不支持：" + multipartFile.getOriginalFilename());
                    baseResult.setCode(ResultConstant.IMAGE_FORMAT_ERROR.code);
                    baseResult.setMessage(ResultConstant.IMAGE_FORMAT_ERROR.message);
                } else {

                    String imageHash = Integer.toHexString(UUID.randomUUID().toString().hashCode());
                    String hashPath = PictureUtil.getHashPath(imageHash);
                    mappedPath += "/" + hashPath;
                    path += "/" + hashPath;
                    String fileName = imageHash + PictureUtil.getImageFormat(multipartFile.getOriginalFilename());
                    // 图片地址为: 服务器路径 + 映射路径 + 文件名，例如：http://localhost:8080 /imageweb/img /abc.jpg
                    String imageUrl = serverUrl + mappedPath + "/" + fileName;
                    File file = new File(path, fileName);
                    UploadImage uploadImage = null;
                    if (!file.exists()) {
                        // 文件不存在，存入
                        log.info("图片不存在，即将存入图片" + imageUrl);
                        file.mkdirs();
                        multipartFile.transferTo(file);
                        uploadImage = new UploadImage();
                        uploadImage.setUploadTime(new Date());
                        uploadImage.setImageUrl(imageUrl);
                        // 压缩及生成缩略图
                        PictureUtil pictureUtil = new PictureUtil(path + "/" + fileName);
                        imageQuality = pictureUtil.getQuality(imageQuality);
                        // 以高度为基准，等比例缩放图片
                        PictureUtil.compressPicForScale(file.getAbsolutePath(), file.getAbsolutePath(), maxSize, imageQuality);
                        // 以高度为基准，等比例缩放图片
                        pictureUtil.resizeByHeight(scale);
                    }
                    ImageResp uploadImageDTO = new ImageResp();
                    uploadImageDTO.setUrl(uploadImage.getImageUrl());
                    uploadImageList.add(uploadImageDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传图片错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        baseResult.setData(uploadImageList);
        log.info("上传图片service返回信息：" + baseResult.toString());
        return baseResult;
    }

    // @CacheEvict(value = {"search", "findEventDetailsByEventId", "findEventGeneralsByTypeId", "UserServiceImpl.getAllMyEvents", "UserServiceImpl.getAllMyAttendEvents"}, allEntries = true)
    @Override
    @Transactional
    public BaseResult comment(Comment comment) {

        BaseResult baseResult = new BaseResult();
        try {
            // 1. 参数校验
            if (StringUtils.isBlank(comment.getContent())) {
                baseResult.setCode(ResultConstant.INPUT_DATA_ERROR.code);
                baseResult.setMessage(ResultConstant.INPUT_DATA_ERROR.message);
            } else {
                int i = userMapper.addComment(comment);
                log.info("comment更新了{}行", i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("comment错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }

        return baseResult;
    }
}
