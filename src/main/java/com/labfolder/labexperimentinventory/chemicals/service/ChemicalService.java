package com.labfolder.labexperimentinventory.chemicals.service;

import com.labfolder.labexperimentinventory.chemicals.store.ChemicalStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChemicalService {

    @Autowired
    private ChemicalStore chemicalStore;


}
