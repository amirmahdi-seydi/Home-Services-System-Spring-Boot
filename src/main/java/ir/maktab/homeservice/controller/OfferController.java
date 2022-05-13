package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/11/2022 10:31 PM
 */


import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.Offer;
import ir.maktab.homeservice.service.OfferService;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOfferDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PreAuthorize("hasRole('specialist')")
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<SecureOfferDTO> save(@RequestBody OfferDTO offerDTO) {
        return new ResponseEntity<>(offerService.save(offerDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('specialist')")
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<SecureOfferDTO> update(@RequestBody OfferDTO offerDTO) {
        return new ResponseEntity<>(offerService.save(offerDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<List<SecureOfferDTO>> fetchAllOffer() {
        return new ResponseEntity<>(offerService.fetchAllOffer(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('customer')")
    @ResponseBody
    @GetMapping("/getAllOrderOffer/{orderId}")
    public ResponseEntity<List<Offer>> fetchAllOrderOffers(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(offerService.fetchAllOrderOffersAscBidPrice(orderId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin') or hasRole('specialist')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        try {
            offerService.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        }
        catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
