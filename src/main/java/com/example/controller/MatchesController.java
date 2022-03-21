package com.example.controller;

import com.example.common.Result;
import com.example.entity.Matches;
import com.example.entity.User;
import com.example.enums.MatchesResult;
import com.example.enums.ResultCode;
import com.example.service.ClubService;
import com.example.service.EventService;
import com.example.service.MatchesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchesController {

    @Resource
    private MatchesService matchesService;

    @Resource
    private EventService eventService;

    @Resource
    private ClubService clubService;

    @GetMapping("/all")
    public Result<?> selectALL(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "") String club,
                               @RequestParam(required = false, defaultValue = "") String name){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(matchesService.selectALL(club,name));
        if(page.getTotal() == 0)
            return Result.ErrorResult(ResultCode.ERROR_MATCHES);
        return Result.SuccessResult(page);
    }

    @GetMapping("/follow")
    public Result<?> follow(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "") String club,
                               @RequestParam(required = false, defaultValue = "") String name){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(matchesService.selectFollow(user.getUserId(),club,name));
        if(page.getTotal() == 0)
            return Result.ErrorResult(ResultCode.ERROR_MATCHES);
        return Result.SuccessResult(page);
    }

    @GetMapping("/time")
    public Result<?> selectALLByTime(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Date time = new Date();

        PageInfo page = new PageInfo(matchesService.selectALLByTime(time));
        if(page.getTotal() == 0)
            return Result.ErrorResult(ResultCode.EMPTY_MATCHES);
        return Result.SuccessResult(page);
    }

    @PostMapping("/set")
    public Result<?> Add(@RequestBody Matches entity){
        System.out.println(entity);
        int row = matchesService.insertSelective(entity);
        if(row != 0){
            Long MatchesId = entity.getId();
//            Long EventId = Long.parseLong(entity.getName());
            Long EventId = eventService.selectEventIdByName(entity.getName());
            eventService.insertEventMatches(EventId,MatchesId);
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PutMapping("/set")
    public Result<?> upload(@RequestBody Matches entity){
        System.out.println(entity);
        int row = matchesService.updateByPrimaryKeySelective(entity);
        if(Objects.equals(entity.getResult(), MatchesResult.HOME_VICTORY.getState())){
            String club = entity.getHomeTeam();
            clubService.updateClubPoints(club);
        }else if(Objects.equals(entity.getResult(), MatchesResult.VISITING_VICTORY.getState())){
            String club = entity.getVisitingTeam();
            clubService.updateClubPoints(club);
        }
        if(row != 0){
            Long MatchesId = entity.getId();
            Long EventId = eventService.selectEventIdByName(entity.getName());
            eventService.updateEventMatches(EventId,MatchesId);
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @DeleteMapping("/set/{id}")
    public Result<?> delete(@PathVariable Long id){
        System.out.println(id);
        int row = matchesService.deleteByPrimaryKey(id);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_DELETE);
        }
    }

}
