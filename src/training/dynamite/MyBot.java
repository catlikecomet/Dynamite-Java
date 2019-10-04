package training.dynamite;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.*;

import java.util.Iterator;

public class MyBot implements Bot {

    public MyBot() {
        // Are you debugging?
        // Put a breakpoint on the line below to see when we start a new match
        System.out.println("Started new match");
    }

        public Move waterMove(Gamestate gamestate){
            if (this.numberOfEnemyDynamites(gamestate) == 100){
                return this.getRandomMove();
            }else if (gamestate.getRounds().size() == 0){
                return this.getRandomMove();
            }else {
                Round lastRound = gamestate.getRounds().get(gamestate.getRounds().size() - 5);
                if(lastRound.getP2() != Move.D) {
                    int waterRandom = (int) Math.floor(Math.random() * 2.0D);
                    Move[] possibleMoveWithWater = new Move[]{Move.R, Move.W};
                    Move randomWater = possibleMoveWithWater[waterRandom];
                    return randomWater;
                }
            }
            return waterMove(gamestate);
        }

        public Move makeMove(Gamestate gamestate) {
            if (this.numberOfDynamitesPlayed(gamestate) == 100) {
                return this.getRandomMove();
            } else if (gamestate.getRounds().size() == 0) {
                return this.getRandomMove();
            } else {
                Round lastRound = gamestate.getRounds().get(gamestate.getRounds().size() - 1);
                return lastRound.getP1() == lastRound.getP2() ? Move.D : this.getRandomMove();
            }
        }

        private int numberOfEnemyDynamites(Gamestate gamestate){
            int enemyDynamites = 0;
            Iterator var2 = gamestate.getRounds().iterator();

            while(var2.hasNext()){
                Round round = (Round)var2.next();
                if(round.getP2() == Move.D){
                    ++enemyDynamites;
                }
            }
            return enemyDynamites;
        }

        private int numberOfDynamitesPlayed(Gamestate gamestate) {
            int dynamites = 0;
            Iterator var3 = gamestate.getRounds().iterator();

            while(var3.hasNext()) {
                Round round = (Round)var3.next();
                if (round.getP1() == Move.D) {
                    ++dynamites;
                }
            }

            return dynamites;
        }

    public Move getRandomMove() {
        // Are you debugging?
        // Put a breakpoint in this method to see when we make a move
        int randomNumberBetween0And3 = (int)Math.floor(Math.random() * 3.0D);
        Move[] possibleMoves = new Move[]{Move.R, Move.P, Move.S};
        Move randomMove = possibleMoves[randomNumberBetween0And3];
        return randomMove;

    }
}

