package com.example.service.impl;

import com.example.entity.Event;
import com.example.enums.EventState;
import com.example.mapper.EventMapper;
import com.example.service.EventService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class EventServiceImp implements EventService {

    @Resource
    private EventMapper eventMapper;


    @Override
    public Event selectByName(String name) {
        return eventMapper.selectByName(name);
    }

    @Override
    public LinkedList<String> selectEventClub(Long id) {
        return eventMapper.selectEventClub(id);
    }

    @Override
    public void insertEventMatches(Long event, Long matches) {
        eventMapper.insertEventMatches(event, matches);
    }

    @Override
    public int updateByPrimaryKeySelective(Event record) {
        return eventMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Event selectByPrimaryKey(Long id) {
        return eventMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Event record, int num) {
        if(num > record.getClubum()){
            record.setStatus(EventState.EVENT_FAIL.getState());
        }else if(num < record.getClubum()){
            record.setStatus(EventState.EVENT_JOIN.getState());
        }else if(num == record.getClubum()){
            record.setStatus(EventState.EVENT_WAIT.getState());
        }
        return eventMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Page<Event> selectALL(String group, String name) {
        return eventMapper.selectALL(group,name);
    }

    @Override
    public int insertSelective(Event record) {
        Date date = new Date();
        if(date.before(record.getTime())){
            record.setStatus(EventState.EVENT_JOIN.getState());
        }else{
            record.setStatus(EventState.EVENT_FAIL.getState());
        }
        return eventMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return eventMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int selectEventClubNum(Long id) {
        return eventMapper.selectEventClubNum(id);
    }

    @Override
    public void init() {
        List<Event> events = eventMapper.init();
        Date date = new Date();
        for(Event e : events){
            int num = eventMapper.selectEventClubNum(e.getId());
            if(num == e.getClubum()){
                e.setStatus(EventState.EVENT_WAIT.getState());
                eventMapper.updateByPrimaryKeySelective(e);
            }else if(num > e.getClubum()){
                e.setStatus(EventState.EVENT_FAIL.getState());
                eventMapper.updateByPrimaryKeySelective(e);
            }else if(num < e.getClubum()){
                e.setStatus(EventState.EVENT_JOIN.getState());
                eventMapper.updateByPrimaryKeySelective(e);
            }else if(date.after(e.getTime())){
                e.setStatus(EventState.EVENT_FAIL.getState());
                eventMapper.updateByPrimaryKeySelective(e);
            }
        }
    }

    @Override
    public Page<Event> selectJoin(String group ,String name) {
        return eventMapper.selectJoin(group,name);
    }

    @Override
    public int insertEventClub(Long event, Long club) {
        return eventMapper.insertEventClub(event,club);
    }

    @Override
    public int deleteEventClub(Long event, Long club) {
        return eventMapper.deleteEventClub(event, club);
    }

    @Override
    public List<Event> selectEvent() {
        return eventMapper.selectEvent();
    }

    @Override
    public void updateEventMatches(Long event, Long matches) {
        eventMapper.updateEventMatches(event,matches);
    }

    @Override
    public Long selectEventIdByName(String event) {
        return eventMapper.selectEventIdByName(event);
    }


}
