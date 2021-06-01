package net.plchampionship.console.model;
import org.springframework.stereotype.Component;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component

public class PremierLeagueManager implements LeagueManger {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<FootballClub> foCbDetails = new ArrayList<>();
    static ArrayList<String> nameCol = new ArrayList<>();
    static ArrayList<LocalDate> dateList = new ArrayList<>();
    static ArrayList<PlayedMatches> matchData = new ArrayList<>();

    /*Creating a football and adding it to the Premier League*/
    public void createFootballClub() {
        System.out.println(".......................Creating a foot ball club.......................\n");


        if (foCbDetails.size()>=20) {
            System.out.println("A maximum of 20 clubs are allowed to compete in the premier league cannot add more \n");
        }

    else {
            while (true) {
                System.out.println("Enter name of sports club");
                String name = sc.nextLine();
                for (FootballClub cbNames : foCbDetails) {
                    nameCol.add(cbNames.getNameOfClub());
                }

                try {
                    /*Arranging the input to one specific style as first letter capital in all words*/
                    name = Arrays.stream(name.split("\\s+"))
                            .map(c -> c.substring(0, 1).toUpperCase() + c.substring(1).toLowerCase())
                            .collect(Collectors.joining(" "));

                    /*Checking whether the name input is already taken by a existing club*/
                    if (nameCol.contains(name)) {
                        System.out.println("Club name already exists \n");

                    } else {
                        System.out.println("Enter location of sports club");
                        String location = sc.nextLine();
                        location = Arrays.stream(location.split("\\s+"))
                                .map(c -> c.substring(0, 1).toUpperCase() + c.substring(1).toLowerCase())
                                .collect(Collectors.joining(" "));

                        System.out.println("Enter type of sports club");
                        String type = sc.nextLine();
                        type = Arrays.stream(type.split("\\s+"))
                                .map(c -> c.substring(0, 1).toUpperCase() + c.substring(1).toLowerCase())
                                .collect(Collectors.joining(" "));

                        /*Adding a football club to the Premier League*/
                        if (type.equals("Football")) {
                            foCbDetails.add(new FootballClub(name, location, type, 0, 0, 0, 0, 0, 0, 0, 0));
                            System.out.println("Football club " + name + " successfully added to premier league \n");
                        } else {
                            System.out.println("The created club is not a football club please add a football club");
                        }
                        break;
                    }

                } catch (Exception ie) {
                    System.out.println("Invalid Input");
                }
            }
        }
    }

