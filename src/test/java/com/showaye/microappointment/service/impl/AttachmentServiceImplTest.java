package com.showaye.microappointment.service.impl;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.entity.Comment;
import com.showaye.microappointment.service.AttachmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/3/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springContext.xml")
public class AttachmentServiceImplTest {

    @Autowired
    AttachmentService attachmentService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void comment() {
        Comment comment = new Comment();
        comment.setContent("hello");
        comment.setEventId(1);
        comment.setPublisherId(1);
        System.out.println(comment.toString());
        BaseResult comment1 = attachmentService.comment(comment);
        System.out.println(comment1.toString());
    }
}