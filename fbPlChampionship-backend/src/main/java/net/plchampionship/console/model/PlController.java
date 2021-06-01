package net.plchampionship.console.model;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("footballClub/")
public class PlController {
    ArrayList<PlayedMatches> randMatch = new ArrayList<>();
    ArrayList<PlayedMatches> searchMatch = new ArrayList<>();

    /*Getting premier league table details sorted by points details to server website*/
        @GetMapping("sortByPoints")
        public List<FootballClub> sortedByPoints(){

        PremierLeagueManager.foCbDetails.sort((c1, c2) -> {
            if (Integer.valueOf(c1.getNoOfClubPoints()).equals(c2.getNoOfClubPoints())) {
                return Integer.compare(c2.getGoalDifference(), c1.getGoalDifference());
            } else {
                return Integer.compare(c2.getNoOfClubPoints(), c1.getNoOfClubPoints());
            }
        });

        return PremierLeagueManager.foCbDetails;
        }

        /*Getting premier league table details sorted by goals details to server website*/
        @GetMapping("sortedByGoals")
        public List<FootballClub> sortedByGoals(){

        PremierLeagueManager.foCbDetails.sort((c1, c2) -> Integer.compare(c2.getNoOfGoalsScored(),
                c1.getNoOfGoalsScored()));

        return PremierLeagueManager.foCbDetails;
        }

        /*Getting premier league table details sorted by wins details to server website*/
        @GetMapping("sortByWins")
        public List<FootballClub> sortedByWins(){
            PremierLeagueManager.foCbDetails.sort((c1, c2) -> Integer.compare(c2.getNoOfWins(), c1.getNoOfWins()));
            return  PremierLeagueManager.foCbDetails;
        }

        /*Getting played match data sorted by date to server website*/
        @GetMapping("sortedByDate")
        public List<PlayedMatches> sortedByDate(){
            PremierLeagueManager.matchData.sort((s1, s2) -> LocalDate.parse(String.valueOf(s2.getMatchDate())).
                    compareTo(LocalDate.parse(String.valueOf(s1.getMatchDate()))));
            return PremierLeagueManager.matchData;
        }

