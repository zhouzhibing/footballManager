package web.data.mapper;
import java.util.ArrayList;
import web.data.entity.Gift;

public interface GiftMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gift record);

    int insertSelective(Gift record);

    Gift selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);
    
    ArrayList<Gift> selectByPageNo(int pageNo , int pageSize);

	void insertList(ArrayList<Gift> giftList);

	ArrayList<Gift> selectByGiftNo(String giftNo);

	ArrayList<Gift> selectByStartEndTime(long startTime, long endTime, int pageNo, int pageSize);

	ArrayList<Gift> selectByUseTimeNotNull(int pageNo, int pageSize);

	ArrayList<Gift> selectByUseTimeNull(int pageNo, int pageSize);
}