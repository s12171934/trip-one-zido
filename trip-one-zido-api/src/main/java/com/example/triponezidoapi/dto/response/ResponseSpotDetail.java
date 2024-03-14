package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseSpotDetail {
    long id;
    String category;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String address;
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
    byte[] profile;
    List<ResponsePhoto> photos;
    List<ResponseMember> members;
    List<ResponseComment> comments;
    boolean isMine;

    public ResponseSpotDetail(long id, LocalDateTime startDate, LocalDateTime endDate, String locCategory, String review, int grade, int viewCount,
                              int goodCount, int myGood, int bookmarkCount, int myBookmark, String title, boolean isPublic, LocalDateTime createdAt,
                              byte[] profile, List<ResponsePhoto> photos, List<ResponseMember> members, List<ResponseComment> comments, boolean isMine){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locCategory = locCategory;
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
        this.profile = profile;
        this.photos = photos;
        this.members = members;
        this.comments = comments;
        this.isMine = isMine;
    }
}
