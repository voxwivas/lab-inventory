package com.labfolder.labexperimentinventory.chemicals.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ChemicalResource.CHEMICAL_RESOURCE_PATH)
public class ChemicalResource {
    public static final String CHEMICAL_RESOURCE_PATH = "/apis/chemicals";
}
