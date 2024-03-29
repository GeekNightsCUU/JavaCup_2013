package org.javahispano.javacup.tacticas.jvc2012.masia2012;

import java.awt.Color;

import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.TacticDetail;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.render.EstiloUniforme;

public class MyTacticDetail implements TacticDetail{
	
	public String getTacticName() {
        return "La Masia";
    }

    public String getCountry() {
        return "Cuba";
    }

    public String getCoach() {
        return "Karel Osorio";
    }

    public Color getShirtColor() {
        return new Color(51, 0, 153);
    }

    public Color getShortsColor() {
        return new Color(51, 0, 153);
    }

    public Color getShirtLineColor() {
        return new Color(153, 0, 0);
    }

    public Color getSocksColor() {
        return new Color(51, 0, 153);
    }

    public Color getGoalKeeper() {
        return new Color(51, 51, 51        );
    }

    public EstiloUniforme getStyle() {
        return EstiloUniforme.LINEAS_VERTICALES;
    }

    public Color getShirtColor2() {
        return new Color(0, 0, 0);
    }

    public Color getShortsColor2() {
        return new Color(0, 0, 0);
    }

    public Color getShirtLineColor2() {
        return new Color(255, 204, 0);
    }

    public Color getSocksColor2() {
        return new Color(0, 0, 0);
    }

    public Color getGoalKeeper2() {
        return new Color(51, 51, 255);
    }

    public EstiloUniforme getStyle2() {
        return EstiloUniforme.SIN_ESTILO;
    }

    class PlayerDetailImpl implements PlayerDetail {

        String nombre;
        int numero;
        Color piel, pelo;
        double velocidad, remate, presicion;
        boolean portero;
        Position posicion;

        public PlayerDetailImpl(String nombre, int numero, Color piel, Color pelo,
                double velocidad, double remate, double presicion, boolean portero) {
            this.nombre=nombre;
            this.numero=numero;
            this.piel=piel;
            this.pelo=pelo;
            this.velocidad=velocidad;
            this.remate=remate;
            this.presicion=presicion;
            this.portero=portero;
        }

        public String getPlayerName() {
            return nombre;
        }

        public Color getSkinColor() {
            return piel;
        }

        public Color getHairColor() {
            return pelo;
        }

        public int getNumber() {
            return numero;
        }

        public boolean isGoalKeeper() {
            return portero;
        }

        public double getSpeed() {
            return velocidad;
        }

        public double getPower() {
            return remate;
        }

        public double getPrecision() {
            return presicion;
        }

    }

    public PlayerDetail[] getPlayers() {
        return new PlayerDetail[]{
        		new PlayerDetailImpl("Valdes", 1, new Color(255,200,150), new Color(50,0,0),	1.0d,0.85d,1.0d, true),
        		new PlayerDetailImpl("Pique", 3, new Color(255,200,150), new Color(50,0,0),		1.0d,0.70d,0.0d, false),
                new PlayerDetailImpl("Puyol", 5, new Color(255,200,150), new Color(50,0,0),		1.0d,0.70d,0.0d, false),
                new PlayerDetailImpl("Alba", 4, new Color(255,200,150), new Color(50,0,0),		1.0d,0.70d,0.0d, false),
                new PlayerDetailImpl("Montoya", 2, new Color(255,200,150), new Color(50,0,0),	1.0d,0.70d,0.0d, false),
                new PlayerDetailImpl("Busquet", 7, new Color(255,200,150), new Color(50,0,0),	1.0d,1.0d,0.95d, false),
                new PlayerDetailImpl("Xavi", 6, new Color(255,200,150), new Color(50,0,0),		1.0d,1.0d,0.95d, false),
                new PlayerDetailImpl("Iniesta", 8, new Color(255,200,150), new Color(50,0,0),	1.0d,1.0d,0.95d, false),
                new PlayerDetailImpl("Cuenca", 9, new Color(255,200,150), new Color(50,0,0),	1.0d,1.0d,1.0d, false),
                new PlayerDetailImpl("Messi", 10, new Color(255,200,150), new Color(50,0,0),	1.0d,1.0d,1.0d, false),
                new PlayerDetailImpl("Tello", 11, new Color(255,200,150), new Color(50,0,0),	1.0d,1.0d,1.0d, false)
        };
    }
}
