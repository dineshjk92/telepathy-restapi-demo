package org.example.telepathyrestdemo.model;

import java.util.List;

public class CloudPlan {

    String planName;
    int planPrice;
    List planFeatures;

    public CloudPlan() {

    }

    public CloudPlan(String planName, int planPrice, List planFeatures) {
        this.planName = planName;
        this.planPrice = planPrice;
        this.planFeatures = planFeatures;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(int planPrice) {
        this.planPrice = planPrice;
    }

    public List getPlanFeatures() {
        return planFeatures;
    }

    public void setPlanFeatures(List planFeatures) {
        this.planFeatures = planFeatures;
    }
}
