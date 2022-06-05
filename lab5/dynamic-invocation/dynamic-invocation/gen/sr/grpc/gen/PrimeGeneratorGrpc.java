package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.28.1)",
    comments = "Source: generator.proto")
public final class PrimeGeneratorGrpc {

  private PrimeGeneratorGrpc() {}

  public static final String SERVICE_NAME = "generator.PrimeGenerator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.PrimeGeneratorInput,
      sr.grpc.gen.PrimeGeneratorResult> getGeneratePrimeNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GeneratePrimeNumbers",
      requestType = sr.grpc.gen.PrimeGeneratorInput.class,
      responseType = sr.grpc.gen.PrimeGeneratorResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.PrimeGeneratorInput,
      sr.grpc.gen.PrimeGeneratorResult> getGeneratePrimeNumbersMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.PrimeGeneratorInput, sr.grpc.gen.PrimeGeneratorResult> getGeneratePrimeNumbersMethod;
    if ((getGeneratePrimeNumbersMethod = PrimeGeneratorGrpc.getGeneratePrimeNumbersMethod) == null) {
      synchronized (PrimeGeneratorGrpc.class) {
        if ((getGeneratePrimeNumbersMethod = PrimeGeneratorGrpc.getGeneratePrimeNumbersMethod) == null) {
          PrimeGeneratorGrpc.getGeneratePrimeNumbersMethod = getGeneratePrimeNumbersMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.PrimeGeneratorInput, sr.grpc.gen.PrimeGeneratorResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GeneratePrimeNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.PrimeGeneratorInput.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.PrimeGeneratorResult.getDefaultInstance()))
              .setSchemaDescriptor(new PrimeGeneratorMethodDescriptorSupplier("GeneratePrimeNumbers"))
              .build();
        }
      }
    }
    return getGeneratePrimeNumbersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PrimeGeneratorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrimeGeneratorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrimeGeneratorStub>() {
        @java.lang.Override
        public PrimeGeneratorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrimeGeneratorStub(channel, callOptions);
        }
      };
    return PrimeGeneratorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PrimeGeneratorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrimeGeneratorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrimeGeneratorBlockingStub>() {
        @java.lang.Override
        public PrimeGeneratorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrimeGeneratorBlockingStub(channel, callOptions);
        }
      };
    return PrimeGeneratorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PrimeGeneratorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrimeGeneratorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrimeGeneratorFutureStub>() {
        @java.lang.Override
        public PrimeGeneratorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrimeGeneratorFutureStub(channel, callOptions);
        }
      };
    return PrimeGeneratorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class PrimeGeneratorImplBase implements io.grpc.BindableService {

    /**
     */
    public void generatePrimeNumbers(sr.grpc.gen.PrimeGeneratorInput request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.PrimeGeneratorResult> responseObserver) {
      asyncUnimplementedUnaryCall(getGeneratePrimeNumbersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGeneratePrimeNumbersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sr.grpc.gen.PrimeGeneratorInput,
                sr.grpc.gen.PrimeGeneratorResult>(
                  this, METHODID_GENERATE_PRIME_NUMBERS)))
          .build();
    }
  }

  /**
   */
  public static final class PrimeGeneratorStub extends io.grpc.stub.AbstractAsyncStub<PrimeGeneratorStub> {
    private PrimeGeneratorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimeGeneratorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrimeGeneratorStub(channel, callOptions);
    }

    /**
     */
    public void generatePrimeNumbers(sr.grpc.gen.PrimeGeneratorInput request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.PrimeGeneratorResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGeneratePrimeNumbersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PrimeGeneratorBlockingStub extends io.grpc.stub.AbstractBlockingStub<PrimeGeneratorBlockingStub> {
    private PrimeGeneratorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimeGeneratorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrimeGeneratorBlockingStub(channel, callOptions);
    }

    /**
     */
    public sr.grpc.gen.PrimeGeneratorResult generatePrimeNumbers(sr.grpc.gen.PrimeGeneratorInput request) {
      return blockingUnaryCall(
          getChannel(), getGeneratePrimeNumbersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PrimeGeneratorFutureStub extends io.grpc.stub.AbstractFutureStub<PrimeGeneratorFutureStub> {
    private PrimeGeneratorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimeGeneratorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrimeGeneratorFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.PrimeGeneratorResult> generatePrimeNumbers(
        sr.grpc.gen.PrimeGeneratorInput request) {
      return futureUnaryCall(
          getChannel().newCall(getGeneratePrimeNumbersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GENERATE_PRIME_NUMBERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PrimeGeneratorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PrimeGeneratorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GENERATE_PRIME_NUMBERS:
          serviceImpl.generatePrimeNumbers((sr.grpc.gen.PrimeGeneratorInput) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.PrimeGeneratorResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PrimeGeneratorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PrimeGeneratorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.GeneratorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PrimeGenerator");
    }
  }

  private static final class PrimeGeneratorFileDescriptorSupplier
      extends PrimeGeneratorBaseDescriptorSupplier {
    PrimeGeneratorFileDescriptorSupplier() {}
  }

  private static final class PrimeGeneratorMethodDescriptorSupplier
      extends PrimeGeneratorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PrimeGeneratorMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PrimeGeneratorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PrimeGeneratorFileDescriptorSupplier())
              .addMethod(getGeneratePrimeNumbersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
