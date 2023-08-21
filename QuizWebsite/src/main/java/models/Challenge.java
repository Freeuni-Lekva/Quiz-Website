package models;


public class Challenge {
    private int challengeId;
    private String fromUsername;
    private String toUsername;
    private int quizId;


    public Challenge(int challengeId, String fromUsername, String toUsername, int quizId) {
        this.challengeId = challengeId;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.quizId = quizId;
    }

    public Challenge(String fromUsername, String toUsername, int quizId) {
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.quizId = quizId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getFromUsername() {
        return this.fromUsername;
    }

    public String getToUsername() {
        return this.toUsername;
    }

    public int getQuizId() {
        return this.quizId;
    }

    public int getChallengeId() {
        return this.challengeId;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "challengeId=" + challengeId +
                ", fromUsername='" + fromUsername + '\'' +
                ", toUsername='" + toUsername + '\'' +
                ", quizId=" + quizId +
                '}';
    }
}
