package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:39 AM
 */

import ir.maktab.homeservice.model.Comment;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.CommentDTO;


public interface CommentService extends BaseService<Comment, Long> {

    Comment save(CommentDTO commentDTO);

}
