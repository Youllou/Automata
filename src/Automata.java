import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

/**
 * this represent the automata itself, it has methods to set it up
 * but also methods to run it (for now only as verification)
 *
 * @author youllou
 */
public class Automata {

    /**
     * S : the complete list of state
     */
    private List<State> S;
    /**
     * A : the complete list of action
     */
    private List<Character> A;
    /**
     * S0 : The initial State
     */
    private State S0;
    /**
     * The list of final state
     */
    private List<State> Sf;


    /**
     * Class constructor
     * instantiate S, A and Sf as LinkedList
     * and S0 to null
     */
    public Automata(){
        S = new LinkedList<State>();
        A = new LinkedList<Character>();
        Sf = new LinkedList<State>();
        S0 = null;
    }


    /**
     * This method adds transition, initial state AND final state from a file
     * it calls addFromString for each line of the file
     *
     * @param toRead the file to read, the file must contain one line with an init and one line with a final               see the readme for more explanation on file creation
     * @throws FileNotFoundException whenever file is not found
     */
    public void addFromFile(File toRead) throws FileNotFoundException {
        Scanner reader = new Scanner(toRead);

        while (reader.hasNextLine()){
            addFromString(reader.nextLine());
        }
    }

    /**
     * Add transition, initial state OR final state from string.
     *
     * @param input the String input
     */
    public void addFromString(String input){

        // we split the string for each ',' it has
        String[] splited = input.split(",");

        // if there's is more than 3 element or less than 2 then it's not valid
        if(splited.length>3 || splited.length<2 ){
            throw new IllegalArgumentException("entry is too big");
        }// if there's 2 element it might be an init or a final
        else if(splited.length == 2){
            // if it's an init, it must be the only one
            if(splited[0].equals("init") && S0 == null){
                // init declaration must be first
                if(S.size()>0){
                    throw new IllegalArgumentException("init must be declared at the beginning");
                }
                // we create the first state
                S0 = new State(splited[1]);
                // and add it to the list of state
                S.add(S0);
            }else if(splited[0].equals("final")){
                // we try to find the state asked by the user
                boolean found = false;
                for (State current:S) {
                    if(current.getName().equals(splited[1])){
                        // if we found it we add it to Sf
                        found = true;
                        Sf.add(current);
                        break;
                    }
                }
                // if we did not found the Sf we throw an error
                if(!found){
                    throw new IllegalArgumentException("final must be declared at the end");
                }
            }else{
                throw new IllegalArgumentException("entry is not recognized");
            }
        // if we got here it means we have 3 args which should be `the main state` `the action` `the state to go`
        }else {
            // we retrieve the main_state
            State main_state = getStateFromString(splited[0]);
            // if the state doesn't exist then we create it and add it to the list
            if (main_state == null) {
                main_state = new State(splited[0]);
                S.add(main_state);
            }

            // we retrieve the action
            char action = splited[1].charAt(0);

            // we retrieve the trans_state
            State trans_state = getStateFromString(splited[2]);
            // if the state doesn't exist we create it and add it to the list
            if (trans_state == null) {
                trans_state = new State(splited[2]);
                S.add(trans_state);
            }

            // we add the transition
            main_state.addTrans(action, trans_state);
        }
    }

    /**
     * Get state from string.
     *
     * @param name the name of the state
     * @return the state
     */
    public State getStateFromString(String name){
        for (State current:S) {
            if(current.getName().equals(name)){
                return current;
            }
        }
        return null;
    }


    /**
     * This is the function that will verify a String given the automata to follow
     *
     * @param to_check the to check
     * @return the boolean
     */
    public boolean verify(String to_check){

        // we start with the first state
        State curentState = S0;
        int ind = 0;
        // and loop while we have char to read or a state to go to
        while(ind<to_check.length() && curentState != null){
            // at each loop we change the state to the next one given the char to read
            curentState = curentState.getNextState(to_check.charAt(ind));
            ind++;
        }
        // at the end, we return the answer to whether or not the current state (it's the last state) is a final state
        return Sf.contains(curentState);
    }

    /**
     * Add state to the automata
     * not used by myself
     *
     * @param s the state to add
     */
    public void addState(State s){
        S.add(s);
    }

    /**
     * Add action to the automata.
     * not used by myself
     *
     * @param c the character to add
     */
    public void addAction(Character c){
        A.add(c);
    }

    /**
     * Gets the state list.
     *
     * @return S the state list
     */
    public List<State> getS() {
        return S;
    }

    /**
     * Set the state list.
     *
     * @param s a state list
     */
    public void setS(List<State> s) {
        S = s;
    }

    /**
     * Gets the action list
     *
     * @return A the action list
     */
    public List<Character> getA() {
        return A;
    }

    /**
     * Sets the action list.
     *
     * @param a an action list
     */
    public void setA(List<Character> a) {
        A = a;
    }

    /**
     * Gets S0 the first state.
     *
     * @return S0 the first state
     */
    public State getS0() {
        return S0;
    }

    /**
     * Sets S0 the first state from a string.
     *
     * @param S0_name the first state name
     */
    public void setS0(String S0_name) {
        S0 = new State(S0_name);
        S.add(S0);
    }

    /**
     * Sets S0 the first state
     *
     * @param S0 the first state
     */
    public void setS0(State S0) {
        this.S0 = S0;
        S.add(S0);
    }

    /**
     * Gets sf the final state.
     *
     * @return the final state sf
     */
    public List<State> getSf() {
        return Sf;
    }

    /**
     * Sets sf the final state.
     *
     * @param sf the final state sf
     */
    public void setSf(List<State> sf) {
        Sf = sf;
    }
}
