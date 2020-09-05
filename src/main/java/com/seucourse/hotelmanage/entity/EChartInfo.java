package com.seucourse.hotelmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EChartInfo {
    List<String> days;
    List<String> rooms;
    List<List<Integer>> data;
    Integer max;
}
