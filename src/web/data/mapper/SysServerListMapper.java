package web.data.mapper;

import java.util.ArrayList;

import web.data.entity.SysServerList;

public interface SysServerListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysServerList record);

    int insertSelective(SysServerList record);

    SysServerList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysServerList record);

    int updateByPrimaryKey(SysServerList record);

	ArrayList<SysServerList> selectAll();

	ArrayList<SysServerList> selectByLikeName(String searchName);
}