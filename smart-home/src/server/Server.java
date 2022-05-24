package server;

import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

//https://doc.zeroc.com/ice/3.7/hello-world-application/writing-an-ice-application-with-java

public class Server {
    public static void main(String[] args) {

        System.out.println("Start Server main...");

        try (com.zeroc.Ice.Communicator communicator = Util.initialize(args)) {

//            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints(
//                    "PrinterAdapter", "tcp -h 127.0.0.1 -p 10000 -z : udp -h 127.0.0.1 -p 10000 -z");
            ObjectAdapter adapter = communicator.createObjectAdapter("PrinterAdapter"); // configuration in config file

            PrinterI printerServant = new PrinterI();
//            adapter.add(printerServant, stringToIdentity("Printer"));   // albo jak na labach?
            adapter.add(printerServant, new Identity("Printer1", "Printer"));
            adapter.activate();

            System.out.println("End Server main...");
            communicator.waitForShutdown();

        }
    }
}
