package web.data.mapper;

import java.util.ArrayList;

import web.data.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser selectByNameAndPwd(String name, String pwd);

	ArrayList<SysUser> selectAll();

	ArrayList<SysUser> selectByLikeName(String searchKey);
	
	ArrayList<SysUser> selectByName(String searchKey);
}