package com.rfid.netty.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rfid.netty.domain.Label;

public interface LabelMapperCustom {
    List<Label> getLabelByUserId(int userId);
    Label getLabelByCode(String labelCode);
    int deleteByCode(String labelCode);
    Label getLabelByUseridAndCode(@Param("labelCode")String labelCode, @Param("userId")int userId);
    int updateLabelByCode(Label label);
    int updateLabelByCodeAndUserid(Label label);
}