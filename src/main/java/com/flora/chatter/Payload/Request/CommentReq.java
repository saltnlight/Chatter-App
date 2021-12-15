package com.flora.chatter.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReq {
    private Long commentID;
    private Long postID;
    private String body;
}
