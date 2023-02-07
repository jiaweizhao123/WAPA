package com.sazkuyo.ultrawallpaper2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRecord {
    private int sid;
    private String keyword;
    private int num;
}
