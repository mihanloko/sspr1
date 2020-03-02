package loko.lab1.service;

import loko.lab1.entity.Tent;

import java.util.List;

public interface ComplexService {

    List<Tent> findAllTents();

    void deleteProperties(long id);

    void saveTent(Tent tent);

    void deleteTent(long id);

}
