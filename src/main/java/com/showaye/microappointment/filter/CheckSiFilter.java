package com.showaye.microappointment.filter;

import com.showaye.microappointment.cache.CacheManager;
import com.showaye.microappointment.constant.ResultConstant;
import com.showaye.microappointment.model.base.BaseResult;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Describe:
 * @Author: HuangShiming
 * @Created: 2018-03-22
 */
public class CheckSiFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (StringUtils.isBlank(CacheManager.getSessionKey(req.getHeader("sessionId")))) {
            BaseResult baseResult = new BaseResult(ResultConstant.SESSION_TIMEOUT.code, ResultConstant.SESSION_TIMEOUT.message);
            resp.setCharacterEncoding("UTF-8");
            PrintWriter pr = response.getWriter();
            pr.write(baseResult.toString());
            pr.flush();
            pr.close();
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
