package com.mario;

/**
 * In Biology, a Gene is a region of DNA that encodes a function. In general a collection
 * of Genes is required to encode something specific such as eye colour. The mixing and
 * inheriting and copying of genes is what powers evolution. The goal of an algorithm such
 * as NEAT is to harness this evolutionary power to evolve a neural network so that it
 * can learn to perform a particular task.
 *
 * Therefore we need to be able to represent a neural network as a collection of genes.
 * There is no one way to do this but in this case we have chosen to design our genes
 * as follows.
 *
 * Each gene specifies a connection between two nodes. The 'into' property is telling us
 * the number of the node that this connection is coming from, 'out' the node this
 * connection is going to.
 *
 * The innovation number is mostly concerned with creating unique genes. It is incremented
 * every time a gene is created globally.
 *
 * A connection can also have a weight. This is not related to the mechanics of how genes
 * work but is a property of the neural network, describing the strength of the connection.
 *
 * A gene can also be switched on and off as a result of mating.
 */

public class Gene {

    public static int globalInnovation = 0;

    public Gene(int into, int out){
        this.into = into;
        this.out = out;
        this.innovation = globalInnovation;
        globalInnovation++;
    }

    int into;
    int out;
    double weight = 1;
    boolean enabled = true;
    int innovation = 0;
}
