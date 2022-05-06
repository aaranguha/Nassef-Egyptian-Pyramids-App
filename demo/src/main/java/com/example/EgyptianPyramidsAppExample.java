package com.example;
import java.util.HashSet;
import java.util.*;
import org.json.simple.*;

public class EgyptianPyramidsAppExample {


  // I've used two arrays here for O(1) reading of the pharaohs and pyramids.
  // other structures or additional structures can be used
  protected Pharaoh[] pharaohArray;
  protected Pyramid[] pyramidArray;

  public static void main(String[] args) {
    // create and start the app
    EgyptianPyramidsAppExample app = new EgyptianPyramidsAppExample();
    app.start();
  }

  // main loop for app
  public void start() {
    Scanner scan = new Scanner(System.in);
    Character command = '_';

    // loop until user quits
    while (command != 'q') {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);

      executeCommand(scan, command);
    }
  }

  // constructor to initialize the app and read commands
  public EgyptianPyramidsAppExample() {
    // read egyptian pharaohs
    String pharaohFile =
      "/Users/aguha2021/Documents/GitHub/Nassef-Egyptian-Pyramids-App/demo/src/main/java/com/example/pharaoh.json";
    JSONArray pharaohJSONArray = JSONFile.readArray(pharaohFile);

    // create and intialize the pharaoh array
    initializePharaoh(pharaohJSONArray);

    // read pyramids
    String pyramidFile =
      "/Users/aguha2021/Documents/GitHub/Nassef-Egyptian-Pyramids-App/demo/src/main/java/com/example/pyramid.json";
    JSONArray pyramidJSONArray = JSONFile.readArray(pyramidFile);

    // create and initialize the pyramid array
    initializePyramid(pyramidJSONArray);

  }

  // initialize the pharaoh array
  private void initializePharaoh(JSONArray pharaohJSONArray) {
    // create array and hash map
    pharaohArray = new Pharaoh[pharaohJSONArray.size()];

    // initalize the array
    for (int i = 0; i < pharaohJSONArray.size(); i++) {
      // get the object
      JSONObject o = (JSONObject) pharaohJSONArray.get(i);

      // parse the json object
      Integer id = toInteger(o, "id");
      String name = o.get("name").toString();
      Integer begin = toInteger(o, "begin");
      Integer end = toInteger(o, "end");
      Integer contribution = toInteger(o, "contribution");
      String hieroglyphic = o.get("hieroglyphic").toString();

      // add a new pharoah to array
      Pharaoh p = new Pharaoh(id, name, begin, end, contribution, hieroglyphic);
      pharaohArray[i] = p;
    }
  }

    // initialize the pyramid array
    private void initializePyramid(JSONArray pyramidJSONArray) {
      // create array and hash map
      pyramidArray = new Pyramid[pyramidJSONArray.size()];
  
      // initalize the array
      for (int i = 0; i < pyramidJSONArray.size(); i++) {
        // get the object
        JSONObject o = (JSONObject) pyramidJSONArray.get(i);
  
        // parse the json object
        Integer id = toInteger(o, "id");
        String name = o.get("name").toString();
        JSONArray contributorsJSONArray = (JSONArray) o.get("contributors");
        String[] contributors = new String[contributorsJSONArray.size()];
        for (int j = 0; j < contributorsJSONArray.size(); j++) {
          String c = contributorsJSONArray.get(j).toString();
          contributors[j] = c;
        }
  
        // add a new pyramid to array
        Pyramid p = new Pyramid(id, name, contributors);
        pyramidArray[i] = p;
      }
    }

  // get a integer from a json object, and parse it
  private Integer toInteger(JSONObject o, String key) {
    String s = o.get(key).toString();
    Integer result = Integer.parseInt(s);
    return result;
  }

  // get first character from input
  private static Character menuGetCommand(Scanner scan) {
    Character command = '_';

    String rawInput = scan.nextLine();

    if (rawInput.length() > 0) {
      rawInput = rawInput.toLowerCase();
      command = rawInput.charAt(0);
    }

    return command;
  }

  // print all pharaohs
  private void printAllPharaoh() {
    for (int i = 0; i < pharaohArray.length; i++) {
      printMenuLine();
      pharaohArray[i].print();
      printMenuLine();
    }
  }

  private void displayPharaoh(){
    System.out.println("Enter a Pharaoh id");
    Scanner scan = new Scanner(System.in);
    while (!scan.hasNextInt()){
      System.out.println("Error: The value entered is not an integer"); 
      scan.nextLine();
    }
    int id = scan.nextInt();
    pharaohArray[id].print();
  }


  private void printAllPyramids() {
    for (int i = 0; i < pyramidArray.length; i++) {
      printMenuLine();
      pyramidArray[i].print();
      printMenuLine();
    }
  }

  HashSet<Integer> i_d = new HashSet<Integer>();
  HashSet<String> name = new HashSet<String>();
  private void displayPyramid(){
    System.out.println("Enter a Pyramid id");
    Scanner scan = new Scanner(System.in);
    while (!scan.hasNextInt()){
      System.out.println("Error: The value entered is not an integer"); 
      scan.nextLine();
    }
    int id_1 = scan.nextInt();
    i_d.add(id_1);
    for (int i = 0; i < pyramidArray.length; i++){
      if (id_1 == pyramidArray[i].get_id()){
        name.add(pyramidArray[i].getname());
      }
    }
    pyramidArray[id_1].print();
  }

  
  private void set_5(){
    System.out.println("List of Requested Pyramids");
    System.out.println(" Id    Name");
    System.out.println("---  ---------");
    Iterator itr = i_d.iterator();
    Iterator itr_2 = name.iterator();
    while (itr.hasNext()) {
      System.out.print(' ');
      System.out.print(itr.next());
      for (int i = 0; i < 3; i++){
        System.out.print(' ');
      }
      System.out.println(itr_2.next());
    }
  }
  

  private Boolean executeCommand(Scanner scan, Character command) {
    Boolean success = true;

    switch (command) {
      case '1':
        printAllPharaoh();
        break;
      case 'q':
        System.out.println("Thank you for using Nassef's Egyptian Pyramid App!");
        break;
      case '2':;
        displayPharaoh();
        break;
      case '3':
        printAllPyramids();
        break;
      case '4':
        displayPyramid();
        break;
      case '5':
        set_5();
        break;  
      default:
        System.out.println("ERROR: Unknown commmand");
        success = false;
    }

    return success;
  }

  private static void printMenuCommand(Character command, String desc) {
    System.out.printf("%s\t\t%s\n", command, desc);
  }

  private static void printMenuLine() {
    System.out.println(
      "--------------------------------------------------------------------------"
    );
  }



  // prints the menu
  public static void printMenu() {
    printMenuLine();
    System.out.println("Nassef's Egyptian Pyramids App");
    printMenuLine();
    System.out.printf("Command\t\tDescription\n");
    System.out.printf("-------\t\t---------------------------------------\n");
    printMenuCommand('1', "List all the pharoahs");
    printMenuCommand('2', "Displays a specific Egyptian Pharaoh");
    printMenuCommand('3', "List all the pyramids");
    printMenuCommand('4', "Displays a specific Pyramid");
    printMenuCommand('5', "Displays a list of requested pyramids");
    printMenuCommand('q', "Quit");
    printMenuLine();
  }
}




