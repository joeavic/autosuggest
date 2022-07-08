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

    private static Gson GSON = new Gson();

    private static final String CACHE_STATE_KEY_PREFIX = "STATE_";
    private static final String CACHE_CITY_KEY_PREFIX = "CITY_";

    public List<CountySuggestion> getSuggestions(String query) {
        if(query.trim().length() > 50){
            return new ArrayList<CountySuggestion>();
        }
        String searchQuery = query.trim().toLowerCase();
        List<CountySuggestion> resultList = new ArrayList<CountySuggestion>();

        // search for state name
        if(searchQuery.length() <= 2){
            List<CountySuggestion> responseList = searchForStateName(searchQuery);
            if(!responseList.isEmpty()){
                return responseList;
            }
        }

        // search for city name
        if(!searchQuery.contains(",")) {
            List<CountySuggestion> responseList = searchForCityName(searchQuery);
            if(!responseList.isEmpty()){
                return responseList;
            }
        }

        // search for city and state combined
        if(searchQuery.contains(",") && searchQuery.length() > 2){
            String[] splitString = searchQuery.split(",");
            String firstSplit = splitString[0].trim();
            String secondSplit = splitString[1].trim();

            // considering first string as city name
            List<CountySuggestion> responseList = searchForCityName(firstSplit);
            for(CountySuggestion c : responseList){
                if(c.getState().equalsIgnoreCase(secondSplit)){
                    List<CountySuggestion> matchedCountySuggestionList = new ArrayList<CountySuggestion>();
                    matchedCountySuggestionList.add(c);
                    return matchedCountySuggestionList;
                }
            }
            return responseList;
        }
        else{
            resultList = searchForCityName(searchQuery);
        }
        return resultList;
    }

    private List<CountySuggestion> searchForCityName(String query) {
        String stateSearchKey = CACHE_CITY_KEY_PREFIX + query;
        List<CountySuggestion> responseFromCache = getDataFromCache(stateSearchKey);
        return responseFromCache;
    }
    private List<CountySuggestion> searchForStateName(String query) {
        String stateSearchKey = CACHE_STATE_KEY_PREFIX + query;
        List<CountySuggestion> responseFromCache = getDataFromCache(stateSearchKey);
        return responseFromCache;
    }

    public List<CountySuggestion> getDataFromCache(String cacheKey){
        List<CountySuggestion> cacheResponseList = new ArrayList<CountySuggestion>();
        Set<byte[]> cacheStateKeyResponse = redisUtil.getRedisConnection().zRange(cacheKey.getBytes(), 0, -1);
        for (byte[] b:cacheStateKeyResponse) {
            cacheResponseList.add(GSON.fromJson(new String(b), CountySuggestion.class));
        }
       return cacheResponseList;
    }
}
