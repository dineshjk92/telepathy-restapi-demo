package org.example.telepathyrestdemo.service;

import org.example.telepathyrestdemo.model.CloudPlan;
import java.util.*;
import java.util.stream.Collectors;

public class FindBestPlan {

    public static Map<String, Integer> sortPlanByPrice(Map<String, Integer> planPrice) {
        Set<Map.Entry<String, Integer>> entries = planPrice.entrySet();

        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                return v1.compareTo(v2);
            }
        };

        // Sort method needs a List, so let's first convert Set to List in Java
        List<Map.Entry<String, Integer>> listOfEntries = new ArrayList<Map.Entry<String, Integer>>(entries);

        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, valueComparator);
        LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());

        // copying entries from List to Map
        for(Map.Entry<String, Integer> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }

        return sortedByValue;
    }

    private static void subsetsOf(List<String> plans, int k, int index, Set<String> tempSet, List<Set<String>> finalSet) {
        if (tempSet.size() == k) {
            finalSet.add(new HashSet<>(tempSet));
            return;
        }

        if (index == plans.size())
            return;

        String str = plans.get(index);

        tempSet.add(str);
        subsetsOf(plans, k, index+1, tempSet, finalSet);

        tempSet.remove(str);
        subsetsOf(plans, k, index+1, tempSet, finalSet);
    }

    public static List<Set<String>> combination(List<String> plans, int k) {
        List<Set<String>> planSubsets = new ArrayList<>();
        subsetsOf(plans, k, 0, new HashSet<String>(), planSubsets);
        return planSubsets;
    }
    
    public static String[] findBestCloudPlan(List<CloudPlan> plans, List<String> reqFeatures) {
        Map<String, Integer> planPrice = new HashMap<>();
        Map<String, List> planFeature = new HashMap<>();
        Set<String> finalPlans = new HashSet<>();
        String bestPlans="";
        Integer bestPrice = 0;

        for(CloudPlan plan : plans) {
            planPrice.put(plan.getPlanName(), plan.getPlanPrice());
            planFeature.put(plan.getPlanName(), plan.getPlanFeatures());
        }

        planPrice = sortPlanByPrice(planPrice);

        for(int k=1; k<=plans.size() && finalPlans.size()==0; k++) {
            List<Set<String>> planSubset = combination(new ArrayList<String>(planPrice.keySet()), k);
            for(Set<String> subset : planSubset) {
                List<String> checkFeatures = reqFeatures;
                for(String plan : subset) {
                    List<String> features = planFeature.get(plan);
                    List<String> finalReqFeatures = checkFeatures;
                    checkFeatures = finalReqFeatures.stream()
                            .filter(o -> !features.contains(o))
                            .collect(Collectors.toList());
                }
                if(checkFeatures.size() == 0) {
                    finalPlans = subset;
                    break;
                }
            }
        }

        for(String plan:finalPlans) {
            bestPrice += planPrice.get(plan);
            bestPlans += ","+plan;
        }

        return new String[]{bestPlans, bestPrice+""};
    }
}
