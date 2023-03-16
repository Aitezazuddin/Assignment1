package com.spamdetector.service;

import com.spamdetector.domain.TestFile;
import com.spamdetector.util.SpamDetector;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Path("/spam")
public class SpamResource {

    // SpamDetector object responsible for all the SpamDetecting logic
    private SpamDetector detector;

    // Constructor to train and test the detector upon instantiation
    public SpamResource() throws IOException {
        System.out.println("Training and testing the model, please wait...");
        this.trainAndTest();
    }

    @GET
    @Produces("application/json")
    public Response getSpamResults() {
        // Return the test results list of TestFile, return in a Response object
        List<TestFile> testResults = this.detector.getTestResults();
        return Response.ok(testResults).build();
    }

    @GET
    @Path("/accuracy")
    @Produces("application/json")
    public Response getAccuracy() {
        // Return the accuracy of the detector, return in a Response object
        double accuracy = this.detector.getAccuracy();
        return Response.ok("{\"val\": " + accuracy + "}").build();
    }

    @GET
    @Path("/precision")
    @Produces("application/json")
    public Response getPrecision() {
        // Return the precision of the detector, return in a Response object
        double precision = this.detector.getPrecision();
        return Response.ok("{\"val\": " + precision + "}").build();
    }

    private void trainAndTest() throws IOException {
        // Load the main directory "data" here from the Resources folder
        File mainDirectory = new File(getClass().getClassLoader().getResource("data").getFile());

        // Train and test the detector
        this.detector = new SpamDetector();
        this.detector.trainAndTest(mainDirectory);
    }
}
