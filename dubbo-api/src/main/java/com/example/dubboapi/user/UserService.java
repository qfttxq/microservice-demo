package com.example.dubboapi.user;

public interface UserService {
    default String sayUser(String name){
        return null;
    }

    default boolean registry(String name){
        return false;
    }
}
