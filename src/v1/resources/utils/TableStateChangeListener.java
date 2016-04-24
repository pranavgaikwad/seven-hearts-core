package v1.resources.utils;

import v1.resources.Card;
import v1.resources.Player;
import v1.resources.Suit;
import v1.resources.Table;

public interface TableStateChangeListener {
	public void onCardAddedToTable(Table t, Card c);
	public void onCardRemovedFromTable(Table t, Card c);
	public void onPlayerAddedToTable(Table t, Player p);
	public void onPlayerRemovedFromTable(Table t, Player p);
	public void onSuitsRefreshed(Table t);
	public void onTableFull(Table t);
	public void onOnePlayerRemaining(Player p);
}
