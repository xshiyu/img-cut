package com.sy.img.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.sy.img.util.FileUtil;
import com.sy.img.util.StringUtil;

public class ThumbImgTag extends TagSupport {
	
	private String src;
	
	private String size;
	
    @Override
    public int doStartTag() throws JspException {
		try {
	    	if(StringUtil.isBlank(src) || !FileUtil.isImg(src)){
	    		return EVAL_BODY_AGAIN;
	    	}else{
	    		final StringBuffer html = new StringBuffer(src);
	    		html.append("_");
	    		html.append(size);
	    		html.append(".");
	    		html.append(FileUtil.getSuffix(src));
				pageContext.getOut().print(html);
	    }
     } catch (final IOException e) {
        e.printStackTrace();
     }
     return EVAL_BODY_AGAIN;
    }
	  
	@Override
	public int doEndTag() throws JspException {
	   return EVAL_PAGE;
	}
	
	public String getSrc() {
		return src;
	}

	public void setSrc(final String src) {
		this.src = src;
	}

	public String getSize() {
		return size;
	}

	public void setSize(final String size) {
		this.size = size;
	}
	
}
