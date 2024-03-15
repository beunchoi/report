package com.sparta.newsfeed.dto;

import com.sparta.newsfeed.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    public Long commentId;
    public Long userId;
    public String contents;

    public CommentResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.userId = comment.getUser().getUserId();
        this.contents = comment.getContents();
    }
}
