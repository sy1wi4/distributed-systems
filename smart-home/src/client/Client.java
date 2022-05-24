package client;

import Demo.PrinterPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.LocalException;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

public class Client {
    public static void main(String[] args) {
        int status = 0;
        Communicator communicator = null;

        System.out.println("Client here");
        try {
            communicator = Util.initialize(args);
            ObjectPrx base = communicator.propertyToProxy("Printer.Proxy");
            PrinterPrx printer = PrinterPrx.checkedCast(base);
            System.out.println(printer);

            if (printer == null) {
                throw new Error("Invalid proxy");
            }
            printer.printString("Hello World!");
            System.out.println("xd");

        } catch (LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (communicator != null) { //clean
            try {
                communicator.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);

    }
}
