package com.jff.engine3d.model.java.components.settings;

import com.jff.engine3d.model.Controller;
import com.jff.engine3d.model.EngineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class Utils {


    public static Listener createVerifyIntegerListener() {
        Listener listener = new Listener() {
            public void handleEvent(Event e) {
                String string = e.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                for (int i = 0; i < chars.length; i++) {
                    if (!('0' <= chars[i] && chars[i] <= '9')) {
                        e.doit = false;
                        return;
                    }
                }
            }
        };

        return listener;
    }

    public static Listener createVerifyFloatListener() {
        Listener listener = new Listener() {
            public void handleEvent(Event e) {
                String string = e.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                for (int i = 0; i < chars.length; i++) {
                    if (!(('0' <= chars[i] && chars[i] <= '9') || chars[i] == '.')) {
                        e.doit = false;
                        return;
                    }
                }
            }
        };

        return listener;
    }

    public static void checkDegrees(int degrees) {

        if (degrees < 0 || degrees >= 360) {
            throw new IllegalArgumentException("Value not in range [0,360)");
        }


    }

    public static int retrieveInteger(Text editText) {
        String text = editText.getText();
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Input text is empty!");
        }
        int integer = Integer.parseInt(text);

        return integer;
    }

    public static void showMessage(String message) {
        Shell shell = Display.getCurrent().getActiveShell();
        MessageBox dialog =
                new MessageBox(shell, SWT.OK);
        dialog.setText("Message");
        dialog.setMessage(message);


        dialog.open();
    }

    public static float retrieveScale(Text editText) {
        String text = editText.getText();
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Input text is empty!");
        }
        float value = Float.parseFloat(text);

        return value;
    }

    public static Controller getController() {
        EngineManager engineManager = EngineManager.getInstance();
        Controller controller = engineManager.getController();

        return controller;
    }
}
