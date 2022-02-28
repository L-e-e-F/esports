package com.example.mapper;

import com.example.entity.Matches;
import com.github.pagehelper.Page;

import java.util.Date;


public interface MatchesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Matches record);

    int insertSelective(Matches record);

    Matches selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Matches record);

    int updateByPrimaryKey(Matches record);

    Page<Matches> selectALL();

    Page<Matches> selectALLByTime(Date time);
}