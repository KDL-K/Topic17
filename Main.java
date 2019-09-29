package com.company;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {
        ReentrantLock rLock1=new ReentrantLock();
        ReentrantLock rLock2=new ReentrantLock(true);

        Tunnel tunnel1=new Tunnel("Tunnel 1",10,rLock1);
        Tunnel tunnel2=new Tunnel("Tunnel 2",5,rLock2);

        Train train1=new Train("Train 1.1",10,tunnel1,tunnel2);
        new Thread(train1).start();
        Train train2=new Train("Train 1.2",5,tunnel1,tunnel2);
        new Thread(train2).start();
        Train train3=new Train("Train 1.3",3,tunnel1,tunnel2);
        new Thread(train3).start();
        Train train4=new Train("Train 1.4",7,tunnel1,tunnel2);
        new Thread(train4).start();
        Train train5=new Train("Train 1.5",5,tunnel1,tunnel2);
        new Thread(train5).start();


        Train train6=new Train("Train 2.1",7,tunnel2,tunnel1);
        new Thread(train6).start();
        Train train7=new Train("Train 2.2",12,tunnel2,tunnel1);
        new Thread(train7).start();
        Train train8=new Train("Train 2.3",10,tunnel2,tunnel1);
        new Thread(train8).start();

    }
}
