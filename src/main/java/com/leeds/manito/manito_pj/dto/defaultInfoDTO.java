package com.leeds.manito.manito_pj.dto;

public class defaultInfoDTO {
    private int manitoIdx;
    private String startDate;
    private String endDate;
    private String missionTime;
    private String status;

	public int getManitoIdx() {
		return this.manitoIdx;
	}

	public void setManitoIdx(int manitoIdx) {
		this.manitoIdx = manitoIdx;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMissionTime() {
		return this.missionTime;
	}

	public void setMissionTime(String missionTime) {
		this.missionTime = missionTime;
	}  

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
