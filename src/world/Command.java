package world;

public enum Command {
    ADD_JOURNEY("addJourney", "Inserts a new journey into the transportation network."),
    FIND_JOURNEY("findJourney", "Finds earliest journey that allows a passenger to " +
            "travel from one station to another."),
    FIND_JOURNEY_MIN_STOPS("findJourneyMinStops", "Finds the direct journey with the minimum number of stops."),
    REMOVE_JOURNEY("removeJourney", "Removes the given journey from the transportation network.");

    String command;
    String desc;
    Command(String command, String desc){
        this.command = command;
        this.desc = desc;
    }

    public void printCommand(){
        for(Command command : Command.values()){
            System.out.println(command + ": " + desc);
        }
    }
}
