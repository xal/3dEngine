package com.jff.engine3d.model.java.components.settings;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class SWTUtils {


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
}
