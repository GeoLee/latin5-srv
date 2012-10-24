package com.latin5.action.common;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

public class BaseAction {
	
	
	public Log log = LogFactory.getLog(this.getClass());
	
	protected String forwardMsgPage(String msg,Model model){
		model.addAttribute("msg", msg);
		return "error/msg";
	}
	
	protected String forwardMsgPage(String msg,ModelMap m){
		m.put("msg", msg);
		return "error/msg";
	}
   
   	/**
   	 * 代替spring mvc中的responseBody标签输出响应信息�?
   	 * @param response
   	 * @param msg
   	 */
   	protected void writeResponseMsg(HttpServletResponse response,String msg){
   		try{
   		response.setContentType("text/html;charset=UTF-8");
   		response.getWriter().write(msg);
   		}catch(Exception e){}
   	}
   	
}
