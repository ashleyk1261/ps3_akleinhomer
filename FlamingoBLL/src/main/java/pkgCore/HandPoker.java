package pkgCore;

import java.util.ArrayList;
import java.util.Collections;

import pkgEnum.*;

public class HandPoker extends Hand {

	private ArrayList<CardRankCount> CRC = null;

	public HandPoker() {
		this.setHS(new HandScorePoker());
	}

	protected ArrayList<CardRankCount> getCRC() {
		return CRC;
	}

	@Override
	public HandScore ScoreHand() {
		Collections.sort(super.getCards());
		Frequency();

		if (isRoyalFlush() || isStraightFlush() || isFourOfAKind() || isFullHouse() ||
				isFlush() || isStraight() || isThreeOfAKind() || isTwoPair() || isPair()
				|| isHighCard()) {
			return this.getHS();
		}
		return null;
	}

	private void Frequency() {

		CRC = new ArrayList<CardRankCount>();

		int iCnt = 0;
		int iPos = 0;

		for (eRank eRank : eRank.values()) {
		iCnt = (CountRank(eRank));
			if (iCnt > 0) {
				iPos = FindCardRank(eRank);
				CRC.add(new CardRankCount(eRank, iCnt, iPos));
			}
		}

		Collections.sort(CRC);
/*
		for (CardRankCount crcount : CRC) {
			System.out.print(crcount.getiCnt());
			System.out.print(" ");
			System.out.print(crcount.geteRank());
			System.out.print(" ");
			System.out.println(crcount.getiCardPosition());		
		}
		*/

	}

	private int CountRank(eRank eRank) {
		int iCnt = 0;
		for (Card c : super.getCards()) {
			if (c.geteRank() == eRank) {
				iCnt++;
			}
		}
		return iCnt;
	}

	private int FindCardRank(eRank eRank) {
		int iPos = 0;

		for (iPos = 0; iPos < super.getCards().size(); iPos++) {
			if (super.getCards().get(iPos).geteRank() == eRank) {
				break;
			}
		}
		return iPos;
	}

	public boolean isRoyalFlush() {
		boolean bisRoyalFlush = false;
		HandScorePoker HSP = (HandScorePoker)this.getHS();
		
		if (isStraightFlush() && HSP.getHiCard().geteRank()== pkgEnum.eRank.ACE) {
			HSP.seteHandStrength(eHandStrength.RoyalFlush);
			HSP.setHiCard(this.getCards().get(eCardNo.FIRST.getiCardNo()));
			HSP.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			HSP.setKickers(kickers);
			bisRoyalFlush = true;
		}
		return bisRoyalFlush;
	}

	public boolean isStraightFlush() {
		boolean bisStraightFlush = false;
		
		if (isFlush() && isStraight()) {
			HandScorePoker HSP = (HandScorePoker)this.getHS();
			HSP.seteHandStrength(eHandStrength.StraightFlush);
			/* Cards are sorted in descending order.  Set high card value to 2nd card if straight is 
			 * Ace, 2, 3,...Otherwise, set the high card value to the 1st card
			 */
			if (this.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() == pkgEnum.eRank.ACE  &&
					 this.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank() == pkgEnum.eRank.TWO) {
				HSP.setHiCard(this.getCards().get(eCardNo.SECOND.getiCardNo()));
			}
			else {
				HSP.setHiCard(this.getCards().get(eCardNo.FIRST.getiCardNo()));
			}
			HSP.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			HSP.setKickers(kickers);
			bisStraightFlush = true;
		}
		return bisStraightFlush;
	}

	public boolean isFourOfAKind() {
		boolean bisFourOfAKind = false;

		if (this.getCRC().size() == 2) {
			if (this.getCRC().get(0).getiCnt() == 4) {
				HandScorePoker HSP = (HandScorePoker)this.getHS();
				HSP.seteHandStrength(eHandStrength.FourOfAKind);
				HSP.setHiCard(this.getCards().get(this.getCRC().get(0).getiCardPosition()));
				HSP.setLoCard(null);				
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bisFourOfAKind=true;
			}
		}
		return bisFourOfAKind;
	}

