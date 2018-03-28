package com.showaye.microappointment.controller;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.entity.Comment;
import com.showaye.microappointment.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 评论，类别等小接口
 * @Author HuangShiming
 * @Date 2018/2/5
 */
@Controller
public class AttachmentController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("/getAllTypes")
    @ResponseBody
    public BaseResult getAllTypes() {

        log.info("收到getAllTypes请求");
        BaseResult baseResult = attachmentService.getAllTypes();
        log.info("getAllTypes返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/uploadPic")
    @ResponseBody
    public BaseResult uploadPicture(HttpServletRequest request) {

        log.info("接收到uploadPic请求");
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getServletContext());

        // 服务器路径
        String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        // 包含了文件上传项
        if (cmr.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> files = mRequest.getFileNames();
            List<MultipartFile> multipartFiles = new ArrayList<>();
            while (files.hasNext()) {
                multipartFiles.add(mRequest.getFile(files.next()));
            }
            BaseResult baseResult = attachmentService.uploadImage(multipartFiles, serverUrl);
            log.info("uploadPic请求返回：" + baseResult.toString());
            return baseResult;
        } else {
            return null;
        }
    }

    @RequestMapping("comment")
    @ResponseBody
    public BaseResult comment(HttpServletRequest request, @RequestBody Comment comment) {

        log.info("收到comment请求:{}", comment.toString());
        BaseResult baseResult = attachmentService.comment(comment);
        log.info("comment返回信息：" + baseResult.toString());
        return baseResult;

    }
}
