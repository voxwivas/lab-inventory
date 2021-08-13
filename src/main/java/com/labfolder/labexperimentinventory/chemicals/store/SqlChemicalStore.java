package com.labfolder.labexperimentinventory.chemicals.store;

import com.labfolder.labexperimentinventory.chemicals.models.Chemical;
import com.labfolder.labexperimentinventory.chemicals.store.repository.ChemicalRepository;
import com.labfolder.labexperimentinventory.exceptions.ChemicalExistsException;
import com.labfolder.labexperimentinventory.exceptions.ChemicalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SqlChemicalStore implements ChemicalStore {

    @Autowired
    private ChemicalRepository chemicalRepository;

    @Override
    public Long saveChemical(Chemical chemical) {
        if (checkChemicalExists(chemical)) {
            throw new ChemicalExistsException(String.format("Chemical with name %s already exists", chemical.getChemicalName()));
        }
        return chemicalRepository.save(chemical).getId();
    }

    @Override
    public Chemical updateChemical(Chemical chemical) {
        var chemical1 = chemicalRepository.findById(chemical.getId());
        if (chemical1.isPresent()) {
            return chemicalRepository.save(chemical);
        }
        throw new ChemicalNotFoundException(String.format("Chemical with id %s not found", chemical.getId()));
    }

    @Override
    public Optional<Chemical> getChemicalById(Long chemicalId) {
        var chemical1 = chemicalRepository.findById(chemicalId);
        if (chemical1.isPresent()) {
            return chemical1;
        }
        throw new ChemicalNotFoundException(String.format("Chemical with id %s not found", chemicalId));
    }

    @Override
    public Optional<List<Chemical>> getAllChemicals() {
        return Optional.of(chemicalRepository.findAll());
    }

    private boolean checkChemicalExists(Chemical chemical) {
        return chemicalRepository.findAll().stream().anyMatch(c -> c.isSameChemical(chemical));
    }

}
