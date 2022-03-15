package com.example.service;

import com.example.entity.Event;
import com.github.pagehelper.Page;

import java.util.LinkedList;
import java.util.List;

public interface EventService {

    Event selectByName(String name);

    LinkedList<String> selectEventClub(Long id);

    void insertEventMatches (Long event, Long matches);

    int updateByPrimaryKeySelective(Event record);

    Event selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Event record, int num);

    Page<Event> selectALL(String group, String name);

    int insertSelective(Event record);

    int deleteByPrimaryKey(Long id);

    int selectEventClubNum(Long id);

    void init();

    Page<Event> selectJoin(String group,String name);

    int insertEventClub(Long event,Long club);

    int deleteEventClub(Long event,Long club);

    List<Event> selectEvent();

    void updateEventMatches(Long event, Long matches);

    Long selectEventIdByName(String event);
}
