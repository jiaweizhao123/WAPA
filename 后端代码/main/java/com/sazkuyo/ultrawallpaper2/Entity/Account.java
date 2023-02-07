package com.sazkuyo.ultrawallpaper2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int uid;
    private String uname;
    private String pwd;
    private String email;
}
