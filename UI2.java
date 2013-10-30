import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;


public class UI {
private UserManager UM;
private String current;
	public UI(LinkedList<User> Users){
		UM = new UserManager(Users);
		
	}
	public void Logout() {
		System.out.println("User " + current + " has successfully been logged out.");
		for(int i = 0; i < UM.Loggedin.size(); i++) {
			if(UM.Loggedin.get(i).equals(current)) {
				UM.Loggedin.remove(i);
			}
		}
		current = "";
		Login();
	}
	public void SwitchUser() {
		boolean login = false;
		boolean checked = false;
		while(login == false){
			System.out.println("Please Enter a Username or type exit to quit the program.");
			Scanner read = new Scanner(System.in);
			String t = read.next();
			if(t.equals("exit")) {
				System.out.println("Goodbye.");
				System.exit(0);
			}
			if(t.equals(current)) {
				System.out.println("You are already logged in as " + current + ".");
				runOutput();
			}
			for(int i = 0; i < UM.Loggedin.size(); i++) {
				if(UM.Loggedin.get(i).equals(t)) {
					checked = true;
				}
			}
			if(checked == false) {
				System.out.println("User not logged in. You cannot switch to a user that has not logged in.");
				System.out.println();
				runOutput();
			}
			
			System.out.println("Please Enter password for " + t + ".");
			String q = read.next();
			LinkedList<User> ll1 = UM.getUsers();
			ListIterator it1 = ll1.listIterator();
			while(it1.hasNext()){
				User temp = (User)it1.next();
				if(temp.getUsername().equals(t)) {
					if(temp.getPassword().equals(q)) {
						System.out.println("Login Successful! \n\n");
						login = true;
						current = t;	
					}
				}
				
			}
			if(login == false) {
				System.out.println("Incorrect Username or Password. Please try again. \n");
			}
			runOutput();
		}	
	}
	public void Login() {
		while(true) {
		
		boolean login = false;
		
		while(login == false){
			System.out.println("Please Enter a Username or type exit to quit the program.");
			Scanner read = new Scanner(System.in);
			String t = read.next();
			if(t.equals("exit")) {
				System.out.println("Goodbye.");
				System.exit(0);
			}
			System.out.println("Please Enter password for " + t + ".");
			String q = read.next();
			LinkedList<User> ll1 = UM.getUsers();
			ListIterator it1 = ll1.listIterator();
			while(it1.hasNext()){
				User temp = (User)it1.next();
				if(temp.getUsername().equals(t)) {
					if(temp.getPassword().equals(q)) {
						System.out.println("Login Successful! \n\n");
						login = true;
						current = t;
						UM.setCurrentUser(t);
						boolean already=false;
						for(int i = 0; i < UM.Loggedin.size(); i++) {
							if(UM.Loggedin.get(i).equals(current)) {
								already=true;
							}
						}
						if (already==false) UM.Loggedin.add(t);
						//Don't add multiple users to logged in list when switching users.
					}
				}
				
			}
			if(login == false) {
				System.out.println("Incorrect Username or Password. Please try again. \n");
			}
		}
		runOutput();
		}
	}
	public void runOutput(){
		System.out.println("Hello " + current + ". Please enter one of the options below: \n");
		String a = "";
		while (true) {
			System.out
			.println("1. Listen to song or playlist\n\n2. Add/Edit playlist or song\n\n3. Borrow or take back a song\n\n"
					+ "4. Profile settings\n\n5. Check messages or Send a friend request(s)\n\n"
					+ "6. Browse song(s)\n\n7. Log out or Switch User\n\n8. Admin/Testing\n\nEnter one of the numbers above: \n");
			Scanner reader = new Scanner(System.in);
			String s;
			s = reader.next();
			switch (s) {
			case "1": {

				String b = "";
				while (b.equals("3") == false) {
					System.out.println("What would you like to do?");
					System.out
					.println("\n1. Play a song\n\n2. Play a playlist\n\n3. Go back\n\nEnter one of the numbers above.\n");
					b = reader.next();
					switch (b) {
					//Need to check if borrowed and decrease playcount if it is.
							case "1": {
								for(int i = 0; i < UM.getUsers().size(); i++) {
									if(UM.getUsers().get(i).getUsername().equals(current)) {
										System.out.println("Hello " + current + ", here is the list of songs you can play.\n");
										for(int j = 0; j < UM.getUsers().get(i).playableLibrary.getSongs().size(); j++) {
											System.out.println(j+1 + ": " + UM.getUsers().get(i).playableLibrary.getSongs().get(j));
										}
										System.out.println("\nType the number of the song you would like to listen to: ");
									}
								}
								boolean playable = false;
								String songrequest = reader.next();
								int songr = Integer.parseInt(songrequest);
								boolean input = true;
								if(songr < 1) {
									System.out.println("Invald input. Please enter a correct number: \n");
									playable = false;
									break;
								}
								for(int i = 0; i < UM.getUsers().size(); i++) {
									if(UM.getUsers().get(i).getUsername().equals(current)) {
										if(songr > UM.getUsers().get(i).playableLibrary.getSongs().size()) {
											input = false;
											break;
										}
									}
								}
								System.out.println();
								for(int i = 0; i < UM.getUsers().size(); i++) {
									if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).playableLibrary.getSongs().size()) {
											System.out.println("You are currently playing the song titled: " + UM.getUsers().get(i).playableLibrary.getSongs().get(songr-1).getName() + "\n");
											playable = true;
									}
								}
								if(playable == false) {
									System.out.println("Invalid input. Please enter a valid input: \n");
								}
								playable = false;
								break;
							}
							case "2": {
								// play playlist method here. We can list the playlist available to play here
								System.out.println("Hello " + current + " here is the list of your available playlists.\n");
								//code
								System.out.println("Please select a playlist number to listen to: \n");
								break;
							}
							case "3":{
								break;
							}
							default: {
								System.out.println("Invalid input. Please enter one of the numerical values above.\n");
								break;
							}
						}
					//end switch
					}
				break;
			}
			case "2": {
				String b = "";
			
			while (b.equals("5") == false) {
				System.out.println("What would you like to do?");
				System.out
				.println("\n1. Add a song\n\n2. Create a playlist\n\n3. Edit a playlist\n\n4. Edit a song's metadata\n\n"
						+ "5. Go back\n\nEnter one of the numbers above: \n");
				b = reader.next();
				switch (b) {
						case "1": {
							String[] tokens = null;
							String[] songdata = null;
							String songdelims = "[//(//,//)]";
							System.out.println("Hello " + current + " Please enter the new song you want to add in the following format:");
							System.out.println("(Name, Artist, Album, Year, Composer, Genre)\n");
							String songstring = "";
							Scanner scan = new Scanner(System.in);
							scan.useDelimiter("[\n]");
							songstring = scan.next();
							songdata = songstring.split(songdelims);
								for(int j = 0; j < songdata.length; j++) {
									while(songdata[j].startsWith(" ")) {
										songdata[j] = songdata[j].substring(1);
									}
									while(songdata[j].endsWith(" ")) {
										songdata[j] = songdata[j].substring(0, songdata[j].length()-1);
									}
								}
							Song newsong = new Song(songdata[1],songdata[2],songdata[3],songdata[4],songdata[5],songdata[6],current);
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									UM.getUsers().get(i).ownedLibrary.getSongs().add(newsong);
								}
							}
							break;
						}
						case "2": {
							//Need to create multiple playlists.
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									System.out.println("Hello " + current + " this is the list of songs you can add to your playlist. Type the number of the song you would like to add to a playlist.");
									for(int j = 0; j < UM.getUsers().get(i).ownedLibrary.getSongs().size(); j++) {
										System.out.println(j+1 + ": " + UM.getUsers().get(i).ownedLibrary.getSongs().get(j));
									}
								}
							}
							boolean playable = false;
							String songrequest = reader.next();
							int songr = Integer.parseInt(songrequest);
							if(playable == false) {
								System.out.println("You do not have the option to play that song. Please enter a valid option from above.");
							}
							playable = false;
							
							//create playlist
							break;
						}
						case "3": {
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									System.out.println("Hello " + current + " this is the list of songs you can add to your playlist. Type the number of the song you would like to add to a playlist.");
									for(int j = 0; j < UM.getUsers().get(i).ownedLibrary.getSongs().size(); j++) {
										System.out.println(j+1 + ": " + UM.getUsers().get(i).ownedLibrary.getSongs().get(j));
									}
								}
							}
							////edit playlist
							break;
						}
						case "4": {
							String songchange = "";
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									System.out.println("Hello " + current + " this is the list of songs you can edit. Type the number of the song you would like to edit.");
									for(int j = 0; j < UM.getUsers().get(i).ownedLibrary.getSongs().size(); j++) {
										System.out.println(j+1 + ": " + UM.getUsers().get(i).ownedLibrary.getSongs().get(j));
									}
								}
							}
							boolean playable = false;
							String songrequest = reader.next();
							int songr = Integer.parseInt(songrequest);
							boolean input = true;
							if(songr < 1) {
								System.out.println("Invald input. Please enter a correct number.");
								playable = false;
								break;
							}
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									if(songr > UM.getUsers().get(i).playableLibrary.getSongs().size()) {
										System.out.println("Invald input. Please enter a correct number.");
										input = false;
										break;
									}
								}
							}
							System.out.println();
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).ownedLibrary.getSongs().size()) {
										playable = true;
								}
							}
							//Loop to change the song's metadata.
							while(playable == true && !songchange.equals("exit")) {
								System.out.println("Which part of the song would you like to change? Please enter either the Name, Album, Artist, Year, Composer, or Genre");
								songchange = reader.next();
								
								if(songchange.equals("Name")) {
									System.out.println("What will the new song's name be?");
									songchange = reader.next();
									for(int i = 0; i < UM.getUsers().size(); i++) {
										if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).ownedLibrary.getSongs().size()) {
											    UM.getUsers().get(i).ownedLibrary.getSongs().get(songr-1).setName(songchange);
												playable = false;
										}
									}
									System.out.println("The name has been changed to " + songchange + ".");
								}
								else if(songchange.equals("Album")) {
									System.out.println("What will the new song's album be?");
									songchange = reader.next();
									for(int i = 0; i < UM.getUsers().size(); i++) {
										if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).ownedLibrary.getSongs().size()) {
											UM.getUsers().get(i).ownedLibrary.getSongs().get(songr-1).setAlbum(songchange);
											playable = false;
										}
									}
									System.out.println("The album title has been changed to " + songchange + ".");
								}
								else if(songchange.equals("Artist")) {
									System.out.println("Who will be the new song's artist?");
									songchange = reader.next();
									for(int i = 0; i < UM.getUsers().size(); i++) {
										if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).ownedLibrary.getSongs().size()) {
											UM.getUsers().get(i).ownedLibrary.getSongs().get(songr-1).setArtist(songchange);
											playable = false;
										}
									}
									System.out.println("The artist has been changed to " + songchange + ".");
								}
								else if(songchange.equals("Year")) {
									System.out.println("What will be the new song's year?");
									songchange = reader.next();
									for(int i = 0; i < UM.getUsers().size(); i++) {
										if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).ownedLibrary.getSongs().size()) {
											    UM.getUsers().get(i).ownedLibrary.getSongs().get(songr-1).setYear(songchange);
												playable = false;
										}
									}
									System.out.println("The year has been changed to " + songchange + ".");
								}
								else if(songchange.equals("Composer")) {
									System.out.println("Who will be the new song's composer?");
									songchange = reader.next();
									for(int i = 0; i < UM.getUsers().size(); i++) {
										if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).ownedLibrary.getSongs().size()) {
											UM.getUsers().get(i).ownedLibrary.getSongs().get(songr-1).setComposer(songchange);
											playable = false;
										}
									}
									System.out.println("The composer has been changed to " + songchange + ".");
								}
								else if(songchange.equals("Genre")) {
									System.out.println("What will be the new song's genre?");
									songchange = reader.next();
									for(int i = 0; i < UM.getUsers().size(); i++) {
										if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).ownedLibrary.getSongs().size()) {
											UM.getUsers().get(i).ownedLibrary.getSongs().get(songr-1).setGenre(songchange);
											playable = false;
										}
									}
									System.out.println("The genre has been changed to " + songchange + ".");
								}
								else{
									System.out.println("Please enter one of the options above. Please try again or type exit to go back.");
									playable = true;
									break;
								}
								
							}
							break;
						}
						case "5": {
							
							break;
						}
						default: {
							System.out.println("Invalid input. Please enter one of the numerical values above.");
							break;
						}
				
			}
					
				}
			break;
			}
			
			case "3": {
				String b = "";
			
			while (b.equals("3") == false) {
				System.out.println("What would you like to do?");
				System.out
				.println("\n1. Borrow a song\n\n2. Take back a song\n\n3. Go back\n\nEnter one of the numbers above.\n");
				b = reader.next();
				switch (b) {
						case "1": {
							System.out.println("Hello " + current + " who would you like to borrow a song from?");
							System.out.println("Please enter the username of the person you would like to borrow from.\n(Note: They must be on your friends list and logged into the system.)");
							String songrequest = reader.next();
							boolean friendslist = false;
							boolean loggedin = false;
							User receiver = null;
							User sender = null;
							
							if(current.equals(songrequest)) {
								System.out.println("You cannot send a song request to yourself.");
								loggedin = false;
								friendslist = true;
							}
							//Check if on the person is on the requesters friends list
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									for(int j = 0; j < UM.getUsers().get(i).getFriends().size(); j++) {
										if(UM.getUsers().get(i).getFriends().get(j).equals(songrequest)) {
											friendslist = true;
										}
									}
								}
							}
							
							//Check if User is logged in.
							for(int i = 0; i < UM.Loggedin.size(); i++) {
								if(songrequest.equals(UM.Loggedin.get(i))) {
									loggedin = true;
								}
							}
							if(loggedin == false) {
								System.out.println("That user is not logged in. Can't send requests to users who have not logged in.\n");
							}
							if(friendslist == false) {
								System.out.println("That user is not on your friends list. You cannot request to borrow songs from them.\n");
							}
							//Grab the User object of the sender & receiver
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									 sender = new User(UM.getUsers().get(i));
								}
								if(UM.getUsers().get(i).getUsername().equals(songrequest)) {
									receiver = new User(UM.getUsers().get(i));
								}
							}
							//if the user is logged in and is on the friends list then send the request.
							if(loggedin == true && (friendslist == true)) {
								System.out.println("Here is a list of the songs your friend has available for playing.\nSelect the number that you want to request to borrow.");
								for(int i = 0; i < UM.getUsers().size(); i++) {
									if(UM.getUsers().get(i).getUsername().equals(songrequest)) {
										for(int j = 0; j < UM.getUsers().get(i).ownedLibrary.getSongs().size(); j++) {
											System.out.println(j+1 + ": " + UM.getUsers().get(i).ownedLibrary.getSongs().get(j));
										}
									}
								}
								String songreq = reader.next();
								int songr = Integer.parseInt(songreq);
								System.out.println();
								boolean input = true;
								if(songr < 1) {
									System.out.println("Invald input. Please enter a correct number.");
									input = false;
									break;
								}
								for(int i = 0; i < UM.getUsers().size(); i++) {
									if(UM.getUsers().get(i).getUsername().equals(songrequest)) {
										if(songr > UM.getUsers().get(i).playableLibrary.getSongs().size()) {
											System.out.println("Invald input. Please enter a correct number.");
											input = false;
											break;
										}
									}
								}
								if(input == true){
									for(int i = 0; i < UM.getUsers().size(); i++) {
										if(UM.getUsers().get(i).getUsername().equals(songrequest)) {
											for(int j = 0; j < UM.getUsers().get(i).ownedLibrary.getSongs().size(); j++) {
												SongRequest newrequest = new SongRequest(sender, receiver, "You have a new song request for the song " + UM.getUsers().get(i).ownedLibrary.getSongs().get(songr-1).getName() + " from " + current, "b", UM.getUsers().get(i).ownedLibrary.getSongs().get(songr-1).getName());
												UM.getUsers().get(i).addMessage(newrequest);
												System.out.println("Your request has been sent.");
												break;
											}		
										}
									}
								}
							}				
							break;
						}
						case "2": {
							System.out.println("Hello " + current + " who would you like to take a song back from?\n(Note: The user must be logged into the system and must be on your friends list.)");
							String friend = reader.next();
							boolean friendslist = false;
							boolean loggedin = false;
							//Check if user is on friends list
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									for(int ix = 0; ix < UM.getUsers().get(i).getFriends().size(); ix++) {
										if(UM.getUsers().get(i).getFriends().get(ix).equals(friend)) {
											friendslist = true;
										}
									}
								}
							}
								if(friendslist == false) {
									System.out.println("That user is not on your friends list.\n");
									break;
								}
								for(int ix = 0; ix < UM.Loggedin.size(); ix++) {
									if(UM.Loggedin.get(ix).equals(friend)) {
										loggedin = true;
									}
								}
								if(loggedin == false) {
									System.out.println("That user is not logged in.\n");
								}
								
							
							System.out.println("Here is the list of your friend's songs.\nPlease enter the number of the song you would like to take back.\n");
							int jk = 0;
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(friend)) {
									
									for(int jx = 0; jx < UM.getUsers().get(i).playableLibrary.getSongs().size(); jx++) {
										System.out.println(jk+1 + ": " + UM.getUsers().get(i).playableLibrary.getSongs().get(jx));
										jk++;
									}	
								}
							}
							String songrequest = reader.next();
							int songr = Integer.parseInt(songrequest);
							boolean owned = false;
							Song songtoadd = null;
							boolean input = true;
							if(songr < 1) {
								System.out.println("Invald input. Please enter a correct number.\n");
								input = false;
								break;
							}
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(friend)) {
									if(songr > UM.getUsers().get(i).playableLibrary.getSongs().size()) {
										System.out.println("Invald input. Please enter a correct number.\n");
										input = false;
										owned = true;
										break;
									}
								}
							}
							//Check if the song is owned by the current user and grab it if it is
							if(input == true) {
							for(int ix = 0; ix < UM.getUsers().size(); ix++) {
								if(UM.getUsers().get(ix).getUsername().equals(friend)) {
									if(UM.getUsers().get(ix).playableLibrary.getSongs().get(songr-1).owner.equals(current)) {
										songtoadd = UM.getUsers().get(ix).playableLibrary.getSongs().get(songr-1);
										owned = true;
									}
								}
							}
							
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current) && songtoadd != null) {
									UM.getUsers().get(i).playableLibrary.getSongs().add(songtoadd);
									System.out.println("Your song " + songtoadd.name + " has been returned to you.");
								}
								else if(UM.getUsers().get(i).getUsername().equals(friend)) {
									UM.getUsers().get(i).playableLibrary.getSongs().remove(songtoadd);
								}
							}
							}
							if(owned == false) {
								System.out.println("You do not own that song.\nIf you would like to borrow a friend's song you can submit a borrow request.");
							}
							//take back song
							break;
						}
						case "3": {
							break;
						}
						default: {
							System.out
							.println("Invalid input. Please enter one of the numerical values above.\n");
							break;
						}
				
			}
					
				}
			break;
			}
			case "4": {
				String b = "";
			
			while (b.equals("4") == false) {
				System.out.println("What would you like to do?");
				System.out
				.println("\n1. Change sorting options\n\n2. Change library visability\n\n3. Edit song borrow length\n\n4. Go back\n\nEnter one of the numbers above.\n");
				b = reader.next();
				switch (b) {
						case "1": {
							System.out.println("Hello " + current + " how would you like to sort your Library?\nYou can sort by Artist, Song Name, Song Year, Song Album, Or the Genre.\nPlease Enter one of the options to sort the song by.");
							boolean sorted = false;
							String sortoption = reader.next();
							if(sortoption.equals("Artist")) {
								for(int i = 0; i < UM.getUsers().size(); i++) {
									if(UM.getUsers().get(i).getUsername().equals(current)) {
										for(int j = 0; j < UM.getUsers().get(i).ownedLibrary.getSongs().size(); j++) {
											Song sortsong = UM.getUsers().get(i).ownedLibrary.getSongs().get(j);
										}
										
									}
								}
							}
							//sorting options
							break;
						}
						case "2": {
							//Library visability
							System.out.println("Hello " + current + " you have the option to make your library visible to everyone or just friends.\nThe default setting is that everyone can see your library.\n");
							User curr = null;
							String vis = "";
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									curr = UM.getUsers().get(i);
								}
							}
							vis = curr.getVisibility();
							if(vis.equals("e")) {
							System.out.println("Would you like to change your visibility?\nIt is currently set to the option that everyone can see your library.\nPlease answer yes or no.\n(Note: Saying yes will change visability to Friends Only.)");	
							}
							else if(vis.equals("f")) {
								System.out.println("Would you like to change your visibility?\nCurrently your friends are the only ones that can see your Library.\nPlease answer yes or no.\n(Note: Saying yes will change visability to Everyone.)");
							}
							String option = reader.next();
							if(option.equals("yes") && vis.equals("f")) {
								curr.setVisibility("e");
								System.out.println("Your library is now visible to everyone.");
							}
							else if(option.equals("yes") && vis.equals("e")) {
								curr.setVisibility("f");
								System.out.println("Your library is now only visible your friends.");
							}
							else if(option.equals("no")) {
								System.out.println("Your library privacy settings have not been changed.");
							}
							else {
								System.out.println("Invalid input. Please enter yes or no.");
							}
								
							break;
						}
						case "3": {
							//borrow length
							break;
						}
						case "4": {
							break;
						}
						default: {
							System.out
							.println("Invalid input. Please enter one of the numerical values above.");
							break;
						}
				
			}
					
				}
			break;
			}
			case "5": {
				String b = "";
			
			while (b.equals("3") == false) {
				System.out.println("What would you like to do?");
				System.out
				.println("\n1. Check messages\n\n2. Send friend request \n\n3. Go back\n\nEnter one of the numbers above.\n");
				b = reader.next();
				switch (b) {
						case "1": {
							boolean flag = false;
							for(int i = 0; i < UM.getUsers().size(); i++){
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									if(UM.getUsers().get(i).getMessages().size() == 0) {
										System.out.println("Hello " + current + " you have no new messages.");
										flag = true;
									}
								}
							}
							if(flag == true) {
								break;
							}
							System.out.println("Hello " + current + " here are your messages.");
						for(int i = 0; i < UM.getUsers().size(); i++) {
							if(UM.getUsers().get(i).getUsername().equals(current) && UM.getUsers().get(i).getMessages().size() != 0) {
									System.out.println(UM.getUsers().get(i).getFirstMessage());
									UM.getUsers().get(i).getFirstMessage();
									String sender = UM.getUsers().get(i).getFirstMessage().sender.getUsername();
									System.out.println("Would you like to accept or deny the request?\nType yes to accept and no to deny.");
									String request = "";
							while(true) {
									request = reader.next();
									if(request.equals("yes")) {
										if(UM.getUsers().get(i).getFirstMessage().type.equals("f")) {
											System.out.println("That user has been added to your friends list.");
											UM.getUsers().get(i).addFriends(sender);
											for(int j = 0; j < UM.getUsers().size(); j++) {
												if(UM.getUsers().get(j).getUsername().equals(sender)) {
													UM.getUsers().get(j).addFriends(current);
												}
											}
										}
										else if(UM.getUsers().get(i).getFirstMessage().type.equals("b")) {
											System.out.println("You have accepted a request for one of your songs from " + UM.getUsers().get(i).getFirstMessage().sender.getUsername());
											String songname = UM.getUsers().get(i).getFirstMessage().songname;
											Song so = null;
											//Remove song from playable library of current user.
											for(int ix = 0; ix < UM.getUsers().get(i).playableLibrary.getSongs().size(); ix++) {
												if(UM.getUsers().get(i).playableLibrary.getSongs().get(ix).name.equals(songname)) {
													so = UM.getUsers().get(i).playableLibrary.getSongs().get(ix);
													UM.getUsers().get(i).playableLibrary.getSongs().remove(ix);
													System.out.println("Your song " + songname + " is no longer playable for you.");
													
												}
											}
											//Find friend and add song to playable library
											for(int ix = 0; ix < UM.getUsers().size(); ix++) {
												if(UM.getUsers().get(ix).getUsername().equals(sender)) {
													UM.getUsers().get(ix).playableLibrary.getSongs().add(so);
												}
											}
										}
										UM.getUsers().get(i).getMessages().removeFirst();
										break;
									
									}
									else if(request.equals("no") && UM.getUsers().get(i).getFirstMessage().type.equals("f")) {
										System.out.println("The user " + UM.getUsers().get(i).getFirstMessage().sender + " has not been added to your friends list.");
										break;
									}
									else if(request.equals("no") && UM.getUsers().get(i).getFirstMessage().type.equals("b")) {
										System.out.println("You have rejected an attempt to borrow your song titled " + UM.getUsers().get(i).getFirstMessage().songname + " from user " + UM.getUsers().get(i).getFirstMessage().sender + ".");
										break;
									}
									else {
										System.out.println("Please enter a valid response. The responses are yes or no.");
									}
							}
							}
						}
							//check message
							break;
						}
						case "2": {
							//send friend request
							System.out.println("Hello " + current + " who would you like to send a friend request to?\nPlease Enter the username of the person you would like to add.");
							String friendrequest = reader.next();
							boolean friendslist = false;
							boolean loggedin = false;
							User receiver = null;
							User sender = null;
							
							if(current.equals(friendrequest)) {
								System.out.println("You cannot send a friend request to yourself.");
								loggedin = false;
								friendslist = true;
							}
							//Check if not already on friends list
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									for(int j = 0; j < UM.getUsers().get(i).getFriends().size(); j++) {
										if(UM.getUsers().get(i).getFriends().get(j).equals(friendrequest)) {
											friendslist = true;
										}
									}
								}
							}
							//Check if User is logged in.
							for(int i = 0; i < UM.Loggedin.size(); i++) {
								if(friendrequest.equals(UM.Loggedin.get(i))) {
									loggedin = true;
								}
							}
							if(loggedin == false) {
								System.out.println("That user is not logged in. Can't send messages to users who have not logged in.");
							}
							if(friendslist == true) {
								System.out.println("You are already friends with that person.");
							}
							//Grab the User object of the sender & receiver
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									 sender = new User(UM.getUsers().get(i));
								}
								if(UM.getUsers().get(i).getUsername().equals(friendrequest)) {
									receiver = new User(UM.getUsers().get(i));
								}
							}
							//if the user is logged in and is on the friends list then send the request.
							if(loggedin == true && (friendslist == false)) {
								FriendRequest friend = new FriendRequest(sender, receiver, "You have a new friend request from " + current, "f");
								for(int i = 0; i < UM.getUsers().size(); i++) {
									if(UM.getUsers().get(i).getUsername().equals(friendrequest)) {
										UM.getUsers().get(i).addMessage(friend);
										System.out.println("Your request has been sent.");
									}
								}
								
								
							}
							break;
						}
						//Send Song Request
						case "3": {
							break;
						}
						default: {
							System.out
							.println("Invalid input. Please enter one of the numerical values above.");
							break;
						}
				
			}
					
				}
			break;
			}
			case "6": {
				String b = "";
			
			while (b.equals("4") == false) {
				System.out.println("What would you like to do?");
				System.out
				.println("\n1. Search by friend\n\n2. Search by all friends\n\n3. Search all non friends\n\n4. Go back\n\nEnter one of the numbers above.\n");
				b = reader.next();
				switch (b) {
						case "1": {
							System.out.println("Which friend's library would you like to see?\n");
							
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									
									System.out.println("Here is a list of your friends: ");
									for(int j = 0; j < UM.getUsers().get(i).getFriends().size(); j++) {
										System.out.println(j+1 + ": " + UM.getUsers().get(i).getFriends().get(j));
									}
								}
							}
							System.out.println("\nEnter the number of the friend whose library you would like to view?");
							String songrequest = reader.next();
							int songr = Integer.parseInt(songrequest);
							String username = "";
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current) && songr <= UM.getUsers().get(i).getFriends().size()) {
									username = UM.getUsers().get(i).getFriends().get(songr-1);
								}
							}
							
							int k = 1;
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(username)) {
									System.out.println("Here is " + UM.getUsers().get(i).getUsername() + "'s library.\n");
									for(int j = 0; j < UM.getUsers().get(i).ownedLibrary.getSongs().size(); j++) {
									System.out.println(k + ": " + UM.getUsers().get(i).ownedLibrary.getSongs().get(j));
									k++;
									}
								}
							}
							System.out.println("");
							break;
						}
						case "2": {
							System.out.println("Here is the entire list of your friend's libraries.");
							User currentuser = null;
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									currentuser = UM.getUsers().get(i);
								}
							}
							for(int i = 0; i < currentuser.getFriends().size(); i++) {
								String friend = currentuser.getFriends().get(i);
								for(int ix = 0; ix < UM.getUsers().size(); ix++) {
									if(UM.getUsers().get(ix).getUsername().equals(friend)) {
										System.out.println();
										System.out.println("Here is library for your friend " + friend + ".");
										System.out.println();
										for(int jx = 0; jx < UM.getUsers().get(ix).playableLibrary.getSongs().size(); jx++) {
											System.out.println(jx +1 + ": " + UM.getUsers().get(ix).playableLibrary.getSongs().get(jx));
										}
									}
								}
								
							}
							System.out.println("");
							//search by all friends
							break;
						}
						case "3": {
							System.out.println("Here is the entire list of libraries from users who are not your friends.\n");
							User currentuser = null;
							LinkedList<String> notfriends = new LinkedList<String>();
							for(int i = 0; i < UM.getUsers().size(); i++) {
								if(UM.getUsers().get(i).getUsername().equals(current)) {
									currentuser = UM.getUsers().get(i);
								}
							}
							//check if the user is on UserList but not friends list.
							boolean friends = false;
							for(int i = 0; i < UM.getUsers().size(); i++) {
								String userl = UM.getUsers().get(i).getUsername();
								for(int ix = 0; ix < currentuser.getFriends().size(); ix++) {
									String friend = currentuser.getFriends().get(ix);
									if(friend.equals(userl)) {
										friends = true;
									}
								}
								if(friends == false) {
									notfriends.add(userl);
								}
							}
							//Remove the current user from not friends.
							for(int i = 0; i < notfriends.size(); i++) {
								if(notfriends.get(i).equals(current)) {
									notfriends.remove(i);
								}
							}
							int kx = 0;
							for(int i = 0; i < UM.getUsers().size(); i++) {
								User curr = UM.getUsers().get(i);
								for(int jx = 0; jx < notfriends.size(); jx++) {
									if(notfriends.get(jx).equals(curr.getUsername())) {
										if(curr.visible.equals("f")) {
											System.out.println("User " + curr.getUsername() + " only allows their library to be seen by friends.");
										}
										else if(curr.visible.equals("e")) {
										System.out.println();
										System.out.println("Here are the songs for the user " + curr.getUsername() + ".");
										for(int xx = 0; xx < curr.playableLibrary.getSongs().size(); xx++) {
											System.out.println(kx+1 + ": " + curr.playableLibrary.getSongs().get(xx));
										}
									}
								  }
								}
							}
							//search all non friends
							break;
						}
						case "4": {
							//go back
							break;
						}
						default: {
							System.out
							.println("Invalid input. Please enter one of the numerical values above.\n");
							break;
						}
				
			}
					
				}
			break;
			}
			case "7":{
				
				String b = "";
				while(b.equals("3") == false) {
					System.out.println("What would you like to do?");
					System.out.println("\n1. Login as someone new/exit the program.\n\n2. Switch Users\n\n3. Logout the current user out of the system. \n\n4. Go back");
					b = reader.next();
					switch(b) {
					case "1": {
						Login();
					}
					case "2": {
						SwitchUser();
					}
					case "3": {
						Logout();
					}
					case "4": {
						break;
					}
					default: {
						System.out.println("Invalid input, Please enter one of the options above.\n");
						break;
					}
					}
				}
			}
			case "8":{
				String b = "";
				
				while (b.equals("5") == false) {
					System.out
					.println("\n1. print users and passwords\n\n2. Enter a user and view their owned songs. \n\n3. Add a user \n\n4. See the Current Users Loggedin\n\n5. Go back\n\nEnter one of the numbers above.\n");
					b = reader.next();
					switch (b) {
							case "1": {
								LinkedList<User> ll = UM.getUsers();
								ListIterator it = ll.listIterator();
								while(it.hasNext()){
									User temp = (User)it.next();
									System.out.println("Name: " + temp.getUsername() + " " + "Password: " + temp.getPassword());
								}
								break;
							}
							case "2": {
								System.out.println("Enter the name of a user.");
								String _user = reader.next();
								if(!UM.setCurrentUser(_user)){
									System.out.print("Name given was not a valid user.");
									break;
								}
								LinkedList<Song> ll = UM.getOwnedSongs();
								ListIterator it = ll.listIterator();
								while(it.hasNext()){	
									System.out.println(it.next().toString());
								}
								break;
							}
							case "3": {
								System.out.println("Enter the name of the new user.");
								String name = reader.next();
								System.out.println("Enter " + name + "'s password.");
								String password = reader.next();
								UM.addUser(name, password);
								break;
							}
							case "4": {
								System.out.println("The current user is " + UM.getCurrentUser() + ".\n");
								System.out.println("These are the users who are currently logged in.\n");
								for(int i = 0; i < UM.Loggedin.size(); i++) {
									System.out.println(UM.Loggedin.get(i));
								}
								
							}
							case "5": {
								break;
							}
							default: {
								System.out
								.println("Invalid input. Please enter one of the numerical values above.");
								break;
							}
			
			}
			
			}
		}
		
	}

}
}
}