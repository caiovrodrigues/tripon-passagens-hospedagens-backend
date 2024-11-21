package com.tech.tripon.web.user;

import com.tech.tripon.domain.entity.Hotel;
import com.tech.tripon.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/hoteis")
@RestController
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> findAll(){
        return ResponseEntity.ok(hotelService.all());
    }

}
