package com.kmsocialapp.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kmsocialapp.comment.Comment;
import com.kmsocialapp.like.Like;
import com.kmsocialapp.myutil.Resource;
import com.kmsocialapp.userprofile.UserProfile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Post implements Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 5,max = 1000)
    private String name;

    @Lob
    @Column(length = 65535)
    private byte[] picture;

    @Transient
    private String Base64pictures;

    @Size(min = 5,max = 1000)
    private String info;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    private Set<Like> likes;


    @JsonIgnore
    @OneToMany(mappedBy = "post",cascade = {CascadeType.REMOVE,CascadeType.DETACH},fetch = FetchType.LAZY)
    private List<Comment> comments;

    private LocalDateTime dateTime;


    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @JsonIgnore
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getBase64pictures() {
        return Base64pictures;
    }

    public void setBase64pictures(String base64pictures) {
        Base64pictures = base64pictures;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
