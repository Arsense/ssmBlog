package com.we.weblog.service;


import com.we.weblog.mapping.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentSerivce {


    private CommentMapper commentMapper;

    @Autowired
    public CommentSerivce(CommentMapper commentMapper){
        this.commentMapper = commentMapper;
    }





}
