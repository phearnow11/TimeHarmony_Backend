package com.example.TimeHarmony.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Favorites {
    private UUID member_id;
    private String watch_id;

    public Favorites() {
    }
}
