package v1.resources.tests;

import org.junit.Test;

import v1.resources.Card;
import v1.resources.Suit;
import v1.resources.constants.Cards;
import v1.resources.constants.Suits;

public class SuitTests {
	public SuitTests() {
		
	}
	
	@Test
	public void testSort() {
//		Suit hearts = new Suit(Suits.HEARTS, 1);
//		System.out.println(hearts.toString());
//		hearts.shuffle();
//		System.out.println(hearts.toString());
//		hearts.sort();
//		System.out.println(hearts.toString());
	}
	
	@Test
	public void testUpdateAvailableCards() {
		Suit hearts = new Suit(Suits.HEARTS);
		hearts.addNewCard(new Card(Cards.SEVEN, Suits.HEARTS));
		System.out.println(hearts.availableCards.toString());
	}

}
