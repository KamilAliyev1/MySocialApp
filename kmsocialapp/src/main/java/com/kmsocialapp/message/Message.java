package com.kmsocialapp.message;

import com.kmsocialapp.myutil.Resource;
import com.kmsocialapp.myutil.customconstroaint.EitherOr;
import com.kmsocialapp.myutil.customconstroaint.EitherorObject;
import com.kmsocialapp.post.Post;
import javax.persistence.*;
import javax.validation.constraints.Size;

@EitherOr(message = "required message or post")
@Entity
public class Message implements Resource, EitherorObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=1,max = 1000)
    String message;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    Post post;

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
}
