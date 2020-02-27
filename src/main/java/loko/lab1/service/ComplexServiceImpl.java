package loko.lab1.service;

import loko.lab1.entity.Tent;
import loko.lab1.repository.ManufacturerRepository;
import loko.lab1.repository.PropertyRepository;
import loko.lab1.repository.TentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComplexServiceImpl implements ComplexService {

    private ManufacturerRepository manufacturerRepository;
    private PropertyRepository propertyRepository;
    private TentRepository tentRepository;

    @Override
    public List<Tent> findAllTents() {
        return tentRepository.findAll();
    }
}
