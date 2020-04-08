public class NBody{
	public static double readRadius(String path){
		In planet_data = new In(path);
		planet_data.readDouble();
		double Radius = planet_data.readDouble();
		return Radius;
	}
	public static Planet [] readPlanets(String path){
		In planet_data = new In(path);
		int number = planet_data.readInt();
		planet_data.readDouble();
		Planet[] Planets = new Planet[number];
		for(int i = 0; i<number;i++){
			Planets[i] = new Planet(planet_data.readDouble(),planet_data.readDouble(), planet_data.readDouble(), planet_data.readDouble(), planet_data.readDouble(), planet_data.readString());
		}
		return Planets;
	}
	public static void main(String[] args){
		StdDraw.enableDoubleBuffering();
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		double radius = readRadius(args[2]);
		Planet [] planets = readPlanets(args[2]);
		String background = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		for(double time = 0; time<T; time += dt){
			double[] xForce = new double[planets.length];
			double[] yForce = new double[planets.length];
			for(int j = 0; j<planets.length;j++){
				xForce[j] = planets[j].calcNetForceExertedByX(planets);
				yForce[j] = planets[j].calcNetForceExertedByY(planets);
			}
			for(int j = 0; j<planets.length;j++){
				planets[j].update(dt, xForce[j], yForce[j]);
			}
			StdDraw.picture(0, 0, background);
			for(Planet p : planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
} 