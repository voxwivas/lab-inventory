package com.labfolder.labexperimentinventory.chemicals.service;

import com.labfolder.labexperimentinventory.chemicals.models.Chemical;
import com.labfolder.labexperimentinventory.chemicals.models.CreateChemicalRequest;
import com.labfolder.labexperimentinventory.chemicals.store.ChemicalStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChemicalService {

    @Autowired
    private ChemicalStore chemicalStore;

    public Long addChemical(CreateChemicalRequest chemicalRequest) {
        return chemicalStore.saveChemical(chemicalRequest.asChemical());
    }

    public Chemical updateChemical(CreateChemicalRequest chemical) {
        return chemicalStore.updateChemical(chemical.asChemicalUpdate());
    }

    public Optional<Chemical> getChecmicalById(Long chemicalId) {
        return chemicalStore.getChemicalById(chemicalId);
    }

    public Optional<List<Chemical>> getAllChemicals() {
        return chemicalStore.getAllChemicals();
    }
}
