package com.example.service;

import com.example.entity.Club;
import com.github.pagehelper.Page;

import java.util.List;

public interface ClubService {

    Page<Club> selectALL(String group,String name);

    int insertSelective(Club record);

    int updateByPrimaryKeySelective(Club record);

    int deleteByPrimaryKey(Long clubId);

    List<Club> selectEventClub (Long id);

    List<Club> ALL(String group);

    void updateClubPoints (String club);

    Page<Club> follow(Long user,String club);
}
