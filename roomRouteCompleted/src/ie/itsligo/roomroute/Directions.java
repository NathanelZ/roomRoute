package ie.itsligo.roomroute;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Directions {
	
	private final int ROOM_LENGTH = 5; // size of the room string
	private char building ;
	private char floor;
	private String locaationOnFloor = null;
	private String buildingSoundFile = null;
	private String floorSoundFile = null;
	private String roomSoundFile = null;


	public char getBuilding() {
		return building;
	}


	public String getBuildingSoundFile() {
		return buildingSoundFile;
	}


	public void setBuildingSoundFile(String buildingSoundFile) {
		this.buildingSoundFile = buildingSoundFile;
	}


	public String getFloorSoundFile() {
		return floorSoundFile;
	}


	public void setFloorSoundFile(String floorSoundFile) {
		this.floorSoundFile = floorSoundFile;
	}


	public String getRoomSoundFile() {
		return roomSoundFile;
	}


	public void setRoomSoundFile(String roomSoundFile) {
		this.roomSoundFile = roomSoundFile;
	}


	public void setBuilding(char building) {
		this.building = building;
	}


	public char getFloor() {
		return floor;
	}


	public void setFloor(char floor) {
		this.floor = floor;
	}


	public String getLocaationOnFloor() {
		return locaationOnFloor;
	}

	public void setLocaationOnFloor(String locaationOnFloor) {
		this.locaationOnFloor = locaationOnFloor;
	}

	/*
	 * This method takes in a room eg E2004 and splits up into the correct block
	 * (Engineering, Science, Business, etc) The correct floor The correct room
	 * number
	 */
	public boolean validate(String room) {

		if (room.length() != ROOM_LENGTH) {
			return false;
		}
		if (Character.isLetter(room.charAt(0)) == false) {
			return false; // room must start with a letter
		}
		for (int i = 1; i < ROOM_LENGTH; i++) {
			if (Character.isDigit(room.charAt(i)) == false) {
				return false; // room must have 4 digits
			}
		}

		// all ok - store the info
		building = room.charAt(0);
		floor = room.charAt(1);
		locaationOnFloor = room.substring(2);

		return true;
	}

	/*
	 * Get directions to building
	 */
	public String toBuilding() {
		String directions = null;
		switch (this.building) { /*Play the sound and display the direction to the building*/
		case 'A':
			this.setBuildingSoundFile("D:/Projects/roomRouteComplete/src/resourcees/building1.wav");
			directions = "From reception, walk straight ahead and then turn to your right";
			break;
		case 'B':
			this.setBuildingSoundFile("D:/Projects/roomRouteComplete/src/resourcees/building2.wav");
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest";
			break;
		case 'C':
			this.setBuildingSoundFile("D:/Projects/roomRouteComplete/src/resourcees/building3.wav");
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and continue through long corridor";
			break;
		case 'D':
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and continue through long corridor";
			break;
		case 'E':
			this.setBuildingSoundFile("D:/Projects/roomRouteComplete/src/resourcees/building4.wav");
			directions = "From reception, move to the center of reception and turn left into the engineering building";
			break;
		case 'F':
			this.setBuildingSoundFile("D:/Projects/roomRouteComplete/src/resourcees/building5.wav");
			directions = "From reception, walk outside and turn to your right.  Walk past the engineering building and the F block is straigt in front";
			break;
		default:
			this.setBuildingSoundFile("D:/Projects/roomRouteComplete/src/resourcees/BuildingError.wav");
			directions = "Sorry, that building is not recognised";
			break;

		}
		return(directions);
	}

	/*
	 * Get directions to floor
	 */
	public String toFloor() {
		String directions = null;
		switch (this.floor) { /*Play the sound and display the direction to the floor*/
		case '0':
			this.setFloorSoundFile("D:/Projects/roomRouteComplete/src/resourcees/floor1.wav");
			directions = "Stay on this floor";
			break;
		case '1':
			this.setFloorSoundFile("D:/Projects/roomRouteComplete/src/resourcees/floor2.wav");
			directions = "Ascend the stairs or take the lift to the first floor";
			break;
		case '2':
			this.setFloorSoundFile("D:/Projects/roomRouteComplete/src/resourcees/floor3.wav");
			directions = "Ascend two flight of stairs or take the lift to the second floor";
			break;
		default:
			this.setFloorSoundFile("D:/Projects/roomRouteComplete/src/resourcees/FloorError.wav");
			directions = "Sorry, floor " + this.floor + " is not recognised";
			break;

		}
		return(directions);
	}

	/*
	 * Get directions to floor
	 */
	public String toLocation() {
		String directions = null;
		switch (this.locaationOnFloor) { /*Play the sound and display the direction to the location*/
		case "006":
			this.setLocaationOnFloor("D:/Projects/roomRouteComplete/src/resourcees/location1.wav");
			directions = "This is the room to the right on this level";
			break;
		case "007":
			this.setLocaationOnFloor("D:/Projects/roomRouteComplete/src/resourcees/location2.wav");
			directions = "This is the room to the right on this level";
			break;
		case "003":
			this.setLocaationOnFloor("D:/Projects/roomRouteComplete/src/resourcees/location3.wav");
			directions = "This is the last room to the right on this level";
			break;
		case "004":
			this.setLocaationOnFloor("D:/Projects/roomRouteComplete/src/resourcees/location4.wav");
			directions = "This is the second last room to the right on this level";
			break;
		default:
			this.setLocaationOnFloor("D:/Projects/roomRouteComplete/src/resourcees/locationError.wav");
			directions = "Sorry, that room is not recognised";
			break;

		}
		return(directions);
	}


	

public static void delayfor(int n)
{
	try {
	    Thread.sleep(n * 1000);                 //1000 milliseconds is one second.
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
}
	
	
	
public void playMusic( String filepath)
	{
		InputStream Music;
		try
		{
			Music = new FileInputStream(new File(filepath));
			AudioStream audios = new AudioStream(Music);
			AudioPlayer.player.start(audios);
			delayfor(8);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error");
		}
	}

public void playMusic1( String filepath)
{
	InputStream Music;
	try
	{
		Music = new FileInputStream(new File(filepath));
		AudioStream audios = new AudioStream(Music);
		AudioPlayer.player.start(audios);
		delayfor(6);
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,"Error");
	}
}


public void playMusic2( String filepath)
{
	InputStream Music;
	try
	{
		Music = new FileInputStream(new File(filepath));
		AudioStream audios = new AudioStream(Music);
		AudioPlayer.player.start(audios);
		delayfor(4);
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,"Error");
	}
}
}