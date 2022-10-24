package com.mmateiuk.springcloudsqssender.controller;

import com.mmateiuk.springcloudsqssender.model.common.TnDetail;
import com.mmateiuk.springcloudsqssender.service.ProvisionTnService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tn")
public class DemoController {

    private final ProvisionTnService provisionTnService;

    public DemoController(ProvisionTnService provisionTnService) {
        this.provisionTnService = provisionTnService;
    }

    @PostMapping
    public ResponseEntity<TnDetail> provisionTn(@RequestBody TnDetail tnDetail) {
        TnDetail provisionedTnDetails = provisionTnService.provision(tnDetail);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(provisionedTnDetails);
    }
}
