package com.pablo.spirngboot.springbootcrudjpa.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimbleGrantedAuthorityJsonCreator {

    @JsonCreator
    public SimbleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){

    }
}
