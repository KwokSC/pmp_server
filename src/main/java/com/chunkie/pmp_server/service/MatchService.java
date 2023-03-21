package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.entity.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MatchService {

    public List<Profile> getMatch(){
        return new ArrayList<>();
    }
}
