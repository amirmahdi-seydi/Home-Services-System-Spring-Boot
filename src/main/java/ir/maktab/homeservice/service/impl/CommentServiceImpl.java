package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:18 PM
 */

import ir.maktab.homeservice.model.Comment;
import ir.maktab.homeservice.repository.CommentRepository;
import ir.maktab.homeservice.service.CommentService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class CommentServiceImpl extends BaseServiceImpl<Comment, Long, CommentRepository>
        implements CommentService {

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }
}
