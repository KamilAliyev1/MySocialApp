package com.kmsocialapp.like;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kmsocialapp.comment.Comment;
import com.kmsocialapp.myutil.Resource;
import com.kmsocialapp.myutil.customconstroaint.EitherOr;
import com.kmsocialapp.myutil.customconstroaint.EitherorObject;
import com.kmsocialapp.post.Post;
import com.kmsocialapp.userprofile.UserProfile;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.time.LocalDateTime;

@EitherOr(message = "required post or comment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "likes")
public class Like implements EitherorObject, Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private LocalDateTime dateTime;


    public Like() {
    }

    @Transient
    public Long getUserProfileId() {
        return userProfile.getId();
    }

    public Like(UserProfile userProfile, Post post, LocalDateTime dateTime) {
        this.userProfile = userProfile;
        this.post = post;
        this.dateTime = dateTime;
    }

    public Like(UserProfile userProfile, Comment comment, LocalDateTime dateTime) {
        this.userProfile = userProfile;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Transient
    @Override
    public Object getFirstforEitheror() {
        return getComment();
    }
    @Transient
    @Override
    public Object getSecondforEeitheror() {
        return getPost();
    }
}
