
package AssignmentOne;


/**
 * this enum represents three different Difficulty Levels
 * @author Mark Manson
 * @author Saif Asad
 */
public enum Difficulty {
    EASY(1), MEDIUM(2), HARD(3);
    
    private int difficultMultiplier;
    
    Difficulty(int multiplier)
    {
        difficultMultiplier = multiplier;
    }
    /**
     * The value of the difficulty is returned
     * @return an integer of the difficulty
     */
    public int getDifficultyValue()
    {
        return difficultMultiplier;
    }
}


