package com.stockproject.service;

import com.stockproject.dto.ComenziDto;
import com.stockproject.dto.ComenziResponseDto;
import com.stockproject.model.Comenzi;
import com.stockproject.model.Produse;
import com.stockproject.model.StatusComanda;
import com.stockproject.repository.ComenziRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComenziServiceTest {

    private ComenziDto comenziDto;
    @Mock
    private ComenziRepository comenziRepository;
    @Mock
    private ProduseService produseService;

    @InjectMocks
    private ComenziService comenziService;

    @BeforeEach
    public void beforeEach() {
        comenziDto = new ComenziDto();
        comenziDto.setIdProdus(1);
        comenziDto.setCantitate(2);
        comenziDto.setNumeClient("Florin");
    }

    @Test
    void processOrderReturnsComenziResponseDto() {
        Optional<Produse> produse = Optional.of(new Produse(1, "Flori", 3));
        Comenzi comenzi = Comenzi.builder()
                .id(1)
                .produse(produse.get())
                .statusComanda(StatusComanda.ACCEPTAT)
                .numeClient("Florin")
                .build();

        ComenziResponseDto expectedResult = ComenziResponseDto.builder()
                .idComanda(1)
                .statusComanda(StatusComanda.ACCEPTAT)
                .build();

        when(produseService.updateStock(any(), any())).thenReturn(produse);
        when(comenziRepository.save(any())).thenReturn(comenzi);

        ComenziResponseDto result = comenziService.processOrder(comenziDto);

        assertEquals(expectedResult.getIdComanda(),result.getIdComanda());
        assertEquals(expectedResult.getStatusComanda(),result.getStatusComanda());
    }


    @Test
    void buildComenziReturnsAcceptedComenzi() {
        Optional<Produse> produse = Optional.of(Produse.builder().id(1).numeProdus("Flori").stoc(3).build());
        Comenzi comenzi = Comenzi.builder()
                .id(1)
                .produse(produse.get())
                .statusComanda(StatusComanda.ACCEPTAT)
                .numeClient("Florin")
                .build();

        Comenzi expectedResult = Comenzi.builder()
                .numeClient("Florin")
                .produse(Produse.builder().id(1).numeProdus("Flori").stoc(3).build())
                .statusComanda(StatusComanda.ACCEPTAT)
                .build();

        Comenzi result = comenziService.buildComenzi(produse,comenziDto);

        assertEquals(expectedResult.getNumeClient(),result.getNumeClient());
        assertEquals(expectedResult.getId(),result.getId());
        assertEquals(expectedResult.getProduse().getId(),result.getProduse().getId());
        assertEquals(expectedResult.getStatusComanda(),result.getStatusComanda());


    }
}
