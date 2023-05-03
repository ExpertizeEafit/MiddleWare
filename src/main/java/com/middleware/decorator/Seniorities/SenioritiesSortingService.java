package com.middleware.decorator.Seniorities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.middleware.dto.Seniority;

import io.micronaut.context.annotation.Prototype;

@Prototype
public class SenioritiesSortingService {

    public SenioritiesSortingService() {}

    public List<Seniority> sortMergedSeniorities(Map<String, Seniority> seniorities) {
        Stack<String> orderedSeniorities = topologicalSort(seniorities);
        List<Seniority> senioritiesToReturn = new ArrayList<>();

        while (!orderedSeniorities.isEmpty()) {
            String seniorityId = orderedSeniorities.pop();
            senioritiesToReturn.add(seniorities.get(seniorityId));
        }

        return senioritiesToReturn;
    }

    void topologicalSortUtil(String node, Map<String, Boolean> visited,
            Stack<String> stack, Map<String, Seniority> seniorities) {

        visited.put(node, true);

        seniorities.get(node).priorTo.forEach(priorTo -> {
            if (!visited.get(priorTo))
                topologicalSortUtil(priorTo, visited, stack, seniorities);
        });

        stack.push(node);
    }

    Stack<String> topologicalSort(Map<String, Seniority> seniorities) {
        Stack<String> stack = new Stack<>();

        Map<String, Boolean> visited = seniorities.keySet().stream()
                .collect(Collectors.toMap(Function.identity(), k -> false));

        seniorities.forEach((id, seniority) -> {
            if (!visited.get(id))
                topologicalSortUtil(id, visited, stack, seniorities);
        });

        return stack;
    }
}
