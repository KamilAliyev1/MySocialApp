package com.kmsocialapp.userprofile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kmsocialapp.chat.Chat;
import com.kmsocialapp.comment.Comment;
import com.kmsocialapp.like.Like;
import com.kmsocialapp.myutil.Resource;
import com.kmsocialapp.post.Post;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class UserProfile implements Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;


    @NotNull
    @OneToOne
    private UserSecurityDetail userSecurityDetail;

    @NotBlank
    @Size(min = 3,max = 30)
    private String username;
    private String profileinfo;

    @JsonIgnore
    @OneToMany(mappedBy = "userProfile",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REMOVE})
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "userProfile",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REMOVE})
    private Set<Like> likes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REMOVE},mappedBy = "userProfile")
    private Set<Post> posts;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REMOVE})
    private List<Chat> chats;

    public UserProfile() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileinfo() {
        return profileinfo;
    }

    public void setProfileinfo(String profileinfo) {
        this.profileinfo = profileinfo;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

   // @JsonIgnore
    public UserSecurityDetail getUserSecurityDetail() {
        return userSecurityDetail;
    }

    //@JsonIgnore
    public void setUserSecurityDetail(UserSecurityDetail userSecurityDetail) {
        this.userSecurityDetail = userSecurityDetail;
    }
}

