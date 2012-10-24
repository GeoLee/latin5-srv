package com.latin5.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggerUtil {

	/**
	 * sms的错误日志打印
	 * @param msg
	 * @param e
	 */
	public static void logSmsError(String msg,Exception e){
		logError(LoggerModuleType.MODULE_NAME_SMS_API, msg, e);
	}
	/**
	 * active mq.错误日志打印。
	 * @param msg
	 * @param e
	 */
	public static void logActiveMqError(String msg,Exception e){
		logError(LoggerModuleType.MODULE_NAME_ACTIVE_MQ_API, msg, e);
	}
	/**
	 * email发送日志。
	 * @param msg
	 * @param e
	 */
	public static void logEmailError(String msg,Exception e){
		logError(LoggerModuleType.MODULE_NAME_EMAIL, msg, e);
	}
	/**
	 * fetch log
	 * @param msg
	 * @param e
	 */
	public static void logFetchError(String msg,Exception e){
		logError(LoggerModuleType.MODULE_NAME_FETCH_API, msg, e);
	}
	public static void logFetchInfo(String msg){
		logInfo(LoggerModuleType.MODULE_NAME_FETCH_API, msg);
	}
	
	
	
	
	public static void logError(String msg,Exception e){
		Log log=LogFactory.getLog("");
		log.error(msg,e);
	}
	
	/**
	 * 针对邮件、53kf将采取独立目录下记录。目前支持mail,53kf、电话接口
	 * @param moduleName
	 * @param msg
	 */
	public static void logError(String moduleName,String msg,Exception e){
		Log log=LogFactory.getLog(moduleName);
		if(e!=null){
			log.error(msg,e);
		}else{
			log.error(msg);
		}
	}
	public static void logInfo(String moduleName,String msg){
		Log log=LogFactory.getLog(moduleName);
		log.info(msg);
	}
	
	public static class LoggerModuleType{
		public static String MODULE_NAME_EMAIL="email";
		public static String MODULE_NAME_ACTIVE_MQ_API="activeMqApi";
		public static String MODULE_NAME_SMS_API="smsApi";
		public static String MODULE_NAME_FETCH_API="fetchApi";
	}
}
