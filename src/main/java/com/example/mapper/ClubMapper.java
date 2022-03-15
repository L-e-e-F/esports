package com.example.mapper;

import com.example.entity.Club;
import com.github.pagehelper.Page;

import java.util.List;

public interface ClubMapper {
    int deleteByPrimaryKey(Long clubId);

    int insert(Club record);

    int insertSelective(Club record);

    Club selectByPrimaryKey(Long clubId);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);

    Page<Club> selectALL(String group, String name);

    List<Club> selectEventClub (Long id);

    List<Club> ALL(String group);

    void updateClubPoints (String club);
}