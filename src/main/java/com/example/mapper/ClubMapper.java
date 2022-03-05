package com.example.mapper;

import com.example.entity.Club;
import com.github.pagehelper.Page;

public interface ClubMapper {
    int deleteByPrimaryKey(Long clubId);

    int insert(Club record);

    int insertSelective(Club record);

    Club selectByPrimaryKey(Long clubId);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);

    Page<Club> selectALL(String group, String name);
}