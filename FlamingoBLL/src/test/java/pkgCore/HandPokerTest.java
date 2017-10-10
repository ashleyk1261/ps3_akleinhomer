package pkgCore;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import pkgEnum.*;


public class HandPokerTest {

	public HandScorePoker HandHelper(ArrayList<Card> cards) {

		HandScorePoker result = null;

		try {
			
			//	c = structure of class 'Hand'
			Class<?> c = Class.forName("pkgCore.HandPoker");

			//	the arguements for 'AddCard' is a instance of Card
			Class[] cArgsAddCard = new Class[1];
			cArgsAddCard[0] = pkgCore.Card.class;

			//	Create an instance of Hand
			Object inst = c.newInstance();

			//	Find the method 'AddCard' in Hand
			Method mAddCard = c.getDeclaredMethod("AddCardToHand", cArgsAddCard);
			
			//	Make the private method accessible 
			mAddCard.setAccessible(true);
			
			//	Invoke the AddCard method for each card passed into this method
			for (Card card: cards)
			{
				mAddCard.invoke(inst, card);
			}
			
			//	Find the method 'ScoreHand' in 'Hand'
			Method mScore = c.getDeclaredMethod("ScoreHand", null);
			
			//	Invoke 'ScoreHand'.  It returns an array of integers
			result = (HandScorePoker) mScore.invoke(inst, null);


		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
public void PrintResult(String result, ArrayList<Card> hand) {
			for (Card c: hand)
	{
		System.out.println(c.geteRank() + " of " +c.geteSuit());
	}
		System.out.println("-------> " + result + "\n\n");

	}


	
	@Test
public void test1() 
{
	
	ArrayList<Card> cards = new ArrayList<Card>();
					
	cards.add(new Card(eSuit.CLUBS,eRank.ACE));
	cards.add(new Card(eSuit.CLUBS,eRank.KING));
	cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
	cards.add(new Card(eSuit.CLUBS,eRank.JACK));
	cards.add(new Card(eSuit.CLUBS,eRank.TEN));
	
	HandScorePoker score = HandHelper(cards);
	assertEquals(pkgEnum.eHandStrength.RoyalFlush,score.geteHandStrength());
	assertEquals(pkgEnum.eRank.ACE,score.getHiCard().geteRank());
	assertEquals(null,score.getLoCard());
	assertEquals(score.getKickers().size(),0);
	PrintResult(score.geteHandStrength().name(), cards);
}
	
	@Test
public void test2() 
{
	
	ArrayList<Card> cards = new ArrayList<Card>();
	
	cards.add(new Card(eSuit.CLUBS,eRank.TEN));
	cards.add(new Card(eSuit.CLUBS,eRank.EIGHT));
	cards.add(new Card(eSuit.CLUBS,eRank.SIX));
	cards.add(new Card(eSuit.CLUBS,eRank.SEVEN));
	cards.add(new Card(eSuit.CLUBS,eRank.NINE));
	
	HandScorePoker score = HandHelper(cards);
	assertEquals(pkgEnum.eHandStrength.StraightFlush,score.geteHandStrength());
	assertEquals(pkgEnum.eRank.TEN,score.getHiCard().geteRank());
	assertEquals(null,score.getLoCard());
	assertEquals(score.getKickers().size(),0);
	PrintResult(score.geteHandStrength().name(), cards);	
}	
	


	@Test
public void test3() 
{
	
	ArrayList<Card> cards = new ArrayList<Card>();
	
	cards.add(new Card(eSuit.CLUBS,eRank.TWO));
	cards.add(new Card(eSuit.CLUBS,eRank.ACE));
	cards.add(new Card(eSuit.CLUBS,eRank.THREE));
	cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
	cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
	
	HandScorePoker score = HandHelper(cards);
	assertEquals(pkgEnum.eHandStrength.StraightFlush,score.geteHandStrength());
	assertEquals(pkgEnum.eRank.FIVE,score.getHiCard().geteRank());
	assertEquals(null,score.getLoCard());
	assertEquals(score.getKickers().size(),0);
	PrintResult(score.geteHandStrength().name(), cards);
}	
	
	
	
	@Test
public void test4() 
{
	
	ArrayList<Card> cards = new ArrayList<Card>();
	
	cards.add(new Card(eSuit.CLUBS,eRank.KING));
	cards.add(new Card(eSuit.CLUBS,eRank.KING));
	cards.add(new Card(eSuit.CLUBS,eRank.KING));
	cards.add(new Card(eSuit.CLUBS,eRank.KING));
	cards.add(new Card(eSuit.CLUBS,eRank.ACE));
	
	HandScorePoker score = HandHelper(cards);
	assertEquals(pkgEnum.eHandStrength.FourOfAKind,score.geteHandStrength());
	assertEquals(pkgEnum.eRank.KING,score.getHiCard().geteRank());
	assertEquals(null,score.getLoCard());
	assertEquals(score.getKickers().size(),1);
	PrintResult(score.geteHandStrength().name(), cards);
}	

	@Test
	public void test5() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.JACK));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.FourOfAKind,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.QUEEN,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),1);
		PrintResult(score.geteHandStrength().name(), cards);
	}	

	@Test
	public void test6() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.JACK));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		cards.add(new Card(eSuit.CLUBS,eRank.JACK));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.FullHouse,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.QUEEN,score.getHiCard().geteRank());
		assertEquals(pkgEnum.eRank.JACK,score.getLoCard().geteRank());
		assertEquals(score.getKickers().size(),0);
		PrintResult(score.geteHandStrength().name(), cards);
	}	
	
	@Test
	public void test6_5() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.JACK));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		cards.add(new Card(eSuit.CLUBS,eRank.JACK));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.FullHouse,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.FOUR,score.getHiCard().geteRank());
		assertEquals(pkgEnum.eRank.JACK,score.getLoCard().geteRank());
		assertEquals(score.getKickers().size(),0);
		PrintResult(score.geteHandStrength().name(), cards);
	}	
	
	@Test
	public void test7() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TEN));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		cards.add(new Card(eSuit.CLUBS,eRank.SEVEN));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Flush,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.QUEEN,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),4);
		PrintResult(score.geteHandStrength().name(), cards);
	}	

	@Test
	public void test8() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.HEARTS,eRank.TEN));
		cards.add(new Card(eSuit.HEARTS,eRank.FOUR));
		cards.add(new Card(eSuit.HEARTS,eRank.KING));
		cards.add(new Card(eSuit.HEARTS,eRank.SEVEN));
		cards.add(new Card(eSuit.HEARTS,eRank.TWO));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Flush,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.KING,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),4);
		PrintResult(score.geteHandStrength().name(), cards);
	}	

	@Test
	public void test9() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.SPADES,eRank.TEN));
		cards.add(new Card(eSuit.SPADES,eRank.FOUR));
		cards.add(new Card(eSuit.SPADES,eRank.ACE));
		cards.add(new Card(eSuit.SPADES,eRank.SEVEN));
		cards.add(new Card(eSuit.SPADES,eRank.TWO));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Flush,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.ACE,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),4);
		PrintResult(score.geteHandStrength().name(), cards);
	}	
	
	
	@Test
	public void test10() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.DIAMONDS,eRank.TEN));
		cards.add(new Card(eSuit.DIAMONDS,eRank.FOUR));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TEN));
		cards.add(new Card(eSuit.DIAMONDS,eRank.SEVEN));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TWO));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Flush,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.TEN,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),4);
		PrintResult(score.geteHandStrength().name(), cards);
	}	
	
	
	@Test
	public void test11() 
	{		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.SEVEN));
		cards.add(new Card(eSuit.HEARTS,eRank.NINE));
		cards.add(new Card(eSuit.CLUBS,eRank.EIGHT));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TEN));
		cards.add(new Card(eSuit.CLUBS,eRank.JACK));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Straight,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.JACK,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),0);
		PrintResult(score.geteHandStrength().name(), cards);
	}	

	@Test
	public void test12() 
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.ACE));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		cards.add(new Card(eSuit.DIAMONDS,eRank.THREE));
		cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Straight,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.FIVE,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),0);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test13() 	{
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.KING));
		cards.add(new Card(eSuit.HEARTS,eRank.ACE));
		cards.add(new Card(eSuit.CLUBS,eRank.JACK));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TEN));
		cards.add(new Card(eSuit.CLUBS,eRank.QUEEN));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Straight,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.ACE,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),0);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test14() 	{
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.DIAMONDS,eRank.THREE));
		cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.ThreeOfAKind,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.TWO,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),2);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test15() 	{	
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		cards.add(new Card(eSuit.HEARTS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.ThreeOfAKind,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.TWO,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),2);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test16() 	{	
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		cards.add(new Card(eSuit.HEARTS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.ThreeOfAKind,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.TWO,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),2);
		PrintResult(score.geteHandStrength().name(), cards);
	}

	@Test
	public void test17() 
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.ThreeOfAKind,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.TWO,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),2);
		PrintResult(score.geteHandStrength().name(), cards);
	}

	@Test
	public void test18() {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.ThreeOfAKind,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.TWO,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),2);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test19() 	{
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
		cards.add(new Card(eSuit.HEARTS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.ThreeOfAKind,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.TWO,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),2);
		PrintResult(score.geteHandStrength().name(), cards);
	}

	@Test
	public void test20() 	{
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.FOUR));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		cards.add(new Card(eSuit.DIAMONDS,eRank.THREE));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.ThreeOfAKind,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.THREE,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),2);
		PrintResult(score.geteHandStrength().name(), cards);
	}

	@Test
	public void test21() {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		cards.add(new Card(eSuit.HEARTS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.DIAMONDS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.TwoPair,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.THREE,score.getHiCard().geteRank());
		assertEquals(pkgEnum.eRank.TWO,score.getLoCard().geteRank());
		assertEquals(score.getKickers().size(),1);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test22() {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		cards.add(new Card(eSuit.DIAMONDS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.TwoPair,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.FIVE,score.getHiCard().geteRank());
		assertEquals(pkgEnum.eRank.FOUR,score.getLoCard().geteRank());
		assertEquals(score.getKickers().size(),1);
		PrintResult(score.geteHandStrength().name(), cards);
	}

	@Test
	public void test23() {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
		cards.add(new Card(eSuit.DIAMONDS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.TwoPair,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.FIVE,score.getHiCard().geteRank());
		assertEquals(pkgEnum.eRank.TWO,score.getLoCard().geteRank());
		assertEquals(score.getKickers().size(),1);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test24() {
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		cards.add(new Card(eSuit.HEARTS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.SIX));
		cards.add(new Card(eSuit.DIAMONDS,eRank.QUEEN));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Pair,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.THREE,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),3);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test25() 	{		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		cards.add(new Card(eSuit.HEARTS,eRank.FOUR));
		cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
		cards.add(new Card(eSuit.DIAMONDS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Pair,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.FIVE,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),3);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test26() 
	{	
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.SIX));
		cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
		cards.add(new Card(eSuit.DIAMONDS,eRank.FIVE));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Pair,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.FIVE,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),3);
		PrintResult(score.geteHandStrength().name(), cards);
	}

	@Test
	public void test27() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.TWO));
		cards.add(new Card(eSuit.HEARTS,eRank.SIX));
		cards.add(new Card(eSuit.CLUBS,eRank.FIVE));
		cards.add(new Card(eSuit.DIAMONDS,eRank.FOUR));
		cards.add(new Card(eSuit.CLUBS,eRank.FOUR));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.Pair,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.FOUR,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),3);
		PrintResult(score.geteHandStrength().name(), cards);
	}
	
	@Test
	public void test28() 
	{
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.ACE));
		cards.add(new Card(eSuit.HEARTS,eRank.TWO));
		cards.add(new Card(eSuit.CLUBS,eRank.SIX));
		cards.add(new Card(eSuit.DIAMONDS,eRank.SEVEN));
		cards.add(new Card(eSuit.CLUBS,eRank.THREE));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.HighCard,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.ACE,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());
		assertEquals(score.getKickers().size(),4);
		PrintResult(score.geteHandStrength().name(), cards);
	}

	@Test
	public void test29() 
	{	
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card(eSuit.CLUBS,eRank.JACK));
		cards.add(new Card(eSuit.HEARTS,eRank.TEN));
		cards.add(new Card(eSuit.CLUBS,eRank.NINE));
		cards.add(new Card(eSuit.DIAMONDS,eRank.EIGHT));
		cards.add(new Card(eSuit.CLUBS,eRank.SIX));
		
		HandScorePoker score = HandHelper(cards);
		assertEquals(pkgEnum.eHandStrength.HighCard,score.geteHandStrength());
		assertEquals(pkgEnum.eRank.JACK,score.getHiCard().geteRank());
		assertEquals(null,score.getLoCard());		
		assertEquals(score.getKickers().size(),4);
		PrintResult(score.geteHandStrength().name(), cards);
	}
}