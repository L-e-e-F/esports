package com.example.controller;

import com.example.common.Result;
import com.example.entity.Event;
import com.example.enums.EventState;
import com.example.enums.ResultCode;
import com.example.service.EventService;
import com.example.service.MatchesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

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
    public Result<?> AddMatches(@RequestParam String EventName){
        Event event = eventService.selectByName(EventName);
        LinkedList<String> club = eventService.selectEventClub(event.getId());
        List<Long> list = matchesService.GenerateMatches(club,event.getTime());
        Long eventId = event.getId();
        for(Long matches : list){
            eventService.insertEventMatches(eventId,matches);
        }
        event.setStatus(EventState.EVENT_START.getState());
        System.out.println(event);
        eventService.updateByPrimaryKeySelective(event);
        return Result.SuccessResult();
    }

    @GetMapping("/all")
    public Result<?> selectAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "Major") String group,
                               @RequestParam(required = false, defaultValue = "") String name){
        eventService.init();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page =  new PageInfo(eventService.selectALL(group,name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_EVENT);
        return Result.SuccessResult(page);
    }

    @GetMapping("/join")
    public Result<?> join(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                          @RequestParam(required = false, defaultValue = "Major") String group,
                          @RequestParam(required = false, defaultValue = "") String name){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page =  new PageInfo(eventService.selectJoin(group,name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_EVENT);
        return Result.SuccessResult(page);
    }

    @GetMapping("/name")
    public Result<?> getName(){
        List<Event> event = eventService.selectEvent();
        return Result.SuccessResult(event);
    }

    @PostMapping("/set")
    public Result<?> Add(@RequestBody Event entity){
        System.out.println(entity);
        int row = eventService.insertSelective(entity);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PutMapping("/set")
    public Result<?> upload(@RequestBody Event entity){
        System.out.println(entity);
        int num = eventService.selectEventClubNum(entity.getId());
        int row = eventService.updateByPrimaryKeySelective(entity,num);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_UPLOAD);
        }
    }

    @PutMapping("/eventclub")
    public Result<?> inserteventclub(@RequestParam Long eventid,@RequestParam Long clubid ){
        int row = eventService.insertEventClub(eventid, clubid);
        if(row != 0){
            int num = eventService.selectEventClubNum(eventid);
            Event e= eventService.selectByPrimaryKey(eventid);
            eventService.updateByPrimaryKeySelective(e,num);
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @DeleteMapping("/set/{id}")
    public Result<?> delete(@PathVariable Long id){
        System.out.println(id);
        int row = eventService.deleteByPrimaryKey(id);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_DELETE);
        }
    }

    @DeleteMapping("/eventclub")
    public Result<?> deleteEventClub(@RequestParam Long eventid,@RequestParam Long clubid ){
        int row = eventService.deleteEventClub(eventid, clubid);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_DELETE);
        }
    }
}