	public boolean isFullHouse() {
		boolean bisFullHouse = false;

		if (this.CRC.size() == 2) {
			if ((CRC.get(0).getiCnt() == 3) && (CRC.get(1).getiCnt() == 2)) {
				bisFullHouse = true;
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.FullHouse);
				HSP.setHiCard(this.getCards().get(CRC.get(0).getiCardPosition()));
				HSP.setLoCard(this.getCards().get(CRC.get(1).getiCardPosition()));
				ArrayList<Card> kickers = new ArrayList<Card>();
				HSP.setKickers(kickers);
				this.setHS(HSP);
			}
		}
		return bisFullHouse;

	}

	public boolean isFlush() {
		boolean bisFlush = false;
		HandScorePoker HSP = (HandScorePoker) this.getHS();
		
		int iCardCnt = this.getCards().size();
		int iSuitCnt = 0;
		
		for (eSuit eSuit : eSuit.values()) {
			for (Card c : this.getCards()) {
				if (eSuit == c.geteSuit()) {
					iSuitCnt++;
				}
			}
			if (iSuitCnt > 0)
				break;
		}

		if (iSuitCnt == iCardCnt) {
			bisFlush = true;
			HSP.seteHandStrength(eHandStrength.Flush);
			HSP.setHiCard(this.getCards().get(eCardNo.FIRST.getiCardNo()));
			HSP.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(this.getCards().get(eCardNo.SECOND.getiCardNo()));
			kickers.add(this.getCards().get(eCardNo.THIRD.getiCardNo()));
			kickers.add(this.getCards().get(eCardNo.FOURTH.getiCardNo()));
			kickers.add(this.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HSP.setKickers(kickers);
		}
		return bisFlush;
	}

	public boolean isStraight() {
		HandScorePoker HSP = (HandScorePoker)this.getHS();
				
		boolean bisStraight = false;

		if (this.getCRC().size() == 5) {
			if (
				((this.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() == pkgEnum.eRank.ACE  &&
						 this.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank() == pkgEnum.eRank.TWO)
						||
						(((this.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank().getiRankNbr())-1) == 
				this.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank().getiRankNbr() )) &&
		
				(((this.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank().getiRankNbr())-1) ==
				this.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank().getiRankNbr()) &&

				(((this.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank().getiRankNbr())-1) ==
				this.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank().getiRankNbr()) &&
				
				((((this.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank().getiRankNbr())-1) ==				
				this.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank().getiRankNbr())))
	
			{		
			HSP.seteHandStrength(eHandStrength.Straight);
			if (this.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() == pkgEnum.eRank.ACE  &&
					 this.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank() == pkgEnum.eRank.TWO) {
				HSP.setHiCard(this.getCards().get(eCardNo.SECOND.getiCardNo()));
			}
			else {
				HSP.setHiCard(this.getCards().get(eCardNo.FIRST.getiCardNo()));
			}
			HSP.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			HSP.setKickers(kickers);
			bisStraight=true;
			this.setHS(HSP);
			}
	}
		return bisStraight;
	}

	public boolean isThreeOfAKind() {
		boolean bisThreeOfAKind = false;

		if (this.getCRC().size() == 3) {
			if (this.getCRC().get(0).getiCnt() == 3) {

				HandScorePoker HSP = (HandScorePoker)this.getHS();
				HSP.seteHandStrength(eHandStrength.ThreeOfAKind);
				HSP.setHiCard(this.getCards().get(this.getCRC().get(0).getiCardPosition()));
				HSP.setLoCard(null);				
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bisThreeOfAKind=true;
			}
		}
		return bisThreeOfAKind;
	}
	
	private ArrayList<Card> FindTheKickers(ArrayList<CardRankCount> CRC){
		ArrayList<Card> kickers = new ArrayList<Card>();
		
		for (CardRankCount crcCheck: CRC)
		{
			if (crcCheck.getiCnt() == 1) {
				kickers.add(this.getCards().get(crcCheck.getiCardPosition()));
			}
		}
		return kickers;
	}

	public boolean isTwoPair() {
		boolean bisTwoPair = false;

		if (this.getCRC().size() == 3) {
			if ((this.getCRC().get(0).getiCnt() == 2) &&
			(this.getCRC().get(1).getiCnt() == 2))
			{
				HandScorePoker HSP = (HandScorePoker)this.getHS();
				HSP.seteHandStrength(eHandStrength.TwoPair);				
				HSP.setHiCard(this.getCards().get(this.getCRC().get(0).getiCardPosition()));
				HSP.setLoCard(this.getCards().get(this.getCRC().get(1).getiCardPosition()));
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bisTwoPair=true;
			}
		}
		return bisTwoPair;
	}

	public boolean isPair() {
		boolean bisPair = false;
		
		if (this.getCRC().size() == 4) {
			if (this.getCRC().get(0).getiCnt() == 2)
			{
				HandScorePoker HSP = (HandScorePoker)this.getHS();
				HSP.seteHandStrength(eHandStrength.Pair);				
				HSP.setHiCard(this.getCards().get(this.getCRC().get(0).getiCardPosition()));
				HSP.setLoCard(null);	
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bisPair=true;
			}
		}	
		return bisPair;
	}

	public boolean isHighCard() {
		boolean bisHighCard = true;

		HandScorePoker HSP = (HandScorePoker)this.getHS();
		HSP.seteHandStrength(eHandStrength.HighCard);				
		HSP.setHiCard(this.getCards().get(this.getCRC().get(0).getiCardPosition()));
		HSP.setLoCard(null);	
		this.getCRC().remove(0);
		HSP.setKickers(FindTheKickers(this.getCRC()));
		this.setHS(HSP);
		bisHighCard=true;
		
		return bisHighCard;
	}
	
	private void AddCardToHand(Card c)
	{
		super.AddCard(c);
	}

	
	
}