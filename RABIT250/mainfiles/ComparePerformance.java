package mainfiles;

import algorithms.ParallelSimulation;
import algorithms.Simulation;
import automata.FAState;
import automata.FiniteAutomaton;

import java.lang.management.*;
import java.lang.Exception;

import java.util.Random;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import datastructure.Pair;

public class ComparePerformance {
    private static FiniteAutomaton toBA(FiniteAutomaton aut){
        if(aut.alphabet.contains("_specialaction359_")){
            System.out.println("Error: Alphabet name clash. Exiting.");
            System.exit(0);
        }
        FAState acc = aut.createState();
        Iterator<FAState> it=aut.F.iterator();
        while(it.hasNext()){
            FAState state=it.next();
            aut.addTransition(state, acc, "_specialaction359_");
        }
        aut.addTransition(acc, acc, "_specialaction359_");
        aut.F.clear();
        aut.F.add(acc);
        return aut;
    }

    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("You need give two Automata");
        }
        FiniteAutomaton aut1 = new FiniteAutomaton(args[0]);
        aut1.name=args[0];
        FiniteAutomaton aut2 = new FiniteAutomaton(args[1]);
        aut2.name=args[1];

        aut1=toBA(aut1);
        aut2=toBA(aut2);

        int la = Integer.parseInt(args[2]);

        int num_proc = Integer.parseInt(args[3]);
        int devideNum = Integer.parseInt(args[4]);

        Simulation sim = new Simulation();
        ParallelSimulation parallelSim = new ParallelSimulation(num_proc, devideNum);

        //NOTICE: the first time running two programs
//        long ttime3 = System.currentTimeMillis();
//        Set<Pair<FAState,FAState>> result3 = sim.BLAFairSimRelNBW(aut1, aut2, la);
//        long ttime4 = System.currentTimeMillis();
//        System.out.println("Time used(ms) for Sequential Version: "+(ttime4-ttime3)+" ms.");
//
//        long ttime1 = System.currentTimeMillis();
//        Set<Pair<FAState,FAState>> result4 = parallelSim.BLAFairSimRelNBW(aut1, aut2, la);
//        long ttime2 = System.currentTimeMillis();
//        System.out.println("Time used(ms) for Parallel Version: "+(ttime2-ttime1)+" ms.");

        System.out.println("--------------------------------------------------");

        //NOTICE: the second time running two programs
        long ttime5 = System.currentTimeMillis();
        Set<Pair<FAState,FAState>> result1 = sim.BLAFairSimRelNBW(aut1, aut2, la);
        long ttime6 = System.currentTimeMillis();
        System.out.println("Time used(ms) for Sequential Version: "+(ttime6-ttime5)+" ms.");

        long ttime7 = System.currentTimeMillis();
        Set<Pair<FAState,FAState>> result2 = parallelSim.BLAFairSimRelNBW(aut1, aut2, la);
        long ttime8 = System.currentTimeMillis();
        System.out.println("Time used(ms) for Parallel Version: "+(ttime8-ttime7)+" ms.");

        System.out.println();

        if(result2==result1){
            System.out.println("Both results are the same object, be careful!");

        } else if (result1.containsAll(result2) && result2.containsAll(result1)){
            //System.out.println("Correct! The calculated parallel result is the same as the sequential result!");

        } else{
            System.out.println("InCorrect! The calculated parallel result is different from the sequential result!");

        }

        System.out.println("================================================");

    }
}
