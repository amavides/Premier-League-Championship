package net.plchampionship.console.model;

import java.io.IOException;

interface LeagueManger {

    void createFootballClub();
    void deleteFootballClub();
    void premierLeagueTable();
    void addPlayedMatch();
    void displayStatistics();
    void saveData();
    void loadClubData() throws IOException;
    void loadMatchData() throws IOException;
    void quitProgram();

}
