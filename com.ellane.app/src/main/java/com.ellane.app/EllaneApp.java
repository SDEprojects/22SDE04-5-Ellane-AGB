package com.ellane.app;

import com.ellane.model.LocationsAndDirections;
import com.ellane.model.Json;
import com.ellane.model.Items;
import com.ellane.model.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ellane.model.PlayerLocationsAndItems;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class EllaneApp {
    private String firstWord;
    private String secondWord;
    private String currentRoom;
    private String newLocation;
    private int roundCount = 50;
    Boolean gameOver = false;
    ArrayList<String> inventory = new ArrayList<>();
    List<Items> gameItems = new ArrayList<>();
    List<LocationsAndDirections> playerLocations = new ArrayList<>();
    Map<String, String> playerlocations3;


    Scanner scan = new Scanner(System.in);
    Player player = new Player("LB", com.ellane.model.Characters.MALE_SOLDIER);


    //this will run the app in the main class
    public void initialize() throws InterruptedException, IOException {
        gameWelcomeMessage();
        promptToStartGame();
    }

    private void gameWelcomeMessage() {
        String banner = null;
        if (Files.exists(Path.of("com.ellane.app/resources/gameArt.txt"))) {
            try {
                banner = Files.readString(Path.of("com.ellane.app/resources/gameArt.txt"));
                System.out.println(banner);
                System.out.println("\n");
                System.out.println("Ellane Needs your Help!");
                System.out.println("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("file wasn't found");
        }
    }
    //this would be one of the first things asked... Needs to make a introGame function again to call before
    //the sout line to play music
    private void promptToStartGame() throws InterruptedException, IOException {
        introMusic("Music/For Work 2_1.wav");
        System.out.println("Do you wish to play? type 'yes' or type 'quit game'");
        String userInput = scan.nextLine().toLowerCase();
        System.out.println("user input: " + userInput);

        if (userInput.equals("quit game")) {
            System.out.println("...Thanks for playing! Goodbye!");
        }

        if (userInput.equals("yes")) {
            System.out.println("Let's Play!");
            startGame();
        } else {
            System.err.println("INVALID INPUT");
            System.out.println();
            promptToStartGame();
        }
    }

    public void generatePlayerItems() throws IOException {
        JsonNode bedroomNode = Json.parse(new File("basementItems.json"));
        Items bedroomItems = Json.fromJson(bedroomNode, Items.class);
        gameItems.add(bedroomItems);

        JsonNode office_1_Node = Json.parse(new File("office_1_Items.json"));
        Items office_1_Items = Json.fromJson(office_1_Node, Items.class);
        gameItems.add(office_1_Items);

        JsonNode mechanical_Room_Node = Json.parse(new File("mechanicalRoomItems.json"));
        Items mechanical_Room_Node_Items = Json.fromJson(mechanical_Room_Node, Items.class);
        gameItems.add(mechanical_Room_Node_Items);

        JsonNode common_Area_Node = Json.parse(new File("common_areaItems.json"));
        Items common_Area_Node_Items = Json.fromJson( common_Area_Node, Items.class);
        gameItems.add(common_Area_Node_Items);

        JsonNode lobby_Node = Json.parse(new File("lobbyItems.json"));
        Items lobby_Items = Json.fromJson( lobby_Node, Items.class);
        gameItems.add(lobby_Items);

        JsonNode office_Floor_1_Node = Json.parse(new File("office_floor_1_Items.json"));
        Items office_Floor_1_Items = Json.fromJson( office_Floor_1_Node, Items.class);
        gameItems.add(office_Floor_1_Items);


        JsonNode office_Floor_2_Node = Json.parse(new File("office_floor_2_Items.json"));
        Items office_Floor_2_Items = Json.fromJson( office_Floor_2_Node, Items.class);
        gameItems.add(office_Floor_2_Items);

        JsonNode office_Floor_3_Node = Json.parse(new File("office_floor_3_Items.json"));
        Items office_Floor_3_Items = Json.fromJson( office_Floor_3_Node, Items.class);
        gameItems.add(office_Floor_3_Items);


        JsonNode office_Floor_4_Node = Json.parse(new File("office_floor_4_Items.json"));
        Items office_Floor_4_Items = Json.fromJson( office_Floor_4_Node, Items.class);
        gameItems.add(office_Floor_4_Items);

        JsonNode rooftop_Node = Json.parse(new File("rooftop_items.json"));
        Items rooftop_Items = Json.fromJson( rooftop_Node, Items.class);
        gameItems.add(rooftop_Items);


    }


       public void generateLocation() throws IOException {
       ObjectMapper objectMapper = new ObjectMapper();
        List<LocationsAndDirections> objects = objectMapper.readValue(new File("AlLLLLLLLROOMS.json"),
                new TypeReference<List<LocationsAndDirections>>() {});
        playerLocations = objects;
        playerLocations.addAll(objects);
       //System.out.println(playerLocations.get(0));
        /*       Gson gson = new Gson();
        LocationsAndDirections basementJson = gson.fromJson(new FileReader("basement.json"),
                LocationsAndDirections.class);
        System.out.println(basementJson.getEast());*/
   }

    public void generateLocation2() throws IOException {
        Gson gson = new Gson();
        Type basementLocation = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("basement.json"),
               basementLocation);

        Type office_1_Location = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("office_1.json"),
                office_1_Location);

        Type mechanical_Room_Location = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("mechanicalRoom.json"),
                mechanical_Room_Location);

        Type common_Area_Location = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("common_area.json"),
                common_Area_Location);

        Type lobby_Location = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("lobby.json"),
                lobby_Location);

        Type office_Floor_1_Location = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("office_Floor_1.json"),
                office_Floor_1_Location);

        Type office_Floor_2_Location = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("office_Floor_2.json"),
                office_Floor_2_Location);

        Type office_Floor_3_Location = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("office_Floor_3.json"),
                office_Floor_3_Location);

        Type office_Floor_4_Location = new TypeToken<Map<String, String>>(){}.getType();
        playerlocations3 = gson.fromJson(new FileReader("office_Floor_4.json"),
                office_Floor_4_Location);


    }

    private void startGame() throws InterruptedException, IOException {
        displayGameInfo();
        while (!gameOver){
            String answer = player.makeDecision();
            verifyDecision(answer);
        }

    }

    //TODO: (Delete this comment later)--- this is the main game info screen
    private void displayGameInfo () throws InterruptedException {
        System.out.println("The chaos spreads & the bombs keep exploding around the city");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("The fire is spreading from building to building & most signs of life as gone!");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("you get stuck inside of a building, but it can collapse at any minute & fire is spreading");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Luckily there id a helicopter on the roof evacuating the survivors that made it to the roof");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Unfortunately, you have been wounded & are losing blood as more time passes");
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Time is ticking & you don't have much time!");
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
        System.out.println("A dying woman tells you her sick daughter Ellane is stuck somewhere inside & asked you to save & escape together");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("The objectives you have are simple:");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("- collect the 3 items needed for a cure to save Ellane");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("- Find Ellane");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("- Safely make it to the roof to escape by helicopter before 50 turns pass");
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
    }

    private void showGameControls() throws InterruptedException {
        System.out.println("GAME COMMANDS: \n" +
                "    GO + [north, south, east, west]\n" +
                "    CLIMB + [up, down]\n" +
                "    GRAB + [item_name]\n" +
                "    DROP + [item_name]\n" +
                "    USE + [item_name]\n" +
                "    LOOK\n" +
                "    INVENTORY\n" +
                "    HEALTH \n" +
                "    HELP\n" +
                "    QUIT\n");
    }

    //TODO: (delete this comment later),this is making a String Array of our words so like ["john", "doe"]
    private void verifyDecision(String decision) throws InterruptedException {
        String[] stringArr = decision.split(" ");
        firstWord = stringArr[0].toLowerCase();

        try {
            if(stringArr.length < 1) {
                System.err.println("MUST ENTER A COMMAND TO CONTINUE...");
                player.makeDecision();
            }
            else if (stringArr.length == 1){
                verifyFirstWord(firstWord);
            }
            else {
                secondWord = stringArr[1].toLowerCase();
                verifyFirstWord(firstWord);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO: MAIN GAME LOGIC
    private void verifyFirstWord(String firstWord) throws InterruptedException, IOException {
        String decision;
        generatePlayerItems();
        generateLocation();
        switch (firstWord) {
            case "look":
                for (Locations location :playerLocations){
                    System.out.println("this is in your inventory" + getInventory() +
                            "this is your current location" +location.getBasement());
                }
                decision = player.makeDecision();
                verifyDecision(decision);
                break;
            case "help":
                showGameControls();
                decision = player.makeDecision();
                verifyDecision(decision);
                break;
            case "go":
            case "climb":
                for (Locations locations : playerLocations){
                    if (locations.getBasement().equals(secondWord)){
                        System.out.println(" This is your current location" + secondWord);
                    }
                    else if (locations.getCommon_Area().equals(secondWord)){
                        System.out.println("this is your current location" + secondWord);
                    }
                    else if (locations.getLobby().equals(secondWord)){
                        System.out.println("this is your current location" + secondWord);
                    }
                    else if (locations.getMechanical_Room().equals(secondWord)){
                        System.out.println("this is your current location" + secondWord);
                    }
                    else if (locations.getOffice_1().equals(secondWord)){
                        System.out.println("this is your current location" + secondWord);
                    }
                    else if (locations.getOffice_Floor_2().equals(secondWord)){
                        System.out.println("this is your current location" + secondWord);
                    }

                    else {
                        System.out.println("not a location");
                    }

                }
                verifyRoomMovement(secondWord);
                decision = player.makeDecision();
                verifyDecision(decision);
                break;
            case "inventory":
                System.out.println(getInventory());
                decision = player.makeDecision();
                verifyDecision(decision);
                break;
            case "grab":
                for (Items items: gameItems){
                    if (items.getItem().equals(secondWord)){
                        inventory.add(items.getItem());

                    }
                    if (items.getItem2().equals(secondWord)){
                        inventory.add(items.getItem2());
                    }
                    else {
                        System.out.println("nothing added to inventory");
                    }
                }
                decision = player.makeDecision();
                verifyDecision(decision);
                break;
            case "drop":
                //implement logic
                break;
            case "health":
                //implement logic
                break;
            case "use":
                //implement logic
                break;
            case "quit":
                System.out.println("Thank you for Playing!");
                TimeUnit.SECONDS.sleep(1);
                break;
            case "play":
                if (secondWord.equals("music")) {
                    System.out.println("These are the directions for the music player");
                    runMusic("Music/intro wav 2_1.wav");
                }
                decision = player.makeDecision();
                verifyDecision(decision);
                break;
            default:
                System.err.println("INVALID COMMAND...");
                System.out.println();
    private void verifyRoomMovement (String secondWord) throws InterruptedException, IOException {
       generateLocation();
       generateLocation2();

                System.err.println("ENTER A VALID COMMAND... ");
                showGameControls();
                System.out.println();

                decision = player.makeDecision();
                verifyDecision(decision);
                break;
        }
    }

    //Chris is working on this method, but we may not need it... depending on the Gson to Json
    //but have him code this still, just in case
    private void verifyRoomMovement (String secondWord) throws InterruptedException {
        String decision;
            switch (secondWord) {
                case "east":
                    System.out.println();
                    currentRoom = secondWord;
                    if (playerlocations3.containsKey(currentRoom)){
                        System.out.println(playerlocations3.get("east"));
                    }
                    //verify there is an east room to move to
                    //update currentroom property
                    //display currentRoom description
                    //display currentRoom items by looping over them all
                    //call player.makeDecision()
                    //If not valid, throw ERROR MESSAGE
                    //makeDecision();


                    break;
                case "west":
                    System.out.println();
                    if (playerlocations3.containsKey(currentRoom)){
                        System.out.println(playerlocations3.get("west"));
                    }
                    break;
                case "north":
                    System.out.println();
                    if (playerlocations3.containsKey(currentRoom))
                        System.out.println(playerlocations3.get("north"));
                    break;
                case "south":
                    System.out.println();
                    if (playerlocations3.containsKey(currentRoom))
                        System.out.println(playerlocations3.get("south"));
                    break;
                case "up":
                    System.out.println();
                    //Implement logic
                    break;
                case "down":
                    System.out.println();
                    //Implement logic
                    break;
                default:
                    System.err.println("INVALID COMMAND. \n MOVING TO DIRECTION " + secondWord + " FROM CURRENT ROOM NOT POSSIBLE");
                    System.out.println();
                    System.err.println("MAKE ANOTHER DECISION");

                    showGameControls();
                    decision = player.makeDecision();
                    verifyDecision(decision);
                    break;
            }
        }

    //TODO: need to make a GameControllerClass--for MVC
    //TODO: View---- the player types in "look"
    //TODO: Controller -this is the response to the command--- responds as a method call

    //TODO: MAIN GAME LOGIC
    private void verifyFirstWord(String firstWord) throws InterruptedException, IOException {
        generatePlayerItems();
        generateLocation();

        switch (firstWord) {
            case "look":
                for (LocationsAndDirections locationsAndDirections : playerLocations){
                    System.out.println("this is in your inventory " + getInventory() +
                            "\n and you are in the " + locationsAndDirections.getName());
                    break;
                }

                /*for (Locations location :playerLocations){
                    System.out.println("this is in your inventory" + getInventory() +
                            "this is your current location" +location.getBasement());
                }*/
                String answer = player.makeDecision();
                verifyDecision(answer);
                break;
            case "controls":
                showGameControls();
                answer = player.makeDecision();
                verifyDecision(answer);
                System.out.println();
                break;
            case "go":
              verifyRoomMovement(secondWord);
                answer = player.makeDecision();
                verifyDecision(answer);
                System.out.println();
                break;
            case "inventory":
                System.out.println(getInventory());
                answer = player.makeDecision();
                verifyDecision(answer);
                break;
            case "get":
                for (LocationsAndDirections andDirections : playerLocations) {
                    if (andDirections.getItem().equals(secondWord) || andDirections.getItem2().equals(secondWord)){
                        System.out.println("you now have this item " + secondWord);
                        break;
                    }
                    else {
                        System.out.println("you cant get this");
                    }
                    break;

                }
                for(LocationsAndDirections items: playerLocations){
                    if(items.getItem().equals(secondWord)){
                        inventory.add(items.getItem());
                        System.out.println(items.getItem_status());
                        System.out.println();
                        break;
                    }
                    if (items.getItem2().equals(secondWord)){
                        inventory.add(items.getItem2());
                        System.out.println(items.getItem_status2());
                        System.out.println();
                        break;
                    }

                    else {
                        System.out.println("nothing to add to inventory");
                        break;
                    }
                }
                answer = player.makeDecision();
                verifyDecision(answer);
                break;

            case "q":
                System.out.println("Thank you for Playing!");
                break;
            case "play":
                if (secondWord.equals("music")){
                    System.out.println("These are the directions for the music player");
                    runMusic("Music/intro wav 2_1.wav");
                }
                answer = player.makeDecision();
                verifyDecision(answer);
                break;
            default:
                System.out.println("incorrect command. Check game control for options");
                answer = player.makeDecision();
                verifyDecision(answer);
                break;


        }
    }
    //TODO: (Delete this comment later)--- this is the main game info screen
    private void displayGameInfo () throws InterruptedException {
            System.out.println("The chaos spreads & the bombs keep exploding around the city");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("The fire is spreading from building to building & most signs of life as gone!");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("you get stuck inside of a building, but it can collapse at any minute & fire is spreading");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Luckily there id a helicopter on the roof evacuating the survivors that made it to the roof");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Unfortunately, you have been wounded & are losing blood as more time passes");
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Time is ticking & you don't have much time!");
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
            System.out.println("A dying woman tells you her sick daughter Ellane is stuck somewhere inside & asked you to save & escape together");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("The objectives you have are simple:");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("- collect the 3 items needed for a cure to save Ellane");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("- Find Ellane");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("- Safely make it to the roof to escape by helicopter before 50 turns pass");
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }

    //TODO: get the Gson working... this code needs modifying
    public void GsonParsing() {
        String bedroom2 = "{\n" +
                "  \"currentRoom\": \"BEDROOM\",\n" +
                "  \"south\": \"OPEN AREA\",\n" +
                "  \"item\": \"sword\",\n" +
                "  \"item_status\": \" inside of a display case. It is unlocked\",\n" +
                "  \"item2\": \"gun\",\n" +
                "  \"item_status2\": \"its a MF gun..but it doesnt do anything with out bullets\",\n" +
                "  \"randenc\": \"20\",\n" +
                "  \"desc\": \"You are in a bedroom. There is nothing of use in this room. It stinks and everything looks crappy, but you see, to the SOUTH; an ugly OPEN AREA\"\n" +
                "\n" +
                "}";
        Gson json = new Gson();
        PlayerLocationsAndItems bedroom3 = json.fromJson(bedroom2, PlayerLocationsAndItems.class);
        System.out.println(bedroom3.getItem2());
    }





  /*  public void readingGenerateTestBedroom() throws FileNotFoundException {
        Gson gson = new Gson();
        Object object = gson.fromJson(new FileReader("testRooms.json"), PlayerLocationsAndItems.class);

        System.out.println(object);
    }
*/







    //this is to run the main game music...after the intro.
    public static void runMusic(String path) {
        try {
            Scanner scanner = new Scanner(System.in);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(1);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
            String response = "";


            while (!response.equals("Q")){
                System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume, Q= quit music player");
                System.out.println("Enter your choice: ");
                response = scanner.next();
                response = response.toUpperCase();

                switch (response){
                    case ("P"):
                        clip.start();
                        break;
                    case ("S"):
                        clip.stop();
                        break;
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("V") :
                        System.out.println("what do you want the level to be? :  (-17 is lower volume " +
                                "and 0.0 is higher volume)");
                        String setVolume = scanner.next();
                        gainControl.setValue(Float.parseFloat(String.valueOf(setVolume)));
                        break;
                    case ("Q"):
                        System.out.println("the music will stop playing... forever, until you activate again");
                        clip.stop();
                        break;
                    default:
                        System.out.println("not a valid response for music player");


                }
            }


        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }

    public static void introMusic(String path) {
        try {
            Scanner scanner = new Scanner(System.in);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(1);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-3.0f); // Reduce volume by 10 decibels.
            String response = "";


            while (!response.equals("Q")){
                System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume Q= STOP MUSIC.. and Begin Game");
                System.out.println("Enter your choice: ");
                response = scanner.next();
                response = response.toUpperCase();

                switch (response){
                    case ("P"):
                        clip.start();
                        break;
                    case ("S"):
                        clip.stop();
                        break;
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("V") :
                        System.out.println("what do you want the level to be? :  (-17 is lower)");
                        String setVolume = scanner.next();
                        gainControl.setValue(Float.parseFloat(String.valueOf(setVolume)));
                        break;
                    case ("Q"):
                        System.out.println("Thank you for quitting the player... Now Let's PLAY!");
                        clip.close();
                        System.out.println();
                        break;
                    default:
                        System.out.println("not a valid response for music player");


                }
            }


        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

}




