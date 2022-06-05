package server;

import SmartHome.Printer;
import com.zeroc.Ice.Current;

public abstract class PrinterI extends DeviceI implements Printer {
    public PrinterI(String location) {
        super(location);
    }

    @Override
    public void print(String message, Current current) {
        System.out.println("Printing document: " + message);
    }
}
