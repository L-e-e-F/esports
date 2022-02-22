package com.example.mapper;

import com.example.entity.Event;

public interface EventMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);
}