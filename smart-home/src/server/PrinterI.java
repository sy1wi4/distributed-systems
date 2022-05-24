package server;

import Demo.Printer;
import com.zeroc.Ice.Current;

public class PrinterI implements Printer {
    @Override
    public void printString(String s, Current current) {
        System.out.println("Oto string: " + s);
    }
}
