package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:18 PM
 */

import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.Comment;
import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.repository.CommentRepository;
import ir.maktab.homeservice.service.CommentService;
import ir.maktab.homeservice.service.OrderService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.CommentDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, Long, CommentRepository>
        implements CommentService {

    private final OrderService orderService;

    public CommentServiceImpl(CommentRepository repository, OrderService orderService) {
        super(repository);
        this.orderService = orderService;
    }

    @Override
    public Comment save(CommentDTO commentDTO) {

        if (orderService.findById(commentDTO.getOrderId()).isEmpty()) {
            throw new NotFoundException("There is no order with this id!");
        }

        Order order = orderService.findById(commentDTO.getOrderId()).get();
        Comment comment = new Comment();
        comment.setCommentBody(comment.getCommentBody());
        comment.setDateAndTimeOfRegisterComment(Date.from(Instant.now()));
        comment = repository.save(comment);

        Set<Comment> comments;
        if (order.getComments().isEmpty()) {
            comments = new HashSet<>();

        } else {
            comments = order.getComments();
        }
        comments.add(comment);
        order.setComments(comments);
        orderService.save(order);

        return repository.save(comment);
    }
}
