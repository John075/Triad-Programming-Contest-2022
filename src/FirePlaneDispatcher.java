import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FirePlaneDispatcher {

	public static void main(String[] args) {
		try {
			List<String> fireLocs = Files.readAllLines(Paths.get("test data/fire.txt"));
			fireLocs.remove(0);

			Location fireLoc = new Location(fireLocs.remove(fireLocs.size() - 1), '1');
			
		    double min_dist = Double.MAX_VALUE;
		    Location closestLoc = null;
			
		    int count = 1;
			for(String otherLocation : fireLocs) {
				Location otherLoc = new Location(otherLocation, (char)((count - 1) + 'A'));
				
				double dist = fireLoc.getDistance(otherLoc);
				if(dist < min_dist) {
					closestLoc = otherLoc;
					min_dist = dist;
				}
				
				count++;
			}
			
			System.out.println("The call should be serviced by airport " + closestLoc.getName());
		} catch (IOException e) {
		}
	}
}

class Location {
	
	int latdeg, latmin, latsec;
	int londeg, lonmin, lonsec;
	
	double latcord, loncord;
    
    char name;
	
	public Location(String location, char value) {
		String[] coordInfo = location.split(" ");
		latdeg = Integer.parseInt(coordInfo[0]);
		latmin = Integer.parseInt(coordInfo[1]);
		latsec = Integer.parseInt(coordInfo[2]);
		londeg = Integer.parseInt(coordInfo[3]);
		lonmin = Integer.parseInt(coordInfo[4]);
		lonsec = Integer.parseInt(coordInfo[5]);
		
		latcord = latdeg + (latmin / 60.0) + (latsec / 3600.0);
		loncord = londeg + (lonmin / 60.0) + (lonsec / 3600.0);
		
		name = value;
	}
	
	public double getDistance(Location otherLoc) {
		return Math.sqrt(Math.pow((otherLoc.latcord - latcord), 2) + Math.pow((otherLoc.loncord - loncord), 2));
	}
	
	public char getName() {
		return name;
	}
}