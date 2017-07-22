package com.rfid.netty.action.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rfid.netty.domain.Label;
import com.rfid.netty.domain.RfidSys;
import com.rfid.netty.mapper.LabelMapper;
import com.rfid.netty.mapper.custom.LabelMapperCustom;
import com.rfid.netty.mapper.custom.RfidSysMapperCustom;
import com.rfid.netty.mapper.custom.UserInfoMapperCustom;

public class LabelService {
	
	@Autowired
	LabelMapperCustom labelMapperCustom;
	
	@Autowired
	LabelMapper labelMapper;
	
	@Autowired
	RfidSysMapperCustom rfidSysMapper;
	
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
	
	public List<Label> getLabels(){
		
		return labelMapperCustom.getLabels();
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
	
	public byte[] getImgByCodeAndUserid(Label label){
		String imgPath = labelMapperCustom.getImgByCodeAndUserid(label);
		if(imgPath == null)
			return null;
		byte[] imgBytes = null;
		try {
			FileInputStream fi = new FileInputStream(imgPath);
			imgBytes = new byte[fi.available()];
			fi.read(imgBytes);
			fi.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return imgBytes;
	}
	
	public byte[] getImgByCode(Label label){
		
		return null;
	}
	
	public byte[] getIconsById(int iconId){
		String iconPath = labelMapperCustom.getIconsById(iconId);
		if(iconPath == null)
			return null;
		byte[] iconBytes = null;
		try {
			FileInputStream fi = new FileInputStream(iconPath);
			iconBytes = new byte[fi.available()];
			fi.read(iconBytes);
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return iconBytes;
	}
	
	public byte[] getIconByCodeAndUserid(Label label){
		String iconPath = labelMapperCustom.getIconByCodeAndUserid(label);
		if(iconPath == null)
			return null;
		byte[] iconBytes = null;
		try {
			FileInputStream fi = new FileInputStream(iconPath);
			iconBytes = new byte[fi.available()];
			fi.read(iconBytes);
			fi.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return iconBytes;
	}
	
	public int upadateOwnLabelIcon(int iconId, String labelCode, int userId){
		String iconPath = labelMapperCustom.getIconsById(iconId);
		if(iconPath == null)
			return 0;
		
		return labelMapperCustom.updateOwnLabelIcon(labelCode, userId, iconPath);
	}
	
	public int getUserId(String labelCode){
		return labelMapperCustom.getUserId(labelCode);
	}
	
	public RfidSys getRfidSys(){
		return rfidSysMapper.getRfidSys();
	}
	
	public byte[] getIconByCode(){
		
		return null;
	}
	public int updateLabelImg(String imgPath, String[] labels){
		return labelMapperCustom.updateLabelImg(imgPath, labels);
	}
}
