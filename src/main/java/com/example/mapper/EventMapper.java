package com.example.mapper;

import com.example.entity.Club;
import com.example.entity.Event;
import com.github.pagehelper.Page;

import java.util.LinkedList;
import java.util.List;

public interface EventMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    Event selectByName(String name);

    LinkedList<String> selectEventClub(Long id);

    void insertEventMatches (Long event, Long matches);

    Page<Event> selectALL(String group, String name);

    List<Event> init();

    int selectEventClubNum(Long id);

    Page<Event> selectJoin(String group,String name);

    int insertEventClub(Long event,Long club);

    int deleteEventClub(Long event,Long club);

    List<Event> selectEvent();

    void updateEventMatches(Long event, Long matches);

    Long selectEventIdByName(String event);

    Page<Event> selectUserEvent(Long user,String group,String name);

    Page<Event> selectUserJoin(Long user,String group,String name);

    void insertUserEvent(Long user, Long event);

    Page<Club> selectNotUserEvent(String group, String name);

    void deleteUserEvent(Long user, Long event);
}