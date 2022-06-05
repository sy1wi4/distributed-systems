package server;

import io.grpc.stub.StreamObserver;
import sr.grpc.gen.PrimeGeneratorGrpc;
import sr.grpc.gen.PrimeGeneratorInput;
import sr.grpc.gen.PrimeGeneratorResult;

import java.util.ArrayList;
import java.util.List;

public class GeneratorImpl extends PrimeGeneratorGrpc.PrimeGeneratorImplBase {

    @Override
    public void generatePrimeNumbers(PrimeGeneratorInput request, StreamObserver<PrimeGeneratorResult> responseObserver) {
        System.out.println("Generating prime numbers (max=" + request.getMax() + ")");
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < request.getMax(); i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                primes.add(i);
            }
        }
        PrimeGeneratorResult primes_ = PrimeGeneratorResult.newBuilder().addAllPrimes(primes).build();
        responseObserver.onNext(primes_);
        responseObserver.onCompleted();

        System.out.println("\nGenerating completed!");
    }

    private boolean isPrime(int val) {
        if (val <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(val); i++) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }
}
