package com.example.service.impl;

import com.example.entity.Event;
import com.example.mapper.EventMapper;
import com.example.service.EventService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;

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
    public int insertEventMatches(Long event, Long matches) {
        return eventMapper.insertEventMatches(event,matches);
    }
}
