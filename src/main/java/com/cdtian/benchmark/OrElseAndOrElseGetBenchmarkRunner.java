package com.cdtian.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(1)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class OrElseAndOrElseGetBenchmarkRunner {

    private OrElseAndOrElseGet orElsevsOrElseGet = new OrElseAndOrElseGet();

    public static void main(String[] args) throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public String orElseBenchmark() {
        return orElsevsOrElseGet.getNameUsingOrElse("baeldung");
    }

    @Benchmark
    public String orElseGetBenchmark() {
        return orElsevsOrElseGet.getNameUsingOrElseGet("baeldung");
    }

}
