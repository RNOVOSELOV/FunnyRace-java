/**
 * Created by novoselov on 25.09.2015.
 */
public class Race {
    public static void main(String[] args) {
        Stadium stadium = Stadium.getInstance();
        stadium.createAndSetUpUnits();
        stadium.startRace();
    }
}
