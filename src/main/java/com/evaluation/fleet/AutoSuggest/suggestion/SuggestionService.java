package com.evaluation.fleet.AutoSuggest.suggestion;

import com.evaluation.fleet.AutoSuggest.config.RedisUtil;
import com.evaluation.fleet.AutoSuggest.domain.CountySuggestion;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class SuggestionService {
    @Autowired
    private RedisUtil redisUtil;

    public List<CountySuggestion> getSuggestions(String query) {
        // todo - add code for state search
        // todo - add code for name and state combined search
        List<CountySuggestion> responseList = new ArrayList<CountySuggestion>();
        Set<byte[]> response = redisUtil.getRedisConnection().zRange(query.getBytes(), 0, -1);
        for (byte[] b:response) {
            responseList.add(new Gson().fromJson(new String(b), CountySuggestion.class));
        }
        return responseList;
    }
}
