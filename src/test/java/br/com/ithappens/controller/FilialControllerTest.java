package br.com.ithappens.controller;

import br.com.ithappens.BaseTest;
import br.com.ithappens.exceptions.BadRequestException;
import br.com.ithappens.mapper.FilialMapper;
import br.com.ithappens.model.Filial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default, prod")
@Transactional
public class FilialControllerTest extends BaseTest {
    @Autowired
    private FilialController filialController;
    @Autowired
    private FilialMapper filialMapper;

    @Test
    public void criarFilialTest() {
        Filial filial = new Filial(null, "789789789");
        ResponseEntity<Long> filialIdResponse = filialController.createUpdate(filial);
        assertEquals(HttpStatus.CREATED, filialIdResponse.getStatusCode());
        filial.setId(filialIdResponse.getBody());
        Filial filialCriado = filialMapper.getById(filial.getId());
        assertEquals(filialCriado, filial);
    }

    @Test
    public void updateFilialTest() {
        Filial filial = criarFilialDeTeste();
        ResponseEntity<Long> filialIdResponse = filialController.createUpdate(filial);
        assertEquals(HttpStatus.OK, filialIdResponse.getStatusCode());
        Filial filialCriado = filialMapper.getById(filial.getId());
        assertEquals(filialCriado, filial);
    }

    @Test(expected = BadRequestException.class)
    public void failUpdateFilialTest(){
        Filial filial = new Filial(new Random().nextLong(), "789789789");
        filialController.createUpdate(filial);
    }

}
