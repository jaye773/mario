package com.mario;

import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Created by root on 1/16/17.
 */
public class GenomeTest {

   @Test
   public void testGenomeConstructor() {
      assertTrue("Genome size error outputs > inputs",
              new Genome(3, 4).genes.size() == 4);

      assertTrue("Genome size error outputs < inputs",
              new Genome(4, 3).genes.size() == 4);

      assertTrue("Inputs Ordered?",
              new Genome(4, 3).genes.stream()
                      .map(gene -> gene.into)
                      .reduce(0, (x,y) -> ((y > x) ? x + 1 : x))
                      == 4);

      assertTrue("Outputs Ordered?",
              new Genome(4, 3).genes.stream()
                      .map(gene -> gene.out)
                      .reduce(0, (x,y) -> ((y > x) ? x + 1 : x))
                      == 3);
   }

   @Test
   public void testAddGeneConnection() {
      Genome addedGene = new Genome(4,3);

      addedGene.addGeneConnection();
      assertTrue("Gene count", addedGene.genes.size() == 5);

      addedGene.addGeneConnection();
      assertTrue("Gene count", addedGene.genes.size() == 6);

      assertTrue("Input node count",
              addedGene.genes.stream()
                      .map(gene -> gene.into)
                      .reduce(0, (x,y) -> ((y > x) ? y : x))
                      == 4);

      assertTrue("Output node count",
              addedGene.genes.stream()
                      .map(gene -> gene.out)
                      .reduce(0, (x,y) -> ((y > x) ? y : x))
                      == 3);

   }

}
