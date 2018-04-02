package web.data.mapper;

import java.util.ArrayList;

import web.data.entity.OperateLog;

public interface SysOperateLogMapper {

	
	int insert(OperateLog log);
	
	ArrayList<OperateLog> getOperateLog(int start,int limit);
	
	int selectLogCount();
}
