// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: generator.proto

package sr.grpc.gen;

public final class GeneratorProto {
  private GeneratorProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generator_PrimeGeneratorInput_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generator_PrimeGeneratorInput_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generator_PrimeGeneratorResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generator_PrimeGeneratorResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017generator.proto\022\tgenerator\"\"\n\023PrimeGen" +
      "eratorInput\022\013\n\003max\030\001 \001(\005\"&\n\024PrimeGenerat" +
      "orResult\022\016\n\006primes\030\001 \003(\0052k\n\016PrimeGenerat" +
      "or\022Y\n\024GeneratePrimeNumbers\022\036.generator.P" +
      "rimeGeneratorInput\032\037.generator.PrimeGene" +
      "ratorResult\"\000B\037\n\013sr.grpc.genB\016GeneratorP" +
      "rotoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_generator_PrimeGeneratorInput_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_generator_PrimeGeneratorInput_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generator_PrimeGeneratorInput_descriptor,
        new java.lang.String[] { "Max", });
    internal_static_generator_PrimeGeneratorResult_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_generator_PrimeGeneratorResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generator_PrimeGeneratorResult_descriptor,
        new java.lang.String[] { "Primes", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}