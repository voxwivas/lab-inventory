package com.labfolder.labexperimentinventory.chemicals.api;

import com.labfolder.labexperimentinventory.chemicals.models.Chemical;
import com.labfolder.labexperimentinventory.chemicals.models.CreateChemicalRequest;
import com.labfolder.labexperimentinventory.chemicals.service.ChemicalService;
import com.labfolder.labexperimentinventory.utils.wrappers.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ChemicalResource.CHEMICAL_RESOURCE_PATH)
public class ChemicalResource {
    public static final String CHEMICAL_RESOURCE_PATH = "/apis/chemicals";

    @Autowired
    private ChemicalService chemicalService;

    @PostMapping
    public ResponseEntity<Id> createChemical(@RequestBody CreateChemicalRequest chemicalRequest) {
        return ResponseEntity.ok(new Id(chemicalService.addChemical(chemicalRequest)));
    }

    @GetMapping("/{chemicalId}")
    public ResponseEntity<Optional<Chemical>> getChemicalById(@PathVariable("chemicalId") Long chemicalId) {
        return ResponseEntity.ok(chemicalService.getChecmicalById(chemicalId));
    }

    @PutMapping("/update")
    public ResponseEntity<Chemical> updateChemical(@RequestBody CreateChemicalRequest chemicalRequest) {
        return ResponseEntity.ok().body(chemicalService.updateChemical(chemicalRequest));
    }

    @GetMapping
    public ResponseEntity<List<Chemical>> getAllChemicals(){
        return ResponseEntity.of(chemicalService.getAllChemicals());
    }
}
