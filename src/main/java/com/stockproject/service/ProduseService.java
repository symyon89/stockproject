package com.stockproject.service;

import com.stockproject.dto.ProduseListDto;
import com.stockproject.model.Produse;
import com.stockproject.repository.ProduseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProduseService {

    private final ProduseRepository produseRepository;
    private final Optional<ProduseListDto> produseListDto;

    @PostConstruct
    private void getlist(){
        if (produseListDto.isPresent())
            produseListDto.get().getStoc().forEach(System.out::println);
        else
            System.out.println("not prezent");
    }


    public Optional<Produse> updateStock(Integer id,Integer quantity){

        Optional<Produse> produse = this.findById(id);
        if (produse.isPresent() && this.checkIfIsStock(produse.get().getStoc(),quantity)){
            int q = produse.get().getStoc() - quantity;
            log.info("Update stock with value " + (produse.get().getStoc()- quantity));
            produse.get().setStoc(q);
            return produse;
        }else{
            return Optional.empty();
        }
    }

    private Optional<Produse> findById(Integer id){
        return produseRepository.findById(id);
    }


    private boolean checkIfIsStock(Integer request, Integer stock) {
        return stock <= request;
    }
}
