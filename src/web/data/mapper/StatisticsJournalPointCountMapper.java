package web.data.mapper;

import java.util.List;

import web.data.entity.StatisticsJournalPointCount;

public interface StatisticsJournalPointCountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StatisticsJournalPointCount record);

    int insertSelective(StatisticsJournalPointCount record);

    StatisticsJournalPointCount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StatisticsJournalPointCount record);

    int updateByPrimaryKey(StatisticsJournalPointCount record);

	List<StatisticsJournalPointCount> selectByPageNo(int pageNo, int pageSize);

	List<StatisticsJournalPointCount> selectByPageNoAndType(int pageNo, int pageSize, String actionId);

	StatisticsJournalPointCount selectByDate(String startDate);

	List<StatisticsJournalPointCount> selectByDates(int pageNo, int pageSize);
}