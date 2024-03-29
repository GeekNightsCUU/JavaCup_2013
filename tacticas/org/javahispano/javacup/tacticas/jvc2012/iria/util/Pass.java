package org.javahispano.javacup.tacticas.jvc2012.iria.util;

import java.util.List;

import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.command.CommandHitBall;
import org.javahispano.javacup.model.command.CommandMoveTo;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;

import org.javahispano.javacup.tacticas.jvc2012.iria.util.TacticUtils.Attitude;

/**** Based in Masia Tactic **/

public class Pass {
	private GameSituations sp;
	private List<Command> comandos;
	private Shoot shoot;
	private Position[] players;
	private Position[] opponents;
	private int[] iterToShoot;
	
	public Pass(List<Command> comandos) {
		this.comandos = comandos;
		shoot = new Shoot(comandos);
	}
	
	public void execute(GameSituations sp, Attitude attitude){
		this.sp = sp;
		int[] canShoot = sp.canKick();
		players = sp.myPlayers();
		opponents = sp.rivalPlayers();
		iterToShoot = sp.iterationsToKick();
		if(canShoot.length > 0){
			for (int i = 0; i < canShoot.length; i++) {
				iterToShoot[canShoot[i]] = Constants.ITERACIONES_GOLPEAR_BALON;
			}
			PassInfo passInfo = calculatePass(canShoot);		
			for (int i = 0; i < canShoot.length; i++) {
				pass(players, canShoot[i], passInfo);
			}
	
			if(passInfo.exist){
				comandos.add(new CommandMoveTo(passInfo.position.nearestIndex(players), passInfo.position));
			}
			
			if(passInfo.fitness < getThreshold()){
				shoot.execute(sp, canShoot);
			}			
		}	
		
	}	

	private double getThreshold() {
		return .975;
	}

	private void pass(Position[] players, int index, PassInfo passInfo) {
		if(passInfo.exist){
			double power = passInfo.velocity/Constants.getVelocidadRemate(sp.myPlayersDetail()[index].getPower());
			if(sp.isStarts()){
				power /= .75;
			} 
			comandos.add(new CommandHitBall(index, passInfo.position, power, passInfo.angleVer*180/Math.PI));
		}
	}

	private PassInfo calculatePass(int[] canShoot) {
		double power = 1; boolean isGoalkeeper = false;
		for (int i = 0; i < canShoot.length; i++) {
			if(sp.myPlayersDetail()[canShoot[i]].getPower() < power)
				power = sp.myPlayersDetail()[canShoot[i]].getPower();
			if(sp.myPlayersDetail()[canShoot[i]].isGoalKeeper())
				isGoalkeeper = true;
		}
		double maxVertAngle = Constants.ANGULO_VERTICAL_MAX*Math.PI/180;
		if(isGoalkeeper || inArea(sp.ballPosition()))
			return comparePass(
					calculatePass(Constants.getVelocidadRemate(power), 0, true),
					calculatePass(Constants.getVelocidadRemate(power), maxVertAngle/8, true),
					calculatePass(Constants.getVelocidadRemate(power), maxVertAngle/4, true),
					calculatePass(Constants.getVelocidadRemate(power), maxVertAngle/2, false),
					calculatePass(Constants.getVelocidadRemate(power), 3 * maxVertAngle/4, true),
					calculatePass(Constants.getVelocidadRemate(power), 7 * maxVertAngle/8, true),
					calculatePass(Constants.getVelocidadRemate(power), maxVertAngle, true));
		
		PassInfo pass = comparePass(
				calculatePass(Constants.VELOCIDAD_MAX + .4, 0, false),
				calculatePass(Constants.getVelocidadRemate(power), 0, false),
				calculatePass(Constants.getVelocidadRemate(power), maxVertAngle/8, false),
				calculatePass(Constants.getVelocidadRemate(power), maxVertAngle/4, false),
				calculatePass(Constants.getVelocidadRemate(power), maxVertAngle/2, false),
				calculatePass(Constants.getVelocidadRemate(power), 3 * maxVertAngle/4, false),
				calculatePass(Constants.getVelocidadRemate(power), 7 * maxVertAngle/8, false),
				calculatePass(Constants.getVelocidadRemate(power), maxVertAngle, false));
		return pass;
	}
	
	private PassInfo comparePass(PassInfo pass1, PassInfo pass2) {
		if(!pass2.exist ||(pass1.exist && pass1.fitness >= pass2.fitness))
			return pass1;
		return pass2;
	}
	
	private PassInfo comparePass(PassInfo... pass) {
		PassInfo best = pass[0];
		for (int i = 1; i < pass.length; i++) {
			best = comparePass(best, pass[i]);
		}
		return best;
	}

