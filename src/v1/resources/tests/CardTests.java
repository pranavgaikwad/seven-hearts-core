package v1.resources.tests;

import org.junit.Test;

import junit.framework.Assert;
import v1.resources.Card;
import v1.resources.constants.Cards;
import v1.resources.constants.Constants;
import v1.resources.constants.Suits;

public class CardTests {
	
	public CardTests() {
		
	}
	
	@Test
	public void testGetMD5() {
		Card c1 = new Card(Cards.ACE, Suits.SPADES);
		Card c2 = new Card(Cards.ACE, Suits.CLUBS);
		System.out.println(c1.getId());
		System.out.println(c2.getId());
	}
	
	@Test
	public void testEquals() {
		Card c1 = new Card(Cards.ACE, Suits.SPADES);
		boolean expected = false;
		boolean actual = c1.equals(new Card(Cards.ACE, Suits.CLUBS));
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCompareRankWith() {
		Card c1 = new Card(Cards.ACE, Suits.SPADES);
		Card c2 = new Card(Cards.TWO, Suits.SPADES);
		Card c3 = new Card(Cards.ACE, Suits.CLUBS);
		
		int expected = Constants.STATUS_GREATER;
		int actual = c1.compareCardRankWith(c2);
		Assert.assertEquals(expected, actual);
		
		expected = Constants.STATUS_EQUAL;
		actual = c1.compareCardRankWith(c3);
		Assert.assertEquals(expected, actual);
		
		expected = Constants.STATUS_LOWER;
		actual = c2.compareCardRankWith(c1);
		Assert.assertEquals(expected, actual);
	}
}
