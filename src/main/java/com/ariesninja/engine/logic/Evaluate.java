package com.ariesninja.engine.logic;

import com.ariesninja.engine.utils.Frame;

public class Evaluate {

    public enum Method {
        GIVE_UP, STREAK, SURVIVE, RANDOM
    }

    public static Method checkFrame(Frame frame) {
        return Method.SURVIVE; // Only this for now
    }

}
