package com.example.service;

import com.example.entity.Club;
import com.github.pagehelper.Page;

public interface ClubService {

    Page<Club> selectALL(String group,String name);

    Long insertSelective(Club record);

    int updateByPrimaryKeySelective(Club record);

    int deleteByPrimaryKey(Long clubId);
}
