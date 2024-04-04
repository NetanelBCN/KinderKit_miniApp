package dev.netanelbcn.kinderkit.Models;

public class myDate {
    private int day;
    private int year;
    private int month;

    public myDate(int day, int year, int month) {
        this.day = day;
        this.year = year;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public myDate setDay(int day) {
        this.day = day;
        return this;
    }

    public int getYear() {
        return year;
    }

    public myDate setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public myDate setMonth(int month) {
        this.month = month;
        return this;
    }
    public int compareTo(myDate date){
        if(this.year>date.getYear()){
            return 1;
        }
        else if(this.year<date.getYear()){
            return -1;
        }
        else{
            if(this.month>date.getMonth()){
                return 1;
            }
            else if(this.month<date.getMonth()){
                return -1;
            }
            else{
                if(this.day>date.getDay()){
                    return 1;
                }
                else if(this.day<date.getDay()){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        return getDay()+"/"+getMonth()+"/"+getYear();
    }
}
