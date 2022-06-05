import grpc
import grpc_requests
from google.protobuf.descriptor_pool import DescriptorPool
from grpc_reflection.v1alpha.proto_reflection_descriptor_database import ProtoReflectionDescriptorDatabase


def listing():
    services = list_available_services()

    while True:
        try:
            input_ = input("\n\nChoose service number to list available methods or press `X` to exit\n")
            if input_ == "X":
                break
            chosen_service = int(input_)
            list_available_service_methods_with_args(services[chosen_service])
        except IndexError:
            print("Wrong service number")


def list_available_services():
    print("--------------------------")
    print("Available services:\n")
    services = list(filter(lambda s: s != "grpc.reflection.v1alpha.ServerReflection", reflection_db.get_services()))
    for i, service in enumerate(services):
        name = service.split(".")[-1]
        print(f"({i}) {name}")
    print("---------------------------")

    return services


def list_available_service_methods_with_args(service_name):
    service = desc_pool.FindServiceByName(service_name)
    service_name = service_name.split(".")[-1]
    print("\n_____________________________________________________")
    print(f"Available operations for [{service_name}]:\n")
    for i, method in enumerate(list(map(lambda m: m.full_name, service.methods))):
        method_name = method.split(".")[-1]
        print(f"({i}) {method_name}")

        method_desc = desc_pool.FindMethodByName(method)

        print("\tInput arguments", end=": ")
        for field in method_desc.input_type.fields:
            print(field.full_name.split(".")[-1], end=" ")
        print()

    print("_____________________________________________________")


def send_example_requests():
    print("\n/////////////////////////////////////////////////////////////")
    print("\texample rpc requests to the enable-reflection server")
    print("/////////////////////////////////////////////////////////////\n")

    example1()
    example2()
    example3()


def example1():
    args = [1, 6, 3, 34, 2, 11]
    operations = ["SUM", "AVG", "MIN", "MAX"]

    print(f"args: {args}\n")
    for op in operations:
        result = client.request("calculator.AdvancedCalculator", "ComplexOperation",
                                {"optype": op, "args": args})
        print(f"Operation {op}\n{result}\n")


def example2():
    input_number = 13
    result = client.request("calculator.AdvancedCalculator", "PrimeChecker",
                            {"input": input_number})
    print(f"{input_number} is prime: {result}")


def example3():
    client.request("generator.PrimeGenerator", "GeneratePrimeNumbers",
                   {"max": 20})


if __name__ == '__main__':
    server_address = 'localhost:50050'
    client = grpc_requests.Client.get_by_endpoint("localhost:50050")
    channel = grpc.insecure_channel(server_address)
    reflection_db = ProtoReflectionDescriptorDatabase(channel)
    desc_pool = DescriptorPool(reflection_db)

    listing()

    send_example_requests()

"""
client is able to"
    * connect to the server`
    * learn which server capabilities exists
    * send rpc requests to the enable-reflection server`

--> list all available services on server
    grpcurl -plaintext localhost:50050 list 

--> list all available service methods
    grpcurl -plaintext localhost:50050 list calculator.AdvancedCalculator
    
--> get description of service    
    grpcurl -plaintext localhost:50050 describe calculator.AdvancedCalculator

--> execute method on service running on server
    grpcurl -plaintext -d '{"optype": "SUM", "args": [1,2]}' localhost:50050 calculator.AdvancedCalculator/ComplexOperation

"""
