package org.example.telepathyrestdemo.controller;

import org.example.telepathyrestdemo.model.CloudPlan;
import org.example.telepathyrestdemo.service.FindBestPlan;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cloudservice")
public class CloudPlanAPIService {

    CloudPlan cloudPlan;
    List<CloudPlan> cloudPlanList = new ArrayList<>();

    @GetMapping("/{planName}")
    public CloudPlan getCloudPlanDetails(@PathVariable(value = "planName") String planName) {
        for(CloudPlan cp : cloudPlanList) {
            if(cp.getPlanName().equalsIgnoreCase(planName))
                return cp;
        }
        return null;
    }

    @GetMapping("/plans")
    public List<CloudPlan> getAllCloudPlanDetails() {
        return cloudPlanList;
    }

    @PostMapping
    public String createCloudPlan(@RequestBody CloudPlan cloudPlan) {
        this.cloudPlan = cloudPlan;
        cloudPlanList.add(cloudPlan);
        return "Cloud plan has been successfully created";
    }

    @PutMapping
    public String updateCloudPlan(@RequestBody CloudPlan cloudPlan) {
        this.cloudPlan = cloudPlan;
        return "Cloud plan has been successfully updated";
    }

    @DeleteMapping("{planName}")
    public String updateCloudPlan(String planName) {
        this.cloudPlan = null;
        return "Cloud plan has been successfully deleted";
    }

    @PostMapping("/findbestplan")
    public String[] getBestCloudPlan(@RequestBody List<String> reqFeatures) {
        return FindBestPlan.findBestCloudPlan(cloudPlanList, reqFeatures);
    }


}
