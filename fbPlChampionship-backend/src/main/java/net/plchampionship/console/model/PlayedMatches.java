package net.plchampionship.console.model;

import java.time.LocalDate;


public class PlayedMatches  {

    private LocalDate matchDate;
    private String tm1Name;
    private String tm2name;
    private int tm1goals;
    private int tm2goals;
    private String winners;

    public PlayedMatches(LocalDate matchDate, String tm1Name, String tm2name, int tm1goals, int tm2goals, String winners) {
        this.matchDate = matchDate;
        this.tm1Name = tm1Name;
        this.tm2name = tm2name;
        this.tm1goals = tm1goals;
        this.tm2goals = tm2goals;
        this.winners = winners;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public String getTm1Name() {
        return tm1Name;
    }

    public void setTm1Name(String tm1Name) {
        this.tm1Name = tm1Name;
    }

    public String getTm2name() {
        return tm2name;
    }

    public void setTm2name(String tm2name) {
        this.tm2name = tm2name;
    }

    public int getTm1goals() {
        return tm1goals;
    }

    public void setTm1goals(int tm1goals) {
        this.tm1goals = tm1goals;
    }

    public int getTm2goals() {
        return tm2goals;
    }

    public void setTm2goals(int tm2goals) {
        this.tm2goals = tm2goals;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }
}

