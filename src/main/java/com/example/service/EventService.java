package com.example.service;

import com.example.entity.Event;

import java.util.LinkedList;

public interface EventService {

    Event selectByName(String name);

    LinkedList<String> selectEventClub(Long id);

    int insertEventMatches (Long event,Long matches);
}
