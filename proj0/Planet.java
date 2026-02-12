public class Planet {
    public double xxPos; // x 坐标
    public double yyPos; // y 坐标
    public double xxVel; // x 方向速度
    public double yyVel; // y 方向速度
    public double mass; // 质量
    public String imgFileName; 
    public static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
            double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double distance;
        double dx;
        double dy;
        dx=this.xxPos-p.xxPos;
        dy=this.yyPos-p.yyPos;
        distance=Math.sqrt(dx*dx+dy*dy);
        return distance;
    }

    public double calcForceExertedBy(Planet p){
        return G*this.mass*p.mass/(calcDistance(p)*calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p)*(p.xxPos-this.xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double result=0;
        for(Planet s:allPlanets){
            if(!this.equals(s)){
                result+=calcForceExertedByX(s);
            }
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double result = 0;
        for (Planet s : allPlanets) {
            if (!this.equals(s)) {
                result += calcForceExertedByY(s);
            }
        }
        return result;
    }

    public void update(double dt,double fX,double fY){
        double ax=fX/this.mass;
        double ay=fY/this.mass;
        this.xxVel+=ax*dt;
        this.yyVel+=ay*dt;
        this.xxPos+=this.xxVel*dt;
        this.yyPos+=this.yyVel*dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}