    /*Deleting a football club from the Premier League*/
    public  void deleteFootballClub() {
        System.out.println(".......................Deleting a foot ball club.......................\n");
        while (true) {
            System.out.println("Enter name of the sports club you want to delete");
            String delete = sc.nextLine();

            try {
                /*Arranging the input to one specific style as first letter capital in all words*/
                delete = Arrays.stream(delete.split("\\s+"))
                        .map(c -> c.substring(0, 1).toUpperCase() + c.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));

                /*Deleting a club if the club is available in the premier league club list*/
                for (FootballClub ob : foCbDetails) {
                    if (ob.getNameOfClub().isEmpty()) {
                        System.out.println("\nNo clubs available to delete");
                    } else {
                        String name = ob.getNameOfClub();
                        if (name.equals(delete)) {
                            foCbDetails.remove(ob);
                            System.out.println("Sports club "+delete+ " successfully deleted \n");
                            break;
                        } else if (ob.equals(foCbDetails.get(foCbDetails.size() - 1))) {
                            System.out.println("Invalid Sports Club Name\n");
                            break;
                        }


                    }

                }
                break;

            }catch (Exception e){
                System.out.println("Invalid Input");

            }
        }
    }

    /*Displaying statistics of football club*/
    public  void displayStatistics(){
        System.out.println("................Displaying Statistics of football clubs................\n");
        System.out.println("Enter name of club to show statistics of that club");
        String name = sc.nextLine();

            try{
                /*Arranging the input to one specific style as first letter capital in all words*/
                name = Arrays.stream(name.split("\\s+"))
                        .map(c -> c.substring(0, 1).toUpperCase() + c.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));

                /*Displaying statistics if the club is available in them premier league*/
                nameCol.clear();
                for (FootballClub cbNames : foCbDetails) {
                    nameCol.add(cbNames.getNameOfClub());
                }
                if (nameCol.contains(name)) {
                    for (FootballClub fbDetails : foCbDetails) {
                        if (fbDetails.getNameOfClub().equals(name)) {
                            System.out.println("Club Name: " + fbDetails.getNameOfClub());
                            System.out.println("Club Location: " + fbDetails.getLocationOfClub());
                            System.out.println("Club Type: " + fbDetails.getTypeOfClub());
                            System.out.println("No of goals scored: " + fbDetails.getNoOfGoalsScored());
                            System.out.println("No of matches played: " + fbDetails.getNoOfMatchesPlayed());
                            System.out.println("No of wins: " + fbDetails.getNoOfWins());
                            System.out.println("No of draws: " + fbDetails.getNoOfDraws());
                            System.out.println("No of defeats: " + fbDetails.getNoOfDefeats());
                            System.out.println("Goals Against: " + fbDetails.getGoalsAgainst());
                            System.out.println("Goal Difference " + fbDetails.getGoalDifference());
                            System.out.println("No of club points: " + fbDetails.getNoOfClubPoints() + "\n");
                        }
                    }
                }else {
                    System.out.println("No club called "+name+" is available in the premier league\n");
                }
            }catch (Exception e){
                System.out.println("Invalid Input");
            }
    }


    public  void premierLeagueTable(){
        System.out.println("..................Displaying the premier league table..................\n");

        int count;

        /*Sorting football clubs according to club points is the points are same best goal difference is considered*/
        foCbDetails.sort((c1, c2) -> {
            if (Integer.valueOf(c1.getNoOfClubPoints()).equals(c2.getNoOfClubPoints())) {
                return Integer.compare(c2.getGoalDifference(), c1.getGoalDifference());
            } else {
                return Integer.compare(c2.getNoOfClubPoints(), c1.getNoOfClubPoints());
            }
        });

        /*Displaying premier league table in console*/
        for (count = 0; count <foCbDetails.size(); count++){
            String name = foCbDetails.get(count).getNameOfClub();

            System.out.println("Club "+name+"        "+"MP- "+foCbDetails.get(count).getNoOfMatchesPlayed()+"    "+"W- "+foCbDetails.get(count).getNoOfWins()+"    "
                    +"D- "+foCbDetails.get(count).getNoOfDraws()+"    "+"L- "+foCbDetails.get(count).getNoOfDefeats()+
                    "    "+"Pts- "+foCbDetails.get(count).getNoOfClubPoints()+"    "+"GF- "+foCbDetails.get(count).getNoOfGoalsScored()+"    "
                    +"GA- "+foCbDetails.get(count).getGoalsAgainst()+"    "+"GD "+foCbDetails.get(count).getGoalDifference()+"\n");
        }
    }

    /*Adding a played match to the premier league*/
    public  void addPlayedMatch() {

        System.out.println(".........................Adding a played match.........................\n");

        for (FootballClub cbNames :foCbDetails ){
            nameCol.add(cbNames.getNameOfClub());
        }

        while (true) {
            System.out.println("Enter name of first team");
            String team1 = sc.nextLine();
            System.out.println("Enter name of second team");
            String team2 = sc.nextLine();

            try {
                /*Arranging the input to one specific style as first letter capital in all words*/
                team1 = Arrays.stream(team1.split("\\s+"))
                        .map(c -> c.substring(0, 1).toUpperCase() + c.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));
                team2 = Arrays.stream(team2.split("\\s+"))
                        .map(c -> c.substring(0, 1).toUpperCase() + c.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));

                /*Checking if the entered team names are available in the premier league to add a match*/
                if (!nameCol.contains(team1)) {
                    System.out.println("There is no football club named " + team1 + " in the premier league \n");
                    continue;
                }

                else if (!nameCol.contains(team2)){
                    System.out.println("There is no football club named " + team2 + " in the premier league \n");
                    continue;
                }

                else if(team1.equals(team2)){
                    System.out.println("A match must be played between two teams");
                    continue;
                }


            } catch (Exception e) {
                System.out.println("Invalid Input");
                break;

            }


            System.out.println("Enter the year of the played match");
            int year = sc.nextInt();
            System.out.println("Enter the month of the played match");
            int month = sc.nextInt();
            System.out.println("Enter the day of the match played");
            int day = sc.nextInt();
            LocalDate date = LocalDate.of(year, month, day);
            dateList.add(date);

            System.out.println("Enter the goals scored by " + team1);
            int team1Goals = sc.nextInt();
            System.out.println("Enter the goals scored by " + team2);
            int team2Goals = sc.nextInt();
            System.out.println("Enter no of club points added to " + team1);
            int team1ClubPoints = sc.nextInt();
            System.out.println("Enter no of club points added to " + team2);
            int team2ClubPoints = sc.nextInt();
            sc.nextLine();

            /*Adding the played match data to the premier league*/
            for (FootballClub fbDetails : foCbDetails) {
                if (fbDetails.getNameOfClub().equals(team1)) {
                    int team1PastGoalCount = fbDetails.getNoOfGoalsScored();
                    fbDetails.setNoOfGoalsScored(team1Goals + team1PastGoalCount);
                    System.out.println("No of goals scored by " + team1 + " added successfully ");

                    int team1PastGoalsAgainst = fbDetails.getGoalsAgainst();
                    fbDetails.setGoalsAgainst(team2Goals+team1PastGoalsAgainst);
                    System.out.println("Goals against count for "+team1+" added successfully");

                    fbDetails.setGoalDifference((fbDetails.getNoOfGoalsScored()- fbDetails.getGoalsAgainst()));
                    System.out.println("Goal Difference for "+team1+" added successfully");


                    int team1PastClubPoints = fbDetails.getNoOfClubPoints();
                    fbDetails.setNoOfClubPoints(team1ClubPoints + team1PastClubPoints);
                    System.out.println("No of club points gained by " + team1 + " added successfully ");

                    int playedMatches = fbDetails.getNoOfMatchesPlayed();
                    fbDetails.setNoOfMatchesPlayed(playedMatches + 1);
                    System.out.println("Successfully added 1 played match to " + team1);

                    if (team1Goals > team2Goals) {
                        int pastWins = fbDetails.getNoOfWins();
                        fbDetails.setNoOfWins(pastWins + 1);
                        System.out.println("Successfully added 1 win to " + team1 + "\n");
                        PlayedMatches matches = new PlayedMatches(date,team1,team2,team1Goals,team2Goals,team1);
                        matchData.add(matches);

                    } else if (team1Goals < team2Goals) {
                        int pastDefeats = fbDetails.getNoOfDefeats();
                        fbDetails.setNoOfDefeats(pastDefeats + 1);
                        System.out.println("Successfully added 1 defeat to " + team1 + "\n");

                    } else {
                        int pastDraws = fbDetails.getNoOfDraws();
                        fbDetails.setNoOfDraws(pastDraws + 1);
                        System.out.println("Successfully added 1 draw to " + team1 + "\n");
                        PlayedMatches matches = new PlayedMatches(date,team1,team2,team1Goals,team2Goals,"Tie");
                        matchData.add(matches);
                    }


                } else if (fbDetails.getNameOfClub().equals(team2)) {
                    int team2PastGoalCount = fbDetails.getNoOfGoalsScored();
                    fbDetails.setNoOfGoalsScored(team2Goals + team2PastGoalCount);
                    System.out.println("No of goals scored by " + team2 + " added successfully");

                    int team2PastGoalsAgainst = fbDetails.getGoalsAgainst();
                    fbDetails.setGoalsAgainst(team1Goals+team2PastGoalsAgainst);
                    System.out.println("Goals against count for "+team2+" added successfully");

                    fbDetails.setGoalDifference((fbDetails.getNoOfGoalsScored() - fbDetails.getGoalsAgainst()));
                    System.out.println("Goal Difference for "+team2+" added successfully");

                    int team2PastClubPoints = fbDetails.getNoOfClubPoints();
                    fbDetails.setNoOfClubPoints(team2ClubPoints + team2PastClubPoints);
                    System.out.println("No of club points gained by " + team2 + " added successfully");

                    int playedMatches = fbDetails.getNoOfMatchesPlayed();
                    fbDetails.setNoOfMatchesPlayed(playedMatches + 1);
                    System.out.println("Successfully added one played match to " + team2);

                    if (team2Goals > team1Goals) {
                        int pastWins = fbDetails.getNoOfWins();
                        fbDetails.setNoOfWins(pastWins + 1);
                        System.out.println("Successfully added one win to " + team2 + "\n");
                        PlayedMatches matches = new PlayedMatches(date,team1,team2,team1Goals,team2Goals,team2);
                        matchData.add(matches);

                    } else if (team2Goals < team1Goals) {
                        int pastDefeats = fbDetails.getNoOfDefeats();
                        fbDetails.setNoOfDefeats(pastDefeats + 1);
                        System.out.println("Successfully added one defeat " + team2 + "\n");
                    } else {
                        int pastDraws = fbDetails.getNoOfDraws();
                        fbDetails.setNoOfDraws(pastDraws + 1);
                        System.out.println("Successfully added one draw to " + team2 + "\n");
                    }
                }
            }
            break;

        }

    }

    /*Saving all user input data*/
    public  void saveData(){
        System.out.println("............................Saving all data............................\n");
        try(FileWriter fileWriter = new FileWriter("PremierLeague.txt")){

            for (FootballClub save : foCbDetails){
                fileWriter.write("Club Name:"+save.getNameOfClub());
                fileWriter.write("\r\n");

                fileWriter.write("Club Location:"+save.getLocationOfClub());
                fileWriter.write("\r\n");

                fileWriter.write("Club Type:"+save.getTypeOfClub());
                fileWriter.write("\r\n");

                fileWriter.write("No of matches played:"+save.getNoOfMatchesPlayed());
                fileWriter.write("\r\n");

                fileWriter.write("No of wins:"+save.getNoOfWins());
                fileWriter.write("\r\n");

                fileWriter.write("No of draws:"+save.getNoOfDraws());
                fileWriter.write("\r\n");

                fileWriter.write("No of defeats:"+save.getNoOfDefeats());
                fileWriter.write("\r\n");

                fileWriter.write("No of goals scored:"+save.getNoOfGoalsScored());
                fileWriter.write("\r\n");

                fileWriter.write("No of goals against:"+save.getGoalsAgainst());
                fileWriter.write("\r\n");

                fileWriter.write("The goal difference:"+save.getGoalDifference());
                fileWriter.write("\r\n");

                fileWriter.write("No of club points:"+save.getNoOfClubPoints());
                fileWriter.write("\r\n");
                fileWriter.write("\r\n");
                fileWriter.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter fileWriter2 = new FileWriter("MatchData.txt")) {
            for (PlayedMatches matches : matchData){

                fileWriter2.write("Match Date:"+matches.getMatchDate());
                fileWriter2.write("\r\n");

                fileWriter2.write("Team 1 name:"+matches.getTm1Name());
                fileWriter2.write("\r\n");

                fileWriter2.write("Team 2 name:"+matches.getTm2name());
                fileWriter2.write("\r\n");

                fileWriter2.write("Team 1 goals:"+matches.getTm1goals());
                fileWriter2.write("\r\n");

                fileWriter2.write("Team 2 goals:"+matches.getTm2goals());
                fileWriter2.write("\r\n");

                fileWriter2.write("Winner:"+matches.getWinners());
                fileWriter2.write("\r\n");
                fileWriter2.write("\r\n");
                fileWriter2.write("\r\n");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Saving Successful \n");
    }

    /*Load football club data*/
    public  void loadClubData() throws IOException {
        System.out.println(".........................Loading previous data.........................\n");
        FileReader fileReader = new FileReader("PremierLeague.txt");
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);

        HashMap<String, String> temporaryStore = new HashMap<>();

        int count1 = 1;
        int count2 = 2;
        int count3 = 3;
        int count4 = 4;
        int count5 = 5;
        int count6 = 6;
        int count7 = 7;
        int count8 = 8;
        int count9 = 9;
        int count10 = 10;
        int count11 = 11;

        for (String line = lineNumberReader.readLine(); line != null; line = lineNumberReader.readLine()) {

            if (lineNumberReader.getLineNumber() == count1) {
                String name = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Name", name);
            } else if (lineNumberReader.getLineNumber() == count2) {
                String location = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Location", location);
            } else if (lineNumberReader.getLineNumber() == count3) {
                String type = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Type", type);
            } else if (lineNumberReader.getLineNumber() == count4) {
                String mtPlayed = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Played", mtPlayed);
            } else if (lineNumberReader.getLineNumber() == count5) {
                String wins = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Wins", wins);
            } else if (lineNumberReader.getLineNumber() == count6) {
                String draws = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Draws", draws);
            } else if (lineNumberReader.getLineNumber() == count7) {
                String defeats = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Defeats", defeats);
            } else if (lineNumberReader.getLineNumber() == count8) {
                String goalsScored = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Goals Scored", goalsScored);
            }else if(lineNumberReader.getLineNumber() == count9){
                String goalsAgainst = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Goals Against", goalsAgainst);
            }else if (lineNumberReader.getLineNumber() == count10){
                String goalDifference = line.substring(line.lastIndexOf(":") + 1);
                temporaryStore.put("Goal Difference",goalDifference);
            } else if (lineNumberReader.getLineNumber() == count11) {
                String points = line.substring(line.lastIndexOf(":") + 1);

                String name = temporaryStore.get("Name");
                String location = temporaryStore.get("Location");
                String type = temporaryStore.get("Type");
                int mtPlayed = Integer.parseInt(temporaryStore.get("Played"));
                int wins = Integer.parseInt(temporaryStore.get("Wins"));
                int draws = Integer.parseInt(temporaryStore.get("Draws"));
                int defeats = Integer.parseInt(temporaryStore.get("Defeats"));
                int goalsScored = Integer.parseInt(temporaryStore.get("Goals Scored"));
                int gLAgainst = Integer.parseInt(temporaryStore.get("Goals Against"));
                int gDifference = Integer.parseInt(temporaryStore.get("Goal Difference"));
                int clubPoints = Integer.parseInt(points);


                FootballClub footballClub = new FootballClub(name, location, type, wins, draws, defeats, goalsScored, clubPoints, mtPlayed,gLAgainst,gDifference);
                foCbDetails.add(footballClub);

                count1 = count1 + 13;
                count2 = count2 + 13;
                count3 = count3 + 13;
                count4 = count4 + 13;
                count5 = count5 + 13;
                count6 = count6 + 13;
                count7 = count7 + 13;
                count8 = count8 + 13;
                count9 = count9 + 13;
                count10 = count10 +13;
                count11 = count11 +13;

            }

        }
        System.out.println("Club Data loaded Successfully \n");
    }

    /*Loading played match data*/
    public void loadMatchData() throws IOException {

        int count1 = 1;
        int count2 = 2;
        int count3 = 3;
        int count4 = 4;
        int count5 = 5;
        int count6 = 6;

        HashMap<String, String> matchStore = new HashMap<>();

        FileReader fileReader = new FileReader("MatchData.txt");
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);


        for (String line = lineNumberReader.readLine(); line != null; line = lineNumberReader.readLine()) {

            if (lineNumberReader.getLineNumber() == count1) {
                String matchDate = line.substring(line.lastIndexOf(":") + 1);
                matchStore.put("matchDate", matchDate);

            }

            else if (lineNumberReader.getLineNumber() == count2) {
                String team1Name = line.substring(line.lastIndexOf(":") + 1);
                matchStore.put("team1Name", team1Name);

            } else if (lineNumberReader.getLineNumber() == count3) {
                String team2Name = line.substring(line.lastIndexOf(":") + 1);
                matchStore.put("team2Name", team2Name);

            } else if (lineNumberReader.getLineNumber() == count4) {
                String team1Goals = line.substring(line.lastIndexOf(":")+1);
                matchStore.put("team1Goals", team1Goals);

            } else if (lineNumberReader.getLineNumber() == count5) {
                String team2Goals = line.substring(line.lastIndexOf(":") + 1);
                matchStore.put("team2Goals", team2Goals);

            }else if (lineNumberReader.getLineNumber() == count6) {
                String winners = line.substring(line.lastIndexOf(":") + 1);
                matchStore.put("winners", winners);

                LocalDate matchDate = LocalDate.parse(matchStore.get("matchDate"));
                String team1Name = matchStore.get("team1Name");
                String team2Name = matchStore.get("team2Name");
                int tm1Goals = Integer.parseInt(matchStore.get("team1Goals"));
                int tm2Goals = Integer.parseInt(matchStore.get("team2Goals"));

                PlayedMatches playedMatches = new PlayedMatches(matchDate,team1Name,team2Name,tm1Goals,tm2Goals,winners);
                matchData.add(playedMatches);

                count1 = count1 + 8;
                count2 = count2 + 8;
                count3 = count3 + 8;
                count4 = count4 + 8;
                count5 = count5 + 8;
                count6 = count6 + 8;
            }

        }
        System.out.println("Match Data loaded successfully\n");
    }

    /*Quiting the program*/
    public  void quitProgram(){
        System.out.println("............................Quitting Program............................\n");
        System.exit(0);
    }

}
