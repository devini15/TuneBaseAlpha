/*
TuneBaseAlpha v1.1
DECTalk music generator by Devini15

New this version:
    - Added Output so Far

Developer contact:
    - Discord: devini15
    - Twitter: @DevinLive15
    - Other: https://devini15.live
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.LinkedList;
import javax.swing.*;

@SuppressWarnings("CallToPrintStackTrace")
//My IDE says that printStackTrace should "probably be replaced with more robust logging", I say my IDE should put a robust log up it's c***.
public class MakeSongs {
    private static final int DEFAULT_WIDTH = 700; //Window width
    private static final int DEFAULT_HEIGHT = 110; //Window height
    private static final String VERSION = "v1.0"; //Current Version

    //these objects are declared globally because if I tried passing them back and forth, my brain would explode
    private static final JTextField timeField = new JTextField(),
            phoneField = new JTextField();
    private static final JComboBox<String> pitchSelector = new JComboBox<>();

    private static final LinkedList<String> notes = new LinkedList<>(); //This will hold all strings generated

    //Way more code than should reasonably be in a main method
    public static void main(String[] args) {
        welcomeMessage();
        JFrame configurationFrame; //The frame where the magic happens
        configurationFrame = setUpFrame();
        String[] pitches = { //compatible pitches
                "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3",
                "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4",
                "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5", "C6"
        };
        //Populate the dropdown menu to select pitches
        for (String s : pitches) pitchSelector.addItem(s);
        //Declare layout components
        JButton addButton = new JButton(onAdd()),
                clearButton = new JButton(onClear()),
                undoButton = new JButton(onUndo()),
                outButton = new JButton(onOut()),
                doneButton = new JButton(onDone());
        JLabel pitchLabel = new JLabel("Select Pitch    "),//Some extra spaces because aligning text in swing is a nightmare
                timeLabel = new JLabel("Enter Duration (ms)"),
                phoneLabel = new JLabel("Enter Phoneme");
        Box inputHBox = Box.createHorizontalBox(),//holds the vertical boxes containing the text fields, labels, and drop down menu as defined below
                actionHBox = Box.createHorizontalBox(),//holds the buttons
                pitchVBox = Box.createVerticalBox(),//holds the label and dropdown for pitch selection
                timeVBox = Box.createVerticalBox(),//holds the label and field for duration input
                phoneVBox = Box.createVerticalBox(),//holds the label and field for phoneme input
                bigOlVBox = Box.createVerticalBox();//holds all UI elements in a cute little package
        //Build Layout
        //Stack labels on top of input fields
        pitchVBox.add(pitchLabel);
        pitchVBox.add(pitchSelector);
        timeVBox.add(timeLabel);
        timeVBox.add(timeField);
        phoneVBox.add(phoneLabel);
        phoneVBox.add(phoneField);
        //Place buttons next to each other
        actionHBox.add(addButton);
        actionHBox.add(clearButton);
        actionHBox.add(undoButton);
        actionHBox.add(outButton);
        actionHBox.add(doneButton);
        //Place the label/input field stacks next to each other
        inputHBox.add(pitchVBox);
        inputHBox.add(timeVBox);
        inputHBox.add(phoneVBox);
        //stack the label/input field stacks on top of the buttons
        bigOlVBox.add(inputHBox);
        bigOlVBox.add(actionHBox);
        //Format buttons
        addButton.setText("Add");
        clearButton.setText("Add & Clear Fields");
        undoButton.setText("Undo");
        outButton.setText("Output so Far");
        doneButton.setText("Finish");
        addButton.setEnabled(true);
        clearButton.setEnabled(true);
        undoButton.setEnabled(true);
        outButton.setEnabled(true);
        doneButton.setEnabled(true);
        //Add everything to the frame
        configurationFrame.add(bigOlVBox);
        //actually show the user something
        configurationFrame.setVisible(true);
    }

    /**
     * Welcomes the user, this method should open README.txt if the user selects "Help"
     * There will be no help button once this window is dismissed, they will either take the help now, or must rely on their wits and intuition.
     */
    private static void welcomeMessage() {
        System.out.println("\u001b[40;30m YOU SHOULD NOT BE ABLE TO READ THIS!\n" +
                "If you can, please refer to the readme to fix your console colors or switch to the no-color version.\u001b[0;0m");
        File readMe = new File("README.txt");
        String[] welcomeOptions = {"Start", "Help"};
        //Open README.txt if the user selects "Help"
        int welcomeChoice = JOptionPane.showOptionDialog(null,
                "Welcome to TuneBaseAlpha " + VERSION + "\n by Devini15",
                "Welcome!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                welcomeOptions,
                null);
        if (welcomeChoice == 1) {
            //Not sure what "isDesktopSupported" means, but let's hope it's true!
            if (Desktop.isDesktopSupported()) {
                try {
                    //Open the readme (I copied this from stack overflow)
                    Desktop.getDesktop().edit(readMe);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to open README.txt\nFind it on GitHub.",
                            "README Error",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Unable to open README.txt\nFind it on GitHub.",
                        "README Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Converts user input into DecTalk compatible text
     *
     * @param pitch    pitch selected from dropdown
     * @param duration pitch duration from text field
     * @param phoneme  phoneme from text field
     * @return Formatted text that can be pasted into DecTalk
     */
    private static String tone(int pitch, int duration, String phoneme) {
        pitch++; //Dropdown is 0 indexed but DecTalk pitches start at 1
        String tone = phoneme + "<" + duration + "," + pitch + ">";
        System.out.println(pitchSelector.getSelectedItem().toString() + ": " + tone);
        return (tone);
    }

    /**
     * Sets up the frame, doing what little I can to remove code from main()
     *
     * @return Blank frame to add layout elements to.
     */
    private static JFrame setUpFrame() {
        JFrame f = new JFrame();
        f.setTitle("TuneBaseAlpha " + VERSION);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        return f;
    }

    /**
     * Takes user input and sends it to tone() to make it a DECTalk compatible string
     * Adds aforementioned string to the "notes" LinkedList to be printed in final output
     */
    private static void addString() {
        try {
            //read user input
            int pitch = pitchSelector.getSelectedIndex();
            int duration = Integer.parseInt(timeField.getText());
            String phoneme = phoneField.getText();
            //Make DECTalk compatible string and add to notes
            notes.add(tone(pitch, duration, phoneme));
            //I was going to put something to check if the phoneme was valid, but I think I'll leave that as an exercise for the user for the time being.
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Duration must be an integer.",
                    "Number Format Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Empties out the text fields, simple as
     */
    private static void clearFields() {
        timeField.setText("");
        phoneField.setText("");
    }

    /**
     * Undo button, removes most recent entry to notes.
     */
    private static void removeEntry() {
        if(notes.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "You can't very well undo something if you haven't done anything yet.\nNice try though.",
                    "Premature Revertion",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            System.out.println("\u001b[0;31mXX: " + notes.removeLast() + " REMOVED\u001b[0m");
        }
    }

    /**
     * Generates output string
     * @return DECTALK formatted string with all notes input so far.
     */
    private static String generateOutput(){
        StringBuilder outString = new StringBuilder("[:phone on]["); //[:phone on] tells DECTalk to enable tones
        for (String s : notes) outString.append(s); //adds all notes generated to a string.
        outString.append("]");//the entire message must be in brackets to be sung.
        return outString.toString();
    }

    /**
     * Outputs the results to console, will also create a file if user specifies.
     */
    private static void outputResults() {
        System.out.println("\u001b[33mCompiling output string...\u001b[0m");
        String finalOutput = generateOutput();
        //ask the user if they would like to output a file in addition to console output
        System.out.println("\u001b[33mAwaitng user input...\u001b[0m");
        int selection = JOptionPane.showConfirmDialog(null,
                "Copy output to file?\nYes: Make File\nNo: Console Output Only",
                "Make a File?",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (selection != 0 && selection != 1) {
            //prevent exit if user clicks "Cancel" or closes the dialog.
            return;
        } else if (selection == 0) {
            //If the user wants an output file, generate a file.
            try {
                File outFile = new File("output.txt");
                FileOutputStream outStream = new FileOutputStream(outFile);
                PrintWriter writer = new PrintWriter(outStream);
                writer.print(finalOutput);
                writer.close();
                outStream.close();
                JOptionPane.showMessageDialog(null,
                        "Output saved to \"output.txt\".",
                        "Output Complete",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Encountered an issue creating the output file.",
                        "File Not Found",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Encountered an issue creating the output file.",
                        "IO Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        //This code will only run if the user clicked on "Yes" or "No".
        System.out.println("\u001b[0;32m" + finalOutput + "\u001b[0m");
        System.exit(0);
    }

    //these 3 methods are all button handlers, the only thing they do is call other methods
    private static Action onAdd() { //calls addString
        return new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                addString();
            }
        };
    }

    private static Action onClear() { //calls addString and clearFields
        return new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                addString();
                clearFields();
            }
        };
    }

    private static Action onUndo() { //calls removeEntry
        return new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                removeEntry();
            }
        };
    }

    private static Action onOut(){ //outputs result of generateOutput
        return new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(generateOutput());
            }
        };
    }

    private static Action onDone() { //calls outputResults
        return new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                outputResults();
            }
        };
    }
}