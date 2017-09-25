package com.ms.springbootdemo_version009.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ms.springbootdemo_version009.bean.City;
import com.ms.springbootdemo_version009.repository.CityRepository;

@Transactional
@Component("cityService")
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City save(City city) {
        return cityRepository.save(city);
    }

    public List<City> save(List<City> list) {
        return cityRepository.save(list);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public void deleteAll() {
        cityRepository.deleteAll();
    }
}
