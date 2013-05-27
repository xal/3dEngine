package com.jff.engine3d.model.java.main;

import com.jff.engine3d.model.java.components.MainWindow;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {


    public static void main(String[] args) {

        final Display display = new Display();
        final Shell shell = new Shell(display);

        shell.setSize(1100, 700);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);

        final MainWindow mainWindow = new MainWindow(shell);

        shell.open();


        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }
}
