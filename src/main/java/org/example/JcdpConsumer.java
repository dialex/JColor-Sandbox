package org.example;

import com.diogonunes.jcdp.bw.Printer;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

public class JcdpConsumer {
    public static void main(String[] args) {
        // =============================
        // Example of a terminal Printer
        // =============================

        Printer p = new Printer.Builder(Printer.Types.TERM).build();
        p.println(p);
        p.println("This is a normal message.");
        p.errorPrintln("This is an error message.");
        p.debugPrintln("This debug message is always printed.");
        p = new Printer.Builder(Printer.Types.TERM).level(1).timestamping(false).build();
        p.println(p);
        p.debugPrintln("This is printed because its level is <= 1", 1);
        p.debugPrintln("This isn't printed because its level is > 1", 2);
        p.setLevel(2);
        p.debugPrintln("Now this is printed because its level is <= 2", 2);

        // =======================================================
        // Example of a Colored terminal Printer (WINDOWS or UNIX)
        // =======================================================
        System.out.println();

        //setting a format for all messages
        ColoredPrinter cp = new ColoredPrinter.Builder(0, false)
                .foreground(FColor.WHITE).background(BColor.BLUE)   //setting format
                .build();
        cp.println(cp);
        cp.println("This printer will always format text with WHITE font on BLUE background.");
        cp.setAttribute(Attribute.REVERSE);
        cp.println("From now on, that format is reversed.");
        System.out.println("ColoredPrinters do not affect System.* format.");
        cp.print("Even if");
        System.out.print(" you mix ");
        cp.println("the two.");

        //using multiple printers for diff purposes
        ColoredPrinter cpWarn = new ColoredPrinter.Builder(1, true)
                .foreground(FColor.RED)
                .build();
        ColoredPrinter cpInfo = new ColoredPrinter.Builder(1, true)
                .foreground(FColor.CYAN)
                .build();
        cpWarn.println("This printer displays timestamps and warning messages.");
        cpInfo.println("And this printer can be used for info messages.");

        //overriding format per message
        cp = new ColoredPrinter.Builder(1, false)
                .build();
        cp.print("This example used JCDP 3.0.2   ");
        cp.print("\tMADE ", Attribute.BOLD, FColor.YELLOW, BColor.GREEN);
        cp.println("IN PORTUGAL", Attribute.BOLD, FColor.YELLOW, BColor.RED);
        cp.println("I hope you find it useful ;)");
    }
}
