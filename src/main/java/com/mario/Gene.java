package com.mario;

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
