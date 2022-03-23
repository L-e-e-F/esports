package com.example.mapper;

import com.example.entity.Player;
import com.github.pagehelper.Page;

public interface PlayerMapper {
    int deleteByPrimaryKey(Long playerId);

    int insert(Player record);

    int insertSelective(Player record);

    Player selectByPrimaryKey(Long playerId);

    int updateByPrimaryKeySelective(Player record);

    int updateByPrimaryKey(Player record);

    Page<Player> selectAll(int position,String name, String club);

    Page<Player> selectUserClubAll(Long user,int position,String name, String club);
}