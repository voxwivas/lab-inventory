package com.labfolder.labexperimentinventory.chemicals.store;

import com.labfolder.labexperimentinventory.chemicals.models.Chemical;
import com.labfolder.labexperimentinventory.exceptions.ChemicalExistsException;
import com.labfolder.labexperimentinventory.exceptions.ChemicalNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FakeChemicalStore implements ChemicalStore {

    private final Map<Long, Chemical> chemicals = new HashMap<>();

    @Override
    public Long saveChemical(Chemical chemical) {
        if (checkChemicalExists(chemical)) {
            throw new ChemicalExistsException(String.format("Chemical with name %s already exists", chemical.getChemicalName()));
        } else {
            Long id = (long) nextChemicalId();
            Chemical chemicalToSave = new Chemical(
                    id,
                    chemical.getChemicalName(),
                    chemical.getFlammability(),
                    chemical.getToxicity(),
                    chemical.getAcidity(),
                    chemical.getReactivity()
            );
            chemicals.put(id, chemicalToSave);
            return id;
        }
    }

    @Override
    public Chemical updateChemical(Chemical chemical) {
        var chemical1 = chemicals.get(chemical.getId());
        if (chemical1 == null) {
            throw new ChemicalNotFoundException(String.format("Chemical with id %s not found", chemical.getId()));
        } else {
            chemicals.put(chemical.getId(), chemical1);
            return chemical1;
        }
    }

    @Override
    public Optional<Chemical> getChemicalById(Long chemicalId) {
        var chemical = chemicals.get(chemicalId);
        if (chemical == null) {
            throw new ChemicalNotFoundException(String.format("Chemical with id %s not found", chemicalId));
        }
        return Optional.of(chemical);
    }

    @Override
    public Optional<List<Chemical>> getAllChemicals() {
        return Optional.of(new ArrayList<>(chemicals.values()));
    }

    private boolean checkChemicalExists(Chemical chemical) {
        return chemicals.values().stream().anyMatch(c -> c.isSameChemical(chemical));
    }

    private int nextChemicalId() {
        return chemicals.size() + 1;
    }
}
