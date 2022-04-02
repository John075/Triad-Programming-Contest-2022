import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

public class AsteroidPosition {
	
	public static void main(String[] args) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("test data/asteroid.txt"));
			String posLine = lines.get(0);
	
			StringTokenizer tokenizer = new StringTokenizer(posLine);
			
			double xCoord = Double.parseDouble(tokenizer.nextToken());
			double yCoord = Double.parseDouble(tokenizer.nextToken());
			double veloX = Double.parseDouble(tokenizer.nextToken());
			double veloY = Double.parseDouble(tokenizer.nextToken());

			final double asteroidMass = Double.parseDouble(tokenizer.nextToken());
			final double SUN_MASS = 1.989 * Math.pow(10, 30);
			final double GRAVITATIONAL_CONST = 6.67 * Math.pow(10, -11);

			for(int t = 0; t < 604800; t += 21600) {
				double r = Math.sqrt(Math.pow(xCoord, 2) + Math.pow(yCoord, 2));
				double force = (GRAVITATIONAL_CONST * asteroidMass * SUN_MASS) / Math.pow(r,  2);

				double Fx = (force * xCoord) / r;
				double Fy = (force * yCoord) / r;
				
				veloX = veloX + (21600 * Fx) / asteroidMass;
				veloY = veloY + (21600 * Fy) / asteroidMass;
				
				xCoord = xCoord + (21600 * veloX);
				yCoord = yCoord + (21600 * veloY);
			}
			
			System.out.println("After a week, the asteroid is at " + xCoord + ", " + yCoord);
		} catch (IOException e) {
		}
		
	}

}
