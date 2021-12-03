import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * This is the main class, the class you will use to launch the application
 * @author youllou
 */
public class Appli {

    /**
     * This is where the programs runs, it's the root
     * @param args not used
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        while (true){
            // at each loop, we print the menu and retrieve the automata given the user choice
            Automata auto = menu();
            if(auto == null){
                // if the automata was null it means the user wanted to quit the software so we return
                return;
            }else{
                // else, we run the automata
                run(auto);
            }
        }
    }

    /**
     * This is the menu, the user can choose what to do
     * @return auto the autmate generated given the user choice
     * @throws FileNotFoundException
     */
    public static Automata menu() throws FileNotFoundException {
       // we print the menu
       System.out.println("~~~~~~~~~~~~~~~Menu~~~~~~~~~~~~~~~");
       System.out.println("1. Smiley");
       System.out.println("2. HH:MM");
       System.out.println("3. Date (JJ/MM/AAAA)");
       System.out.println("4. Mail");
       System.out.println("5. Polynomial");
       System.out.println("6. Add automata from csv file");
       System.out.println("9. Exit");
       System.out.print("Your choice : ");
       // we scan the next input
       Scanner input_scanner = new Scanner(System.in);
       String input = input_scanner.nextLine();
       Automata auto;
       // given the imput we create the good automata
       switch (input) {
           case ("1"):
               auto = createSmiley();
               break;
           case ("2"):
               auto = createHeureMinute();
               break;
           case ("3"):
               auto = createDate();
               break;
           case ("4"):
               auto = createAddrNum();
               break;
           case ("5"):
               auto = createPolynome();
               break;
           case ("6"):
               auto = getFromNewFile();
               break;
           case ("9"):
               // if the input is 9 then we return null, so we can exit the software
               return null;
           default:
               // if the input is something else, we print that the choice was bad and relaunch the menu
               System.out.println("Please make a valid choice");
               // we do not forget to return the value returned by menu
               return menu();
       }
        // if the software made it here it means he created an automata and we return it
        return auto;
    }

    /**
     * this create an automata that recognize smileys
     * @return the automata
     */
    public static Automata createSmiley() {
        Automata auto = new Automata();
        auto.addFromString("init,E0");
        auto.addFromString("E0,:,E1");
        auto.addFromString("E0,;,E2");
        auto.addFromString("E1,-,N2");
        auto.addFromString("E1,=,N2");
        auto.addFromString("E2,-,N2");
        auto.addFromString("N2,(,M");
        auto.addFromString("N2,),M");
        auto.addFromString("final,M");
        return auto;
    }

    /**
     * this creates an automata that recognize HH:MM format,
     * it is made from a csv file named HM.csv which can be found in the lib folder
     * @return the automata
     * @throws FileNotFoundException
     */
    public static Automata createHeureMinute() throws FileNotFoundException {
        File file = new File("lib/HM.csv");
        Automata auto = new Automata();
        auto.addFromFile(file);
        return auto;
    }

    /**
     * this creates an automata that recognize JJ/MM/AAAA format,
     * it is made from a csv file named Date.csv which can be found in the lib folder
     * @return the automata
     * @throws FileNotFoundException
     */
    public static Automata createDate() throws FileNotFoundException{
        File file = new File("lib/Date.csv");
        Automata auto = new Automata();
        auto.addFromFile(file);
        return auto;
    }

    /**
     * this creates an automata that recognize mail addresses,
     * it is made from a csv file named AddrNum.csv which can be found in the lib folder
     * @return the automata
     * @throws FileNotFoundException
     */
    public static Automata createAddrNum() throws FileNotFoundException{
        File file = new File("lib/AddrNum.csv");
        Automata auto = new Automata();
        auto.addFromFile(file);
        return auto;
    }

    /**
     * this creates an automata that recognize second order polynomial,
     * it is made from a csv file named polynome.csv which can be found in the lib folder
     * @return the automata
     * @throws FileNotFoundException
     */
    public static Automata createPolynome() throws FileNotFoundException{
        File file = new File("lib/polynome.csv");
        Automata auto = new Automata();
        auto.addFromFile(file);
        return auto;
    }

    /**
     * this creates an automata from a csv file
     * the csv file path has to be given by the user
     * see the readme to have information on how to create such csv files
     * @return the automata
     * @throws FileNotFoundException
     */
    public static Automata getFromNewFile() throws FileNotFoundException {
        // we print the question
        System.out.println("Veuillez renseigner le chemin du fichier csv");
        // wait for the answer
        Scanner input_scanner = new Scanner(System.in);
        String input = input_scanner.nextLine();
        // create an automata from this file
        Automata auto = new Automata();
        File file = new File(input);
        try{
            auto.addFromFile(file);
        }catch (FileNotFoundException e){
            // if the file isn't found we print that it wasnt found and return to the menue
            System.out.println(input+" file wasn't found");
            return menu();
        }// else, we return the automata
        return auto;
    }

    /**
     * this function must verify a String given an automata
     * @param auto the automata that verify
     */
    public static void run(Automata auto){
        // we ask the user the String to verify
        System.out.print("Please enter the string to verify");
        Scanner input_scanner = new Scanner(System.in);
        // and we test it
        if(auto.verify(input_scanner.nextLine())){
            System.out.println("String is valid");
        }else{
            System.out.println("String isn't valid");
        }
    }
}
