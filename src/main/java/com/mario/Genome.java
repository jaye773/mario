package com.mario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Genome {
    List<Gene> genes = new ArrayList<Gene>();

    public Genome(int inputs, int outputs) {

        int max = Math.max(inputs, outputs);

        for(int i = 0; i < max; i++){
            int input = i % inputs + 1;
            int output = i % outputs + 1;
            Gene gene = new Gene(input, output);
            genes.add(gene);
        }

    }

    public void addGeneConnection() {
        List<Integer> nodesForInput = new ArrayList<>();
        nodesForInput.addAll(getHiddenNodes());
        nodesForInput.addAll(getInputNodes());

        List<Integer> nodesForOutput = new ArrayList<>();
        nodesForOutput.addAll(getHiddenNodes());
        nodesForOutput.addAll(getOutputNodes());

        int into = nodesForInput.get(new Random().nextInt(nodesForInput.size()));
        int out = nodesForOutput.get(new Random().nextInt(nodesForOutput.size()));

        Gene gene = new Gene(into, out);
        gene.weight = new Random().nextDouble();

        genes.add(gene);
    }

    public void addGeneNode() {
        Gene gene = genes.get(new Random().nextInt(genes.size()));
        gene.enabled = false;

        int maxNum = genes.stream()
                .mapToInt(gene1 -> Math.max(gene1.into, gene1.out))
                .reduce(0, (x, y) -> Math.max(x, y));

        int newNodeNumber = maxNum + 1;
        Gene goingOut = new Gene(newNodeNumber, gene.out);
        goingOut.weight = gene.weight;

        genes.add(goingOut);

        Gene goingIn = new Gene(gene.into, newNodeNumber);
        goingIn.weight = 1;

        genes.add(goingIn);
    }

    Set<Integer> getHiddenNodes() {
        List<Integer> outputs = genes.stream()
                .map(gene -> gene.out)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> inputs = genes.stream()
                .map(gene -> gene.into)
                .distinct()
                .collect(Collectors.toList());

        return outputs.stream().filter(out -> inputs.contains(out)).collect(Collectors.toSet());
    }

    Set<Integer> getInputNodes() {
        List<Integer> outputs = genes.stream()
                .map(gene -> gene.out)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> inputs = genes.stream()
                .map(gene -> gene.into)
                .distinct()
                .collect(Collectors.toList());

        return inputs.stream().filter(in -> !outputs.contains(in)).collect(Collectors.toSet());
    }

    Set<Integer> getOutputNodes() {
        List<Integer> outputs = genes.stream()
                .map(gene -> gene.out)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> inputs = genes.stream()
                .map(gene -> gene.into)
                .distinct()
                .collect(Collectors.toList());
        return outputs.stream().filter(out -> !inputs.contains(out)).collect(Collectors.toSet());
    }
}
