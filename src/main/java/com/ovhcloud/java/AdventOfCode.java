package com.ovhcloud.java;

import java.util.concurrent.Callable;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine.Command;

@TopCommand
@Command(name = "aoc", mixinStandardHelpOptions = true, subcommands = {NewDay.class, Day1.class, Day2.class, Day3.class, Day4.class, Day5.class, Day6.class, Day7.class})
public class AdventOfCode implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.printf("Go for advent of code !!! 🧑‍💻\n");

        return 0;
    }
}
