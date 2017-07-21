package com.rfid.netty.action.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rfid.netty.domain.Label;
import com.rfid.netty.mapper.LabelMapper;
import com.rfid.netty.mapper.custom.LabelMapperCustom;
import com.rfid.netty.mapper.custom.UserInfoMapperCustom;

public class LabelService {
	
	@Autowired
	LabelMapperCustom labelMapperCustom;
	
	@Autowired
	LabelMapper labelMapper;
	
	@Autowired
	UserInfoMapperCustom userInfoMapperCustom;
	
	public List<Label> getLabelByUserId(int userId){
		return labelMapperCustom.getLabelByUserId(userId);
	}
	
	public List<Label> getLabelByUserName(String userName){
		Integer userId = userInfoMapperCustom.getIdByName(userName);
		if(userId == null){
			return null;
		}
		return labelMapperCustom.getLabelByUserId(userId);
	}
	
	public Label getLabelByCode(String labelCode){
		return labelMapperCustom.getLabelByCode(labelCode);
	}
	
	public Label getLabelByUseridAndCode(String labelCode, int userId){
		
		return labelMapperCustom.getLabelByUseridAndCode(labelCode, userId);
	}
	
	
	public int deleteLabelByCode(String labelCode){
		return labelMapperCustom.deleteByCode(labelCode);
	}
	
	public int addLabel(Label label){
		return labelMapper.insertSelective(label);
	}
	
	public int updateLabelByCode(Label label){
		
		return labelMapperCustom.updateLabelByCode(label);
	}
	
	public int updateLabelByCodeAndUserid(Label label){
		return labelMapperCustom.updateLabelByCodeAndUserid(label);
	}
}
