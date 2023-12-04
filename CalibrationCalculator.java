import java.util.HashMap;
import java.util.Map;

public class CalibrationCalculator {

	public int getCalibrationSum(String input) {
		Map<String, String> digitMap = getDigitMap();
		String[] lines = StringSplitter.splitString(input, StringSplitter.LB);
		int calibrationSum = 0;
		for (String line : lines) {
			int first = getDigit(digitMap, line, true);
			int last = getDigit(digitMap, line, false);
			calibrationSum += getConcatenatedValue(first, last);
		}
		return calibrationSum;
	}

	private Map<String, String> getDigitMap() {
		Map<String, String> digitMap = new HashMap<>(Map.of(//
				"one", "1", //
				"two", "2", //
				"three", "3", //
				"four", "4", //
				"five", "5", //
				"six", "6", //
				"seven", "7", //
				"eight", "8", //
				"nine", "9" //
		));
		putIdentityMappings(digitMap);
		return digitMap;
	}

	private void putIdentityMappings(Map<String, String> digitMap) {
		for (int i = 1; i <= 9; i++) {
			digitMap.put("" + i, "" + i);
		}
	}

	public static int getDigit(Map<String, String> digitMap, String line, boolean extractFirstDigit) {
		int foundIndex = -1;
		String foundDigit = "";
		for (String digit : digitMap.keySet()) {
			int digitIndex = extractFirstDigit ? line.indexOf(digit) : line.lastIndexOf(digit);
			boolean shouldUpdateIndex = extractFirstDigit ? (digitIndex < foundIndex) : (digitIndex > foundIndex);
			boolean isInitialIndex = foundIndex == -1;
			boolean hasFoundDigit = digitIndex != -1;
			if (hasFoundDigit && (shouldUpdateIndex || isInitialIndex)) {
				foundDigit = digit;
				foundIndex = digitIndex;
			}
		}
		return Integer.parseInt(digitMap.get(foundDigit));
	}

	private int getConcatenatedValue(int first, int last) {
		String concatDigits = first + "" + last;
		return Integer.parseInt(concatDigits);
	}

}
