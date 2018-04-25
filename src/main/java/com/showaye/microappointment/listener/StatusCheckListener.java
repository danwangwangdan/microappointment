package com.showaye.microappointment.listener;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.dto.EventGeneralResp;
import com.showaye.microappointment.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/4/25
 */
public class StatusCheckListener implements ServletContextListener {
    private static Logger log = LoggerFactory.getLogger(StatusCheckListener.class);
    private boolean isRun = true;
    private EventService eventService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        eventService = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(EventService.class);
        new StatusCheckThread().start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        isRun = false;
    }

    class StatusCheckThread extends Thread {
        @Override
        public void run() {
            while (isRun) {
                try {
                    Thread.sleep(1000);
                    // 循环遍历已过期活动，并将他们的状态更新为已完成
                    BaseResult<List<EventGeneralResp>> expiredEvents = eventService.findExpiredEvents();
                    for (EventGeneralResp eventGeneralResp : expiredEvents.getData()) {
                        eventService.updateStatus(3, eventGeneralResp.getId());
                        log.info("已自动将：{}活动状态更新为已结束", eventGeneralResp.getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("活动状态检查出错：" + e.toString());
                }
            }
        }
    }
}
