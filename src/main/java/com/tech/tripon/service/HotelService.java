package com.tech.tripon.service;

import com.tech.tripon.domain.entity.Hotel;
import com.tech.tripon.domain.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> all(){
        return hotelRepository.findAll();
    }

}
