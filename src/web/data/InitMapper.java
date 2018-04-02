package web.data;

import org.springframework.beans.factory.annotation.Autowired;

import web.data.mapper.SysOperateLogMapper;
import web.data.mapper.SysServerListMapper;
import web.data.mapper.SysUserMapper;

public class InitMapper {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysServerListMapper sysServerListMapper;
	@Autowired
	private SysOperateLogMapper sysOperateLogMapper;


	public void initMapper() {
		Mappers.SYS_USER_MAPPER = sysUserMapper;
		Mappers.SYS_SERVER_LIST_MAPPER = sysServerListMapper;
		Mappers.SYS_OPERATE_LOG_MAPPER = sysOperateLogMapper;

	}

}
