package v1.resources;

import java.util.ArrayList;

import v1.resources.constants.Cards;
import v1.resources.constants.Constants;
import v1.resources.constants.Suits;
import v1.resources.utils.TableStateChangeListener;
import v1.resources.utils.exceptions.NullTableException;
import v1.resources.utils.exceptions.PlayerNotFoundException;

public class Table {
	private Deck deck; 
	private ArrayList<Player> players;
	
	private ArrayList<Card> cards;
	private static ArrayList<Card> openCards= new ArrayList<Card>();
	
	private final ArrayList<Suit> localSuits = new ArrayList<Suit>();
	private int currentPlayerIndex;
	
	private static Table instance = null;
	private TableStateChangeListener l;
	
	public Table(TableStateChangeListener l, Player...players) {
		// TODO Auto-generated constructor stub
		this.players = new ArrayList<Player>();
		this.cards = new ArrayList<Card>();
		this.l = l;
		this.deck = new Deck();
		registerPlayersToTable(players);
		createLocalSuits();
		currentPlayerIndex = 0;
	}
	
	public void incrementCurrentPlayerIndex() {
		if(players.size() != 0)
			currentPlayerIndex = ( currentPlayerIndex + 1 ) % players.size();
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	
	public void createLocalSuits() {
		localSuits.add(new Suit(Suits.CLUBS));
		localSuits.add(new Suit(Suits.DIAMONDS));
		localSuits.add(new Suit(Suits.HEARTS));
		localSuits.add(new Suit(Suits.SPADES));
	}
	
	public void init(Player...players) {
		this.deck.shuffle();
		this.deck.distributeCardsToPlayers();
		updateTableStatus();
	}
	
	public void updateTableStatus() {
		updateSuits();
		updateOpenCards();
		checkTableFull();
	}
	
	public void checkTableFull() {
		if(this.players.size() == 1) 
			l.onOnePlayerRemaining(players.get(0));
		if(isTableFull())
			l.onTableFull(this);
	}
	
	public void addCardToTable(Card c) {
		cards.add(c);
		l.onCardAddedToTable(this, c);
		updateTableStatus();
	}
	
	public int addNewCardToTable(Card c) {
		if(!isMoveValid(c)) return Constants.STATUS_MOVE_INVALID;
		else {
			addCardToTable(c);
			return Constants.STATUS_MOVE_VALID;
		}
	}
	
	public boolean isMoveValid(Card c) {
		for(Card c1 : Table.openCards) if(c.equals(c1)) return true;
		return false;
	}
	
	/**
	 * provokes the re-arrangement of the new 
	 * cards according to suits
	 */
	public void updateSuits() {
		for(Card c : cards) 
			for(Suit s : localSuits) {
				if(c.getSuit() == s.getSuit()) s.addNewCard(c);
			}
		l.onSuitsRefreshed(this);
	}
	
	public void updateOpenCards() {
		Table.openCards = new ArrayList<Card>();
		if (isEmpty())
			Table.openCards.add(new Card(Cards.SEVEN, Suits.HEARTS));
		else {
			for (Suit s : localSuits) {
				Table.openCards.addAll(s.availableCards);
			}
		}
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public static Table getInstance(TableStateChangeListener l, Player...players) {
		if(instance == null) {
			instance = new Table(l, players);
		}
		return instance;
	}
	
	public static Table getInstance() throws NullTableException {
		if(instance != null) return instance;
		throw new NullTableException(Constants.MESSAGE_NULL_TABLE);
	}
	
	public void registerPlayersToTable(Player...players) {
		for(Player p:players) {
			registerPlayerToTable(p);
		}
	}
	
	public void registerPlayerToTable(Player p) {
		players.add(p);
		l.onPlayerAddedToTable(this, p);
	}
	
	public boolean isTableFull() {
		if(cards.size() == 52) return true;
		return false;
	}
	
	public void removePlayerFromTable(Player p) {
		try {
			players.remove(getPlayerIndex(p));
			l.onPlayerRemovedFromTable(this, p);
		} catch (PlayerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
	public ArrayList<Card> getAvailableMovesForCurrentPlayer(){
		Player p = this.players.get(currentPlayerIndex);
		return getAvailableMovesFor(p);
	}
	
	public ArrayList<Card> getAvailableMovesFor(Player p) {
		ArrayList<Card> playerCards = p.getCards();
		ArrayList<Card> availableCards = new ArrayList<Card>();
		for(Card c1: playerCards) {
			for(Card c2: Table.openCards) if((c2 != null) && c1.equals(c2)) availableCards.add(c1);
		}
		return availableCards;
	}
	
	public Player whoHasSevenOfHearts() {
		for(Player p : this.players) if(p.hasCard(new Card(Cards.SEVEN, Suits.HEARTS))) return p;
		return null;
	}
	
	/**
	 * returns string representation of possible moves for current
	 * status of the table
	 * @return open cards
	 */
	public String getOpenCardsAsString() {
		String oc = "Available moves : " + "\n";
		if(Table.openCards == null) System.out.println("null opencards");
		for(Card c : Table.openCards) {
			if(oc == null) System.out.println("oc null");
			if(c == null) System.out.println("c null");
			else oc = oc + c.toString() + "\n";
		}
		return oc;
	}
	
	/**
	 * returns index for the given player 
	 * @param p player
	 * @return index of the player
	 * @throws PlayerNotFoundException if player is not available on the current table
	 */
	public int getPlayerIndex(Player p) throws PlayerNotFoundException {
		int i = 0;
		for(Player pl : players) {
			if(pl.equals(p)) return i;
			i++;
		}
		throw new PlayerNotFoundException(Constants.MESSAGE_PLAYER_NOT_FOUND);
	}
	
	/**
	 * returns current status of the table in {@link String} object
	 * @return status 
	 */
	public String getTableStatus() {
		String status = "==> Players : " + "\n";
		for(Player p:players)
			status = status + p.toString() + "\n"; 
		status = status + "==> Cards on table : " + "\n";
		status = status + String.valueOf(cards.size()) + "\n";
		return status;
	}
	
	public String getCardsStatus() {
		String status = "==> cards on table : " + "\n";
		for(Card c:cards) {
			status = status + c.toString() + "\n";
		}
		return status;
	}
}
