package web.data.football.mapper;

import java.util.ArrayList;

import web.data.football.entity.JournalPoint;

public interface JournalPointMapper {
    int insert(JournalPoint record);

    int insertSelective(JournalPoint record);
    
    ArrayList<JournalPoint> selectAll();

	ArrayList<JournalPoint> selectByDate(String startDate, int pageNo, int pageSize);
	
	int selectByDateAndAction(String startDate, String action);
}