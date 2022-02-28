package com.example.service;

import com.example.entity.Matches;
import com.github.pagehelper.Page;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public interface MatchesService {

    List<Long> GenerateMatches(LinkedList<String> club, Date time);

    Page<Matches> selectALL();

    Page<Matches> selectALLByTime(Date time);
}
