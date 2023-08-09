package models;

import dao.FriendRequestDao;
import dao.FriendsDao;
import dao.UsersDao;

/**
 * FriendsHandler class handles friendship actions between two users and interacts with the database.
 * It allows adding or removing a friendship relationship between users in the database.
 */
public class FriendsHandler {

    private UsersDao users;
    private FriendsDao friends;
    private FriendRequestDao friendReqs;
    private String fromUsername;
    private String toUsername;

    /**
     * Constructs a new FriendsHandler instance with necessary data.
     * @param fromUsername The username of the user initiating the friendship action.
     * @param toUsername The username of the user receiving the friendship action.
     * @param users The DAO object for User data in the database.
     * @param friends The DAO object for Friends data in the database.
     * @param friendsReqs The DAO object for Friendship Requests data in the database.
     */
    public FriendsHandler(String fromUsername, String toUsername,
                          UsersDao users, FriendsDao friends,
                          FriendRequestDao friendsReqs) {
        this.users = users;
        this.friends = friends;
        this.friendReqs = friendsReqs;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
    }

    /**
     * Handles the friendship action based on the given flag toAdd.
     * If toAdd is true, adds a friendship relationship between the users.
     * If toAdd is false, removes the friendship relationship between the users.
     * Also removes any pending friendship request between the users.
     * @param toAdd A boolean flag indicating whether to add or remove the friendship.
     */
    public void handle(boolean toAdd) {
        User fromUser = users.getUser(fromUsername);
        User toUser = users.getUser(toUsername);
        friendReqs.deleteFriendRequest(new FriendRequest(fromUsername, toUsername));
        if (toAdd) {
            friends.addFriend(fromUser.getId(), toUser.getId());
        } else {
            friends.removeFriend(fromUser.getId(), toUser.getId());
        }
    }
}
