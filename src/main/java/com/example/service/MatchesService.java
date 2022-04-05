package com.example.service;

import com.example.entity.Matches;
import com.github.pagehelper.Page;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public interface MatchesService {

    List<Long> GenerateMatches(LinkedList<String> club, Date time);

    Page<Matches> selectALL(String club,String name);

    Page<Matches> selectALLByTime(Date time);

    int insertSelective(Matches entity);

    int updateByPrimaryKeySelective(Matches record);

    int deleteByPrimaryKey(Long id);

    Page<Matches> selectFollow(Long user,String club,String name);

    Page<Matches> selectUser(Long user,String club,String name);
}
