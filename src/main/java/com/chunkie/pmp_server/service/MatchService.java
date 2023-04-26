package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.entity.Match;
import com.chunkie.pmp_server.entity.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MatchService {


    @Resource
    private PreferenceService preferenceService;

    @Resource
    private ProfileService profileService;

    @Resource
    private AuthService authService;

    public List<Profile> getMatch(){
        List<Profile> matchList = new ArrayList<>();

        return matchList;
    }

    public void like(String matcher, String target){
        Match match = new Match();
        match.setMatchId(UUID.randomUUID().getLeastSignificantBits());
        match.setMatchDate(new Date());
        match.setMatcherId(matcher);
        match.setTargetId(target);
    }

    public void dislike(){

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
