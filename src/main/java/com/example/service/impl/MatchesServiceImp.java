package com.example.service.impl;

import com.example.entity.Matches;
import com.example.mapper.MatchesMapper;
import com.example.service.MatchesService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MatchesServiceImp implements MatchesService {

    @Resource
    private MatchesMapper matchesMapper;

    @Override
    public List<Long> GenerateMatches(LinkedList<String> club, Date time) {
        Matches matches = new Matches();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        List<Long> list = new ArrayList<>();
        if(club.size() % 2 != 0){
            club.add(null);
        }
        for (int i = 0; i < club.size() - 1; i++)  {
            if(i > 0){
                calendar.add(Calendar.DATE,1);
            }
            for (int j = 0; j < club.size() / 2; j++)  {
                if(club.get(j) !=null && club.get(club.size() - 1 - j)!=null){
                    matches.setTime(calendar.getTime());
                    matches.setHomeTeam(club.get(j));
                    matches.setVisitingTeam(club.get(club.size() - 1 - j));
                    matches.setResult("未出结果");
                    matchesMapper.insertSelective(matches);
                    list.add(matches.getId());
                    System.out.println(list);
                }
            }
            String temp = club.pollLast();  //获取最后一个元素
            club.add(1, temp);//将最后一个元素放在List的第二个位置
        }
        return list;
    }

    @Override
    public Page<Matches> selectALL(String club, String name) {
        return matchesMapper.selectALL(club,name);
    }

    @Override
    public Page<Matches> selectALLByTime(Date time) {
        return matchesMapper.selectALLByTime(time);
    }

    @Override
    public int insertSelective(Matches entity) {
        return matchesMapper.insertSelective(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(Matches record) {
        return matchesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return matchesMapper.deleteByPrimaryKey(id);
    }
}
