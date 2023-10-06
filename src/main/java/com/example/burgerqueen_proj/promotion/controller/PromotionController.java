package com.example.burgerqueen_proj.promotion.controller;

import com.example.burgerqueen_proj.promotion.dto.PromotionPatchDto;
import com.example.burgerqueen_proj.promotion.dto.PromotionPostDto;
import com.example.burgerqueen_proj.promotion.dto.PromotionResponseDto;
import com.example.burgerqueen_proj.promotion.entity.Promotion;
import com.example.burgerqueen_proj.promotion.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/promotion")
public class PromotionController {
    private final PromotionService promotionService;

    //READ : 프로모션 전체 조회
    @GetMapping
    public ResponseEntity findAllPromotion(){
        return new ResponseEntity(PromotionResponseDto.promotionResponseDtos(promotionService.getAllPromotion()), HttpStatus.OK);
    }

    //관리자 기능
    //CREAT : 프로모션 생성
    @PostMapping("/adm/new")
    public ResponseEntity createPromotion(@RequestBody PromotionPostDto promotionPostDto){
        Promotion newPromotion = promotionService.createPromotion(promotionPostDto.toEntity());

        return new ResponseEntity(new PromotionResponseDto(newPromotion),HttpStatus.CREATED);
    }


    //UPDATE : 프로모션 정보 변경
    @PatchMapping("/adm/{promotion-id}")
    public ResponseEntity updatePromotion(@PathVariable("promotion-id")long promotionId, @RequestBody PromotionPatchDto promotionPatchDto){
        promotionPatchDto.setPromotionId(promotionId);
        Promotion updatePromotion = promotionService.updatePromotion(promotionPatchDto.toEntity());
        return new ResponseEntity(new PromotionResponseDto(updatePromotion), HttpStatus.OK);
    }

    //DELETE : 프로모션 삭제 -> status 변경
    @DeleteMapping("/adm/{promotion-id}")
    public ResponseEntity stopPromotion(@PathVariable("promotion-id")long promotionId){
        Promotion stopPromotion = promotionService.stopPromotion(promotionId);
        return new ResponseEntity(new PromotionResponseDto(stopPromotion), HttpStatus.OK);
    }

}
