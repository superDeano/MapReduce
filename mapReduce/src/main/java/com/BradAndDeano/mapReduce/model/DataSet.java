package com.BradAndDeano.mapReduce.model;


import org.springframework.data.annotation.Id;

public class DataSet {
    @Id
    private long id;
    private int cpuUtilization;
    private int networkIn;
    private int networkOut;
    private float memoryUtilization;

    public DataSet(int cpuUtilization, int networkIn, int networkOut, float memoryUtilization) {
        this.cpuUtilization = cpuUtilization;
        this.networkIn = networkIn;
        this.networkOut = networkOut;
        this.memoryUtilization = memoryUtilization;
    }

    public DataSet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(int cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public int getNetworkIn() {
        return networkIn;
    }

    public void setNetworkIn(int networkIn) {
        this.networkIn = networkIn;
    }

    public int getNetworkOut() {
        return networkOut;
    }

    public void setNetworkOut(int networkOut) {
        this.networkOut = networkOut;
    }

    public float getMemoryUtilization() {
        return memoryUtilization;
    }

    public void setMemoryUtilization(float memoryUtilization) {
        this.memoryUtilization = memoryUtilization;
    }
}
