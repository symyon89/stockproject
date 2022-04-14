package com.stockproject.service;

import com.stockproject.dto.ComenziDto;
import com.stockproject.dto.ComenziResponseDto;
import com.stockproject.model.Comenzi;
import com.stockproject.model.Produse;
import com.stockproject.model.StatusComanda;
import com.stockproject.repository.ComenziRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ComenziService {

    private final ComenziRepository comenziRepository;
    private final ProduseService produseService;


    public ComenziResponseDto processOrder(ComenziDto comenziDto) {
        Optional<Produse> produse = produseService.updateStock(comenziDto.getIdProdus(),comenziDto.getCantitate());
        Comenzi comenzi =  this.buildComenzi(produse,comenziDto);
        comenzi = comenziRepository.save(comenzi);
        return ComenziResponseDto.builder()
                .idComanda(comenzi.getId())
                .statusComanda(comenzi.getStatusComanda())
                .build();
    }

    private Comenzi buildComenzi(Optional<Produse> produse, ComenziDto comenziDto) {
        return produse.isPresent() ?
             Comenzi.builder()
                    .numeClient(comenziDto.getNumeClient())
                    .produse(produse.get())
                    .statusComanda(StatusComanda.ACCEPTAT).build()
             : Comenzi.builder()
                    .numeClient(comenziDto.getNumeClient())
                    .produse(null)
                    .statusComanda(StatusComanda.STOC_INSUFICIENT).build();
        }



}
