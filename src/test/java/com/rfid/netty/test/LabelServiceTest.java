package com.rfid.netty.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.rfid.netty.action.service.LabelService;
import com.rfid.netty.domain.Label;
import com.rfid.netty.utils.ApplicationContextUtil;

public class LabelServiceTest {

	@Test
	public void test() {
		LabelService labelService = (LabelService) ApplicationContextUtil.getBean("labelService");
		List<Label> labels = labelService.getLabelByUserName("rfidl");
		System.out.println(labels);
	}

}
