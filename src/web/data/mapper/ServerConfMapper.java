package web.data.mapper;

import java.util.ArrayList;
import game.tools.db.conf.ServerConfig;
import web.data.entity.ServerConf;

public interface ServerConfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerConf record);

    int insertSelective(ServerConf record);

    ServerConf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerConf record);

    int updateByPrimaryKey(ServerConfig record);

	ArrayList<ServerConfig> selectAll();
}