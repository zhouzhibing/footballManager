package web.data.mapper;

import java.util.ArrayList;

import web.data.entity.SysDbList;


public interface SysDbListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDbList record);

    int insertSelective(SysDbList record);

    SysDbList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDbList record);

    int updateByPrimaryKey(SysDbList record);

	ArrayList<SysDbList> selectAll();

	ArrayList<SysDbList> selectByLikeName(String searchName);
}