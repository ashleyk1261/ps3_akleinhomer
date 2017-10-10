package pkgCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.commons.math3.exception.OutOfRangeException;

import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;

public class Deck {

	private ArrayList<Card> cardsInDeck = new ArrayList<Card>();

	public Deck() {
		for (eSuit eSuit : eSuit.values()) {
			for (eRank eRank : eRank.values()) {
				cardsInDeck.add(new Card(eSuit, eRank));
			}
		}
		Collections.shuffle(cardsInDeck);
	}

	// Method throws an exception if the deck is empty
	public Card Draw() throws DeckException {
		if (cardsInDeck.size() == 0) {
			throw new DeckException(this);
		}
		return cardsInDeck.remove(0);
	}

	// An overloaded Draw method that draws a card of a given eSuit
	public Card Draw(eSuit eSuit) throws DeckException {
		if (cardsInDeck.size() == 0) {
			throw new DeckException(this);
		} else {
			if (Count(eSuit) != 0) {
				ArrayList<Card> subsetOfCards = cardsInDeck.stream().filter(deckCards -> deckCards.geteSuit() == eSuit)
						.collect(Collectors.toCollection(ArrayList::new));
				cardsInDeck.remove(subsetOfCards.get(0));
				return subsetOfCards.get(0);
			} else
				return null;
		}
	}

	// An overloaded Draw method that draws a card of a given eRank
	public Card Draw(eRank eRank) throws DeckException {
		if (cardsInDeck.size() == 0) {
			throw new DeckException(this);
		} else {
			if (Count(eRank) != 0) {
				ArrayList<Card> subsetOfCards = cardsInDeck.stream().filter(deckCards -> deckCards.geteRank() == eRank)
						.collect(Collectors.toCollection(ArrayList::new));
				cardsInDeck.remove(subsetOfCards.get(0));
				return subsetOfCards.get(0);
			} else
				return null;
		}
	}

	// A method that returns the number of a given eSuit left in the deck.
	public int Count(eSuit eSuit) {
		ArrayList<Card> subsetOfCards = cardsInDeck.stream().filter(deckCards -> deckCards.geteSuit() == eSuit)
				.collect(Collectors.toCollection(ArrayList::new));
		return subsetOfCards.size();
	}

	// A method that returns the number of a given eRank left in the deck.
	public int Count(eRank eRank) {
		ArrayList<Card> subsetOfCards = cardsInDeck.stream().filter(deckCards -> deckCards.geteRank() == eRank)
				.collect(Collectors.toCollection(ArrayList::new));
		return subsetOfCards.size();
	}

	// A method that returns 0 or 1 if a given card is left in the deck.
	public int Count(Card card) {
		ArrayList<Card> subsetOfCards = cardsInDeck.stream().filter(
				deckCards -> ((deckCards.geteRank() == card.geteRank()) && (deckCards.geteSuit() == card.geteSuit())))
				.collect(Collectors.toCollection(ArrayList::new));
		return subsetOfCards.size();
	}


}
