package com.amrut.prabhu.Springcachingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.UUID;

@org.springframework.stereotype.Service
public class Service {

    Logger logger = LoggerFactory.getLogger(Service.class);

    @CachePut(cacheNames = "cacheStore", key = "#name")
    public Person generatePerson(String name) {
        Person person = new Person(UUID.randomUUID().toString(), name, "Switzerland");
        logger.info("Generated Person: {}", person);
        return person;
    }

    @Cacheable(cacheNames = "cacheStore", key = "#person.name")
    public Person fetchPerson(Person person) {
        logger.info("Person request received = {}", person);
        return person;
    }

    @CacheEvict(cacheNames = "cacheStore", key = "#person.name")
    public void evictPerson(Person person) {
        logger.info("evicting Person = {}", person);
    }
}
