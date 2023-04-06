package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.entity.Match;
import com.chunkie.pmp_server.entity.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MatchService {

    public List<Match> getMatch(){
        List<Match> matchList = new ArrayList<>();

        return matchList;
    }

    private double getDistance(Profile matcher,Profile target){
        double radius = 6371;
        double latDistance = Math.toRadians(matcher.getProfileLatitude() - target.getProfileLatitude());
        double lonDistance = Math.toRadians(matcher.getProfileLongitude() - target.getProfileLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(matcher.getProfileLatitude())) * Math.cos(Math.toRadians(target.getProfileLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radius * c;

        return distance;
    }

}
