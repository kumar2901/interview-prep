package com.kumar.interview.prep.design_pattern.structural;

/**
 * allowing incompatible classes to work together by converting the interface of one class into another expected by the
 * clients. It is especially useful for integrating legacy code or third-party libraries into a new system.
 */
public class AdapterExample {
    public static void clientCode(Printer printer) {
        printer.print();
    }

    static void main() {
        PrinterAdapter printerAdapter = new PrinterAdapter();
        clientCode(printerAdapter);
    }
}

// Target Interface
interface Printer {
    void print();
}

// Adaptee
class LegacyPrinter {
    public void printDocument() {
        System.out.println("Legacy Printer is printing a document.");
    }
}

// Adapter
class PrinterAdapter implements Printer {
    private final LegacyPrinter legacyPrinter = new LegacyPrinter();

    @Override
    public void print() {
        legacyPrinter.printDocument();
    }
}
