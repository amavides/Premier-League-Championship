package net.plchampionship.console;
import net.plchampionship.console.model.PremierLeagueManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;


@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200/football/")
public class ConsoleApplication {

    public static void main(String[] args) throws IOException {
        /*Loading data and running the spring boot application*/
        Desktop desktop = Desktop.getDesktop();
        SpringApplication.run(ConsoleApplication.class, args);
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        Scanner sc = new Scanner(System.in);
        premierLeagueManager.loadClubData();
        premierLeagueManager.loadMatchData();

        while (true) {

            /* Menu Display */
            System.out.println(".........................Displaying Main Menu..........................\n");
            System.out.println("Enter C to create a new football club");
            System.out.println("Enter D to delete an existing football club");
            System.out.println("Enter S to display statistics for a football club ");
            System.out.println("Enter P to display the premier league table");
            System.out.println("Enter A to add a played match");
            System.out.println("Enter G to display gui");
            System.out.println("Enter SV to save all data");
            System.out.println("Enter Q to quit the program");
            String input = sc.nextLine();

            /*Running user input calls*/
           try {

                switch (input) {

                    case "C":
                    case "c":
                        premierLeagueManager.createFootballClub();
                        break;

                    case "D":
                    case "d":
                        premierLeagueManager.deleteFootballClub();
                        break;

                    case "P":
                    case "p":
                        premierLeagueManager.premierLeagueTable();
                        break;

                    case "A":
                    case "a":
                        premierLeagueManager.addPlayedMatch();
                        break;

                    case "S":
                    case "s":
                        premierLeagueManager.displayStatistics();
                        break;

                    case "SV":
                    case "sv":
                        premierLeagueManager.saveData();
                        break;

                    case "Q":
                    case "q":
                        premierLeagueManager.quitProgram();
                        break;

                    case "G":
                    case "g":
                        try {
                            URI url = new URI("http://localhost:4200/football");
                            desktop.browse(url);
                        }catch (URISyntaxException | IOException e){
                            System.out.println();
                        }
                        break;

                    default:
                        System.out.println(".................Invalid Input redirecting back to menu.................\n");
                }

            } catch (Exception e) {
               System.out.println("Invalid input please check again");
               sc.nextLine();
            }
        }
    }
}


