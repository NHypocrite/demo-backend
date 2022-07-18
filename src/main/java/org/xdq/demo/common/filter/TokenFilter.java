package org.xdq.demo.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.xdq.demo.common.DemoConstants;
import org.xdq.demo.common.Result;
import org.xdq.demo.common.TokenUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*") //说明本类是一个过滤器，过滤所有的请求
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器TokenFilter加载了！");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        /*
         * 接受跨域访问
         */
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        res.setHeader("Access-Control-Max-Age", "0");//预检请求的声明周期
        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,Token,notoken");
        res.setHeader("Access-Control-Allow-Credentials", "false");//不使用session
        res.setHeader("XDomainRequestAllowed","1");



        String path = req.getServletPath();//获取请求路径

        if("/user/login".equals(path) || "/user/register".equals(path)  || path.startsWith("/blob/")){
            chain.doFilter(request,response);//放行通过

            return;
        }

        //从请求对象中获取请求头Token的值
        String token = req.getHeader(DemoConstants.HEADER_PARAM_TOKEN);

        try {
            TokenUtils.verify(token);
            //验证通过
            chain.doFilter(request,response);//放行通过
            return;
        } catch (Exception e) {
            //验证未通过

            Result result = Result.err(
                    Result.CODE_ERR_UNLOGIN,e.getMessage()+",请登录");

            ObjectMapper objectMapper = new ObjectMapper();//创建json工具对象
            String resultJson = objectMapper.writeValueAsString(result);

            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();//获得通向浏览器的字符输出流

            out.print(resultJson);

            out.flush();
            out.close();

        }


    }
}