	private PassInfo calculatePass(double velocity, double angleVer, boolean forward) {
		int n = 360;
		double dAngle = 360.0/n;
    	Trajectory[] trajectory = new Trajectory[n];    	
    	for (int i = 0; i < n; i++) {
    		trajectory[i] = new Trajectory(sp.ballPosition(), velocity, i*dAngle*Math.PI/180, angleVer);
		}
    	double fitness, bestFitness = Double.MIN_VALUE;
    	Position pass = null;
    	int m = forward? n/2 : n;
    	for (int i = 0; i < m; i++) {
			for (int j = 0; j < trajectory[i].length; j++) {
				if(j == 0)
					fitness = calculateFitness(trajectory[i].positions[j], trajectory[i].z[j], j + 1, sp.ballPosition(), 0);
				else
					fitness = calculateFitness(trajectory[i].positions[j], trajectory[i].z[j], j + 1, trajectory[i].positions[j-1], trajectory[i].z[j-1]);
				if(fitness != 0){
					if(fitness > bestFitness)
					{
						bestFitness = fitness;
						pass = trajectory[i].positions[j];
					}					
					if(fitness < 0 || fitness > 1)
						break;
				}
			}
		}
    	if(pass == null){    		
    		return new PassInfo();
    	}
		return new PassInfo(pass, velocity, angleVer, bestFitness);
	}	

	private double calculateFitness(Position position, double z, int iter, Position last, double lastZ) {
		int oppIterToBall = calculateIterToBall(position, z, opponents, sp.rivalPlayersDetail(), sp.rivalIterationsToKick());
		double Dx = position.getX()-last.getX();
		double Dy = position.getY()-last.getY();
		if(!position.isInsideGameField(0)){
			if(position.getY() > Constants.LARGO_CAMPO_JUEGO/2){
				double posX = (Dx / Dy) * (Constants.LARGO_CAMPO_JUEGO/2 - position.getY()) + position.getX();//proyeccion x de la trayectoria del ballPosition en la linea de meta
	            double Dz = z - lastZ;
				double posZ = (Dz  / Dy) * (Constants.LARGO_CAMPO_JUEGO/2 - position.getY()) + z;//proyeccion z de la trayectoria del ballPosition en la linea de meta
				if(posZ <= Constants.ALTO_ARCO &&
					Math.abs(posX) < Constants.LARGO_ARCO / 2 - Constants.RADIO_BALON &&
					z - Dz <= Constants.ALTO_ARCO){
					double fvel = Math.sqrt(Dx*Dx + Dy*Dy)/Constants.REMATE_VELOCIDAD_MAX;
					double fiter = Math.max(0, Math.min(1, (oppIterToBall - iter)/75.0));
					double fx = Math.max(0, Math.min(1, 1 - Math.abs(posX) /( Constants.LARGO_ARCO / 2 - Constants.RADIO_BALON)));
					return 1 + .40*fvel + .45*fiter + .15*fx;
				}
			}
			return -1;
		}
		int iterToBall = calculateIterToBall(position, z, players, sp.myPlayersDetail(), iterToShoot);
		if(oppIterToBall <= iter){
			double fdist = Math.max(0, 1 - Constants.penalSup.distance(position)/Constants.penalSup.distance(Constants.cornerInfDer));
			double fiter = Math.max(0.0, 1 - iterToBall/75.0);
			double fvel = Math.max(0, Math.min(1, Math.sqrt(Dx*Dx + Dy*Dy)/Constants.REMATE_VELOCIDAD_MAX));
			return -1 + (.6*fdist + .1*fiter + .3*fvel);
		}			
		if(iterToBall > iter )
			return 0;			
		double fdist = Math.max(0, 1 - Constants.penalSup.distance(position)/Constants.penalSup.distance(Constants.cornerInfDer));
		double fiter = Math.max(0.0, 1 - iterToBall/75.0);
		double fball = Math.min(1,(oppIterToBall - iterToBall)/5.0);		
		double fvel = 1 - Math.sqrt(Dx*Dx + Dy*Dy)/Constants.REMATE_VELOCIDAD_MAX;
		return .6*fdist + .1*fiter + .2*fball + .1*fvel;
	}		
	
	private boolean inArea(Position pos) {
		if(Math.abs(pos.getX()) <= Constants.LARGO_AREA_GRANDE/2 &&
				pos.getY() <= Constants.centroArcoInf.getY()+Constants.ANCHO_AREA_GRANDE)
			return true;
		return false;
	}
	
	public int calculateIterToBall(Position position, double z, Position[] players, PlayerDetail[] details, int[] iterToShoot) {
		int it, best = Constants.ITERACIONES;
		for (int i = 0; i < players.length; i++) {
			if((details[i].isGoalKeeper()? Constants.ALTO_ARCO : Constants.ALTURA_CONTROL_BALON) >= z){
				it = (int) Math.ceil(Math.max(0, players[i].distance(position)- (details[i].isGoalKeeper() ? Constants.DISTANCIA_CONTROL_BALON_PORTERO : Constants.DISTANCIA_CONTROL_BALON))/Constants.getVelocidad(details[i].getSpeed()));
				if (iterToShoot[i] <= it && it < best) {
	            	best = it;                            
	            }
			}			            
        }         
		return best;		
	}
	class PassInfo{
		public Position position;
		public double velocity;
		public boolean exist;
		public double angleVer;
		public double fitness; 
		public PassInfo(Position position, double velocity, double angleVer,double fitness) {
			super();
			this.position = position;
			this.velocity = velocity;
			this.angleVer = angleVer;
			this.fitness = fitness;
			this.exist = true;
		}
		
		public PassInfo() {
			super();
			this.position = null;
			this.velocity = 0;
			this.angleVer = 0;
			this.exist = false;
		}
	}
}
