package ie.itsligo.roomroute;

public class Room {

	public Room() {
	
	}
	//Parse the QR code data to get the room
	public String get(String data)
	{
		String delims = "[ ]+";
		String[] tokens = data.split(delims);
		
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i]; 
            String t = s.trim();
            
            if (t.equals("Room:") == true ) {
            	if (i < tokens.length)
            		return tokens[i+1];
            }
            	
        }
        return "Class not found";		  
	}
	
	//Parse the QR code data to get the beginning hour
	public String getHour(String data)
	{
		String delims = "[. ]+";
		String[] tokens = data.split(delims);
		
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i]; 
            String t = s.trim();
            
            if (t.equals("Time:") == true ) {
            	if (i < tokens.length) {
            		s = tokens[i+1]; 
                	t = s.trim();
            		return t;
            	}
            }	
        }
		return null;	  
	}
	
	//Parse the QR code data to get the beginning minute
	public String getMinute(String data)
	{
		String delims = "[. ]+";
		String[] tokens = data.split(delims);
		String s=null, t=null;
        for (int i = 0; i < tokens.length; i++) {
            s = tokens[i]; 
            t = s.trim();
            
	        if (t.equals("Time:") == true) {
	        	if (i < tokens.length) {
	        		s = tokens[i+2]; 
	            	t = s.trim();
	        		return t;
	        	}
	        }
        }
		return null;	  
	}

}
