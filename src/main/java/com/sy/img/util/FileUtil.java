package com.sy.img.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	
	/**
	 * 判断是否为图片
	 * @param url
	 * @return
	 */
	public static boolean isImg(final String url){
		if(url.endsWith("jpg") || url.endsWith("JPG") || url.endsWith("png")
				|| url.endsWith("PNG") || url.endsWith("bmp")
				|| url.endsWith("BMP") || url.endsWith("gif")
				|| url.endsWith("GIF")){
			return true;
		}else{
			return false;
		}
	}

    /**
     * 判断文件是否存在
     * 
     * @param filePath
     * @return
     */
    public static boolean exists(final String filePath) {
    	return new File(filePath).exists();
    }
    
    /**
     * 获取文件后缀
     * 
     * @param fileName
     * @return
     */
    public static String getSuffix(final String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 获取文件名
     * 
     * @param filePath
     * @return
     */
    public static String getFileName(final String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    /**
     * 获取文件不带后缀的全路径
     * 
     * @param filePath
     * @return
     */
    public static String getNoSuffixFilePath(final String filePath) {
        return filePath.substring(0, filePath.lastIndexOf("."));
    }

    /**
     * 获取图片保存名称
     * 
     * @param filePath
     * @param scale
     * @param width
     * @param height
     * @param quality
     * @param rotate
     * @return
     */
    public static String getImgPath(final String filePath, final Integer width, final Integer height, final Double quality, final Double scale,  final Double rotate) {
        // abc_300x500_s_xx_q90_r90.png
        final String k = getNoSuffixFilePath(filePath);
        final StringBuffer sb = new StringBuffer(k);
        if ((null != width) && (null != height) && (width != 0) && (height != 0)) {
            sb.append("_" + width + "x" + height);
        }
        if ((null != scale) && (scale.compareTo(0.0D) > 0)) {
            sb.append("_" + scale);
        }
        if ((null != quality) && (quality > 0)) {
            sb.append("_q" + quality);
        }
        if ((null != rotate) && (rotate.compareTo(0.0D) > 0)) {
            sb.append("_r" + rotate);
        }
        return sb.append("." + getSuffix(filePath)).toString();
    }
    
    /**
     * 读取文件转换为byte[]
     * 复制commons FileUtils
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] readFileToByteArray(final String filename) throws IOException {  
    	final File file = new File(filename);
    	InputStream in = null;
        try {
            in = new FileInputStream(file);
            return FileUtil.toByteArray(in, (int)file.length());
        } finally {
        	if (in != null) {
        		in.close();
            }
        }
    }
    
    private static final int EOF = -1;
    
    public static byte[] toByteArray(final InputStream input, final int size) throws IOException {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
        }
        if (size == 0) {
            return new byte[0];
        }
        final byte[] data = new byte[size];
        int offset = 0;
        int readed;
        while ((offset < size) && ((readed = input.read(data, offset, size - offset)) != EOF)) {
            offset += readed;
        }
        if (offset != size) {
            throw new IOException("Unexpected readed size. current: " + offset + ", excepted: " + size);
        }
        return data;
    }

}
