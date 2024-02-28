package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    List<ResponseContentList> getPlanList(long id);
    void addPlan(Plan plan);
    void updatePlan(Plan plan);
    void addSpot(PlanSpotConnector planSpotConnector);
}
