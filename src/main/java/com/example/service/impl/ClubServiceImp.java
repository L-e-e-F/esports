package com.example.service.impl;

import com.example.entity.Club;
import com.example.mapper.ClubMapper;
import com.example.service.ClubService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClubServiceImp implements ClubService {

    @Resource
    private ClubMapper clubMapper;

    @Override
    public Page<Club> selectALL(String group, String name) {
            return clubMapper.selectALL(group,name);
    }

    @Override
    public int insertSelective(Club record) {
        return clubMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Club record) {
        return clubMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long clubId) {
        return clubMapper.deleteByPrimaryKey(clubId);
    }

    @Override
    public List<Club> selectEventClub(Long id) {
        return clubMapper.selectEventClub(id);
    }

    @Override
    public List<Club> ALL(String group) {
        return clubMapper.ALL(group);
    }

    @Override
    public void updateClubPoints(String club) {
        clubMapper.updateClubPoints(club);
    }

    @Override
    public Page<Club> follow(Long user, String club) {
        return clubMapper.follow(user,club);
    }

    @Override
    public Page<Club> selectUserClub(String group, String name, Long user) {
        return clubMapper.selectUserClub(group,name,user);
    }

    @Override
    public List<Club> selectUserClubList(String group, Long user) {
        return clubMapper.selectUserClubList(group,user);
    }

    @Override
    public void insertUserClub(Long user, Long club) {
        clubMapper.insertUserClub(user,club);
    }

    @Override
    public void deleteUserClub(Long user, Long club) {
        clubMapper.deleteUserClub(user,club);
    }

    @Override
    public Page<Club> selectNotUserClub(String group, String name) {
        return clubMapper.selectNotUserClub(group,name);
    }

}
