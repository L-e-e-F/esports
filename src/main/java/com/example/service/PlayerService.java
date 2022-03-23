package com.example.service;

import com.example.entity.Player;
import com.github.pagehelper.Page;

public interface PlayerService {

    Page<Player> selectAll(int position, String name, String club);

    int insertSelective(Player record);

    int updateByPrimaryKey(Player entity);

    int deleteByPrimaryKey(Long playerId);

    Page<Player> selectUserClubAll(Long user,int position,String name, String club);
}
