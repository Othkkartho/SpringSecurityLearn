package com.example.springsecuritylearn.aopsecurity;

import org.springframework.stereotype.Service;

@Service
public class AopLiveMethodService {
    public void liveMethodSecured(){
        System.out.println("liveMethodSecured");
    }
}
