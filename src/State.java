import java.util.HashMap;
import java.util.Objects;

/**
 * this represent a State
 *
 * @author youllou
 */
public class State {

    private String name;
    private HashMap<Character,State> outgoingTrans;

    /**
     * creates a State
     *
     * @param name the name of the state
     */
    public State(String name){
        this.name = name;
        outgoingTrans = new HashMap<>();
    }


    /**
     * Add a transition.
     *
     * @param action the action
     * @param state  the state to go to
     */
    public void addTrans(Character action, State state){
        outgoingTrans.put(action,state);
    }


    /**
     * Get the next state.
     *
     * @param action the action to check
     * @return the state
     */
    public State getNextState(Character action){
        return outgoingTrans.get(action);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets outgoing trans.
     *
     * @return the outgoing trans
     */
    public HashMap<Character, State> getOutgoingTrans() {
        return outgoingTrans;
    }

    /**
     * Sets outgoing trans.
     *
     * @param outgoingTrans the outgoing trans
     */
    public void setOutgoingTrans(HashMap<Character, State> outgoingTrans) {
        this.outgoingTrans = outgoingTrans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return name.equals(state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