        /*Getting random match data to server website*/
        @GetMapping("randomMatch")
        public List<PlayedMatches> randomMatchPlay(){
            randMatch.clear();
            System.out.println("\n.......................Playing a random match.......................");
            Random rand1 = new Random();
            /*Generating a random team 1 name*/
            int randomIndex1 = rand1.nextInt(PremierLeagueManager.foCbDetails.size());
            FootballClub team1 = PremierLeagueManager.foCbDetails.get(randomIndex1);
            String team1Name = team1.getNameOfClub();

            /*Generating random goals for team 1 from the premier league*/
            Random rand3 = new Random();
            int team1Goals = rand3.nextInt(6);

            while (true) {
                /*Generating a random team 2 name from the premier league*/
                Random rand2 = new Random();
                int randomIndex2 = rand2.nextInt(PremierLeagueManager.foCbDetails.size());
                if (randomIndex2 != randomIndex1) {
                    FootballClub team2 = PremierLeagueManager.foCbDetails.get(randomIndex2);
                    String team2Name = team2.getNameOfClub();

                    /*Generating a random day between 2010-2021*/
                    long startDate = LocalDate.of(2010, 1, 1).toEpochDay();
                    long endDate = LocalDate.of(2021, 12, 31).toEpochDay();
                    long randomDay = ThreadLocalRandom.current().nextLong(startDate, endDate);
                    LocalDate randDate = LocalDate.ofEpochDay(randomDay);

                    /*Generating random goals for team 2 from the premier league*/
                    Random rand4 = new Random();
                    int team2Goals = rand4.nextInt(6);


                    /*Adding random match data to the premier league*/
                    for (FootballClub details : PremierLeagueManager.foCbDetails) {
                        if (details.getNameOfClub().equals(team1Name)) {

                            details.setNoOfMatchesPlayed(details.getNoOfMatchesPlayed() + 1);

                            int team1PastGoalsAgainst = details.getGoalsAgainst();
                            details.setGoalsAgainst(team2Goals+team1PastGoalsAgainst);


                            int team1PastGoalCount = details.getNoOfGoalsScored();
                            details.setNoOfGoalsScored(team1Goals + team1PastGoalCount);


                            details.setGoalDifference((details.getNoOfGoalsScored()- details.getGoalsAgainst()));


                            if (team1Goals > team2Goals) {
                                details.setNoOfWins(details.getNoOfWins() + 1);
                                details.setNoOfClubPoints(details.getNoOfClubPoints()+3);
                                PlayedMatches matches = new PlayedMatches(randDate,team1Name,team2Name,team1Goals,team2Goals,team1Name);
                                PremierLeagueManager.matchData.add(matches);
                                randMatch.add(matches);

                            } else if (team1Goals < team2Goals) {
                                details.setNoOfDefeats(details.getNoOfDefeats() + 1);

                            } else {
                                details.setNoOfDraws(details.getNoOfDraws() + 1);
                                details.setNoOfClubPoints(details.getNoOfClubPoints()+1);
                                PlayedMatches matches = new PlayedMatches(randDate,team1Name,team2Name,team1Goals,team2Goals,"no one(Tie)");
                                PremierLeagueManager.matchData.add(matches);
                                randMatch.add(matches);
                            }


                        } else if (details.getNameOfClub().equals(team2Name)) {
                            details.setNoOfMatchesPlayed(details.getNoOfMatchesPlayed() + 1);

                            int team2PastGoalsAgainst = details.getGoalsAgainst();
                            details.setGoalsAgainst(team1Goals+team2PastGoalsAgainst);

                            int team2PastGoalCount = details.getNoOfGoalsScored();
                            details.setNoOfGoalsScored(team2Goals + team2PastGoalCount);

                            details.setGoalDifference((details.getNoOfGoalsScored() - details.getGoalsAgainst()));


                            if (team2Goals > team1Goals) {
                                details.setNoOfWins(details.getNoOfWins() + 1);
                                details.setNoOfClubPoints(details.getNoOfClubPoints()+3);
                                PlayedMatches matches = new PlayedMatches(randDate,team1Name,team2Name,team1Goals,team2Goals,team2Name);
                                PremierLeagueManager.matchData.add(matches);
                                randMatch.add(matches);

                            } else if (team2Goals < team1Goals) {
                                details.setNoOfDefeats(details.getNoOfDefeats() + 1);

                            } else {
                                details.setNoOfDraws(details.getNoOfDraws() + 1);
                                details.setNoOfClubPoints(details.getNoOfClubPoints()+1);
                            }


                        }

                    }
                    System.out.println("Random match successfully played\n");
                    break;

                }

            }
            /* Menu Display after a random match */
            System.out.println(".........................Displaying Main Menu..........................\n");
            System.out.println("Enter C to create a new football club");
            System.out.println("Enter D to delete an existing football club");
            System.out.println("Enter S to display statistics for a football club ");
            System.out.println("Enter P to display the premier league table");
            System.out.println("Enter A to add a played match");
            System.out.println("Enter G to display gui");
            System.out.println("Enter SV to save all data");
            System.out.println("Enter Q to quit the program \n");
        return randMatch;
        }

        /*Getting random match data to server website*/
        @GetMapping("randMthData")
        public List<PlayedMatches> randomMtcData(){
            return randMatch;
        }

        /*Getting date input from clientside and getting the relevant match details for the date*/
        @RequestMapping("/date")
        @PostMapping()
        public void sendDateValue(@RequestBody String date){
            searchMatch.clear();
            noSearch.clear();

                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse(date,dateTimeFormatter);
                    for (PlayedMatches matches : PremierLeagueManager.matchData) {
                        if (matches.getMatchDate().equals(localDate)) {
                            searchMatch.add(matches);
                        }

                    }

                }catch (DateTimeException e){
                    System.out.println("Invalid Date Input \n");
                }

        }

        /*Getting search match data to server website*/
        @GetMapping("searchDate")
        public List<PlayedMatches> searchDate(){
        return searchMatch;
        }

        /*Getting search match data not available message to server website*/
        ArrayList<String> noSearch = new ArrayList<>();
        @GetMapping("noSearchFound")
        public List<String> noSearchFound(){
            noSearch.clear();
            if (searchMatch.isEmpty()){
                noSearch.add("No match played in the  searched day");
            }
            return noSearch;
        }



}

