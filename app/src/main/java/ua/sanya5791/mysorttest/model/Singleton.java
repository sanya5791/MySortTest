package ua.sanya5791.mysorttest.model;

import java.util.ArrayList;

/**
 * Created by sanya on 25.05.2015.
 * place to persist all Workers.
 */
public class Singleton extends ArrayList<Worker> {
    private static Singleton instance;

    private Singleton(){
        // Constructor hidden because this is a singleton
    }

    public static void initInstance(){
        if(instance == null){
            instance=new Singleton();
//            instance
        }
    }
    public static Singleton getInstance(){
        return instance;
    }

}
