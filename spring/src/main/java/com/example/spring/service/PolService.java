package com.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.spring.osnova.Shag;
import com.example.spring.osnova.Pol;
import com.example.spring.rep.PulJPARep;
import com.example.spring.rep.ShagJPARep;
import com.example.spring.rep.PolJPARep;
import com.example.spring.rest.dto.PolDTO;
import com.example.spring.rest.dto.PolLogDTO;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolService {
    
    private final PolJPARep polRep;
    private final ShagJPARep shagRep;
    private final PulJPARep pulRep;

    public Pol addPol(PolDTO poldto) {
        Pol pol = new Pol();
        pol.setLogin(poldto.getUsername());
        pol.setFirstName(poldto.getFirstName());
        pol.setLastName(poldto.getLastName());
        pol.setAge(poldto.getAge());
        pol.setWeight(poldto.getWeight());
        pol.setHashPassword(poldto.getPassword().hashCode());
        return polRep.save(pol);
    }

    public String authorize(PolLogDTO pollogdto) {
        Pol pol = polRep.findByLogin(pollogdto.getLogin());
        if (pol != null && pol.getHashPassword() == pollogdto.getPassword().hashCode()) {
            return String.format("%s.%d", pol.getLogin(), pol.getHashPassword());
        } else {
            throw new IllegalArgumentException("Wrong Pass or Login!");
        }
    }

    public Integer getTodayShag(String token) {
        Pol pol = checkToken(token);
        Optional<Shag> shagData = pol.getShagData().stream().filter(shag -> shag.getDate().equals(LocalDate.now())).findFirst();
        return shagData.isPresent() ? shagData.get().getShagCount() : 0;
    }

    @Transactional
    public void updateShagCount(String token, Integer shagAmount) {
        Pol pol = checkToken(token);
        Optional<Shag> shagData = pol.getShagData().stream().filter(shag -> shag.getDate().equals(LocalDate.now())).findFirst();
        if (shagData.isEmpty()) {
            Shag shag = new Shag();
            shag.setDate(LocalDate.now());
            shag.setShagCount(shagAmount);
            pol.getShagData().add(shag);
            shag.setPol(pol);
            shagRep.save(shag);
        } else {
            Integer currentAmount = shagData.get().getShagCount();
            shagData.get().setShagCount(currentAmount + shagAmount);
        }
        polRep.save(pol);
    }

    @Transactional
    public void deletePol(String token) {
        Pol pol = checkToken(token);
        polRep.deleteById(pol.getId());
    }

    private Pol checkToken(String token) {
        String[] tokenData = token.split("\\.");
        assert tokenData.length == 2 : "Invalid Token";
        String login = tokenData[0];
        Pol pol = polRep.findByLogin(login);
        assert pol != null : "No such pol";
        assert pol.getHashPassword() == Integer.parseInt(tokenData[1]) : "Wrong password";
        return pol;
    }
}