package web.data.entity;

import game.tools.utils.UUIDTools;

public class Gift {
    private long id;

    private String giftNo;
    
    private String item;
    
    private long startTime;

    private long endTime;

    private String useTime;

    public Gift() {	}
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGiftNo() {
        return giftNo;
    }

    public void setGiftNo(String giftNo) {
        this.giftNo = giftNo == null ? null : giftNo.trim();
    }

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	public static Gift create(String plaform, String channel, long startTime, long endTime, String itemString) 
	{
		Gift gift = new Gift();
		gift.giftNo = plaform + "" + channel + "" + UUIDTools.getUUid();
		gift.startTime = startTime;
		gift.endTime = endTime;
		gift.item = itemString;
		
		return gift;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	
}