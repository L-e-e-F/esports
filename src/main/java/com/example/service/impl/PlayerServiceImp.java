package com.example.service.impl;

import com.example.entity.Player;
import com.example.mapper.PlayerMapper;
import com.example.service.PlayerService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PlayerServiceImp implements PlayerService {

    @Resource
    private PlayerMapper playerMapper;

    @Override
    public Page<Player> selectAll(int position , String name, String club) {
        return playerMapper.selectAll(position,name,club);
    }

    @Override
    public int insertSelective(Player record) {
        return playerMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(Player record) {
        return playerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Long playerId) {
        return playerMapper.deleteByPrimaryKey(playerId);
    }

    @Override
    public Page<Player> selectUserClubAll(Long user, int position, String name, String club) {
        return playerMapper.selectUserClubAll(user,position,name,club);
    }
}
