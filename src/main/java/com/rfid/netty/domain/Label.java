package com.rfid.netty.domain;

import java.util.Date;

public class Label {
    private Integer labelId;

    private Integer userId;

    private String labelCode;

    private String labelName;

    private String lastLocal;

    private String lastImage;

    private Short newImg;

    private Date lastTime;

    private Integer x;

    private Integer y;

    private Short wanted;

    private Short found;

    private String icon;

    private Short valid;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode == null ? null : labelCode.trim();
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    public String getLastLocal() {
        return lastLocal;
    }

    public void setLastLocal(String lastLocal) {
        this.lastLocal = lastLocal == null ? null : lastLocal.trim();
    }

    public String getLastImage() {
        return lastImage;
    }

    public void setLastImage(String lastImage) {
        this.lastImage = lastImage == null ? null : lastImage.trim();
    }

    public Short getNewImg() {
        return newImg;
    }

    public void setNewImg(Short newImg) {
        this.newImg = newImg;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Short getWanted() {
        return wanted;
    }

    public void setWanted(Short wanted) {
        this.wanted = wanted;
    }

    public Short getFound() {
        return found;
    }

    public void setFound(Short found) {
        this.found = found;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

	@Override
	public String toString() {
		return "Label [labelId=" + labelId + ", userId=" + userId + ", labelCode=" + labelCode + ", labelName="
				+ labelName + ", lastLocal=" + lastLocal + ", lastImage=" + lastImage + ", newImg=" + newImg
				+ ", lastTime=" + lastTime + ", x=" + x + ", y=" + y + ", wanted=" + wanted + ", found=" + found
				+ ", icon=" + icon + ", valid=" + valid + "]";
	}
    
}