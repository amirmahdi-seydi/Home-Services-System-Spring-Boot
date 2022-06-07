package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/11/2022 9:04 PM
 */


import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.Comment;
import ir.maktab.homeservice.service.OrderService;
import ir.maktab.homeservice.service.dto.CommentDTO;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.OrderDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('customer')")
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<SecureOrderDTO> save(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.save(orderDTO), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('customer')")
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<SecureOrderDTO> update(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.save(orderDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('customer') or hasRole('admin')")
    @ResponseBody
    @PutMapping("/choose/of")
    public ResponseEntity<SecureOrderDTO> chooseSpecialistOffer(@RequestBody OfferDTO offerDTO) {
        return new ResponseEntity<>(orderService.chooseSpecialistOffer(offerDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('specialist') or hasRole('admin')")
    @ResponseBody
    @GetMapping("/getOrders/spe")
    public ResponseEntity<List<SecureOrderDTO>> seeCustomerOrder() {
        return new ResponseEntity<>(orderService.seeOrdersByBaseService(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('specialist')")
    @ResponseBody
    @PutMapping("/changeOrderStatus/spe/{offerId}")
    public ResponseEntity<SecureOrderDTO> changeOrderStatusBySpecialist(@PathVariable String offerId) {

        return new ResponseEntity<>(orderService.changeOrderStatusBySpecialist(Long.valueOf(offerId)), HttpStatus.OK);

    }


    @ResponseBody
    @PostMapping("/comment")
    public ResponseEntity<Comment> save(@RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(orderService.addComment(commentDTO), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<List<SecureOrderDTO>> fetchAllOrders() {
        return new ResponseEntity<>(orderService.fetchAllAvailableOrders(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin') or hasRole('customer')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        try {
            orderService.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
