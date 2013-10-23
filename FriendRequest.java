
public class FriendRequest extends Message {
	private User sender;
	private User recipiant;
	private String message;
	public FriendRequest(User sender_, User recipiant_, String message_) {
		super(sender_, recipiant_, message_);
		// TODO Auto-generated constructor stub
	}
	
	//find out if accepted and the friends list between the recipiant and the sender
	public boolean Accepted() {
		sender.addFriends(recipiant.getUsername());
		recipiant.addFriends(sender.getUsername());
		return true;
	}
	//find the recipiant and the sender and send a message saying that they have been denied
	public boolean Denied() {
		return false;
	}
}
