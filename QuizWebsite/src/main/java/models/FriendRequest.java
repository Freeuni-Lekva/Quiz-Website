package models;

public class FriendRequest {
    private String fromUsername;
    private String toUsername;
    private int id;


    public FriendRequest(String fromUsername, String toUsername) {
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
    }

    public FriendRequest(int id, String fromUsername, String toUsername) {
        this.id = id;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getFromUsername() {
        return this.fromUsername;
    }

    public String getToUsername() {
        return this.toUsername;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "fromUsername='" + fromUsername + '\'' +
                ", toUsername='" + toUsername + '\'' +
                ", id=" + id +
                '}';
    }
}
