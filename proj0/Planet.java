public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
	}
	public Planet(Planet p){
		xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p){
		return Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
	}
	public double calcForceExertedBy(Planet p){
		double F = G*this.mass*p.mass/(this.calcDistance(p)*this.calcDistance(p));
		return F;
	}
	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);
	}
		public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet [] ps){
		Double NetForceX = 0.0;
		for(Planet p : ps){
			if(this.equals(p)){
				continue;
			}
			NetForceX = NetForceX + this.calcForceExertedByX(p);
		}
		return NetForceX;
	}
	public double calcNetForceExertedByY(Planet [] p){
		Double NetForceY = 0.0;
		for(int i=0;i<p.length;i++){
			if(this.equals(p[i])){
				continue;
			}
			NetForceY = NetForceY + this.calcForceExertedByY(p[i]);
		}
		return NetForceY;
	}
	public void update(double dt, double fX, double fY){
		double ax = fX/this.mass;
		double ay = fY/this.mass;
		this.xxVel = this.xxVel + dt*ax;
		this.yyVel = this.yyVel + dt*ay;
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos = this.yyPos + this.yyVel*dt;
	}
	public void draw(){
		String path = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, path);
	}
}
