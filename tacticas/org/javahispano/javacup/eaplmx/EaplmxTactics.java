package org.javahispano.javacup.eaplmx;

import java.awt.Color;

import org.javahispano.javacup.model.*;
import org.javahispano.javacup.model.util.*;
import org.javahispano.javacup.render.EstiloUniforme;
import org.javahispano.javacup.model.command.*;
import org.javahispano.javacup.model.engine.GameSituations;

import java.util.LinkedList;
import java.util.List;

public class EaplmxTactics implements Tactic {
    Position alineacion1[] = new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-31.082089552238806),
        new Position(11.16030534351145,-31.6044776119403),
        new Position(27.251908396946565,-27.94776119402985),
        new Position(-29.84732824427481,-26.902985074626866),
        new Position(8.564885496183205,-7.574626865671642),
        new Position(-10.641221374045802,-7.052238805970149),
        new Position(18.545454545454543,12.115384615384617),
        new Position(-21.3986013986014,13.065610859728507),
        new Position(-8.083916083916083,26.131221719457013),
        new Position(3.5664335664335667,32.782805429864254)
    };

    Position alineacion2[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-19.46564885496183,-31.6044776119403),
        new Position(0.2595419847328244,-31.082089552238806),
        new Position(19.984732824427482,-31.6044776119403),
        new Position(7.526717557251908,-11.753731343283583),
        new Position(-8.564885496183205,-11.753731343283583),
        new Position(-24.65648854961832,-2.3507462686567164),
        new Position(23.099236641221374,-2.873134328358209),
        new Position(-15.454545454545453,22.805429864253394),
        new Position(0.4755244755244755,13.778280542986426),
        new Position(15.216783216783217,23.042986425339365)
    };

    Position alineacion3[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-31.082089552238806),
        new Position(11.16030534351145,-31.6044776119403),
        new Position(26.732824427480914,-20.111940298507463),
        new Position(-29.32824427480916,-21.67910447761194),
        new Position(0.2595419847328244,-0.26119402985074625),
        new Position(-18.946564885496183,-0.26119402985074625),
        new Position(18.946564885496183,-0.26119402985074625),
        new Position(-19.46564885496183,35.78358208955224),
        new Position(-0.2595419847328244,19.067164179104477),
        new Position(18.946564885496183,35.26119402985075)
    };

    Position alineacion4[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-31.082089552238806),
        new Position(11.16030534351145,-31.6044776119403),
        new Position(28.290076335877863,-28.470149253731343),
        new Position(-28.290076335877863,-28.470149253731343),
        new Position(11.16030534351145,-1.3059701492537314),
        new Position(-10.641221374045802,-0.7835820895522387),
        new Position(-27.251908396946565,31.6044776119403),
        new Position(-10.641221374045802,30.559701492537314),
        new Position(9.603053435114505,28.992537313432837),
        new Position(25.69465648854962,28.992537313432837)
    };

    Position alineacion5[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-35.78358208955224),
        new Position(12.717557251908397,-35.26119402985075),
        new Position(28.290076335877863,-28.470149253731343),
        new Position(-28.290076335877863,-28.470149253731343),
        new Position(8.797202797202797,-16.391402714932127),
        new Position(-9.986013986013985,-16.866515837104075),
        new Position(-23.618320610687025,-0.7835820895522387),
        new Position(5.969465648854961,-5.485074626865671),
        new Position(0.2595419847328244,-0.26119402985074625),
        new Position(22.580152671755727,-1.3059701492537314)
    };

    Position alineacion6[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-35.78358208955224),
        new Position(12.717557251908397,-35.26119402985075),
        new Position(28.290076335877863,-28.470149253731343),
        new Position(-28.290076335877863,-28.470149253731343),
        new Position(9.034965034965035,-19.479638009049776),
        new Position(-9.510489510489512,-19.2420814479638),
        new Position(-23.618320610687025,-0.7835820895522387),
        new Position(6.6573426573426575,-10.927601809954751),
        new Position(-7.132867132867133,-11.402714932126697),
        new Position(22.580152671755727,-1.3059701492537314)
    };

    class TacticDetailImpl implements TacticDetail {

        public String getTacticName() {
            return "Eapl.mx Geek Knights CUU";
        }

        public String getCountry() {
            return "México";
        }

        public String getCoach() {
            return "Emmanuel Parada Licea";
        }

        public Color getShirtColor() {
            return new Color(255, 255, 255);
        }

        public Color getShortsColor() {
            return new Color(0, 0, 0);
        }

        public Color getShirtLineColor() {
            return new Color(204, 204, 204);
        }

        public Color getSocksColor() {
            return new Color(255, 255, 255);
        }

        public Color getGoalKeeper() {
            return new Color(126, 88, 237        );
        }

        public EstiloUniforme getStyle() {
            return EstiloUniforme.FRANJA_DIAGONAL;
        }

        public Color getShirtColor2() {
            return new Color(131, 202, 208);
        }

        public Color getShortsColor2() {
            return new Color(91, 154, 55);
        }

        public Color getShirtLineColor2() {
            return new Color(160, 67, 167);
        }

        public Color getSocksColor2() {
            return new Color(151, 3, 141);
        }

        public Color getGoalKeeper2() {
            return new Color(49, 124, 166        );
        }

        public EstiloUniforme getStyle2() {
            return EstiloUniforme.FRANJA_VERTICAL;
        }

        class JugadorImpl implements PlayerDetail {
            String nombre;
            int numero;
            Color piel, pelo;
            double velocidad, remate, presicion;
            boolean portero;
            Position Position;

            public JugadorImpl(String nombre, int numero, Color piel, Color pelo,
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
                new JugadorImpl("Emmanuel", 1, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, true),
                new JugadorImpl("Aro", 2, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Ricardo", 3, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Magio", 4, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Balam", 5, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Jugador", 6, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Jugador", 7, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Jugador", 8, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Jugador", 9, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Jugador", 10, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false),
                new JugadorImpl("Jugador", 11, new Color(255,200,150), new Color(50,0,0),0.78d,0.91d,0.81d, false)
            };
        }
    }

    TacticDetail detalle = new TacticDetailImpl();
    public TacticDetail getDetail() {
        return detalle;
    }

    public Position[] getStartPositions(GameSituations sp) {
    return alineacion5;
    }

    public Position[] getNoStartPositions(GameSituations sp) {
    return alineacion6;
    }
    
    LinkedList<Command> commands = new LinkedList<Command>();

    public List<Command> execute(GameSituations sp) {
        commands.clear();
        Position ball = sp.ballPosition();
        Position[] players = sp.myPlayers();
        
        // Move the closest player to the ball
        int closest_player = ball.nearestIndex(players);
        commands.add(new CommandMoveTo(closest_player, ball));
        
        // TO DO: Align the other players to their formations
        int[] can_kick = sp.canKick();
        
        for (int current_player:can_kick) {
        	commands.add(new CommandHitBall(current_player, Constants.posteIzqArcoSup, 0.4, true));
        }
        
        return commands;
    }
    
    
    
    
}















