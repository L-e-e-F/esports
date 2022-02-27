package com.example.mapper;

import com.example.entity.Event;

import java.util.LinkedList;

public interface EventMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    Event selectByName(String name);

    LinkedList<String> selectEventClub(Long id);

    int insertEventMatches (Long event,Long matches);
}