package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponsePlanDetail {
    long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String status;
    String review;
    int grade;
    int viewCount;
    int goodCount;
    int myGood;
    int bookmarkCount;
    int myBookmark;
    String title;
    boolean isPublic;
    LocalDateTime createdAt;
    List<ResponseSpotPlan> responseSpotPlans;
    List<ResponseMember> members;
    List<ResponseComment> comments;
    boolean isMine;

    public ResponsePlanDetail(long id, LocalDateTime startDate, LocalDateTime endDate, String locCategory, String status, String review, int grade, int viewCount,
                              int goodCount, int myGood, int bookmarkCount, int myBookmark, String title, boolean isPublic, LocalDateTime createdAt,
                              List<ResponseSpotPlan> responseSpotPlans, List<ResponseMember> members, List<ResponseComment> comments, boolean isMine){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locCategory = locCategory;
        this.status = status;
        this.review = review;
        this.grade = grade;
        this.viewCount = viewCount;
        this.goodCount = goodCount;
        this.myGood = myGood;
        this.bookmarkCount = bookmarkCount;
        this.myBookmark = myBookmark;
        this.title = title;
        this.isPublic = isPublic;
        this.createdAt =createdAt;
        this.responseSpotPlans = responseSpotPlans;
        this.members = members;
        this.comments = comments;
        this.isMine = isMine;
    }
}
