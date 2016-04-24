package v1.resources.tests;

import org.junit.Test;

import v1.resources.Game;
import v1.resources.Player;
import v1.resources.utils.PlayerStateChangeListener;
import v1.resources.utils.exceptions.CardNotFoundException;

public class GameTests implements PlayerStateChangeListener{

	public GameTests() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testInit() throws CardNotFoundException {
		String p1 = new String("Pranav");
		String p2 = new String("Advait");
		String p3 = new String("Ahuja");
		Game game = new Game(p1, p2, p3);
		game.start();
//		System.out.println(p1.getStatus());
//		System.out.println(p2.getStatus());
//		System.out.println(p3.getStatus());
//		System.out.println(game.getDeck().toString());
	}

	@Override
	public void onPlayerCardsExhausted(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerCardsExhausted(Player p, String status) {
		// TODO Auto-generated method stub
		
	}
}
