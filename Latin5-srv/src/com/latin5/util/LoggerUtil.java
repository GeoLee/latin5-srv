package com.latin5.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggerUtil {
	
	/**
	 * 普通日志
	 * @param msg
	 * @param e
	 */
	public static void logError(String msg,Exception e){
		Log log=LogFactory.getLog("");
		log.error(msg,e);
	}
	public static void logInfo(String moduleName,String msg){
		Log log=LogFactory.getLog(moduleName);
		log.info(msg);
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
	
	/**
	 * 独立模块日志
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
	
	public static class LoggerModuleType{
		public static String MODULE_NAME_FETCH_API="fetchApi";
	}
}
