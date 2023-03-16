package com.spamdetector.util;

import com.spamdetector.domain.TestFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides methods for detecting spam emails by training and testing a model on a given set of emails.
 */
public class SpamDetector {

    private static final double SPAM_THRESHOLD = 0;
    private Map<String, Double> spamProbs;
    private Map<String, Double> hamProbs;
    private List<TestFile> testResults;

    /**
     * Trains a model on a set of emails located in the given main directory, and tests the model on a separate set of emails.
     *
     * @param mainDirectory the directory containing the training and testing email files
     * @return a list of TestFile objects representing the results of the testing
     * @throws IOException if there is an error reading the email files
     */
    public List<TestFile> trainAndTest(File mainDirectory) throws IOException {
        // Load the directories and files
        File trainDir = new File(mainDirectory, "train");
        File testDir = new File(mainDirectory, "test");
        List<File> trainFiles = getFiles(trainDir);
        List<File> testFiles = getFiles(testDir);

        // Train the model
        spamProbs = new HashMap<>();
        hamProbs = new HashMap<>();
        int spamCount = 0;
        int hamCount = 0;
        for (File file : trainFiles) {
            // Read the contents of the file
            String content = new String(Files.readAllBytes(file.toPath()));
            // Split the contents into individual words
            String[] words = content.split("\\s+");
            // Update the word probabilities based on whether the file is spam or ham
            if (file.getName().startsWith("sp")) {
                spamCount++;
                for (String word : words) {
                    spamProbs.put(word, spamProbs.getOrDefault(word, 0.0) + 1.0);
                }
            } else {
                hamCount++;
                for (String word : words) {
                    hamProbs.put(word, hamProbs.getOrDefault(word, 0.0) + 1.0);
                }
            }
        }
        // Normalize the word probabilities
        for (String word : spamProbs.keySet()) {
            spamProbs.put(word, spamProbs.get(word) / spamCount);
        }
        for (String word : hamProbs.keySet()) {
            hamProbs.put(word, hamProbs.get(word) / hamCount);
        }

        // Test the model
        testResults = new ArrayList<>();
        for (File file : testFiles) {
            // Read the contents of the file
            String content = new String(Files.readAllBytes(file.toPath()));
            // Split the contents into individual words
            String[] words = content.split("\\s+");
            // Calculate the spam and ham scores for the email
            double spamScore = 0.0;
            double hamScore = 0.0;
            for (String word : words) {
                spamScore += Math.log(spamProbs.getOrDefault(word, 0.01));
                hamScore += Math.log(hamProbs.getOrDefault(word, 0.01));
            }
            // Classify the email as spam or ham based on the scores
            boolean isSpam = spamScore > hamScore;
            testResults.add(new TestFile(file.getName(), isSpam));
        }

        // Return the results of the testing
        return testResults;
    }

    private List<File> getFiles(File trainDir) {
        return null;
    }

    public double getAccuracy() {
        int total = testResults.size();
    int correct = 0;
    for (TestFile file : testResults) {
        if (file.isSpam() == isSpam(file.getFilename())) {
            correct++;
        }
    }
    return (double) correct / total * 100.0;
    }


    private Object isSpam(String filename) {
        try {
            // read the email content from file
            String emailContent = new String(Files.readAllBytes(Paths.get(filename)));
    
            // get the SpamDetector instance
            SpamDetector spamDetector = SpamDetector.getInstance();
    
            // predict if the email is spam or not
            double[] probabilities = ((SpamDetector)spamDetector).predictProba(new String[]{emailContent});
    
            // if the probability of spam is greater than the threshold, return true
            if (probabilities[1] > SPAM_THRESHOLD) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

    private double[] predictProba(String[] strings) {
        return null;
    }

    private static SpamDetector getInstance() {
        return null;
    }

    public double getPrecision() {
        int spamCount = 0;
    int correct = 0;
    for (TestFile file : testResults) {
        if ((boolean) file.isSpam()) {
            spamCount++;
            if ((boolean) isSpam(file.getFilename())) {
                correct++;
            }
        }
    }
    return (double) correct / spamCount * 100.0;
    }

    public List<TestFile> getTestResults() {
        return testResults;
    }
}

   