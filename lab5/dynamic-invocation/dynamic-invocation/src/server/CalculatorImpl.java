package server;


import io.grpc.stub.StreamObserver;
import sr.grpc.gen.*;

public class CalculatorImpl extends AdvancedCalculatorGrpc.AdvancedCalculatorImplBase {
    @Override
    public void complexOperation(ComplexArithmeticOpArguments request, StreamObserver<ComplexArithmeticOpResult> responseObserver) {
        System.out.println("multipleArgumentsRequest (" + request.getOptypeValue() + ", #" + request.getArgsCount() + ")");

        if (request.getArgsCount() == 0) {
            System.out.println("No arguments");
        }

        double res = 0;
        switch (request.getOptype()) {
            case SUM:
                for (Double d : request.getArgsList()) res += d;
                break;
            case AVG:
                for (Double d : request.getArgsList()) res += d;
                res /= request.getArgsCount();
                break;
            case MIN:
                res = request.getArgsList().get(0);
                for (Double d : request.getArgsList()) res = (d < res) ? d : res;
                break;
            case MAX:
                res = request.getArgsList().get(0);
                for (Double d : request.getArgsList()) res = (d > res) ? d : res;
                break;
            case UNRECOGNIZED:
                throw new RuntimeException("Unsupported operation");
        }

        ComplexArithmeticOpResult result = ComplexArithmeticOpResult.newBuilder().setRes(res).build();
        System.out.println(result);
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void primeChecker(PrimeCheckerInput request, StreamObserver<PrimeCheckerResult> responseObserver) {
        System.out.println("Checking if " + request.getInput() + " is prime...");
        boolean isPrime = true;
        int val = request.getInput();

        if (val <= 1) {
            isPrime = false;
        }
        for (int i = 2; i <= Math.sqrt(val); i++) {
            if (val % i == 0) {
                isPrime = false;
                break;
            }
        }

        PrimeCheckerResult result = PrimeCheckerResult.newBuilder().setRes(isPrime).build();
        System.out.println(result);
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
