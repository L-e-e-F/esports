package com.example.controller;

import com.example.common.Result;
import com.example.entity.Event;
import com.example.service.EventService;
import com.example.service.MatchesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Resource
    private EventService eventService;
    @Resource
    private MatchesService matchesService;

    @PostMapping("/AddMatches")
    public Result<?> AddMatches(@RequestBody String EventName){
//    public Result<?> AddMatches(){
        Event event = eventService.selectByName(EventName);
//        Event event = eventService.selectByName("DPC");
        LinkedList<String> club = eventService.selectEventClub(event.getId());
        List<Long> list = matchesService.GenerateMatches(club,event.getTime());
        Long eventId = event.getId();
        for(Long matches : list){
            eventService.insertEventMatches(eventId,matches);
        }
        return Result.SuccessResult();
    }
}
