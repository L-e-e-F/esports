package com.example.controller;

import com.example.common.Result;
import com.example.entity.Event;
import com.example.entity.User;
import com.example.enums.EventState;
import com.example.enums.ResultCode;
import com.example.service.EventService;
import com.example.service.MatchesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
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
    public Result<?> AddMatches(@RequestParam String EventName) {
        Event event = eventService.selectByName(EventName);
        LinkedList<String> club = eventService.selectEventClub(event.getId());
        List<Long> list = matchesService.GenerateMatches(club, event.getTime());
        Long eventId = event.getId();
        for (Long matches : list) {
            eventService.insertEventMatches(eventId, matches);
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
                               @RequestParam(required = false, defaultValue = "") String name) {
        eventService.init();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(eventService.selectALL(group, name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_EVENT);
        return Result.SuccessResult(page);
    }

    @GetMapping("/join")
    public Result<?> join(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                          @RequestParam(required = false, defaultValue = "Major") String group,
                          @RequestParam(required = false, defaultValue = "") String name) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(eventService.selectJoin(group, name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_EVENT);
        return Result.SuccessResult(page);
    }

    @GetMapping("/userJoin")
    public Result<?> userJoin(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                          @RequestParam(required = false, defaultValue = "Major") String group,
                          @RequestParam(required = false, defaultValue = "") String name) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(eventService.selectUserJoin(user.getUserId(),group, name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_EVENT);
        return Result.SuccessResult(page);
    }

    @GetMapping("/name")
    public Result<?> getName() {
        List<Event> event = eventService.selectEvent();
        return Result.SuccessResult(event);
    }

    @GetMapping("/UserName")
    public Result<?> getUserName() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Event> event = eventService.selectUserEventName(user.getUserId());
        return Result.SuccessResult(event);
    }

    @GetMapping("/NotUser")
    public Result<?> NotUser(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                             @RequestParam(required = false, defaultValue = "Major") String group,
                             @RequestParam(required = false, defaultValue = "") String name) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(eventService.selectNotUserEvent(group, name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_CLUB);
        return Result.SuccessResult(page);
    }

    @GetMapping("/user")
    public Result<?> selectUserClub(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                                    @RequestParam(required = false, defaultValue = "Major") String group,
                                    @RequestParam(required = false, defaultValue = "") String name) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(eventService.selectUserEvent(user.getUserId(), group, name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_CLUB);
        return Result.SuccessResult(page);
    }

    @PostMapping("/set")
    public Result<?> Add(@RequestBody Event entity) {
        System.out.println(entity);
        int row = eventService.insertSelective(entity);
        if (row != 0) {
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PostMapping("/userSet")
    public Result<?> AddUserEvent(@RequestBody Event entity) {
        System.out.println(entity);
        int row = eventService.insertSelective(entity);
        if (row != 0) {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            Long id = entity.getId();
            eventService.insertUserEvent(user.getUserId(), id);
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PostMapping("/contract")
    public Result<?> acquisition(@RequestParam Long event) {
        System.out.println(event);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        try {
            eventService.insertUserEvent(user.getUserId(), event);
        } catch (Exception e) {
            return Result.ErrorResult(ResultCode.NOT_CONTRACT);
        }
        return Result.SuccessResult();
    }

    @PutMapping("/set")
    public Result<?> upload(@RequestBody Event entity) {
        System.out.println(entity);
        int num = eventService.selectEventClubNum(entity.getId());
        int row = eventService.updateByPrimaryKeySelective(entity, num);
        if (row != 0) {
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_UPLOAD);
        }
    }

    @PutMapping("/eventClub")
    public Result<?> insertEventClub(@RequestParam Long eventId, @RequestParam Long clubId) {
        int row = eventService.insertEventClub(eventId, clubId);
        if (row != 0) {
            int num = eventService.selectEventClubNum(eventId);
            Event e = eventService.selectByPrimaryKey(eventId);
            eventService.updateByPrimaryKeySelective(e, num);
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @DeleteMapping("/userSet/{id}")
    public Result<?> deleteUserClub(@PathVariable Long id) {
        System.out.println(id);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        try {
            eventService.deleteUserEvent(user.getUserId(), id);
        } catch (Exception e) {
            return Result.ErrorResult(ResultCode.NOT_SELL);
        }
        return Result.SuccessResult();
    }

    @DeleteMapping("/set/{id}")
    public Result<?> delete(@PathVariable Long id) {
        System.out.println(id);
        int row = eventService.deleteByPrimaryKey(id);
        if (row != 0) {
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_DELETE);
        }
    }

    @DeleteMapping("/eventClub")
    public Result<?> deleteEventClub(@RequestParam Long eventId, @RequestParam Long clubId) {
        int row = eventService.deleteEventClub(eventId, clubId);
        if (row != 0) {
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_DELETE);
        }
    }
}
