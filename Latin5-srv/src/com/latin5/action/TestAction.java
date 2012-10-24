package com.latin5.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.latin5.action.common.BaseAction;
import com.latin5.service.TestService;

@Component("questionAction")
public class TestAction extends BaseAction{

	@Autowired
	TestService testService;
	
	
}
