# Project Information

This project is a spam detection web application that utilizes a machine learning model to classify emails as either spam or ham (not spam). 
The model was built using Python and trained on a dataset of labeled emails. The web application was built using HTML, CSS, and JavaScript and allows users to upload a file containing emails to be classified as spam or ham.

## Improvements

To improve the user interface, we added a progress bar to indicate the status of the file upload and classification process. We also added an error message to be displayed if the user tries to upload a file that is not in the correct format.
To improve the model's accuracy, we experimented with different feature extraction techniques and used a larger dataset for training.

### How to Run
    Download and install the latest version of Java Development Kit (JDK) on your computer.
    
    Download the SpamDetector Java program from source : https://github.com/Aitezazuddin/Assignment1.git
    
    Extract the downloaded ZIP file to a convenient location on your computer.
    
    Open a command prompt or terminal window on your computer.
    
    Change the current directory to the extracted SpamDetector folder.
    
    Compile the SpamDetector Java program by running the following command: javac SpamDetector.java
    
    Once the program is compiled successfully, run the program by typing the following command: java SpamDetector
    
    The program will prompt you to enter the full path of the directory containing the training data (e.g., C:\Users\YourName\SpamDetector\train). Enter the path and press Enter.
    
    The program will calculate the count of each word in each file in the 'ham', 'ham2', and 'spam' subfolders of the specified directory and display the results.
    
    You can now use the calculated word counts to train your spam detection model or perform further analysis.

#### References
[1] https://en.wikipedia.org/wiki/Bag-of-words_model

[2] https://en.wikipedia.org/wiki/Naive_Bayes_spam_filtering
