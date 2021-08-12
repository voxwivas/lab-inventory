package com.labfolder.labexperimentinventory.chemicals.store;


import com.labfolder.labexperimentinventory.chemicals.models.Chemical;

import java.util.List;
import java.util.Optional;

public interface ChemicalStore {

    Long saveChemical(Chemical chemical);

    Chemical updateChemical(Chemical chemical);

    Optional<Chemical> getChemicalById(Long chemicalId);

    Optional<List<Chemical>> getAllChemicals();
}
