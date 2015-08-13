package AssignmentOne;

public class Player {

    private String playerName;
    private int score;

    public Player(String playerName, int score) {
        this.setPlayerName(playerName);
        this.setScore(this.getScore() + score);
    }
    
    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
}
