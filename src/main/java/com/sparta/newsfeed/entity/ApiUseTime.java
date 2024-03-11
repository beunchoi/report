package com.sparta.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "apiusetime")
public class ApiUseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long totalTime;

    @ManyToOne
    @JoinColumn
    private User user;

    public ApiUseTime(User loginUser, long runTime) {
        this.totalTime = runTime;
        this.user = loginUser;
    }

    public void addUseTime(long runTime) {
        this.totalTime = runTime;
    }
}
