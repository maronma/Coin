package Coin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Coin {
	List<Integer> coins;
	List<Integer> coins2;
	static int n = 6;

	public Coin() {
		coins = new ArrayList<Integer>(15);
		coins2 = new ArrayList<Integer>(15);
	}

	// losowanie monet
	public List<Integer> lottery() {
		Random generator = new Random();
		for (int i = 0; i < n; i++) {
			coins.add(generator.nextInt(2));
		}
		return coins;
	}

	public int checkPair() {
		int pairNumber = 0;
		for (int i = 1; i < coins.size(); i++) {
			if ((coins.get(i)).equals(coins.get(i - 1))) {
				pairNumber++;
			}
		}
		return pairNumber;
	}

	public int checkPairInNewList() {
		int pairNumber = 0;
		for (int i = 1; i < coins2.size(); i++) {
			if ((coins2.get(i)).equals(coins2.get(i - 1))) {
				pairNumber++;
			}
		}
		return pairNumber;
	}

	public List<Integer> changeOneCoin() {
		coins2 = new ArrayList<Integer>(coins);
		boolean isCoinChanged = false;

		// 1 warunek-szukamy mozliwosci uzyskania dodatkowych 2 par
		isCoinChanged = addTwoPairs(isCoinChanged);
		// 2 warunek -szukamy mozliwosci uzyskania dodatkowo 1 pary

		if (isCoinChanged == false && n > 2) {
			isCoinChanged = addOnePair(isCoinChanged);
		}
		// 3 warunek- sytuacja gdzie nie zyskujemy nowej pary (ta sama ilosc par
		// po zmianie monety)
		if (isCoinChanged == false) {
			isCoinChanged = addZeroPairs(isCoinChanged);

		}
		// 4 warunek- wszystkie monety to orly albo reszki (-1 para po zmianie
		// monety)
		if (isCoinChanged == false) {
			changeElement(0, coins2);
		}	
		return coins2;
	}

	private boolean addZeroPairs(boolean isCoinChanged) {
		for (int i = 1; i < (coins2.size() - 1); i++) {
			if ((coins2.get(i)).equals(coins2.get(i - 1)) == true
					&& (coins2.get(i)).equals(coins2.get(i + 1)) == false
					&& isCoinChanged == false) {

				isCoinChanged = true;

				changeElement(i + 1, coins2);
				break;

			}
		}
		return isCoinChanged;
	}

	private boolean addOnePair(boolean isCoinChanged) {

		if (checkIfDifferentValuesOnLeftEdge(coins2)) {
			// podmienia element zerowy
//			System.out.println("Zmieniam lewa skrajna");
			changeElement(0, coins2);
			isCoinChanged = true;

			// podmienia element ostatni
		} else if (checkIfDifferentValuesOnRightEdge(coins2)) {
//			System.out.println("Zmieniam prawa skrajna");
			changeElement(n - 1, coins2);
			isCoinChanged = true;
		}
		return isCoinChanged;
	}

	private boolean addTwoPairs(boolean isCoinChanged) {
		for (int i = 1; i < (coins2.size() - 1); i++) {
			if ((coins2.get(i)).equals(coins2.get(i - 1)) == false
					&& (coins2.get(i)).equals(coins2.get(i + 1)) == false
					&& isCoinChanged == false) {
				isCoinChanged = true;
				changeElement(i, coins2);
				break;

			}
		}
		return isCoinChanged;
	}

	private void changeElement(int i, List<Integer> coinList) {
		if (coinList.get(i) == 0) {
			coinList.remove(i);
			coinList.add(i, 1);
		} else {
			coinList.remove(i);
			coinList.add(i, 0);
		}
	}

	private boolean checkIfDifferentValuesOnRightEdge(List<Integer> coinList) {
		boolean answer = false;
		if (!coinList.get(n - 1).equals(coinList.get(n - 2))) {
			answer = true;
		}
		return answer;
	}

	private boolean checkIfDifferentValuesOnLeftEdge(List<Integer> coinList) {
		boolean answer = false;
		if (!coinList.get(0).equals(coinList.get(1))) {
			answer = true;
		}
		return answer;
	}

	@Override
	public String toString() {
		return "Coin " + lottery() + "\n" + "Number of pair before lottery: "
				+ checkPair() + "\n" + "New List with chenge one coin: "
				+ changeOneCoin() + "\n" + "Number of pair after lottery: "
				+ checkPairInNewList();
	}

}
