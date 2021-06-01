package net.plchampionship.console.model;

class FootballClub extends SportsClub {
    private int noOfWins;
    private int noOfDefeats;
    private int noOfDraws;
    private int noOfGoalsScored;
    private int noOfClubPoints;
    private int noOfMatchesPlayed;
    private int goalsAgainst;
    private int goalDifference;


    public FootballClub(String nameOfClub,String locationOfClub,String typeOfClub,int noOfWins, int noOfDraws,int noOfDefeats, int noOfGoalsScored, int noOfClubPoints, int noOfMatchesPlayed, int goalsAgainst, int goalDifference) {
        super(nameOfClub,locationOfClub,typeOfClub);
        this.noOfWins = noOfWins;
        this.noOfDraws = noOfDraws;
        this.noOfDefeats = noOfDefeats;
        this.noOfGoalsScored = noOfGoalsScored;
        this.noOfClubPoints = noOfClubPoints;
        this.noOfMatchesPlayed = noOfMatchesPlayed;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;

    }

    public int getNoOfWins() {
        return noOfWins;
    }

    public void setNoOfWins(int noOfWins) {
        this.noOfWins = noOfWins;
    }

    public int getNoOfDraws() {
        return noOfDraws;
    }

    public void setNoOfDraws(int noOfDraws) {
        this.noOfDraws = noOfDraws;
    }

    public int getNoOfDefeats() {
        return noOfDefeats;
    }

    public void setNoOfDefeats(int noOfDefeats) {
        this.noOfDefeats = noOfDefeats;
    }

    public int getNoOfGoalsScored() {
        return noOfGoalsScored;
    }

    public void setNoOfGoalsScored(int noOfGoalsScored) {
        this.noOfGoalsScored = noOfGoalsScored;
    }

    public int getNoOfClubPoints() {
        return noOfClubPoints;
    }

    public void setNoOfClubPoints(int noOfClubPoints) {
        this.noOfClubPoints = noOfClubPoints;
    }

    public int getNoOfMatchesPlayed() {
        return noOfMatchesPlayed;
    }

    public void setNoOfMatchesPlayed(int noOfMatchesPlayed) {
        this.noOfMatchesPlayed = noOfMatchesPlayed;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }
}
