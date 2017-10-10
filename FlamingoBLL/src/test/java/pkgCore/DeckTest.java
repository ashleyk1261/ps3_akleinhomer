package pkgCore;

import static org.junit.Assert.*;

import org.junit.Test;
import pkgException.DeckException;

import pkgEnum.eRank;
import pkgEnum.eSuit;

public class DeckTest {

	@Test
	public void TestEmptyDeck() {
		// TODO: Build a deck, draw until you get a DeckException
		try {
			Deck AKDeck = new Deck();
			for (int i = 1; i <= 54; i++) {
				AKDeck.Draw();
			}
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}

	}

	@Test
	public void TestDrawSuit() {
		// TODO: Build a deck, test the Draw(eSuit) method
		try {
			Deck AKDeck = new Deck();
			assertEquals(eSuit.DIAMONDS, AKDeck.Draw(eSuit.DIAMONDS).geteSuit());
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}
	}

	@Test
	public void TestDrawRank() {
		// TODO: Build a deck, test the Draw(eRank) method
		try {
			Deck AKDeck = new Deck();
			assertEquals(eRank.FOUR, AKDeck.Draw(eRank.FOUR).geteRank());
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}
	}

	@Test
	public void TestDeckRankCount() {
		// TODO: Build a deck, test the DeckRankCount method
		try {
			Deck AKDeck = new Deck();
			for (int i = 4; i >= 0; i--) {
				assertEquals(i, AKDeck.Count(eRank.ACE));
				AKDeck.Draw(eRank.ACE);
			}
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}
	}

	@Test
	public void TestDeckSuitCount() {
		// TODO: Build a deck, test the DeckSuitCount method
		try {
			Deck AKDeck = new Deck();
			for (int i = 13; i >= 0; i--) {
				assertEquals(i, AKDeck.Count(eSuit.HEARTS));
				AKDeck.Draw(eSuit.HEARTS);
			}
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}
	}

	@Test
	public void TestDeckCount() {
		Deck AKDeck = new Deck();
		Card testCard = new Card(eSuit.CLUBS, eRank.ACE);
		assertEquals(1, AKDeck.Count(testCard));
	}

	@Test
	public void TestDeckCountNoMatch() {
		try {
			Deck AKDeck = new Deck();
			Card testCard = new Card(eSuit.CLUBS, eRank.ACE);
			for (int i = 5; i >= 0; i--) {
				AKDeck.Draw(eRank.ACE);
			}
			assertEquals(0, AKDeck.Count(testCard));
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}
	}

	@Test
	public void TestDeckCountNoMatch2() {
		try {
			Deck AKDeck = new Deck();
			Card testCard = new Card(eSuit.CLUBS, eRank.ACE);
			for (int i = 14; i >= 0; i--) {
				AKDeck.Draw(eSuit.CLUBS);
			}
			assertEquals(0, AKDeck.Count(testCard));
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}
	}

	@Test
	public void TestDeckDrawFromEmptyDeck() {
		try {
			Deck AKDeck = new Deck();
			Card testCard = new Card(eSuit.CLUBS, eRank.ACE);
			for (int i = 52; i >= 1; i--) {
				AKDeck.Draw();
			}
			AKDeck.Draw(eSuit.CLUBS);
			assertEquals(0, AKDeck.Count(testCard));
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}
	}

	@Test
	public void TestDeckDrawFromEmptyDeck2() {
		try {
			Deck AKDeck = new Deck();
			Card testCard = new Card(eSuit.CLUBS, eRank.ACE);
			for (int i = 52; i >= 1; i--) {
				AKDeck.Draw();
			}
			AKDeck.Draw(eRank.THREE);
			assertEquals(0, AKDeck.Count(testCard));
		} catch (DeckException ex) {
			System.out.println("The deck is empty.  No more cards can be drawn.");
		}
	}

}
