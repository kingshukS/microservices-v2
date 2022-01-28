package io.kingshuk.limitsservice.resource;

import io.kingshuk.limitsservice.model.LimitsConfig;
import io.kingshuk.limitsservice.service.LimitsConfigPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/limits-service-v2")
public class LimitsConfigResource {

    @Autowired
    private LimitsConfigPropertyService limitsConfigPropertyService;

    @GetMapping("/config")
    public LimitsConfig retrieveLimitConfig(){
        int min = limitsConfigPropertyService.getMinimum();
        int max = limitsConfigPropertyService.getMaximum();
        return new LimitsConfig(max,min);
    }

    @GetMapping("/config-fault-tolerance")
    public LimitsConfig retrieveLimitConfigFaultTolerance(){
        throw new RuntimeException("Not Available");
    }

    public LimitsConfig fallbackRetrieveLimitConfigFaultTolerance(){
        return new LimitsConfig(999,9);
    }
}
