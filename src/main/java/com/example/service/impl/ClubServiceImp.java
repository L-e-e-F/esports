package com.example.service.impl;

import com.example.entity.Club;
import com.example.mapper.ClubMapper;
import com.example.service.ClubService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClubServiceImp implements ClubService {

    @Resource
    private ClubMapper clubMapper;

    @Override
    public Page<Club> selectALL(String group, String name) {
            return clubMapper.selectALL(group,name);
    }

    @Override
    public Long insertSelective(Club record) {
        clubMapper.insertSelective(record);
        return record.getClubId();
    }

    @Override
    public int updateByPrimaryKeySelective(Club record) {
        return clubMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long clubId) {
        return clubMapper.deleteByPrimaryKey(clubId);
    }

}
