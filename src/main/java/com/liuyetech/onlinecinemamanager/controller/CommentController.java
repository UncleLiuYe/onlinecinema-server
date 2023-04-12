package com.liuyetech.onlinecinemamanager.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.Comment;
import com.liuyetech.onlinecinemamanager.entity.CommentVo;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("{nid}")
    public R<List<Comment>> getCommentByNewsId(@PathVariable("nid") Integer nid) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("comment_news_id", nid);
        return R.success(commentService.list(commentQueryWrapper));
    }

    @PostMapping("comment")
    public R<String> comment(@RequestBody CommentVo commentVo) {
        Comment comment = new Comment();
        comment.setCommentCreateTime(LocalDateTime.now());
        comment.setCommentNewsId(commentVo.getNid());
        comment.setCommentContent(commentVo.getContent());
        comment.setCommentUserId((Integer) StpUtil.getLoginId());
        if (commentService.save(comment)) {
            return R.success("评论成功");
        } else {
            return R.fail("评论失败");
        }
    }
}
