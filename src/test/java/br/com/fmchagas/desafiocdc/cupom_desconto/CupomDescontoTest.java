package br.com.fmchagas.desafiocdc.cupom_desconto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

public class CupomDescontoTest {
    
    @ParameterizedTest
    @CsvSource({
        "1,true",
        "2,true"
    })
    void teste1(long valor, boolean resultado) throws Exception {
        /** Não é possivel passar valor de borda igual a 0, pois interfere no nano do time sempre sendo invalido a criação do cupom */
        CupomDesconto cupomDesconto = new CupomDesconto("codigo", BigDecimal.ONE, LocalDateTime.now().plusSeconds(valor));

        Assertions.assertEquals(resultado, cupomDesconto.valido());
    }

    @Test
    void naoDeveSerValido_QuandoCupomDescontoComDataNoPassado(){
        CupomDesconto cupomDesconto = new CupomDesconto("codigo", BigDecimal.ONE, LocalDateTime.now().plusSeconds(1));
        ReflectionTestUtils.setField(cupomDesconto, "validade", LocalDateTime.now().minusSeconds(1));
       
        Assertions.assertTrue(cupomDesconto.invalido());
        Assertions.assertFalse(cupomDesconto.valido());
    }

    @Test
    void naoDeveCriarCupom_QuandoDataForNoPassado(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new CupomDesconto("codigo", BigDecimal.TEN, LocalDateTime.now().minusNanos(1));
        });
    }
}
