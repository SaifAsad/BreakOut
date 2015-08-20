
package AssignmentOne;


/**
 * this enum represents three different Difficulty Levels
 * 
 * @author Saif Asad
 */
public enum Difficulty {
    EASY(1), MEDIUM(2), HARD(3);
    
    private int difficultMultiplier;
    
    Difficulty(int multiplier)
    {
        difficultMultiplier = multiplier;
    }
    
    public int getDifficulty()
    {
        return difficultMultiplier;
    }
}


