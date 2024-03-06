package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    //insert
    void addPlan(RequestPlan requestPlan);
    void addSpot(RequestPlanSpot requestPlanSpot);

    //select
    ResponsePlanDetail getPlan(RequestSessionTarget requestSessionTarget);

    List<ResponseSpotPlan> getSpot(long contentId);
    List<ResponseContentList> getPlanList(RequestSessionTarget requestSessionTarget);

    //update
    void updatePlan(RequestPlan requestPlan);
}
