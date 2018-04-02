package web.action;

import java.util.ArrayList;

import game.tools.page.Page;
import game.tools.page.PageWork;
import game.tools.utils.DateTools;
import game.tools.utils.StringTools;
import web.action.url.ActionURL;
import web.control.entity.Const;
import web.data.MysqlControl;
import web.data.entity.Gift;
import web.msg.Message;
import web.tools.properties.FootballProperties;

public class GiftAction extends Action 
{

	@Override
	protected void init() 
	{
		
	}
	
	public String searchGift()
	{
		String giftNo = getRequest().getParameter("giftNo");
		String startTimeString = getRequest().getParameter("startTime");
		String endTimeString = getRequest().getParameter("endTime");
		String useString = getRequest().getParameter("use");
		  
		clearGift();
		
		setSessionValue("startTime", startTimeString);
		setSessionValue("endTime", endTimeString);
		setSessionValue("use", useString);
		
		int pageSize = FootballProperties.getPAGE_SIZE();
		
		if(!StringTools.empty(useString))			//根据使用情况来查询
		{
			PageWork pageWork = null;
			
			Boolean use = Boolean.parseBoolean(useString);
			
			if(use)
			{
				pageWork = new PageWork() 
				{
					@Override
					public Object selectPageNo(int pageNo, int pageSize) 
					{
						return MysqlControl.getGiftMapper().selectByUseTimeNotNull(pageNo , pageSize);
					}
				};
			}
			else
			{
				pageWork = new PageWork() 
				{
					@Override
					public Object selectPageNo(int pageNo, int pageSize) 
					{
						return MysqlControl.getGiftMapper().selectByUseTimeNull(pageNo , pageSize);
					}
				};
			}
			
			boolean next = Boolean.parseBoolean(getRequest().getParameter("next"));
			
			String jumpNoString = getRequest().getParameter("jumpNo");
			if(jumpNoString != null)
			{
				int jumpNo = Integer.parseInt(jumpNoString);
				Page.selectByPageNo(getSession(), jumpNo , pageSize, pageWork);
			}
			else
			{
				Page.selectByPageNo(getSession(), next , pageSize, pageWork);
			}
			
			return ActionURL.TO_SHOW_GIFT_PAGE;
		}
		else if(!StringTools.empty(startTimeString) && !StringTools.empty(endTimeString))			//是否是根据时间范围来查询的
		{
			long startTime = DateTools.getCurrentTimeLong(startTimeString);
			long endTime = DateTools.getCurrentTimeLong(endTimeString);
			
			PageWork pageWork = new PageWork() 
			{
				@Override
				public Object selectPageNo(int pageNo, int pageSize) 
				{
					return MysqlControl.getGiftMapper().selectByStartEndTime(startTime, endTime ,pageNo , pageSize);
				}
			};
			
			boolean next = Boolean.parseBoolean( getRequest().getParameter("next"));
			
			String jumpNoString = getRequest().getParameter("jumpNo");
			if(jumpNoString != null)
			{
				int jumpNo = Integer.parseInt(jumpNoString);
				Page.selectByPageNo(getSession(), jumpNo , pageSize, pageWork);
			}
			else
			{
				Page.selectByPageNo(getSession(), next , pageSize, pageWork);
			}
		}
		else
		{
			if(StringTools.empty(giftNo))
			{
				alert("请输入要查询的礼包码!");
				return ActionURL.TO_SHOW_GIFT_PAGE;
			}
			
			ArrayList<Gift> giftList = MysqlControl.getGiftMapper().selectByGiftNo(giftNo);
			
			setSessionValue(Page.PAGE_NO_RESULT, giftList);
		}
		
		return ActionURL.TO_SHOW_GIFT_PAGE;
	}
	
	public String createGift()
	{
		String startTimeString = getRequest().getParameter("startTime");
		String endTimeString = getRequest().getParameter("endTime");
		String createCountString = getRequest().getParameter("giftCount");
		String plaform = getRequest().getParameter("plaform");
		String channel = getRequest().getParameter("channel");
		String itemString = getRequest().getParameter("item");
		
		if(StringTools.empty(plaform) || StringTools.empty(channel) || 
				StringTools.empty(createCountString) || StringTools.empty(endTimeString) || 
				StringTools.empty(startTimeString))
		{
			alert("请注意输入礼包参数");
			return ActionURL.TO_SHOW_GIFT_PAGE;
		}
		
		long startTime = DateTools.getCurrentTimeLong(startTimeString);
		long endTime = DateTools.getCurrentTimeLong(endTimeString);
		int createCount = Integer.parseInt(createCountString);
		
		long startTimes = System.currentTimeMillis();
		
		ArrayList<Gift> giftList = new ArrayList<>(createCount);
		for (int i = 0; i < createCount; i++) 
		{
			Gift gift = Gift.create(plaform , channel , startTime , endTime , itemString);
			giftList.add(gift);
		}
		long endTimes = System.currentTimeMillis();
		long createUseTime = (endTimes - startTimes);
		
		startTimes = System.currentTimeMillis();
		MysqlControl.getGiftMapper().insertList(giftList);
		endTimes = System.currentTimeMillis();
		long insertUseTime = (endTimes - startTimes);
		
		alert(Message.MSG_GENERATE_GIFT_OK + " 数量 : " + createCount + " 用时 : create : " + createUseTime + ", insert : " + insertUseTime);
		
		return showGiftList();
	}
	
	public String showGiftList() 
	{
		clearGift();
		
		String jumpNoString = getRequest().getParameter("jumpNo");
		
		int pageSize = FootballProperties.getPAGE_SIZE();
		
		PageWork pageWork = new PageWork() 
		{
			@Override
			public Object selectPageNo(int pageNo, int pageSize) 
			{
				return MysqlControl.getGiftMapper().selectByPageNo(pageNo, pageSize);
			}
		};
		
		//执行分布数据
		if(jumpNoString != null)
		{
			int jumpNo = Integer.parseInt(jumpNoString);
			Page.selectByPageNo(getSession(), jumpNo , pageSize, pageWork);
		}
		else
		{
			String nextString = getRequest().getParameter("next");
			
			boolean next = true;
			
			if(nextString != null)
				next = Boolean.parseBoolean(nextString);
			
			Page.selectByPageNo(getSession(), next , pageSize, pageWork);
			
		}
		
		return ActionURL.TO_SHOW_GIFT_PAGE;
	}
	
	
	private void clearGift()
	{
		if(getRequest().getParameter("clear") != null)
		{
			Page.clear(getSession());
			
			setSessionValue("startTime", null);
			setSessionValue("endTime", null);
			setSessionValue("use", null);
			setSessionValue("notuse", null);
		}
	}

}
