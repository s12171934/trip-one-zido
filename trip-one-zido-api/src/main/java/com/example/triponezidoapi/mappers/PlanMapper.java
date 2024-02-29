package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.beforeUse.RequestListInfo;
import com.example.triponezidoapi.dto.beforeUse.RequestOwner;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponsePlanDetail;
import com.example.triponezidoapi.dto.beforeUse.PlanSpotConnector;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    List<ResponseContentList> getPlanList(RequestListInfo requestListInfo);
    ResponsePlanDetail getPlan(RequestOwner requestOwner);
    void addPlan(ResponsePlanDetail responsePlanDetail);
    void updatePlan(ResponsePlanDetail responsePlanDetail);
    void addSpot(PlanSpotConnector planSpotConnector);
}
