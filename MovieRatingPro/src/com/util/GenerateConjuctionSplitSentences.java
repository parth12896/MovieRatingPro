package com.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateConjuctionSplitSentences {

	public Set<String> obtainSentencesBasedOnConjuctionList(String reviewDetails) {
		
		reviewDetails=reviewDetails.toUpperCase();

		Set<String> sentencesList = new HashSet<String>();

		if (reviewDetails != null && !reviewDetails.isEmpty()) {

			String[] reviewArray = reviewDetails.split("\\.");

			if (null == reviewArray || reviewArray.length == 0) {
				reviewArray[0] = reviewDetails;
			}

			if (reviewDetails.contains("AND") || reviewDetails.contains("BUT")) {

				for (String reviewStatement : reviewArray) {

					String statementTemp[] = reviewStatement.split("AND");

					if (null == statementTemp || statementTemp.length == 0) {

						statementTemp[0] = reviewStatement;

					}

					if (statementTemp != null && statementTemp.length >= 0) {

						for (String statementTemporary : statementTemp) {

							String statementTemp1[] = statementTemporary.split("BUT");

							if (null == statementTemp1 || statementTemp1.length == 0) {
								reviewArray[0] = statementTemporary;
								sentencesList.add(reviewArray[0]);
							} else {

								int len = statementTemp.length;

								if (len > 0) {

									for (String temp : statementTemp1) {
										sentencesList.add(temp);
									}

								}

							}

						}

					}

				}

				return sentencesList;

			} else {

				for (String s : reviewArray) {
					sentencesList.add(s);
				}

				return sentencesList;

			}

		}

		// TODO Auto-generated method stub
		return sentencesList;
	}

	public static void main(String s[]) {

		GenerateConjuctionSplitSentences generateConjuctionSplitSentences = new GenerateConjuctionSplitSentences();

		Set<String> newSentenaces=generateConjuctionSplitSentences
				.obtainSentencesBasedOnConjuctionList("The battery is nice but Camera is aweosome but  is good . The Mobile is Awesome");
		
		
		for(String value:newSentenaces){
			
			System.out.println(value);
			
		}

	}

}
