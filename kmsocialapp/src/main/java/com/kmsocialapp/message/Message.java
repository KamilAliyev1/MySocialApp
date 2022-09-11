package com.kmsocialapp.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kmsocialapp.myutil.Resource;
import com.kmsocialapp.myutil.customconstraint.EitherOr;
import com.kmsocialapp.myutil.customconstraint.EitherorObject;
import com.kmsocialapp.post.Post;
import com.kmsocialapp.userprofile.UserProfile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EitherOr(message = "required message or post")
@Entity
public class Message implements Resource, EitherorObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=1,max = 1000)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private Post post;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private UserProfile userProfile;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public Object getFirstforEitheror() {
        return getMessage();
    }

    @Override
    public Object getSecondforEeitheror() {
        return getPost();
    }

    @JsonIgnore
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
