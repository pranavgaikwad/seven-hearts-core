package v1.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import v1.resources.constants.Suits;
import v1.resources.utils.exceptions.NullTableException;

public class Deck {
	private Stack<Card> cards;
	
	/**
	 * Creates single deck of cards 
	 * with default options
	 */
	public Deck() {
		init();
	}
	
	/**
	 * initiates the deck with all four suits 
	 * with default options
	 */
	public void init() {
		this.cards = new Stack<Card>();
		Suit hearts = new Suit(Suits.HEARTS, 1);
		Suit clubs = new Suit(Suits.CLUBS, 1);
		Suit spades = new Suit(Suits.SPADES, 1);
		Suit diamonds = new Suit(Suits.DIAMONDS, 1);
		addSuitsToDesk(hearts, clubs, spades, diamonds);
	}
	
	/**
	 * adds given suits to deck 
	 * @param s suit
	 */
	private void addSuitsToDesk(Suit...s) {
		for(Suit st:s) {
			addSuitToDeck(st);
		}
	}
	
	/**
	 * add given suit to deck
	 * @param s suit
	 */
	private void addSuitToDeck(Suit s) {
		for(Card c:s.getCards()) {
			addNewCard(c);
		}
	}
	
	/**
	 * add a single card to deck 
	 * @param c card
	 */
	private void addNewCard(Card c) {
		cards.push(c);
	}
	
	/**
	 * shuffles the deck
	 */
	public void shuffle() {
		Collections.shuffle(cards, new Random(System.nanoTime()));
	}
	
	/**
	 * checks if the deck is empty or not
	 * @return true if deck is empty
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	/**
	 * re-initialize deck with default options
	 */
	public void reset() {
		init();
	}
	
	/**
	 * distributes all available cards to players 
	 * @param players players to pass cards to 
	 */
	public void distributeCardsToPlayers() {
		int i = 0, temp;
		ArrayList<Player> players;
		try {
			players = Table.getInstance().getPlayers();
			while(!isEmpty()) {
				temp = i % players.size();
				players.get(temp).giveCard(cards.pop());
				i++;
			}
		} catch (NullTableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		str = str + "==> Deck : " +"\n";
		for(Card c:cards) {
			str = str + c.toString() + "\n";
		}
		return str;
	}

}
