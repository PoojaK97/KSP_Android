package com.project.coders.ksp;

public class Assignment {
    private String Start_Time;
    private String End_Time;
    private String Beat;

    public Assignment(String a, String b, String c) {
        Start_Time = a;
        End_Time = b;
        Beat = c;
    }

    public Assignment() {
        Start_Time = "";
        End_Time = "";
        Beat = "";
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(String s) {
        this.Start_Time = s;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String s) {
        this.End_Time = s;
    }

    public String getBeat() {
        return Beat;
    }

    public void setBeat(String s) {
        this.Beat = s;
    }
}
