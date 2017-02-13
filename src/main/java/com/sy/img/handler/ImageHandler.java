package com.sy.img.handler;

import java.io.OutputStream;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sy.img.ImageFilter;
import com.sy.img.util.FileUtil;

import net.coobird.thumbnailator.Thumbnails;

public class ImageHandler {
	
	private static String SPEL = "_";
	
	private static String X = "x";
	
    private ImageHandler() {
    }

    public static ImageHandler single() {
        return ImageHandlerHodler.single;
    }

    private static class ImageHandlerHodler {
        private static ImageHandler single = new ImageHandler();
    }

    /**
     * 
     * @param target 请求的图片路径
     * @param request
     * @param response
     * @return
     */
    public HttpServletResponse handler(final String target, final HttpServletRequest request, final HttpServletResponse response) {
        //请求文件物理路径
        final String filePath = request.getSession().getServletContext().getRealPath(target);
        if(filePath == null){
        	//获取不到原图
        	return response;
        }
        //文件存在直接返回
        if (FileUtil.exists(filePath)) {
            return response;
        }
        //文件不存在
        final String[] _ary = new String[2];
        final int inx = filePath.lastIndexOf(SPEL);
        if(inx < 0){
        	_ary[0] = filePath;
        }else{
        	_ary[0] = filePath.substring(0, inx);
            _ary[1] = filePath.substring(inx+1, filePath.length());
        }
        if(_ary.length == 1){
        	//请求的是原图，且没有图片
        	return response;
        }
        if(!FileUtil.exists(_ary[0])){
        	//请求的是缩略图，但是原图不存在
        	return response;
        }
        //请求生成缩略图
        final String area = _ary[1].substring(0, _ary[1].indexOf("."));
        if(!areaExist(area)){
        	return response;
        }
        //判断是否允许压缩此尺寸
        final String[] sizes = area.split(X);
        if(sizes.length != 2){
        	return response;
        }
        // 缩略图路径
        final String thumbPath = filePath;
		try {
			Thumbnails.of(_ary[0])   
			          .size(new Integer(sizes[0]), new Integer(sizes[1]))  
			          .outputQuality(1.0f)
			          .toFile(thumbPath);
			
			final byte data[] = FileUtil.readFileToByteArray(thumbPath);    
	        response.setContentType("image/jpg"); //设置返回的文件类型     
	        final OutputStream os = response.getOutputStream();    
	        os.write(data);    
	        os.flush();    
	        os.close(); 
		} catch (final Exception e) {
			e.printStackTrace();
		}
        return response;
    }
    
    /**
     * 判断图片尺寸是否存在
     * @param area
     * @return
     */
    private boolean areaExist(final String area){
    	if(Arrays.binarySearch(ImageFilter.imgSizeArr, area) >= 0){
    		return true;
    	}else{
    		return false;
    	}
    }
    
}
