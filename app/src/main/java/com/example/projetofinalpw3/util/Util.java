package com.example.projetofinalpw3.util;

import java.util.Random;

public class Util {

    public static Integer geraId(){
        Random random = new Random();
        return random.nextInt(1000);
    }
}
