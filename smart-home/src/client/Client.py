import Ice
import sys
import traceback

import Demo


if __name__ == '__main__':
    status = 0
    ic = None
    try:
        ic = Ice.initialize(sys.argv)
        base = ic.stringToProxy("SimplePrinter:default -p 10000")
        printer = Demo.PrinterPrx.checkedCast(base)
        if not printer:
            raise RuntimeError("Invalid proxy")

        printer.printString("Hello World!")
    except:
        traceback.print_exc()
        status = 1

    if ic:
        # Clean up
        try:
            ic.destroy()
        except:
            traceback.print_exc()
            status = 1

    sys.exit(status)
