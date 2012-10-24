package com.latin5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latin5.dao.TestDAO;
import com.latin5.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	@Autowired
	TestDAO testDAO;

}
