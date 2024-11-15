package model;

public class OrderScheduleItem {
	
	private String deviceName;
	private String startTime;
	private String endTime;

	public OrderScheduleItem(String deviceName, String startTime, String endTime) {
		this.deviceName = deviceName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
