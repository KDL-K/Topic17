package com.company;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private String name;
    private long timeMovingThroughTunnelInSec;
    ReentrantLock rLock;

    public Tunnel(String name,long timeMovingThroughTunnelInSec, ReentrantLock rLock){
        this.name=name;
        this.timeMovingThroughTunnelInSec=timeMovingThroughTunnelInSec;
        this.rLock=rLock;
    }

    public String getName(){
        return name;
    }


    public boolean tryGoThroughTunnel(long timeWaitingFreeTunnel, String nameOfTrain){
        System.out.println(nameOfTrain+" tries to go through "+name);
        boolean canGo=false;
        try {
            canGo=rLock.tryLock(timeWaitingFreeTunnel, TimeUnit.SECONDS);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        if(!canGo){
            return false;
        }
        Instant endOfGoing=Instant.now().plusMillis(timeMovingThroughTunnelInSec*1000);
        try {
            System.out.println(nameOfTrain+" is going through "+name);
            Thread.sleep(timeMovingThroughTunnelInSec*1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println(nameOfTrain+" has gone out of "+name);
        rLock.unlock();
        return true;
    }

}
