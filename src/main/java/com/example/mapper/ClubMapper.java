package com.example.mapper;

import com.example.entity.Club;

public interface ClubMapper {
    int deleteByPrimaryKey(Long clubId);

    int insert(Club record);

    int insertSelective(Club record);

    Club selectByPrimaryKey(Long clubId);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);
}