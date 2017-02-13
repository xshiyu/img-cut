package com.sy.img;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sy.img.handler.ImageHandler;
import com.sy.img.util.FileUtil;

public class ImageFilter implements Filter {
    
    private final ImageHandler imgHandler = ImageHandler.single();
    
    public static String[] imgSizeArr;
    
	/**
     * init
     */
    @Override
	public void init(final FilterConfig config) throws ServletException {
    	final String imageSizes = config.getInitParameter("imageSizes");
    	imgSizeArr = imageSizes.split(",");
		Arrays.sort(imgSizeArr);
    }

    @Override
	public void doFilter(final ServletRequest req, ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String target = request.getRequestURI();
        if (FileUtil.isImg(target)) {
            res = imgHandler.handler(target, request, response);
        }
        chain.doFilter(req, res);
    }

    @Override
	public void destroy() {
    }
    
}
