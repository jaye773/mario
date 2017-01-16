import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Genome {
    List<Gene> genes = new ArrayList<Gene>();
    int innovation = 0;

    public void addGeneConnection() {
        innovation++;
        Gene gene = new Gene();
        gene.weight = new Random().nextDouble();
        gene.innovation = innovation;

        List<Integer> nodesForInput = new ArrayList<>();
        nodesForInput.addAll(getHiddinNodes());
        nodesForInput.addAll(getInputNodes());

        List<Integer> nodesForOutput = new ArrayList<>();
        nodesForOutput.addAll(getHiddinNodes());
        nodesForOutput.addAll(getOutputNodes());

        gene.out = nodesForOutput.get(new Random().nextInt(nodesForOutput.size()));
        gene.into = nodesForInput.get(new Random().nextInt(nodesForInput.size()));

        genes.add(gene);
    }

    public void addGeneNode() {
        Gene gene = genes.get(new Random().nextInt(genes.size()));
        gene.enabled = false;

        int maxNum = genes.stream().mapToInt(gene1 -> Math.max(gene1.into, gene1.out)).reduce(0, (x, y) -> Math.max(x, y));

        Gene goingOut = new Gene();
        goingOut.weight = gene.weight;
        goingOut.out = gene.out;
        int newNodeNumber = maxNum + 1;
        goingOut.into = newNodeNumber;

        genes.add(goingOut);

        Gene goingIn = new Gene();
        goingIn.weight = 1;
        goingIn.out = newNodeNumber;
        goingIn.into = gene.into;

        genes.add(goingIn);
    }

    Set<Integer> getHiddinNodes() {
        List<Integer> outputs = genes.stream().map(gene -> gene.out).distinct().collect(Collectors.toList());
        List<Integer> inputs = genes.stream().map(gene -> gene.into).distinct().collect(Collectors.toList());
        return outputs.stream().filter(out -> inputs.contains(out)).collect(Collectors.toSet());
    }

    Set<Integer> getInputNodes() {
        List<Integer> outputs = genes.stream().map(gene -> gene.out).distinct().collect(Collectors.toList());
        List<Integer> inputs = genes.stream().map(gene -> gene.into).distinct().collect(Collectors.toList());
        return inputs.stream().filter(in -> !outputs.contains(in)).collect(Collectors.toSet());
    }

    Set<Integer> getOutputNodes() {
        List<Integer> outputs = genes.stream().map(gene -> gene.out).distinct().collect(Collectors.toList());
        List<Integer> inputs = genes.stream().map(gene -> gene.into).distinct().collect(Collectors.toList());
        return outputs.stream().filter(out -> !inputs.contains(out)).collect(Collectors.toSet());
    }
}
