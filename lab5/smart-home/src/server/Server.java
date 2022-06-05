package server;

import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;


public class Server {
    public static void main(String[] args) {

        try (com.zeroc.Ice.Communicator communicator = Util.initialize(args)) {

            ObjectAdapter adapter = communicator.createObjectAdapter("SmartHomeAdapter1"); // configuration in config file

            BulbI bathroomBulbServant = new BulbI("bathroom");
            PrinterI colorPrinterServant = new ColorPrinter("home office");

            adapter.add(bathroomBulbServant, new Identity("bathroomBulb", "Bulb"));
            adapter.add(colorPrinterServant, new Identity("homeOfficeColorPrinter", "Printer"));

            adapter.activate();

            communicator.waitForShutdown();
        }
    }
}
