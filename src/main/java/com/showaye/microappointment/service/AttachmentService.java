package com.showaye.microappointment.service;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.entity.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/2/6
 */
public interface AttachmentService {

    BaseResult getAllTypes();

    BaseResult uploadImage(List<MultipartFile> multipartFiles, String serverUrl);

    BaseResult comment(Comment comment);
}
