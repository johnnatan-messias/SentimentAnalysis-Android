package com.lg.sentiment;

import java.util.Scanner;
import com.lg.sentimentalanalysis.Method;
/*
import opin.config.Config;
import opin.entity.Corpus;
import opin.featurefinder.ClueFind;
import opin.logic.AnnotationHandler;
import opin.main.RunOpinionFinder;
import opin.output.SGMLOutput;
import opin.preprocessor.PreProcess;
import opin.rulebased.RuleBasedClassifier;
import opin.supervised.ExpressionPolarityClassifier;
import opin.supervised.SentenceSubjectivityClassifier;
*/
/**
 * @author jpaulo
 * Adapter class to run sentiment analysis from MyOpinionFinder.jar
 * and convert its output to standard implementation.
 */
public class MPQAAdapter extends Method {

	/**
	 * from ???OpinionFinder.jar
	 */
//	private ;	

	/**
	 * @param path to folder that contains required data and dictionaries of .jar
	 */
	public MPQAAdapter(String lexiconsFolderPath, String modelsFolderPath) {
		
		testCodeLike_main_RunOpinionFinder(new String[] {
//				"-t", "i love you" //Filipe's config change
				"PATH/TO/FILE/WITH/SENTENCES/sentences_test.txt"
				, "-l", lexiconsFolderPath
				, "-w", modelsFolderPath
		 });

		//this. = new ();
	}
	
	private static void testCodeLike_main_RunOpinionFinder(String[] args) {
/*
		final Config conf = new Config();
		if (!conf.parseCommandLineOptions(args)) {
			System.exit(-1);
		}
		final Corpus corpus = new Corpus(conf);
		if (conf.isRunPreprocessor()) {
			final PreProcess preprocessor = new PreProcess(conf);
			preprocessor.process(corpus);
		}
		if (conf.isRunClueFinder()) {
			final ClueFind clueFinder = new ClueFind(conf);
			clueFinder.process(corpus);
		}
		final AnnotationHandler annHandler = new AnnotationHandler(conf);
		if (conf.isRunRulebasedClassifier() || conf.isRunSubjClassifier() || conf.isRunPolarityClassifier()) {
			annHandler.buildSentencesFromGateDefault(corpus);
		}
		if (conf.isRunRulebasedClassifier()) {
			annHandler.readInRequiredAnnotationsForRuleBased(corpus);
			final RuleBasedClassifier rulebased = new RuleBasedClassifier();
			rulebased.process(corpus);
		}
		if (conf.isRunSubjClassifier()) {
			annHandler.readInRequiredAnnotationsForSubjClassifier(corpus);
			final SentenceSubjectivityClassifier subjClassifier = new SentenceSubjectivityClassifier(conf);
			subjClassifier.process(corpus);
		}
		if (conf.isRunPolarityClassifier()) {
			annHandler.readInRequiredAnnotationsForPolarityClassifier(corpus);
			final ExpressionPolarityClassifier polarityClassifier = new ExpressionPolarityClassifier(conf);
			polarityClassifier.process(corpus);
		}
		if (conf.isRunSGMLOutput()) {
			final SGMLOutput output = new SGMLOutput(conf.isRunRulebasedClassifier(), conf.isRunSubjClassifier(), conf.isRunPolarityClassifier());
			output.process(corpus);
		}
*/
	}
	
	@Override
	public int analyseText(String text) {

//		String out = this.opinionFinder.computeSentimentScores(text);
		String out = "";
		//System.out.println(out);

		Scanner sc = new Scanner(out);
		int posRate = Math.abs(sc.nextInt());
		int negRate = Math.abs(sc.nextInt());
		int neuRate = Math.abs(sc.nextInt());
		sc.close();

		if (posRate > negRate && posRate > neuRate) {
			return POSITIVE;
		}
		else if (negRate > posRate && negRate > neuRate) {
			return NEGATIVE;
		}

		return NEUTRAL;
	}

	/**
	 * do nothing, it's a Adapter class
	 */
	@Override
	public void loadDictionaries() {
	}
}
