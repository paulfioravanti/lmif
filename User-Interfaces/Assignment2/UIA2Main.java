   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.*;
   import java.util.*;

/**
	User Interfaces Assignment 2
	@version 1.0 22nd March, 2005
	@author Paul Fioravanti
	
	This is a prototype for a user interface for an Inflght Entertainment System.
	The system has the following features: Radio stations, Movies, Streaming programs, video games, sms/emailing capability, 
	reading hypertext news articles. 
	
*/
    public class UIA2Main extends JFrame implements ActionListener
   {
   
   //JPanel on top of the JFrame
      JPanel contentPane;
      JPanel southPanel = new JPanel(new FlowLayout());
      JPanel northPanel = new JPanel(new FlowLayout());
      JPanel mainMenuPanel = new JPanel(new GridBagLayout());
      GridBagConstraints mmc = new GridBagConstraints();
      JPanel audioMenuPanel = new JPanel(new GridBagLayout());
      GridBagConstraints amc = new GridBagConstraints();
      JPanel audioStationPanel = new JPanel(new GridBagLayout());
      GridBagConstraints mcc = new GridBagConstraints();
      JPanel videoMenuPanel = new JPanel(new GridBagLayout());
      GridBagConstraints vmc = new GridBagConstraints();
      JPanel movieMenuPanel = new JPanel(new GridBagLayout());
      GridBagConstraints movmc = new GridBagConstraints();
      JPanel movieChannelPanel = new JPanel(new GridBagLayout());
      GridBagConstraints mchc = new GridBagConstraints();
      JPanel moviePlayingPanel = new JPanel(new GridBagLayout());
      GridBagConstraints mpc = new GridBagConstraints();
      JPanel vodMenuPanel = new JPanel(new GridBagLayout());
      GridBagConstraints vodc = new GridBagConstraints();
      JPanel vodProgPanel = new JPanel(new GridBagLayout());
      GridBagConstraints vpc = new GridBagConstraints();
      JPanel vodProgDescPanel = new JPanel(new GridBagLayout());
      GridBagConstraints vpdc = new GridBagConstraints();
      JPanel progPlayingPanel = new JPanel(new GridBagLayout());
      GridBagConstraints ppc = new GridBagConstraints();
      JPanel progControlPanel = new JPanel(new GridBagLayout());
      GridBagConstraints pcc = new GridBagConstraints();
      JPanel allProgPanel = new JPanel(new GridBagLayout());
      GridBagConstraints apc = new GridBagConstraints();
      JPanel interactiveMenuPanel = new JPanel(new GridBagLayout());
      GridBagConstraints imc = new GridBagConstraints();
      JPanel gameMenuPanel = new JPanel(new GridBagLayout());
      GridBagConstraints gmc = new GridBagConstraints();
      JPanel gamePlayingPanel = new JPanel(new GridBagLayout());
      GridBagConstraints gpc = new GridBagConstraints();
      JPanel gameInvitePanel = new JPanel(new GridBagLayout());
      GridBagConstraints gic = new GridBagConstraints();
      JPanel smsPanel = new JPanel(new GridBagLayout());
      GridBagConstraints smcc = new GridBagConstraints();
      JPanel newsNetMenuPanel = new JPanel(new GridBagLayout());
      GridBagConstraints nnmc = new GridBagConstraints();
      JPanel newsNetHeadlinePanel = new JPanel(new GridBagLayout());
      GridBagConstraints nnhc = new GridBagConstraints();
      JPanel newsNetTextPanel = new JPanel(new GridBagLayout());
      GridBagConstraints nntc = new GridBagConstraints();
		JPanel languagePanel = new JPanel(new GridBagLayout());
      GridBagConstraints lpc = new GridBagConstraints();
   
   //Define all buttons needed	
      JButton mainMenuButton = new JButton("Main Menu");
      JButton sleepButton = new JButton("Sleep");
      JButton playButton = new JButton("PLAY");
      JButton audioButton = new JButton("AUDIO");
      JButton videoButton = new JButton("VIDEO");
      JButton interactiveButton = new JButton("INTERACTIVE");
      JButton comedyButton = new JButton("Comedy");
      JButton comedy2Button = new JButton("Comedy");
      JButton technoButton = new JButton("Techno");
      JButton countryButton = new JButton("Country");
      JButton contemporaryButton = new JButton("Contemporary");
      JButton rocknRollButton = new JButton("Rock 'n' Roll");
      JButton easyListeningButton = new JButton("Easy Listening");
      JButton top40Button = new JButton("Top 40");
      JButton worldButton = new JButton("World");
      JButton world2Button = new JButton("World");
      JButton indieButton = new JButton("Indie");
      JButton popButton = new JButton("Pop");
      JButton audioMenuButton = new JButton("Audio Menu");
      JButton moviesButton = new JButton("MOVIES");
      JButton vodButton = new JButton("VIDEO-ON-DEMAND");
      JButton movie1Button = new JButton("Movie 1");
      JButton movie2Button = new JButton("Movie 2");
      JButton movie3Button = new JButton("Movie 3");
      JButton movie4Button = new JButton("Movie 4");
      JButton movie5Button = new JButton("Movie 5");
      JButton movie6Button = new JButton("Movie 6");
      JButton movie7Button = new JButton("Movie 7");
      JButton movie8Button = new JButton("Movie 8");
      JButton movie9Button = new JButton("Movie 9");
      JButton movie10Button = new JButton("Movie 10");
      JButton nextButton = new JButton("Next");
      JButton prevButton = new JButton("Previous");
      JButton movieMenuButton = new JButton("Movie Menu");
      JButton lifestyleButton = new JButton("Lifestyle");
      JButton documentaryButton = new JButton("Documentary");
      JButton currentAffairsButton = new JButton("Current Affairs");
      JButton forKidsButton = new JButton("For Kids");
      JButton sportsButton = new JButton("Sports");
      JButton sports2Button = new JButton("Sports");
      JButton newsButton = new JButton("News");
      JButton dramaButton = new JButton("Drama");
      JButton travelButton = new JButton("Travel");
      JButton suspenseButton = new JButton("Suspense");
      JButton allButton = new JButton("All");
      JButton prog1Button = new JButton("Program 1");
      JButton prog2Button = new JButton("Program 2");
      JButton prog3Button = new JButton("Program 3");
      JButton prog4Button = new JButton("Program 4");
      JButton prog5Button = new JButton("Program 5");
      JButton prog6Button = new JButton("Program 6");
      JButton prog7Button = new JButton("Program 7");
      JButton prog8Button = new JButton("Program 8");
      JButton prog9Button = new JButton("Program 9");
      JButton prog10Button = new JButton("Program 10");
      JButton backButton = new JButton("Back");
      JButton play2Button = new JButton(">");
      JButton rewButton = new JButton("<<");
      JButton ffButton = new JButton(">>");
      JButton vodMenuButton = new JButton("Video-on-Demand Menu");
      JButton gamesButton = new JButton("GAMES");
      JButton smsButton = new JButton("SMS/EMAIL");
      JButton newsNetButton = new JButton("NEWS NET");
      JButton interactiveMenuButton = new JButton("Interactive Menu");
      JButton gamesMenuButton = new JButton("Games Menu");
      JButton newButton = new JButton("New");
      JButton replyButton = new JButton("Reply");
      JButton forwardButton = new JButton("Forward");
      JButton sendButton = new JButton("Send");
      JButton inboxButton = new JButton("Inbox");
      JButton sentButton = new JButton("Sent");
      JButton breakingNewsButton = new JButton("Breaking News");
      JButton topStoriesButton = new JButton("Top Stories");
      JButton localButton = new JButton("Local");
      JButton businessButton = new JButton("Business");
      JButton entertainmentButton = new JButton("Entertainment");
      JButton weatherButton = new JButton("Weather");
      JButton humourButton = new JButton("Humour");
      JButton technologyButton = new JButton("Technology");
      JButton headline1Button = new JButton("Headline 1");
      JButton headline2Button = new JButton("Headline 2");
      JButton headline3Button = new JButton("Headline 3");
      JButton headline4Button = new JButton("Headline 4");
      JButton headline5Button = new JButton("Headline 5");
      JButton headline6Button = new JButton("Headline 6");
      JButton headline7Button = new JButton("Headline 7");
      JButton headline8Button = new JButton("Headline 8");
      JButton headline9Button = new JButton("Headline 9");
      JButton headline10Button = new JButton("Headline 10");
		JButton childFilter = new JButton("Child Filter Off");
		JButton languageButton = new JButton("Language");
      JButton language1Button = new JButton("Language 1");
      JButton language2Button = new JButton("Language 2");
      JButton language3Button = new JButton("Language 3");
      JButton language4Button = new JButton("Language 4");
      JButton language5Button = new JButton("Language 5");
      JButton language6Button = new JButton("Language 6");
      JButton language7Button = new JButton("Language 7");
      JButton language8Button = new JButton("Language 8");
      JButton language9Button = new JButton("Language 9");
      JButton language10Button = new JButton("Language 10");
   	
   	//define all JLabels
      JLabel iesLabel = new JLabel("INFLIGHT ENTERTAINMENT SYSTEM");
      JLabel audioLabel = new JLabel("AUDIO");
      JLabel selectStationLabel = new JLabel("Select a Station");
      JLabel stationNameLabel = new JLabel("Station Name");
      JLabel stationDescriptionLabel = new JLabel("Station Description");
      JLabel playListLabel = new JLabel("PlayList");
      JLabel track1Label = new JLabel("1. Track 1");
      JLabel track2Label = new JLabel("2. Track 2");
      JLabel track3Label = new JLabel("3. Track 3");
      JLabel track4Label = new JLabel("4. Track 4");
      JLabel track5Label = new JLabel("5. Track 5");
      JLabel track6Label = new JLabel("6. Track 6");
      JLabel track7Label = new JLabel("7. Track 7");
      JLabel track8Label = new JLabel("8. Track 8");
      JLabel track9Label = new JLabel("9. Track 9");
      JLabel track10Label = new JLabel("10. Track 10");
      JLabel videoLabel = new JLabel("VIDEO");
      JLabel moviesLabel = new JLabel("MOVIES");
      JLabel thisMonthsMoviesLabel = new JLabel("This Month's Movies");
      JLabel movieNameLabel = new JLabel("Movie Name");
      JLabel movieDescriptionLabel = new JLabel("Movie Description");
      JLabel movieThumbnailLabel = new JLabel("Movie Poster");
      JLabel timeElapsedLabel = new JLabel("Time Elapsed: 00:58");
      JLabel moviePlayingLabel = new JLabel("Movie Playing");
      JLabel vodLabel = new JLabel("VIDEO-ON-DEMAND");
      JLabel selectGenreLabel = new JLabel("Select a genre");
      JLabel selectProgLabel = new JLabel("Select a program");
      JLabel progNameLabel = new JLabel("Program Name");
      JLabel progDescriptionLabel = new JLabel("Program Description");
      JLabel progThumbnailLabel = new JLabel("Program Picture");
      JLabel progPlayingLabel = new JLabel("Program Playing");
      JLabel interactiveLabel = new JLabel("INTERACTIVE");
      JLabel gamesLabel = new JLabel("GAMES");
      JLabel boxArtLabel = new JLabel("Box Art");
      JLabel gameDescriptionLabel = new JLabel("Game Description");
      JLabel playersOnlineLabel = new JLabel("Players Online: 2");
      JLabel gamePlayingLabel = new JLabel("Game Playing");
      JLabel smsLabel = new JLabel("SMS/EMAIL");
      JLabel smsToLabel = new JLabel("To: (Email address or SMS number)");
      JLabel subjectLabel = new JLabel("Subject");
		JLabel messageLabel = new JLabel("Message");
      JLabel qwertyLabel = new JLabel("QWERTY Keyboard");
      JLabel newsNetLabel = new JLabel("NEWS NET");
      JLabel newsNetGenreLabel = new JLabel("Select News Topic");
      JLabel newsNetHeadlineLabel = new JLabel("Select Headline");
      JLabel headlineLabel = new JLabel("Headline");
      JLabel authorLabel = new JLabel("Author");
      JLabel articleText = new JLabel("Article Text");
		JLabel languageLabel = new JLabel("Select Language");
   	
   	
   	//Other necessary parts
      String[] players = {"Invite Player", "C3", "D1", "E2", "H5"};
      JComboBox invitePlayer = new JComboBox(players);
      JTextField toField = new JTextField();
      JTextField subjectField = new JTextField();
      JTextArea messageArea = new JTextArea();
      JTextArea articleArea = new JTextArea();
      Font mainLabelFont = new Font("Default", Font.BOLD, 35);
      Font buttonFont = new Font("Default", Font.BOLD, 25);
      String[] gameGenres = {"All", "Action", "Adventure", "Strategy", "Sports", "Role-playing", "Sports", "Puzzle", "Multipayer", "etc"};
      String[] games = {"Game 1", "Game 2", "Game 3", "Game 4", "Game 5", "Game 6", "Game 7", "Game 8", "Game 9", "Game 10"};
      JList gameGenreList = new JList(gameGenres);
      JList gamesList = new JList(games);
		String message = "Dear Paul,\n\nEven though some of your GridBagLayouts look a bit dodgy,\nyour assignment is still awesome.\n\nYour pal,\n\nThe Marker"; 
 		//String[] languages = {"Select Language", "English", "French", "Italian", "German", "Spanish", "Simplied Chinese","Traditional Chinese", "Japanese", "Bahasa", "etc"};
		//JComboBox languageList = new JComboBox(languages);  
	
   /**
   	Constructor for objects of class Assign2.  Creates the interface.
   */
       public UIA2Main()
      {
         mainMenuButton.addActionListener(this);
         sleepButton.addActionListener(this);
         playButton.addActionListener(this);
         audioButton.addActionListener(this);
         videoButton.addActionListener(this);
         interactiveButton.addActionListener(this);
         comedyButton.addActionListener(this);
			comedy2Button.addActionListener(this);
         technoButton.addActionListener(this);
         countryButton.addActionListener(this);
         contemporaryButton.addActionListener(this);
         rocknRollButton.addActionListener(this);
         easyListeningButton.addActionListener(this);
         top40Button.addActionListener(this);
         worldButton.addActionListener(this);
         indieButton.addActionListener(this);
         popButton.addActionListener(this);
         audioMenuButton.addActionListener(this);
         moviesButton.addActionListener(this);
         vodButton.addActionListener(this);
         movie1Button.addActionListener(this);
         movie2Button.addActionListener(this);
         movie3Button.addActionListener(this);
         movie4Button.addActionListener(this);
         movie5Button.addActionListener(this);
         movie6Button.addActionListener(this);
         movie7Button.addActionListener(this);
         movie8Button.addActionListener(this);
         movie9Button.addActionListener(this);
         movie10Button.addActionListener(this);
         nextButton.addActionListener(this);
         prevButton.addActionListener(this);
         movieMenuButton.addActionListener(this);
         lifestyleButton.addActionListener(this);
         documentaryButton.addActionListener(this);
         currentAffairsButton.addActionListener(this);
         forKidsButton.addActionListener(this);
         sportsButton.addActionListener(this);
         newsButton.addActionListener(this);
         dramaButton.addActionListener(this);
         travelButton.addActionListener(this);
         suspenseButton.addActionListener(this);
         allButton.addActionListener(this);
         prog1Button.addActionListener(this);
         prog2Button.addActionListener(this);
         prog3Button.addActionListener(this);
         prog4Button.addActionListener(this);
         prog5Button.addActionListener(this);
         prog6Button.addActionListener(this);
         prog7Button.addActionListener(this);
         prog8Button.addActionListener(this);
         prog9Button.addActionListener(this);
         prog10Button.addActionListener(this);
         backButton.addActionListener(this);
         play2Button.addActionListener(this);
         rewButton.addActionListener(this);
         ffButton.addActionListener(this);
         vodMenuButton.addActionListener(this);
         gamesButton.addActionListener(this);
         smsButton.addActionListener(this);
         newsNetButton.addActionListener(this);
         interactiveMenuButton.addActionListener(this);
         gamesMenuButton.addActionListener(this);
         newButton.addActionListener(this);
         replyButton.addActionListener(this);
         forwardButton.addActionListener(this);
         sendButton.addActionListener(this);
         inboxButton.addActionListener(this);
         sentButton.addActionListener(this);
         breakingNewsButton.addActionListener(this);
         topStoriesButton.addActionListener(this);
         localButton.addActionListener(this);
         world2Button.addActionListener(this);
         businessButton.addActionListener(this);
         sports2Button.addActionListener(this);
         entertainmentButton.addActionListener(this);
         weatherButton.addActionListener(this);
         technologyButton.addActionListener(this);
         humourButton.addActionListener(this);
         headline1Button.addActionListener(this);
         headline2Button.addActionListener(this);
         headline3Button.addActionListener(this);
         headline4Button.addActionListener(this);
         headline5Button.addActionListener(this);
         headline6Button.addActionListener(this);
         headline7Button.addActionListener(this);
         headline8Button.addActionListener(this);
         headline9Button.addActionListener(this);
         headline10Button.addActionListener(this);
			childFilter.addActionListener(this);
			languageButton.addActionListener(this);
			language1Button.addActionListener(this);
			language1Button.addActionListener(this);
			language3Button.addActionListener(this);
			language4Button.addActionListener(this);
			language5Button.addActionListener(this);
			language6Button.addActionListener(this);
			language7Button.addActionListener(this);
			language8Button.addActionListener(this);
			language9Button.addActionListener(this);
			language10Button.addActionListener(this);
      
      //Set program to close when shut down button clicked
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Retrieve window's content pane and assign it to a JPanel
         contentPane = (JPanel)this.getContentPane();
         this.setResizable(false);
      
      //Create static NORTH panel with only Sleep button
			northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
         northPanel.add(invitePlayer);
			invitePlayer.setVisible(false);
			northPanel.add(languageButton);
			languageButton.setVisible(true);
			northPanel.add(childFilter);
			childFilter.setVisible(true);
         northPanel.add(Box.createHorizontalGlue());
         sleepButton.setPreferredSize(new Dimension(100,30));
         northPanel.add(sleepButton);
         northPanel.setVisible(true);
         northPanel.setBackground(Color.BLACK);
         contentPane.add(northPanel, BorderLayout.NORTH);
      	
      //Create Main Menu Panel
         mmc.gridx = 0;
         mmc.gridy = 0;
         mmc.ipady = 1;
         iesLabel.setFont(mainLabelFont);
         mainMenuPanel.add(iesLabel, mmc);
         mmc.gridx = 0;
         mmc.gridy = 1;
         mmc.ipady = 0;
         audioButton.setPreferredSize(new Dimension(600,150));
         audioButton.setFont(buttonFont);	
         mainMenuPanel.add(audioButton, mmc);
         mmc.gridx = 0;
         mmc.gridy = 2;
         videoButton.setPreferredSize(new Dimension(600,150));
         videoButton.setFont(buttonFont);
         mainMenuPanel.add(videoButton, mmc);
         mmc.gridx = 0;
         mmc.gridy = 3;
         interactiveButton.setPreferredSize(new Dimension(600,150));
         interactiveButton.setFont(buttonFont);
         mainMenuPanel.add(interactiveButton, mmc);
      	
      //Create Audio Menu Panel
         amc.gridx = 0;
         amc.gridy = 0;
         amc.ipady = 1;
         amc.gridwidth = 2;
         audioLabel.setFont(mainLabelFont);
         audioMenuPanel.add(audioLabel, amc);
         amc.gridx = 0;
         amc.gridy = 1;
         amc.ipady = 0;
         amc.gridwidth = 2;
         selectStationLabel.setFont(buttonFont);
         audioMenuPanel.add(selectStationLabel, amc);
         amc.gridx = 0;
         amc.gridy = 2;
         amc.gridwidth = 1;
         comedyButton.setPreferredSize(new Dimension(300,80));
         comedyButton.setFont(buttonFont);
         audioMenuPanel.add(comedyButton, amc);
         amc.gridx = 1;
         amc.gridy = 2;
         technoButton.setPreferredSize(new Dimension(300,80));
         technoButton.setFont(buttonFont);
         audioMenuPanel.add(technoButton, amc);
         amc.gridx = 0;
         amc.gridy = 3;
         countryButton.setPreferredSize(new Dimension(300,80));
         countryButton.setFont(buttonFont);
         audioMenuPanel.add(countryButton, amc);
         amc.gridx = 1;
         amc.gridy = 3;
         contemporaryButton.setPreferredSize(new Dimension(300,80));
         contemporaryButton.setFont(buttonFont);
         audioMenuPanel.add(contemporaryButton, amc);
         amc.gridx = 0;
         amc.gridy = 4;
         rocknRollButton.setPreferredSize(new Dimension(300,80));
         rocknRollButton.setFont(buttonFont);
         audioMenuPanel.add(rocknRollButton, amc);
         amc.gridx = 1;
         amc.gridy = 4;
         easyListeningButton.setPreferredSize(new Dimension(300,80));
         easyListeningButton.setFont(buttonFont);
         audioMenuPanel.add(easyListeningButton, amc);
         amc.gridx = 0;
         amc.gridy = 5;
         top40Button.setPreferredSize(new Dimension(300,80));
         top40Button.setFont(buttonFont);
         audioMenuPanel.add(top40Button, amc);
         amc.gridx = 1;
         amc.gridy = 5;
         worldButton.setPreferredSize(new Dimension(300,80));
         worldButton.setFont(buttonFont);
         audioMenuPanel.add(worldButton, amc);
         amc.gridx = 0;
         amc.gridy = 6;
         indieButton.setPreferredSize(new Dimension(300,80));
         indieButton.setFont(buttonFont);
         audioMenuPanel.add(indieButton, amc);
         amc.gridx = 1;
         amc.gridy = 6;
         popButton.setPreferredSize(new Dimension(300,80));
         popButton.setFont(buttonFont);
         audioMenuPanel.add(popButton, amc);
         audioMenuPanel.setVisible(false);
      	
      	//Create Audio Station Panel
         mcc.fill = GridBagConstraints.HORIZONTAL;
         mcc.gridx = 0;
         mcc.gridy = 0;
         mcc.ipady = 1;
         mcc.gridwidth = 2;
         audioLabel.setFont(mainLabelFont);
         audioStationPanel.add(audioLabel, mcc);
         mcc.gridx = 0;
         mcc.gridy = 1;
         mcc.ipady = 0;
         mcc.gridwidth = 1;
         stationNameLabel.setFont(buttonFont);
         stationNameLabel.setPreferredSize(new Dimension(300,100));
         audioStationPanel.add(stationNameLabel, mcc);
         mcc.gridx = 1;
         mcc.gridy = 1;
         playListLabel.setFont(buttonFont);
         playListLabel.setPreferredSize(new Dimension(300,100));
         audioStationPanel.add(playListLabel, mcc);
         mcc.gridx = 0;
         mcc.gridy = 2;
         mcc.gridheight = 10;
         stationDescriptionLabel.setFont(buttonFont);
         stationDescriptionLabel.setBackground(Color.LIGHT_GRAY);
         stationDescriptionLabel.setPreferredSize(new Dimension(200,300));
         audioStationPanel.add(stationDescriptionLabel, mcc);
         mcc.gridx = 1;
         mcc.gridy = 2;
         mcc.gridheight = 1;
         track1Label.setFont(buttonFont);
         track1Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track1Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 3;
         track2Label.setFont(buttonFont);
         track2Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track2Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 4;
         track3Label.setFont(buttonFont);
         track3Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track3Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 5;
         track4Label.setFont(buttonFont);
         track4Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track4Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 6;
         track5Label.setFont(buttonFont);
         track5Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track5Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 7;
         track6Label.setFont(buttonFont);
         track6Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track6Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 8;
         track7Label.setFont(buttonFont);
         track7Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track7Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 9;
         track8Label.setFont(buttonFont);
         track8Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track8Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 10;
         track9Label.setFont(buttonFont);
         track9Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track9Label, mcc);
         mcc.gridx = 1;
         mcc.gridy = 11;
         track10Label.setFont(buttonFont);
         track10Label.setPreferredSize(new Dimension(300,30));
         audioStationPanel.add(track10Label, mcc);
         audioStationPanel.setVisible(false);
      	
      	//Create Video Menu
         vmc.gridx = 0;
         vmc.gridy = 0;
         vmc.ipady = 2;
         videoLabel.setFont(mainLabelFont);
         videoMenuPanel.add(videoLabel, vmc);
         vmc.gridx = 0;
         vmc.gridy = 1;
         vmc.ipady = 0;
         moviesButton.setPreferredSize(new Dimension(600,150));
         moviesButton.setFont(buttonFont);	
         videoMenuPanel.add(moviesButton, vmc);
         vmc.gridx = 0;
         vmc.gridy = 2;
         vodButton.setPreferredSize(new Dimension(600,150));
         vodButton.setFont(buttonFont);
         videoMenuPanel.add(vodButton, vmc);
         videoMenuPanel.setVisible(false);
      	
      	//Create Movie Menu Panel
         movmc.gridx = 0;
         movmc.gridy = 0;
         movmc.ipady = 1;
         movmc.gridwidth = 2;
         moviesLabel.setFont(mainLabelFont);
         movieMenuPanel.add(moviesLabel, movmc);
         movmc.gridx = 0;
         movmc.gridy = 1;
         movmc.ipady = 0;
         movmc.gridwidth = 2;
         thisMonthsMoviesLabel.setFont(buttonFont);
         movieMenuPanel.add(thisMonthsMoviesLabel, movmc);
         movmc.gridx = 0;
         movmc.gridy = 2;
         movmc.gridwidth = 1;
         movie1Button.setPreferredSize(new Dimension(300,80));
         movie1Button.setFont(buttonFont);
         movieMenuPanel.add(movie1Button, movmc);
         movmc.gridx = 1;
         movmc.gridy = 2;
         movie2Button.setPreferredSize(new Dimension(300,80));
         movie2Button.setFont(buttonFont);
         movieMenuPanel.add(movie2Button, movmc);
         movmc.gridx = 0;
         movmc.gridy = 3;
         movie3Button.setPreferredSize(new Dimension(300,80));
         movie3Button.setFont(buttonFont);
         movieMenuPanel.add(movie3Button, movmc);
         movmc.gridx = 1;
         movmc.gridy = 3;
         movie4Button.setPreferredSize(new Dimension(300,80));
         movie4Button.setFont(buttonFont);
         movieMenuPanel.add(movie4Button, movmc);
         movmc.gridx = 0;
         movmc.gridy = 4;
         movie5Button.setPreferredSize(new Dimension(300,80));
         movie5Button.setFont(buttonFont);
         movieMenuPanel.add(movie5Button, movmc);
         movmc.gridx = 1;
         movmc.gridy = 4;
         movie6Button.setPreferredSize(new Dimension(300,80));
         movie6Button.setFont(buttonFont);
         movieMenuPanel.add(movie6Button, movmc);
         movmc.gridx = 0;
         movmc.gridy = 5;
         movie7Button.setPreferredSize(new Dimension(300,80));
         movie7Button.setFont(buttonFont);
         movieMenuPanel.add(movie7Button, movmc);
         movmc.gridx = 1;
         movmc.gridy = 5;
         movie8Button.setPreferredSize(new Dimension(300,80));
         movie8Button.setFont(buttonFont);
         movieMenuPanel.add(movie8Button, movmc);
         movmc.gridx = 0;
         movmc.gridy = 6;
         movie9Button.setPreferredSize(new Dimension(300,80));
         movie9Button.setFont(buttonFont);
         movieMenuPanel.add(movie9Button, movmc);
         movmc.gridx = 1;
         movmc.gridy = 6;
         movie10Button.setPreferredSize(new Dimension(300,80));
         movie10Button.setFont(buttonFont);
         movieMenuPanel.add(movie10Button, movmc);
         movieMenuPanel.setVisible(false);
      	
      	//Create Movie Channel Panel
         mchc.fill = GridBagConstraints.BOTH;
         mchc.gridx = 0;
         mchc.gridy = 0;
         mchc.ipady = 1;
         mchc.gridwidth = 2;
         moviesLabel.setFont(mainLabelFont);
         movieChannelPanel.add(moviesLabel, mchc);
         mchc.gridx = 0;
         mchc.gridy = 1;
         mchc.ipady = 0;
         mchc.gridwidth = 1;
         movieNameLabel.setFont(buttonFont);
         movieNameLabel.setPreferredSize(new Dimension(400,100));
         movieChannelPanel.add(movieNameLabel, mchc);
         mchc.gridx = 1;
         mchc.gridy = 1;
         movieThumbnailLabel.setFont(buttonFont);
         movieThumbnailLabel.setPreferredSize(new Dimension(400,300));
         movieChannelPanel.add(movieThumbnailLabel, mchc);
         mchc.gridx = 0;
         mchc.gridy = 2;
         movieDescriptionLabel.setFont(buttonFont);
         movieDescriptionLabel.setBackground(Color.LIGHT_GRAY);
         movieDescriptionLabel.setPreferredSize(new Dimension(200,300));
         movieChannelPanel.add(movieDescriptionLabel, mchc);
         mchc.gridx = 0;
         mchc.gridy = 3;
         mchc.gridheight = 1;
         timeElapsedLabel.setFont(buttonFont);
         timeElapsedLabel.setPreferredSize(new Dimension(300,300));
         movieChannelPanel.add(timeElapsedLabel, mchc);
         movieChannelPanel.setVisible(false);
      	
      	//Create Movie Playing Panel
         moviePlayingLabel.setFont(mainLabelFont);
         moviePlayingPanel.add(moviePlayingLabel);
         moviePlayingPanel.setVisible(false);
      	
      	//Create VOD Menu Panel
         vodc.gridx = 0;
         vodc.gridy = 0;
         vodc.ipady = 1;
         vodc.gridwidth = 2;
         vodLabel.setFont(mainLabelFont);
         vodMenuPanel.add(vodLabel, vodc);
         vodc.gridx = 0;
         vodc.gridy = 1;
         vodc.ipady = 0;
         vodc.gridwidth = 2;
         selectGenreLabel.setFont(buttonFont);
         vodMenuPanel.add(selectGenreLabel, vodc);
         vodc.gridx = 0;
         vodc.gridy = 2;
         vodc.gridwidth = 1;
         comedy2Button.setPreferredSize(new Dimension(300,80));
         comedy2Button.setFont(buttonFont);
         vodMenuPanel.add(comedy2Button, vodc);
         vodc.gridx = 1;
         vodc.gridy = 2;
         lifestyleButton.setPreferredSize(new Dimension(300,80));
         lifestyleButton.setFont(buttonFont);
         vodMenuPanel.add(lifestyleButton, vodc);
         vodc.gridx = 0;
         vodc.gridy = 3;
         documentaryButton.setPreferredSize(new Dimension(300,80));
         documentaryButton.setFont(buttonFont);
         vodMenuPanel.add(documentaryButton, vodc);
         vodc.gridx = 1;
         vodc.gridy = 3;
         currentAffairsButton.setPreferredSize(new Dimension(300,80));
         currentAffairsButton.setFont(buttonFont);
         vodMenuPanel.add(currentAffairsButton, vodc);
         vodc.gridx = 0;
         vodc.gridy = 4;
         forKidsButton.setPreferredSize(new Dimension(300,80));
         forKidsButton.setFont(buttonFont);
         vodMenuPanel.add(forKidsButton, vodc);
         vodc.gridx = 1;
         vodc.gridy = 4;
         sportsButton.setPreferredSize(new Dimension(300,80));
         sportsButton.setFont(buttonFont);
         vodMenuPanel.add(sportsButton, vodc);
         vodc.gridx = 0;
         vodc.gridy = 5;
         newsButton.setPreferredSize(new Dimension(300,80));
         newsButton.setFont(buttonFont);
         vodMenuPanel.add(newsButton, vodc);
         vodc.gridx = 1;
         vodc.gridy = 5;
         dramaButton.setPreferredSize(new Dimension(300,80));
         dramaButton.setFont(buttonFont);
         vodMenuPanel.add(dramaButton, vodc);
         vodc.gridx = 0;
         vodc.gridy = 6;
         travelButton.setPreferredSize(new Dimension(300,80));
         travelButton.setFont(buttonFont);
         vodMenuPanel.add(travelButton, vodc);
         vodc.gridx = 1;
         vodc.gridy = 6;
         suspenseButton.setPreferredSize(new Dimension(300,80));
         suspenseButton.setFont(buttonFont);
         vodMenuPanel.add(suspenseButton, vodc);
         vodMenuPanel.setVisible(false);
      
      	//Create VOD Program Menu
         vpc.gridx = 0;
         vpc.gridy = 0;
         vpc.ipady = 1;
         vpc.gridwidth = 2;
         vodLabel.setFont(mainLabelFont);
         vodProgPanel.add(vodLabel, vpc);
         vpc.gridx = 0;
         vpc.gridy = 1;
         vpc.ipady = 0;
         vpc.gridwidth = 2;
         selectProgLabel.setFont(buttonFont);
         vodProgPanel.add(selectProgLabel, vpc);
         vpc.gridx = 0;
         vpc.gridy = 2;
         vpc.gridwidth = 1;
         prog1Button.setPreferredSize(new Dimension(300,80));
         prog1Button.setFont(buttonFont);
         vodProgPanel.add(prog1Button, vpc);
         vpc.gridx = 1;
         vpc.gridy = 2;
         prog2Button.setPreferredSize(new Dimension(300,80));
         prog2Button.setFont(buttonFont);
         vodProgPanel.add(prog2Button, vpc);
         vpc.gridx = 0;
         vpc.gridy = 3;
         prog3Button.setPreferredSize(new Dimension(300,80));
         prog3Button.setFont(buttonFont);
         vodProgPanel.add(prog3Button, vpc);
         vpc.gridx = 1;
         vpc.gridy = 3;
         prog4Button.setPreferredSize(new Dimension(300,80));
         prog4Button.setFont(buttonFont);
         vodProgPanel.add(prog4Button, vpc);
         vpc.gridx = 0;
         vpc.gridy = 4;
         prog5Button.setPreferredSize(new Dimension(300,80));
         prog5Button.setFont(buttonFont);
         vodProgPanel.add(prog5Button, vpc);
         vpc.gridx = 1;
         vpc.gridy = 4;
         prog6Button.setPreferredSize(new Dimension(300,80));
         prog6Button.setFont(buttonFont);
         vodProgPanel.add(prog6Button, vpc);
         vpc.gridx = 0;
         vpc.gridy = 5;
         prog7Button.setPreferredSize(new Dimension(300,80));
         prog7Button.setFont(buttonFont);
         vodProgPanel.add(prog7Button, vpc);
         vpc.gridx = 1;
         vpc.gridy = 5;
         prog8Button.setPreferredSize(new Dimension(300,80));
         prog8Button.setFont(buttonFont);
         vodProgPanel.add(prog8Button, vpc);
         vpc.gridx = 0;
         vpc.gridy = 6;
         prog9Button.setPreferredSize(new Dimension(300,80));
         prog9Button.setFont(buttonFont);
         vodProgPanel.add(prog9Button, vpc);
         vpc.gridx = 1;
         vpc.gridy = 6;
         prog10Button.setPreferredSize(new Dimension(300,80));
         prog10Button.setFont(buttonFont);
         vodProgPanel.add(prog10Button, vpc);
         vodProgPanel.setVisible(false);
      	
      	//Create VOD program Description Panel
         vpdc.fill = GridBagConstraints.BOTH;
         vpdc.gridx = 0;
         vpdc.gridy = 0;
         vpdc.ipady = 1;
         vpdc.gridwidth = 2;
         vodLabel.setFont(mainLabelFont);
         vodProgDescPanel.add(vodLabel, vpdc);
         vpdc.gridx = 0;
         vpdc.gridy = 1;
         vpdc.ipady = 0;
         vpdc.gridwidth = 1;
         progNameLabel.setFont(buttonFont);
         progNameLabel.setPreferredSize(new Dimension(400,100));
         vodProgDescPanel.add(progNameLabel, vpdc);
         vpdc.gridx = 1;
         vpdc.gridy = 1;
         progThumbnailLabel.setFont(buttonFont);
         progThumbnailLabel.setPreferredSize(new Dimension(400,300));
         vodProgDescPanel.add(progThumbnailLabel, vpdc);
         vpdc.gridx = 0;
         vpdc.gridy = 2;
         progDescriptionLabel.setFont(buttonFont);
         progDescriptionLabel.setBackground(Color.LIGHT_GRAY);
         progDescriptionLabel.setPreferredSize(new Dimension(200,300));
         vodProgDescPanel.add(progDescriptionLabel, vpdc);
         vodProgDescPanel.setVisible(false);
      	
      	//Create Program Playing screen
         ppc.gridx = 0;
         ppc.gridy = 0;
         ppc.gridwidth = 3;
         progPlayingLabel.setFont(mainLabelFont);
         progPlayingPanel.add(progPlayingLabel);
         ppc.gridx = 0;
         ppc.gridy = 1;
         ppc.gridwidth = 1;
         rewButton.setPreferredSize(new Dimension(100,80));
         rewButton.setFont(buttonFont);
         progPlayingPanel.add(rewButton, ppc);
         ppc.gridx = 1;
         ppc.gridy = 1;
         play2Button.setPreferredSize(new Dimension(100,80));
         play2Button.setFont(buttonFont);
         progPlayingPanel.add(play2Button, ppc);
         ppc.gridx = 2;
         ppc.gridy = 1;
         ffButton.setPreferredSize(new Dimension(100,80));
         ffButton.setFont(buttonFont);
         progPlayingPanel.add(ffButton, ppc);
         progPlayingPanel.setVisible(false);
      	
      	//Create Interactive menu Panel
         imc.gridx = 0;
         imc.gridy = 0;
         imc.ipady = 1;
         interactiveLabel.setFont(mainLabelFont);
         interactiveMenuPanel.add(interactiveLabel, imc);
         imc.gridx = 0;
         imc.gridy = 1;
         imc.ipady = 0;
         gamesButton.setPreferredSize(new Dimension(600,150));
         gamesButton.setFont(buttonFont);	
         interactiveMenuPanel.add(gamesButton, imc);
         imc.gridx = 0;
         imc.gridy = 2;
         smsButton.setPreferredSize(new Dimension(600,150));
         smsButton.setFont(buttonFont);
         interactiveMenuPanel.add(smsButton, imc);
         imc.gridx = 0;
         imc.gridy = 3;
         newsNetButton.setPreferredSize(new Dimension(600,150));
         newsNetButton.setFont(buttonFont);
         interactiveMenuPanel.add(newsNetButton, imc);
      	
      	//Create News Net Panel
         nnmc.gridx = 0;
         nnmc.gridy = 0;
         nnmc.ipady = 1;
         nnmc.gridwidth = 2;
         newsNetLabel.setFont(mainLabelFont);
         newsNetMenuPanel.add(newsNetLabel, nnmc);
         nnmc.gridx = 0;
         nnmc.gridy = 1;
         nnmc.ipady = 0;
         nnmc.gridwidth = 2;
         newsNetGenreLabel.setFont(buttonFont);
         newsNetMenuPanel.add(newsNetGenreLabel, nnmc);
         nnmc.gridx = 0;
         nnmc.gridy = 2;
         nnmc.gridwidth = 1;
         breakingNewsButton.setPreferredSize(new Dimension(300,80));
         breakingNewsButton.setFont(buttonFont);
         newsNetMenuPanel.add(breakingNewsButton, nnmc);
         nnmc.gridx = 1;
         nnmc.gridy = 2;
         topStoriesButton.setPreferredSize(new Dimension(300,80));
         topStoriesButton.setFont(buttonFont);
         newsNetMenuPanel.add(topStoriesButton, nnmc);
         nnmc.gridx = 0;
         nnmc.gridy = 3;
         localButton.setPreferredSize(new Dimension(300,80));
         localButton.setFont(buttonFont);
         newsNetMenuPanel.add(localButton, nnmc);
         nnmc.gridx = 1;
         nnmc.gridy = 3;
         world2Button.setPreferredSize(new Dimension(300,80));
         world2Button.setFont(buttonFont);
         newsNetMenuPanel.add(world2Button, nnmc);
         nnmc.gridx = 0;
         nnmc.gridy = 4;
         businessButton.setPreferredSize(new Dimension(300,80));
         businessButton.setFont(buttonFont);
         newsNetMenuPanel.add(businessButton, nnmc);
         nnmc.gridx = 1;
         nnmc.gridy = 4;
         sports2Button.setPreferredSize(new Dimension(300,80));
         sports2Button.setFont(buttonFont);
         newsNetMenuPanel.add(sports2Button, nnmc);
         nnmc.gridx = 0;
         nnmc.gridy = 5;
         entertainmentButton.setPreferredSize(new Dimension(300,80));
         entertainmentButton.setFont(buttonFont);
         newsNetMenuPanel.add(entertainmentButton, nnmc);
         nnmc.gridx = 1;
         nnmc.gridy = 5;
         technologyButton.setPreferredSize(new Dimension(300,80));
         technologyButton.setFont(buttonFont);
         newsNetMenuPanel.add(technologyButton, nnmc);
         nnmc.gridx = 0;
         nnmc.gridy = 6;
         weatherButton.setPreferredSize(new Dimension(300,80));
         weatherButton.setFont(buttonFont);
         newsNetMenuPanel.add(weatherButton, nnmc);
         nnmc.gridx = 1;
         nnmc.gridy = 6;
         humourButton.setPreferredSize(new Dimension(300,80));
         humourButton.setFont(buttonFont);
         newsNetMenuPanel.add(humourButton, nnmc);
         newsNetMenuPanel.setVisible(false);
      	
      	//Create Net News Headline Panel
         nnhc.gridx = 0;
         nnhc.gridy = 0;
         nnhc.ipady = 1;
         nnhc.gridwidth = 2;
         newsNetLabel.setFont(mainLabelFont);
         newsNetHeadlinePanel.add(newsNetLabel, nnhc);
         nnhc.gridx = 0;
         nnhc.gridy = 1;
         nnhc.ipady = 0;
         nnhc.gridwidth = 2;
         newsNetHeadlineLabel.setFont(buttonFont);
         newsNetHeadlinePanel.add(newsNetHeadlineLabel, nnhc);
         nnhc.gridx = 0;
         nnhc.gridy = 2;
         nnhc.gridwidth = 1;
         headline1Button.setPreferredSize(new Dimension(300,80));
         headline1Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline1Button, nnhc);
         nnhc.gridx = 1;
         nnhc.gridy = 2;
         headline2Button.setPreferredSize(new Dimension(300,80));
         headline2Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline2Button, nnhc);
         nnhc.gridx = 0;
         nnhc.gridy = 3;
         headline3Button.setPreferredSize(new Dimension(300,80));
         headline3Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline3Button, nnhc);
         nnhc.gridx = 1;
         nnhc.gridy = 3;
         headline4Button.setPreferredSize(new Dimension(300,80));
         headline4Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline4Button, nnhc);
         nnhc.gridx = 0;
         nnhc.gridy = 4;
         headline5Button.setPreferredSize(new Dimension(300,80));
         headline5Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline5Button, nnhc);
         nnhc.gridx = 1;
         nnhc.gridy = 4;
         headline6Button.setPreferredSize(new Dimension(300,80));
         headline6Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline6Button, nnhc);
         nnhc.gridx = 0;
         nnhc.gridy = 5;
         headline7Button.setPreferredSize(new Dimension(300,80));
         headline7Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline7Button, nnhc);
         nnhc.gridx = 1;
         nnhc.gridy = 5;
         headline8Button.setPreferredSize(new Dimension(300,80));
         headline8Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline8Button, nnhc);
         nnhc.gridx = 0;
         nnhc.gridy = 6;
         headline9Button.setPreferredSize(new Dimension(300,80));
         headline9Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline9Button, nnhc);
         nnhc.gridx = 1;
         nnhc.gridy = 6;
         headline10Button.setPreferredSize(new Dimension(300,80));
         headline10Button.setFont(buttonFont);
         newsNetHeadlinePanel.add(headline10Button, nnhc);
         newsNetHeadlinePanel.setVisible(false);
      	
      	//Create News Net Text Panel
         nntc.gridy = 0;
         headlineLabel.setFont(mainLabelFont);
         newsNetTextPanel.add(headlineLabel, nntc);
         nntc.gridy = 1;
         authorLabel.setFont(buttonFont);
         newsNetTextPanel.add(authorLabel, nntc);
         nntc.gridy = 2;
         newsNetTextPanel.add(articleText, nntc);
         newsNetTextPanel.setVisible(false);
      	
      	//Create Game Selection Panel
         gmc.gridx = 0;
         gmc.gridy = 0;
         gmc.gridwidth = 3;
         gamesLabel.setFont(mainLabelFont);
         gameMenuPanel.add(gamesLabel, gmc);
      	gmc.gridx = 0;
         gmc.gridy = 1;
         gmc.gridwidth = 1;
			gmc.gridheight = 4;
      	gameMenuPanel.add(gameGenreList, gmc);
			gmc.gridx = 1;
         gmc.gridy = 1;
			gameMenuPanel.add(gamesList, gmc);
			gmc.gridx = 2;
         gmc.gridy = 1;
			gmc.gridheight = 1;
			boxArtLabel.setFont(buttonFont);
			gameMenuPanel.add(boxArtLabel, gmc);
			gmc.gridx = 2;
         gmc.gridy = 2;
			gameDescriptionLabel.setFont(buttonFont);
			gameMenuPanel.add(gameDescriptionLabel, gmc);
			gmc.gridx = 2;
         gmc.gridy = 3;
			playersOnlineLabel.setFont(buttonFont);
			gameMenuPanel.add(playersOnlineLabel, gmc);
			gmc.gridx = 2;
         gmc.gridy = 4;
			gameMenuPanel.setVisible(false);
      	
      	
      	//Create Game Playing Panel
      	gamePlayingLabel.setFont(mainLabelFont);
         gamePlayingPanel.add(gamePlayingLabel);
         gamePlayingPanel.setVisible(false);
      	
      	//Create SMS/Email Panel
      	smcc.gridx = 0;
         smcc.gridy = 0;
         smcc.gridwidth = 4;
         smsLabel.setFont(mainLabelFont);
         smsPanel.add(smsLabel, smcc);
			smcc.gridx = 0;
         smcc.gridy = 1;
         smcc.gridwidth = 1;
			newButton.setPreferredSize(new Dimension(150,50));
         newButton.setFont(buttonFont);
         smsPanel.add(newButton, smcc);
			smcc.gridx = 1;
         smcc.gridy = 1;
         smcc.gridwidth = 1;
			replyButton.setPreferredSize(new Dimension(150,50));
         replyButton.setFont(buttonFont);
         smsPanel.add(replyButton, smcc);
			smcc.gridx = 2;
         smcc.gridy = 1;
         smcc.gridwidth = 1;
			forwardButton.setPreferredSize(new Dimension(150,50));
         forwardButton.setFont(buttonFont);
         smsPanel.add(forwardButton, smcc);
			smcc.gridx = 3;
         smcc.gridy = 1;
         smcc.gridwidth = 1;
			sendButton.setPreferredSize(new Dimension(150,50));
         sendButton.setFont(buttonFont);
         smsPanel.add(sendButton, smcc);
			smcc.gridx = 0;
         smcc.gridy = 2;
         smcc.gridwidth = 1;
			smcc.gridheight = 2;
			inboxButton.setPreferredSize(new Dimension(150,50));
         inboxButton.setFont(buttonFont);
         smsPanel.add(inboxButton, smcc);
			smcc.gridx = 0;
         smcc.gridy = 4;
         smcc.gridwidth = 1;
			smcc.gridheight = 2;
			sentButton.setPreferredSize(new Dimension(150,50));
         sentButton.setFont(buttonFont);
         smsPanel.add(sentButton, smcc);
			smcc.gridx = 1;
         smcc.gridy = 2;
         smcc.gridwidth = 3;
			smcc.gridheight = 1;
         smsToLabel.setFont(buttonFont);
         smsPanel.add(smsToLabel, smcc);
			smcc.gridx = 1;
         smcc.gridy = 3;
         smcc.gridwidth = 3;
			smcc.gridheight = 1;
			toField.setColumns(35);
         smsPanel.add(toField, smcc);
			smcc.gridx = 1;
         smcc.gridy = 4;
         smcc.gridwidth = 3;
			smcc.gridheight = 1;
         subjectLabel.setFont(buttonFont);
         smsPanel.add(subjectLabel, smcc);
			smcc.gridx = 1;
         smcc.gridy = 5;
         smcc.gridwidth = 3;
			smcc.gridheight = 1;
			subjectField.setColumns(35);
         smsPanel.add(subjectField, smcc);
			smcc.gridx = 1;
         smcc.gridy = 6;
         smcc.gridwidth = 3;
			smcc.gridheight = 1;
         messageLabel.setFont(buttonFont);
         smsPanel.add(messageLabel, smcc);
			smcc.gridx = 1;
         smcc.gridy = 7;
         smcc.gridwidth = 3;
			smcc.gridheight = 3;
			messageArea.setColumns(35);
			messageArea.setRows(10);
			messageArea.setText(message);
         smsPanel.add(messageArea, smcc);
			smcc.gridx = 0;
         smcc.gridy = 18;
         smcc.gridwidth = 4;
			smcc.gridheight = 1;
         qwertyLabel.setFont(mainLabelFont);
         smsPanel.add(qwertyLabel, smcc);
			smsPanel.setVisible(false);
			
			//Create Language screen
			lpc.gridx = 0;
         lpc.gridy = 0;
         lpc.ipady = 1;
         lpc.gridwidth = 2;
         languageLabel.setFont(mainLabelFont);
         languagePanel.add(languageLabel, lpc);
         lpc.gridx = 0;
         lpc.gridy = 2;
         lpc.gridwidth = 1;
         language1Button.setPreferredSize(new Dimension(300,80));
         language1Button.setFont(buttonFont);
         languagePanel.add(language1Button, lpc);
         lpc.gridx = 1;
         lpc.gridy = 2;
         language2Button.setPreferredSize(new Dimension(300,80));
         language2Button.setFont(buttonFont);
         languagePanel.add(language2Button, lpc);
         lpc.gridx = 0;
         lpc.gridy = 3;
         language3Button.setPreferredSize(new Dimension(300,80));
         language3Button.setFont(buttonFont);
         languagePanel.add(language3Button, lpc);
         lpc.gridx = 1;
         lpc.gridy = 3;
         language4Button.setPreferredSize(new Dimension(300,80));
         language4Button.setFont(buttonFont);
         languagePanel.add(language4Button, lpc);
         lpc.gridx = 0;
         lpc.gridy = 4;
         language5Button.setPreferredSize(new Dimension(300,80));
         language5Button.setFont(buttonFont);
         languagePanel.add(language5Button, lpc);
         lpc.gridx = 1;
         lpc.gridy = 4;
         language6Button.setPreferredSize(new Dimension(300,80));
         language6Button.setFont(buttonFont);
         languagePanel.add(language6Button, lpc);
         lpc.gridx = 0;
         lpc.gridy = 5;
         language7Button.setPreferredSize(new Dimension(300,80));
         language7Button.setFont(buttonFont);
         languagePanel.add(language7Button, lpc);
         lpc.gridx = 1;
         lpc.gridy = 5;
         language8Button.setPreferredSize(new Dimension(300,80));
         language8Button.setFont(buttonFont);
         languagePanel.add(language8Button, lpc);
         lpc.gridx = 0;
         lpc.gridy = 6;
         language9Button.setPreferredSize(new Dimension(300,80));
         language9Button.setFont(buttonFont);
         languagePanel.add(language9Button, lpc);
         lpc.gridx = 1;
         lpc.gridy = 6;
         language10Button.setPreferredSize(new Dimension(300,80));
         language10Button.setFont(buttonFont);
         languagePanel.add(language10Button, lpc);
         languagePanel.setVisible(false);
      	
      	
         mainMenuPanel.setVisible(true);
         contentPane.add(mainMenuPanel, BorderLayout.CENTER); //FIRST PANEL -- DO NOT DELETE
      // 			movieMenuPanel.setVisible(true);
      // 			contentPane.add(movieMenuPanel, BorderLayout.CENTER); //FIRST PANEL -- DO NOT DELETE
      
      //create static South panel
         southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
         mainMenuButton.setVisible(true);
         southPanel.add(mainMenuButton);
         backButton.setVisible(false);
         southPanel.add(backButton);
         southPanel.add(Box.createHorizontalGlue());
         playButton.setPreferredSize(new Dimension(100,30));
         playButton.setVisible(false);
         southPanel.add(playButton);
         southPanel.setVisible(true);
         southPanel.setBackground(Color.BLACK);
         contentPane.add(southPanel, BorderLayout.SOUTH);
      	
      }
   /**
   	Describes the action each button should do when pressed depending on
   	what button is pressed and what panel is currently visible.  This method covers actions
		for all buttons in the user interface.
		@param e the ActionEvent to be performed
   */
       public void actionPerformed(ActionEvent e)
      {
         Object source = e.getSource();
      	
         if (source == sleepButton)
            System.exit(0);
         
         else if (source == mainMenuButton)
         {
            audioMenuPanel.setVisible(false);
            contentPane.remove(audioMenuPanel);
            audioStationPanel.setVisible(false);
            contentPane.remove(audioStationPanel);
            videoMenuPanel.setVisible(false);
            contentPane.remove(videoMenuPanel);
            movieMenuPanel.setVisible(false);
            contentPane.remove(movieMenuPanel);
            movieChannelPanel.setVisible(false);
            contentPane.remove(movieChannelPanel);
            moviePlayingPanel.setVisible(false);
            contentPane.remove(moviePlayingPanel);
            vodMenuPanel.setVisible(false);
            contentPane.remove(vodMenuPanel);
            vodProgPanel.setVisible(false);
            contentPane.remove(vodProgPanel);
            vodProgDescPanel.setVisible(false);
            contentPane.remove(vodProgDescPanel);
            progPlayingPanel.setVisible(false);
            contentPane.remove(progPlayingPanel);
            progControlPanel.setVisible(false);
            contentPane.remove(progControlPanel);
            allProgPanel.setVisible(false);
            contentPane.remove(allProgPanel);
            gameMenuPanel.setVisible(false);
            contentPane.remove(gameMenuPanel);
            gamePlayingPanel.setVisible(false);
            contentPane.remove(gamePlayingPanel);
            gameInvitePanel.setVisible(false);
            contentPane.remove(gameInvitePanel);
            smsPanel.setVisible(false);
            contentPane.remove(smsPanel);
            newsNetMenuPanel.setVisible(false);
            contentPane.remove(newsNetMenuPanel);
            interactiveMenuPanel.setVisible(false);
            contentPane.remove(interactiveMenuPanel);
            newsNetHeadlinePanel.setVisible(false);
            contentPane.remove(newsNetHeadlinePanel);
				newsNetTextPanel.setVisible(false);
				contentPane.remove(newsNetTextPanel);
				languagePanel.setVisible(false);
				contentPane.remove(languagePanel);
            playButton.setVisible(false);
            backButton.setVisible(false);
            mainMenuPanel.setVisible(true);
				childFilter.setVisible(true);
				languageButton.setVisible(true);
         	
            contentPane.add(mainMenuPanel, BorderLayout.CENTER);
         } 
         else if (source == backButton)
         {
            if (audioStationPanel.isVisible() == true)
            {
               audioStationPanel.setVisible(false);
               contentPane.remove(audioStationPanel);
               contentPane.add(audioMenuPanel, BorderLayout.CENTER);
               audioMenuPanel.setVisible(true);
               backButton.setVisible(false);
               playButton.setVisible(false);	
            }
            else if (movieMenuPanel.isVisible() == true)
            {
               movieMenuPanel.setVisible(false);
               contentPane.remove(movieMenuPanel);
               contentPane.add(videoMenuPanel, BorderLayout.CENTER);
               videoMenuPanel.setVisible(true);
               backButton.setVisible(false);
            }
            else if (movieChannelPanel.isVisible() == true)
            {
               movieChannelPanel.setVisible(false);
               contentPane.remove(movieChannelPanel);
               contentPane.add(movieMenuPanel, BorderLayout.CENTER);
               movieMenuPanel.setVisible(true);
               backButton.setText("Video Menu");
            }
            else if (moviePlayingPanel.isVisible() == true)
            {
               moviePlayingPanel.setVisible(false);
               contentPane.remove(moviePlayingPanel);
               contentPane.add(movieMenuPanel, BorderLayout.CENTER);
               movieMenuPanel.setVisible(true);
               backButton.setText("Video Menu");
            }
            else if (vodMenuPanel.isVisible() == true)
            {
               vodMenuPanel.setVisible(false);
               contentPane.remove(vodMenuPanel);
               contentPane.add(videoMenuPanel, BorderLayout.CENTER);
               videoMenuPanel.setVisible(true);
               backButton.setVisible(false);
            }
            else if (vodProgPanel.isVisible() == true)
            {
               vodProgPanel.setVisible(false);
               contentPane.remove(vodProgPanel);
               contentPane.add(vodMenuPanel, BorderLayout.CENTER);
               vodMenuPanel.setVisible(true);
               backButton.setText("Video Menu");
            }
            else if (vodProgDescPanel.isVisible() == true)
            {
               vodProgDescPanel.setVisible(false);
               contentPane.remove(vodProgDescPanel);
               contentPane.add(vodProgPanel, BorderLayout.CENTER);
               vodProgPanel.setVisible(true);
               backButton.setText("Genre Menu");
               playButton.setVisible(false);
            }
            else if (progPlayingPanel.isVisible() == true)
            {
               progPlayingPanel.setVisible(false);
               contentPane.remove(progPlayingPanel);
               contentPane.add(vodProgPanel, BorderLayout.CENTER);
               vodProgPanel.setVisible(true);
               backButton.setText("Genre Menu");
               playButton.setVisible(false);
            }
            else if (newsNetMenuPanel.isVisible() == true)
            {
               newsNetMenuPanel.setVisible(false);
               contentPane.remove(newsNetMenuPanel);
               contentPane.add(interactiveMenuPanel, BorderLayout.CENTER);
               interactiveMenuPanel.setVisible(true);
               backButton.setVisible(false);
            }
            else if (newsNetHeadlinePanel.isVisible() == true)
            {
               newsNetHeadlinePanel.setVisible(false);
               contentPane.remove(newsNetHeadlinePanel);
               contentPane.add(newsNetMenuPanel, BorderLayout.CENTER);
               newsNetMenuPanel.setVisible(true);
               backButton.setText("Interactive Menu");
            }
            else if (newsNetTextPanel.isVisible() == true)
            {
               newsNetTextPanel.setVisible(false);
               contentPane.remove(newsNetTextPanel);
               contentPane.add(newsNetHeadlinePanel, BorderLayout.CENTER);
               newsNetHeadlinePanel.setVisible(true);
               backButton.setText("Net News Topic Menu");
            }
				else if (gameMenuPanel.isVisible() == true)
            {
               gameMenuPanel.setVisible(false);
               contentPane.remove(gameMenuPanel);
               contentPane.add(interactiveMenuPanel, BorderLayout.CENTER);
               interactiveMenuPanel.setVisible(true);
               backButton.setVisible(false);
					playButton.setVisible(false);
            }
				else if (gamePlayingPanel.isVisible() == true)
            {
               gamePlayingPanel.setVisible(false);
               contentPane.remove(gamePlayingPanel);
               contentPane.add(gameMenuPanel, BorderLayout.CENTER);
               gameMenuPanel.setVisible(true);
					backButton.setText("Interactive Menu");
					playButton.setVisible(true);
					invitePlayer.setVisible(false);
				}
				else if (smsPanel.isVisible() == true)
            {
               smsPanel.setVisible(false);
               contentPane.remove(smsPanel);
               contentPane.add(interactiveMenuPanel, BorderLayout.CENTER);
               interactiveMenuPanel.setVisible(true);
               backButton.setVisible(false);
            }
  
         }
         else if (source == playButton)
         {
            if (audioStationPanel.isVisible() == true)
            {
               return;
            }
            else if (movieChannelPanel.isVisible() == true)
            {
               movieChannelPanel.setVisible(false);
               contentPane.remove(movieChannelPanel);
               contentPane.add(moviePlayingPanel, BorderLayout.CENTER);
               moviePlayingPanel.setVisible(true);
               playButton.setVisible(false);
            }
            else if (vodProgDescPanel.isVisible() == true)
            {
               vodProgDescPanel.setVisible(false);
               contentPane.remove(vodProgDescPanel);
               contentPane.add(progPlayingPanel, BorderLayout.CENTER);
               progPlayingPanel.setVisible(true);
               playButton.setVisible(false);
            }
				else if (gameMenuPanel.isVisible() == true)
            {
               gameMenuPanel.setVisible(false);
               contentPane.remove(gameMenuPanel);
               contentPane.add(gamePlayingPanel, BorderLayout.CENTER);
               gamePlayingPanel.setVisible(true);
					invitePlayer.setVisible(true);
					backButton.setText("Games Menu");
               playButton.setVisible(false);
            }
         }     
         
         else if (source == audioButton)
         {
				childFilter.setVisible(false);
				languageButton.setVisible(false);
            mainMenuPanel.setVisible(false);
            contentPane.remove(mainMenuPanel);
            contentPane.add(audioMenuPanel, BorderLayout.CENTER);
            audioMenuPanel.setVisible(true);	
         }
         else if (source == comedyButton || source == technoButton || source == countryButton || source == contemporaryButton || source == rocknRollButton || source == easyListeningButton || source == top40Button || source == worldButton || source == indieButton || source == popButton)
         {
            audioMenuPanel.setVisible(false);
            contentPane.remove(audioMenuPanel);
            contentPane.add(audioStationPanel, BorderLayout.CENTER);
            audioStationPanel.setVisible(true);
            backButton.setText("Audio Menu");
            backButton.setVisible(true);
            playButton.setVisible(true);
         }
         else if (source == videoButton)
         {
				childFilter.setVisible(false);
				languageButton.setVisible(false);
            mainMenuPanel.setVisible(false);
            contentPane.remove(mainMenuPanel);
            contentPane.add(videoMenuPanel, BorderLayout.CENTER);
            videoMenuPanel.setVisible(true);
         }
         else if (source == moviesButton)
         {
            videoMenuPanel.setVisible(false);
            contentPane.remove(videoMenuPanel);
            contentPane.add(movieMenuPanel, BorderLayout.CENTER);
            movieMenuPanel.setVisible(true);
            backButton.setText("Video Menu");
            backButton.setVisible(true);
         }
         else if (source == movie1Button || source == movie2Button || source == movie3Button || source == movie4Button || source == movie5Button || source == movie6Button || source == movie7Button || source == movie8Button || source == movie9Button || source == movie10Button)
         {
            movieMenuPanel.setVisible(false);
            contentPane.remove(movieMenuPanel);
            contentPane.add(movieChannelPanel, BorderLayout.CENTER);
            movieChannelPanel.setVisible(true);
            backButton.setText("Movie Menu");
            backButton.setVisible(true);
            playButton.setVisible(true);
         }
         else if (source == vodButton)
         {
            videoMenuPanel.setVisible(false);
            contentPane.remove(videoMenuPanel);
            contentPane.add(vodMenuPanel, BorderLayout.CENTER);
            vodMenuPanel.setVisible(true);
            backButton.setText("Video Menu");
            backButton.setVisible(true);
         }
         else if (source == comedy2Button || source == lifestyleButton || source == documentaryButton || source == currentAffairsButton || source == forKidsButton || source == sportsButton || source == newsButton || source == dramaButton || source == travelButton || source == suspenseButton)
         {
            vodMenuPanel.setVisible(false);
            contentPane.remove(vodMenuPanel);
            contentPane.add(vodProgPanel, BorderLayout.CENTER);
            vodProgPanel.setVisible(true);
            backButton.setText("Genre Menu");
            backButton.setVisible(true);
         }
         
         else if (source == prog1Button || source == prog2Button || source == prog3Button || source == prog4Button || source == prog5Button || source == prog6Button || source == prog7Button || source == prog8Button || source == prog9Button || source == prog10Button)
         {
            vodProgPanel.setVisible(false);
            contentPane.remove(vodProgPanel);
            contentPane.add(vodProgDescPanel, BorderLayout.CENTER);
            vodProgDescPanel.setVisible(true);
            backButton.setText("Program Menu");
            playButton.setVisible(true);
         }
         else if (source == interactiveButton)
         {
				childFilter.setVisible(false);
				languageButton.setVisible(false);
            mainMenuPanel.setVisible(false);
            contentPane.remove(mainMenuPanel);
            contentPane.add(interactiveMenuPanel, BorderLayout.CENTER);
            interactiveMenuPanel.setVisible(true);
         }
         else if (source == newsNetButton)
         {
            interactiveMenuPanel.setVisible(false);
            contentPane.remove(interactiveMenuPanel);
            contentPane.add(newsNetMenuPanel, BorderLayout.CENTER);
            newsNetMenuPanel.setVisible(true);
            backButton.setText("Interactive Menu");
            backButton.setVisible(true);
         	
         }
         else if (source == breakingNewsButton || source == topStoriesButton || source == localButton || source == world2Button || source == businessButton || source == sports2Button || source == entertainmentButton || source == technologyButton || source == weatherButton || source == humourButton)
         {
            newsNetMenuPanel.setVisible(false);
            contentPane.remove(newsNetMenuPanel);
            contentPane.add(newsNetHeadlinePanel, BorderLayout.CENTER);
            newsNetHeadlinePanel.setVisible(true);
            backButton.setText("News Net Topic Menu");
         }
         else if (source == headline1Button || source == headline2Button || source == headline3Button || source == headline4Button || source == headline5Button || source == headline6Button || source == headline7Button || source == headline8Button || source == headline9Button || source == headline10Button)
         {
            newsNetHeadlinePanel.setVisible(false);
            contentPane.remove(newsNetHeadlinePanel);
            contentPane.add(newsNetTextPanel, BorderLayout.CENTER);
            newsNetTextPanel.setVisible(true);
            backButton.setText("News Net Headline Menu");
         }
			else if (source == gamesButton)
         {
            interactiveMenuPanel.setVisible(false);
            contentPane.remove(interactiveMenuPanel);
            contentPane.add(gameMenuPanel, BorderLayout.CENTER);
            gameMenuPanel.setVisible(true);
            backButton.setText("Interactive Menu");
            backButton.setVisible(true);
				playButton.setVisible(true);
         }
			else if (source == smsButton)
         {
            interactiveMenuPanel.setVisible(false);
            contentPane.remove(interactiveMenuPanel);
            contentPane.add(smsPanel, BorderLayout.CENTER);
            smsPanel.setVisible(true);
            backButton.setText("Interactive Menu");
            backButton.setVisible(true);
         }
			else if (source == childFilter)
			{
				if (childFilter.getText().equals("Child Filter On"))
					childFilter.setText("Child Filter Off");
				else
					childFilter.setText("Child Filter On");
			}
			else if (source == language1Button || source == language2Button || source == language3Button || source == language4Button || source == language5Button || source == language6Button || source == language7Button || source == language8Button || source == language9Button || source == language10Button)
         {
            languagePanel.setVisible(false);
				contentPane.remove(languagePanel);
				contentPane.add(mainMenuPanel, BorderLayout.CENTER);
            mainMenuPanel.setVisible(true);
				childFilter.setVisible(true);
				languageButton.setVisible(true);
         }
			else if (source == languageButton)
         {
				childFilter.setVisible(false);
				languageButton.setVisible(false);
            mainMenuPanel.setVisible(false);
            contentPane.remove(mainMenuPanel);
            contentPane.add(languagePanel, BorderLayout.CENTER);
            languagePanel.setVisible(true);	
         }
      }
   
   /**
   	Main method to start the application.
   	@param args the command line parameters
   	@return void
   */
       public static void main(String[] args)
      {
      
      //Initialises new instance of Assign1 class
         UIA2Main myProgram = new UIA2Main();
      //validate frame and all of its components
         myProgram.validate();
      //sets initial frame size to 400x400
         myProgram.setSize(700, 700);
      //sets frame to be visible on screen
         myProgram.setVisible(true);
      
      //initialises Dimension objects to contain size of the myProgram frame and size of computer screen
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         Dimension frameSize = myProgram.getSize();
      
      //ensures that the frame size cannot be bigger than the screen it is on
         if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
         if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
      
      //sets myProgram frame to the middle of the screen
         myProgram.setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
      
      }
   }
