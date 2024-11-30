package com.tech.tripon.web.user;

import com.tech.tripon.domain.entity.Localidade;
import com.tech.tripon.service.LocalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/localidades")
@RestController
public class LocalidadeController {

    private final LocalidadeService locService;

    @GetMapping
    public ResponseEntity<List<Localidade>> getAll(){
        return ResponseEntity.ok(locService.getAll());
    }

}
