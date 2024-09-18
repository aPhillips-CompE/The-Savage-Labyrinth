import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class the_Savage_Labyrinth extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	private Stage stage1;
	private Stage stage2;
	private Stage stage3;
	
	private double hp;
	private double magicka;
	private double defense;
	private double totalGold;
	private double playerLevel;
	private double playerXP;
	private String playerClass;
	private String playerName;
	private String playerClass1;
	
	//Skill stats
	private int blade;
	private int mysticism;
	private int mercantile;
	private int heavyArmor;
	private int destruction;
	private int speechcraft;
	private int athletics;
	private int handToHand;
	private int restoration;
	private int lightArmor;
	private int marksman;
	
	//Location for movement
	private int locationX;
	private int locationY;
	
	//For class choosing
	private int choosen;
	
	/* svubbsfhvbhsfvbhjv */
	//Main method for the game where all other methods will tie
	@Override
	public void start(Stage primaryStage) throws IOException {
		Image titleScreenImage = new Image("file:gr/title_screen.png");
		ImageView titleScreenView = new ImageView(titleScreenImage);
			titleScreenView.setFitWidth(800);
			titleScreenView.setPreserveRatio(true);
			
		VBox titleScreenV = new VBox(titleScreenView);
			titleScreenV.setAlignment(Pos.CENTER);
			
		Scene titleScreen = new Scene(titleScreenV);
		
		titleScreen.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.L) {
				primaryStage.close();
				load();
			}
			else if (event.getCode() == KeyCode.E) {
				System.exit(0);
			}
			else {
				primaryStage.close();
				characterCreator1();
			}
		});
		
		primaryStage.setScene(titleScreen);
		primaryStage.setTitle("The Savage Labyrinth - Title Screen");
		primaryStage.show();
	}
	
	//Method for determining player class
	private final String characterCreator1() {
		stage1 = new Stage();

		Label playerClassLabel = new Label("Choose a class, adventurer!");
		
		Button fighterButton = new Button("Fighter");
			fighterButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) {
					playerClass1 = "Fighter";
					choosen = 1;
				}
			});
		Button rogueButton = new Button("Rogue");
			rogueButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) {
					playerClass1 = "Rogue";
					choosen = 1;
				}
			});
		Button mageButton = new Button("Mage");
			mageButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) {
					playerClass1 = "Mage";
					choosen = 1;
				}
			});
		
		HBox classBtns = new HBox(20, fighterButton, rogueButton, mageButton);
			classBtns.setAlignment(Pos.CENTER);
		
		Button nameScreen = new Button("Next");
			nameScreen.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) {
					if (choosen == 0) {
						JOptionPane.showMessageDialog(null, "Choose a class!");
					}
					else {
						stage1.close();
						characterCreator2();
					}
				}
			});
			
		VBox fin = new VBox(10, playerClassLabel, classBtns, nameScreen);
			fin.setAlignment(Pos.CENTER);
			fin.setStyle("-fx-font-size: 20; -fx-text-fill: white; -fx-background-color: #EBCFA7;");
	
		GridPane characterCreatorScreen = new GridPane();
			characterCreatorScreen.add(fin, 0, 0);
			characterCreatorScreen.setAlignment(Pos.CENTER);
			characterCreatorScreen.setStyle("-fx-background-image: url('gr/night.jpg'); -fx-background-repeat: no-repeat; -fx-background-size: contain;");
		
		Scene s = new Scene(characterCreatorScreen, 900, 550);
			s.getStylesheets().add("the_Savage_CSS.css");
		
		stage1.setScene(s);
		stage1.setTitle("The Savage Labyrinth - Character Creator");
		stage1.show();
		return playerClass1;
	}
	
	public final String playerClassChoosen() {
		String clasS = characterCreator1();
		stage1.close();
		return clasS;
	}
	
	//Method for determining player name
	private final String characterCreator2() {
		stage1 = new Stage();
		
		Label playerNameLabel = new Label("What is your name?");
		TextField playerNameTF = new TextField();
			playerNameTF.setStyle("-fx-background-color: black; -fx-text-fill: orange;");
			
		String playerName2 = playerNameTF.getText();
	
		Button finalScreen = new Button("Next");
		if (playerNameTF.getText() != "") {
			finalScreen.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override public void handle(MouseEvent ev) {
						stage1.close();
						characterCreator3();
					}
				});
		}
		else {
			JOptionPane.showMessageDialog(null, "Enter a name!");
		}
		
		String playerName1 = playerName2;
		
		VBox fin = new VBox(playerNameLabel, playerNameTF, finalScreen);
			fin.setAlignment(Pos.CENTER);
			fin.setStyle("-fx-font-size: 20; -fx-text-fill: white; -fx-background-color: #EBCFA7;");
		GridPane characterCreatorScreen = new GridPane();
			characterCreatorScreen.add(fin, 0, 0);
			characterCreatorScreen.setAlignment(Pos.CENTER);
			characterCreatorScreen.setStyle("-fx-background-image: url('gr/night.jpg'); -fx-background-repeat: no-repeat; -fx-background-size: contain;");
		
		Scene s = new Scene(characterCreatorScreen, 900, 550);
			s.getStylesheets().add("the_Savage_CSS.css");
			
		
		stage1.setScene(s);
		stage1.setTitle("The Savage Labyrinth - Character Creator");
		stage1.show();
		return playerName1;
	}
	
	public final String playerNameChoosen() {
		String name = characterCreator2();
		stage1.close();
		return name;
	}
	
	//Method for determining player stats								
	private final void characterCreator3() {
		stage1 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
	
		Button continueToGame = new Button("Done");
			continueToGame.setOnAction(e -> {
				stage1.close();
				
				stage2 = grid1();
				stage2.show();
			});
		
		int bladeSkill = 10;
		int mysticismSkill = 10;
		int mercantileSkill = 10;
		int heavyArmorSkill = 10;		
		int destructionSkill = 10;
		int speechcraftSkill = 10;
		int athleticsSkill = 10;
		int handToHandSkill = 10;		
		int restorationSkill = 10;			
		int lightArmorSkill = 10;
		int marksmanSkill = 10;
		
		Image fighterImage = new Image("file:gr/fighterPlayerMenu.png");
		Image rogueImage = new Image("file:gr/roguePlayerMenu.png");
		Image mageImage = new Image("file:gr/magePlayerMenu.png");
		ImageView character = new ImageView();
			character.setFitWidth(250);
			character.setPreserveRatio(true);
			
		if (playerClass.equals("Fighter")) {
			bladeSkill = 15;
			heavyArmorSkill = 15;
			athleticsSkill = 15;
			handToHandSkill = 15;
			hp = 125;
			magicka = 75;
			character.setImage(fighterImage);
		}
		if (playerClass.equals("Rogue")) {
			marksmanSkill = 15;
			lightArmorSkill = 15;
			speechcraftSkill = 15;
			bladeSkill = 15;
			hp = 100;
			magicka = 100;
			character.setImage(rogueImage);
		}
		if (playerClass.equals("Mage")) {
			mysticismSkill = 15;
			mercantileSkill = 15;
			destructionSkill = 15;
			restorationSkill = 15;
			hp = 75;
			magicka = 125;
			character.setImage(mageImage);
		}
		
		blade = bladeSkill;
		mysticism = mysticismSkill;
		mercantile = mercantileSkill;
		heavyArmor = heavyArmorSkill;		
		destruction	= destructionSkill;
		speechcraft	= speechcraftSkill;
		athletics = athleticsSkill;
		handToHand = handToHandSkill;		
		restoration	=  restorationSkill;			
		lightArmor = lightArmorSkill;
		marksman = marksmanSkill;
		
		Label bladeSkillLabel = new Label("Blade: " + bladeSkill);
		Label mysticismSkillLabel = new Label("Myst: " + mysticismSkill);
		Label mercantileSkillLabel = new Label("Merc: " + mercantileSkill);
		Label heavyArmorSkillLabel = new Label("HvyArmor: " + heavyArmorSkill);
		Label destructionSkillLabel = new Label("Destr: " + destructionSkill);
		Label speechcraftSkillLabel = new Label("Spch: " + speechcraftSkill);
		Label athleticsSkillLabel = new Label("Athl: " + athleticsSkill);
		Label handToHandSkillLabel = new Label("Hand: " + handToHandSkill);
		Label restorationSkillLabel = new Label("Rest: " + restorationSkill);
		Label lightArmorSkillLabel = new Label("LitArmor: " + lightArmorSkill);
		Label marksmanSkillLabel = new Label("Mark: " + marksmanSkill);
		
		Label userName= new Label(playerName);
		Label userClass = new Label(playerClass);
		Label hpL = new Label("Health: 100");
		Label gold = new Label("Gold: 50");
		Label exp = new Label("Experience: 0");
		Label playerLevel = new Label("Level: 1");

		VBox playerInfo = new VBox(10, userName, userClass);
			playerInfo.setAlignment(Pos.CENTER_LEFT);
			playerInfo.setStyle("-fx-text-fill: white");
		VBox playerInfo2 = new VBox(10, hpL, gold, exp, playerLevel);
			playerInfo2.setAlignment(Pos.CENTER_LEFT);
			playerInfo2.setStyle("-fx-text-fill: white");
			
		VBox stats1 = new VBox(20, bladeSkillLabel, mercantileSkillLabel, heavyArmorSkillLabel, destructionSkillLabel,  speechcraftSkillLabel);
			stats1.setAlignment(Pos.CENTER_LEFT);
		VBox stats2 = new VBox(20, athleticsSkillLabel, handToHandSkillLabel, restorationSkillLabel, lightArmorSkillLabel, marksmanSkillLabel);
			stats2.setAlignment(Pos.CENTER_LEFT);
			
		HBox stats = new HBox(20, stats1, stats2);
			stats.setStyle("-fx-text-fill: white");
			
		VBox characterStatScreen = new VBox(20, playerInfo, stats, playerInfo2, continueToGame);
			characterStatScreen.setStyle("-fx-background-image: url('gr/blue_tile.png'); -fx-background-size: contain; -fx-text-fill: white;");
			characterStatScreen.setAlignment(Pos.CENTER);
		
		HBox imageScene = new HBox(character);
			imageScene.setAlignment(Pos.CENTER);
				
		HBox characterFinalScreen = new HBox(100, characterStatScreen, imageScene);
			characterFinalScreen.setStyle("-fx-background-image: url('gr/night.jpg'); -fx-background-repeat: no-repeat; -fx-background-size: contain;");
			characterFinalScreen.setAlignment(Pos.CENTER_LEFT);
			
		Scene s = new Scene(characterFinalScreen, 1000, 600);
			s.getStylesheets().add("the_Savage_CSS.css");
			
		stage1.setScene(s);
		stage1.setTitle("The Savage Labyrinth - Character Creator");
		stage1.show();
	}

	//Method to load a game save
	private Stage load() {
		stage1 = new Stage();
		
		Button go = new Button("Continue to the game");
			go.setOnAction(event -> {
				stage1.close();
				
				stage1 = grid5();
				stage1.show();
			});
		
		VBox fin = new VBox(go);
			fin.setAlignment(Pos.CENTER);
			fin.setStyle("-fx-font-size: 20; -fx-text-fill: white; -fx-background-color: #EBCFA7;");
		GridPane loadPane = new GridPane();
			loadPane.add(fin, 0, 0);
			loadPane.setAlignment(Pos.CENTER);
			loadPane.setStyle("-fx-background-image: url('gr/night.jpg'); -fx-background-repeat: no-repeat; -fx-background-size: contain;");
			
		Scene loadScene = new Scene(loadPane);
		
		stage1.setScene(loadScene);
		stage1.setTitle("The Savage Labyrinth - Load Game");
		stage1.show();
		return stage1;
	}
	
	//Method to save a game state
	private final void save() {
		hp = 100;
		magicka = magicka;
		defense = 3;
		totalGold = 50;
		playerLevel = 1;
		playerXP = 0;
		playerClass = playerClass;
		playerName = playerName;
	
		//Skill stats
		blade = blade;
		mysticism = mysticism;
		mercantile = mercantile;
		heavyArmor = heavyArmor;
		destruction = destruction;
		speechcraft = speechcraft;
		athletics = athletics;
		handToHand = handToHand;
		restoration = restoration;
		lightArmor = lightArmor;
		marksman = marksman;
		
		JOptionPane.showMessageDialog(null, "Game Saved");
	}
	
	//Method for the main game, section 1
	private Stage grid1() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 0, 0);
			map.add(imgWV2, 1, 0);
			map.add(imgWV3, 2, 0);
			map.add(imgWV4, 3, 0);
			map.add(imgWV5, 4, 0);
			map.add(imgWV6, 5, 0);
			map.add(imgWV7, 6, 0);
			map.add(imgWV8, 0, 1);
			map.add(imgWV9, 0, 2);
			map.add(imgWV10, 0, 3);
			map.add(imgWV11, 0, 4);
			map.add(imgWV12, 0, 5);
			map.add(imgWV13, 0, 6);
			map.add(imgWV14, 2, 2);
			map.add(imgWV15, 2, 4);
			map.add(imgWV16, 2, 5);
			map.add(imgWV17, 2, 6);
			map.add(imgWV18, 3, 2);
			map.add(imgWV19, 4, 2);
			map.add(imgWV20, 5, 2);
			map.add(imgWV21, 6, 2);
			map.add(imgWV22, 3, 4);
			map.add(imgWV23, 4, 4);
			map.add(imgWV24, 5, 4);
			map.add(imgWV25, 6, 4);
			map.add(imgFV1, 1, 1);
			map.add(imgFV2, 2, 1);
			map.add(imgFV3, 3, 1);
			map.add(imgFV4, 4, 1);
			map.add(imgFV5, 5, 1);
			map.add(imgFV6, 6, 1);
			map.add(imgFV7, 1, 2);
			map.add(imgFV8, 1, 3);
			map.add(imgFV9, 1, 4);
			map.add(imgFV10, 1, 5);
			map.add(imgFV11, 1, 6);
			map.add(imgFV12, 2, 3);
			map.add(imgFV13, 3, 3);
			map.add(imgFV14, 4, 3);
			map.add(imgFV15, 5, 3);
			map.add(imgFV16, 6, 3);
			map.add(playerMove, 1, 1);
			map.setAlignment(Pos.CENTER);
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setPadding(new Insets(40));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 1;
		locationY = 1;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 1 && locationX != 3 || event.getCode() == KeyCode.W && locationY == 1 && locationX != 1) {
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationY != 1) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 7 && locationX != 3 && locationX != 1 || event.getCode() == KeyCode.S && locationY == 1) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 7 && locationY != 1 || event.getCode() == KeyCode.D && locationX == 1 && locationY != 6 || event.getCode() == KeyCode.D && locationX == 3 && locationY != 7) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 1 && locationY == 7) {
				stage2.close();
				
				stage2 = grid2Top();
				stage2.show();
			}
			if (locationY == 7 && locationX == 3) {
				stage2.close();
				
				stage2 = grid2Bottom();
				stage2.show();
			}
			if (locationY == 1 && locationX == 7) {
				stage2.close();
				
				stage2 = grid3Top();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 1 returning from section 2 top
	private Stage grid1Top() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 0, 0);
			map.add(imgWV2, 1, 0);
			map.add(imgWV3, 2, 0);
			map.add(imgWV4, 3, 0);
			map.add(imgWV5, 4, 0);
			map.add(imgWV6, 5, 0);
			map.add(imgWV7, 6, 0);
			map.add(imgWV8, 0, 1);
			map.add(imgWV9, 0, 2);
			map.add(imgWV10, 0, 3);
			map.add(imgWV11, 0, 4);
			map.add(imgWV12, 0, 5);
			map.add(imgWV13, 0, 6);
			map.add(imgWV14, 2, 2);
			map.add(imgWV15, 2, 4);
			map.add(imgWV16, 2, 5);
			map.add(imgWV17, 2, 6);
			map.add(imgWV18, 3, 2);
			map.add(imgWV19, 4, 2);
			map.add(imgWV20, 5, 2);
			map.add(imgWV21, 6, 2);
			map.add(imgWV22, 3, 4);
			map.add(imgWV23, 4, 4);
			map.add(imgWV24, 5, 4);
			map.add(imgWV25, 6, 4);
			map.add(imgFV1, 1, 1);
			map.add(imgFV2, 2, 1);
			map.add(imgFV3, 3, 1);
			map.add(imgFV4, 4, 1);
			map.add(imgFV5, 5, 1);
			map.add(imgFV6, 6, 1);
			map.add(imgFV7, 1, 2);
			map.add(imgFV8, 1, 3);
			map.add(imgFV9, 1, 4);
			map.add(imgFV10, 1, 5);
			map.add(imgFV11, 1, 6);
			map.add(imgFV12, 2, 3);
			map.add(imgFV13, 3, 3);
			map.add(imgFV14, 4, 3);
			map.add(imgFV15, 5, 3);
			map.add(imgFV16, 6, 3);
			map.add(playerMove, 6, 1);
			map.setAlignment(Pos.CENTER);
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setPadding(new Insets(40));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 1;
		locationY = 6;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 1 && locationX != 3 || event.getCode() == KeyCode.W && locationY == 1 && locationX != 1) {
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationY != 1) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 7 && locationX != 3 && locationX != 1 || event.getCode() == KeyCode.S && locationY == 1) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 7 && locationY != 1 || event.getCode() == KeyCode.D && locationX == 1 && locationY != 6 || event.getCode() == KeyCode.D && locationX == 3 && locationY != 7) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 1 && locationY == 7) {
				stage2.close();
				
				stage2 = grid2Top();
				stage2.show();
			}
			if (locationY == 7 && locationX == 3) {
				stage2.close();
				
				stage2 = grid2Bottom();
				stage2.show();
			}
			if (locationY == 1 && locationX == 7) {
				stage2.close();
				
				stage2 = grid3Top();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 1 returning from section 2 bottom
	private Stage grid1Middle() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 0, 0);
			map.add(imgWV2, 1, 0);
			map.add(imgWV3, 2, 0);
			map.add(imgWV4, 3, 0);
			map.add(imgWV5, 4, 0);
			map.add(imgWV6, 5, 0);
			map.add(imgWV7, 6, 0);
			map.add(imgWV8, 0, 1);
			map.add(imgWV9, 0, 2);
			map.add(imgWV10, 0, 3);
			map.add(imgWV11, 0, 4);
			map.add(imgWV12, 0, 5);
			map.add(imgWV13, 0, 6);
			map.add(imgWV14, 2, 2);
			map.add(imgWV15, 2, 4);
			map.add(imgWV16, 2, 5);
			map.add(imgWV17, 2, 6);
			map.add(imgWV18, 3, 2);
			map.add(imgWV19, 4, 2);
			map.add(imgWV20, 5, 2);
			map.add(imgWV21, 6, 2);
			map.add(imgWV22, 3, 4);
			map.add(imgWV23, 4, 4);
			map.add(imgWV24, 5, 4);
			map.add(imgWV25, 6, 4);
			map.add(imgFV1, 1, 1);
			map.add(imgFV2, 2, 1);
			map.add(imgFV3, 3, 1);
			map.add(imgFV4, 4, 1);
			map.add(imgFV5, 5, 1);
			map.add(imgFV6, 6, 1);
			map.add(imgFV7, 1, 2);
			map.add(imgFV8, 1, 3);
			map.add(imgFV9, 1, 4);
			map.add(imgFV10, 1, 5);
			map.add(imgFV11, 1, 6);
			map.add(imgFV12, 2, 3);
			map.add(imgFV13, 3, 3);
			map.add(imgFV14, 4, 3);
			map.add(imgFV15, 5, 3);
			map.add(imgFV16, 6, 3);
			map.add(playerMove, 6, 3);
			map.setAlignment(Pos.CENTER);
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setPadding(new Insets(40));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 3;
		locationY = 6;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 1 && locationX != 3 || event.getCode() == KeyCode.W && locationY == 1 && locationX != 1) {
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationY != 1) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 7 && locationX != 3 && locationX != 1 || event.getCode() == KeyCode.S && locationY == 1) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 7 && locationY != 1 || event.getCode() == KeyCode.D && locationX == 1 && locationY != 6 || event.getCode() == KeyCode.D && locationX == 3 && locationY != 7) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 1 && locationY == 7) {
				stage2.close();
				
				stage2 = grid2Top();
				stage2.show();
			}
			if (locationY == 7 && locationX == 3) {
				stage2.close();
				
				stage2 = grid2Bottom();
				stage2.show();
			}
			if (locationY == 1 && locationX == 7) {
				stage2.close();
				
				stage2 = grid3Top();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 1 returning from section 3 top
	private Stage grid1Bottom() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 0, 0);
			map.add(imgWV2, 1, 0);
			map.add(imgWV3, 2, 0);
			map.add(imgWV4, 3, 0);
			map.add(imgWV5, 4, 0);
			map.add(imgWV6, 5, 0);
			map.add(imgWV7, 6, 0);
			map.add(imgWV8, 0, 1);
			map.add(imgWV9, 0, 2);
			map.add(imgWV10, 0, 3);
			map.add(imgWV11, 0, 4);
			map.add(imgWV12, 0, 5);
			map.add(imgWV13, 0, 6);
			map.add(imgWV14, 2, 2);
			map.add(imgWV15, 2, 4);
			map.add(imgWV16, 2, 5);
			map.add(imgWV17, 2, 6);
			map.add(imgWV18, 3, 2);
			map.add(imgWV19, 4, 2);
			map.add(imgWV20, 5, 2);
			map.add(imgWV21, 6, 2);
			map.add(imgWV22, 3, 4);
			map.add(imgWV23, 4, 4);
			map.add(imgWV24, 5, 4);
			map.add(imgWV25, 6, 4);
			map.add(imgFV1, 1, 1);
			map.add(imgFV2, 2, 1);
			map.add(imgFV3, 3, 1);
			map.add(imgFV4, 4, 1);
			map.add(imgFV5, 5, 1);
			map.add(imgFV6, 6, 1);
			map.add(imgFV7, 1, 2);
			map.add(imgFV8, 1, 3);
			map.add(imgFV9, 1, 4);
			map.add(imgFV10, 1, 5);
			map.add(imgFV11, 1, 6);
			map.add(imgFV12, 2, 3);
			map.add(imgFV13, 3, 3);
			map.add(imgFV14, 4, 3);
			map.add(imgFV15, 5, 3);
			map.add(imgFV16, 6, 3);
			map.add(playerMove, 1, 6);
			map.setAlignment(Pos.CENTER);
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setPadding(new Insets(40));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 6;
		locationY = 1;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 1 && locationX != 3 || event.getCode() == KeyCode.W && locationY == 1 && locationX != 1) {
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationY != 1) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 7 && locationX != 3 && locationX != 1 || event.getCode() == KeyCode.S && locationY == 1) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 7 && locationY != 1 || event.getCode() == KeyCode.D && locationX == 1 && locationY != 6 || event.getCode() == KeyCode.D && locationX == 3 && locationY != 7) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 1 && locationY == 7) {
				stage2.close();
				
				stage2 = grid2Top();
				stage2.show();
			}
			if (locationY == 7 && locationX == 3) {
				stage2.close();
				
				stage2 = grid2Bottom();
				stage2.show();
			}
			if (locationY == 1 && locationX == 7) {
				stage2.close();
				
				stage2 = grid3Top();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 2 coming from the section 1 top
	private Stage grid2Top() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 1, 3);
			map.add(imgWV2, 2, 3);
			map.add(imgWV3, 2, 2);
			map.add(imgWV4, 2, 1);
			map.add(imgWV5, 2, 0);
			map.add(imgWV6, 4, 0);
			map.add(imgWV7, 4, 1);
			map.add(imgWV8, 4, 2);
			map.add(imgWV9, 4, 3);
			map.add(imgWV10, 5, 3);
			map.add(imgWV11, 6, 3);
			map.add(imgWV12, 6, 4);
			map.add(imgWV13, 6, 5);
			map.add(imgWV14, 5, 5);
			map.add(imgWV15, 4, 5);
			map.add(imgWV16, 3, 5);
			map.add(imgWV17, 3, 6);
			map.add(imgWV18, 3, 7);
			map.add(imgWV19, 2, 7);
			map.add(imgWV20, 1, 7);
			map.add(imgWV21, 1, 5);
			map.add(imgWV22, 3, 0);
			map.add(imgFV1, 1, 4);
			map.add(imgFV2, 2, 4);
			map.add(imgFV3, 3, 4);
			map.add(imgFV4, 4, 4);
			map.add(imgFV5, 5, 4);
			map.add(imgFV6, 2, 5);
			map.add(imgFV7, 2, 6);
			map.add(imgFV8, 1, 6);
			map.add(imgFV9, 3, 3);
			map.add(imgFV10, 3, 2);
			map.add(imgFV11, 3, 1);
			map.add(playerMove, 1, 4);
			map.setAlignment(Pos.CENTER);
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setPadding(new Insets(40));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 4;
		locationY = 1;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 0 && locationY == 3 || event.getCode() == KeyCode.W && locationX != 4 && locationY != 1){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationX != 3 && locationX != 5) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 4 && locationX != 6 && locationY == 3 || event.getCode() == KeyCode.S && locationY == 2 && locationX != 6) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 6 && locationX == 4 || event.getCode() == KeyCode.D && locationY != 4 && locationX == 4 || event.getCode() == KeyCode.D && locationY == 1) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 4 && locationY == 0) {
				stage2.close();
				
				stage2 = grid1Top();
				stage2.show();
			}
			if (locationY == 0 && locationX == 6 ) {
				stage2.close();
				
				stage2 = grid1Bottom();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 2 coming from the section 1 bottom
	private Stage grid2Bottom() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 1, 3);
			map.add(imgWV2, 2, 3);
			map.add(imgWV3, 2, 2);
			map.add(imgWV4, 2, 1);
			map.add(imgWV5, 2, 0);
			map.add(imgWV6, 4, 0);
			map.add(imgWV7, 4, 1);
			map.add(imgWV8, 4, 2);
			map.add(imgWV9, 4, 3);
			map.add(imgWV10, 5, 3);
			map.add(imgWV11, 6, 3);
			map.add(imgWV12, 6, 4);
			map.add(imgWV13, 6, 5);
			map.add(imgWV14, 5, 5);
			map.add(imgWV15, 4, 5);
			map.add(imgWV16, 3, 5);
			map.add(imgWV17, 3, 6);
			map.add(imgWV18, 3, 7);
			map.add(imgWV19, 2, 7);
			map.add(imgWV20, 1, 7);
			map.add(imgWV21, 1, 5);
			map.add(imgWV22, 3, 0);
			map.add(imgFV1, 1, 4);
			map.add(imgFV2, 2, 4);
			map.add(imgFV3, 3, 4);
			map.add(imgFV4, 4, 4);
			map.add(imgFV5, 5, 4);
			map.add(imgFV6, 2, 5);
			map.add(imgFV7, 2, 6);
			map.add(imgFV8, 1, 6);
			map.add(imgFV9, 3, 3);
			map.add(imgFV10, 3, 2);
			map.add(imgFV11, 3, 1);
			map.add(playerMove, 1, 6);
			map.setAlignment(Pos.CENTER);
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setPadding(new Insets(40));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 6;
		locationY = 1;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 0 && locationY == 3 || event.getCode() == KeyCode.W && locationX != 4 && locationY != 1){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationX != 3 && locationX != 5) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 4 && locationX != 6 && locationY == 3 || event.getCode() == KeyCode.S && locationY == 2 && locationX != 6) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 6 && locationX == 4 || event.getCode() == KeyCode.D && locationY != 4 && locationX == 4 || event.getCode() == KeyCode.D && locationY == 1) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 4 && locationY == 0) {
				stage2.close();
				
				stage2 = grid1Top();
				stage2.show();
			}
			if (locationY == 0 && locationX == 6 ) {
				stage2.close();
				
				stage2 = grid1Middle();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 3 coming from section 1 bottom
	private Stage grid3Top() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		Image floorBlockedImage = new Image("file:gr/yellow_tile_blocked.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorBlockedImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		ImageView imgFV17 = new ImageView(floorImage);
		ImageView imgFV18 = new ImageView(floorImage);
		ImageView imgFV19 = new ImageView(floorImage);
		ImageView imgFV20 = new ImageView(floorImage);
		ImageView imgFV21 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		ImageView imgWV26 = new ImageView(wallImage);
		ImageView imgWV27 = new ImageView(wallImage);
		ImageView imgWV28 = new ImageView(wallImage);
		ImageView imgWV29 = new ImageView(wallImage);
		ImageView imgWV30 = new ImageView(wallImage);
		ImageView imgWV31 = new ImageView(wallImage);
		ImageView imgWV32 = new ImageView(wallImage);
		ImageView imgWV33 = new ImageView(wallImage);
		ImageView imgWV34 = new ImageView(wallImage);
		ImageView imgWV35 = new ImageView(wallImage);
		ImageView imgWV36 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 2, 1);
			map.add(imgWV2, 2, 2);
			map.add(imgWV3, 1, 2);
			map.add(imgWV4, 0, 2);
			map.add(imgWV5, 0, 3);
			map.add(imgWV6, 0, 4);
			map.add(imgWV7, 1, 4);
			map.add(imgWV8, 2, 4);
			map.add(imgWV9, 2, 5);
			map.add(imgWV10, 2, 6);
			map.add(imgWV11, 2, 7);
			map.add(imgWV12, 2, 8);
			map.add(imgWV13, 2, 9);
			map.add(imgWV14, 4, 1);
			map.add(imgWV15, 4, 2);
			map.add(imgWV16, 5, 2);
			map.add(imgWV17, 6, 2);
			map.add(imgWV18, 7, 2);
			map.add(imgWV19, 8, 2);
			map.add(imgWV20, 4, 4);
			map.add(imgWV21, 5, 4);
			map.add(imgWV22, 6, 4);
			map.add(imgWV23, 7, 4);
			map.add(imgWV24, 8, 4);
			map.add(imgWV25, 4, 5);
			map.add(imgWV26, 4, 6);
			map.add(imgWV27, 4, 7);
			map.add(imgWV28, 4, 9);
			map.add(imgWV29, 5, 7);
			map.add(imgWV30, 6, 7);
			map.add(imgWV31, 7, 7);
			map.add(imgWV32, 8, 7);
			map.add(imgWV33, 5, 9);
			map.add(imgWV34, 6, 9);
			map.add(imgWV35, 7, 9);
			map.add(imgWV36, 8, 9);
			map.add(imgFV1, 3, 1);
			map.add(imgFV2, 3, 2);
			map.add(imgFV3, 3, 3);
			map.add(imgFV4, 3, 4);
			map.add(imgFV5, 3, 5);
			map.add(imgFV6, 3, 6);
			map.add(imgFV7, 3, 7);
			map.add(imgFV8, 3, 8);
			map.add(imgFV9, 3, 9);
			map.add(imgFV10, 2, 3);
			map.add(imgFV11, 1, 3);
			map.add(imgFV12, 4, 3);
			map.add(imgFV13, 5, 3);
			map.add(imgFV14, 6, 3);
			map.add(imgFV15, 7, 3);
			map.add(imgFV16, 8, 3);
			map.add(imgFV17, 4, 8);
			map.add(imgFV18, 5, 8);
			map.add(imgFV19, 6, 8);
			map.add(imgFV20, 7, 8);
			map.add(imgFV21, 8, 8);
			map.add(playerMove, 3, 1);
			map.setAlignment(Pos.CENTER);
			map.setPadding(new Insets(20));
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setAlignment(Pos.CENTER);
			menuBar.setPadding(new Insets(10));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 1;
		locationY = 3;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 3 && locationX != 8 || event.getCode() == KeyCode.W && locationY == 3){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationX == 3 && locationY != 1 || event.getCode() == KeyCode.A && locationX == 8 && locationY != 3) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationY == 3 && locationX != 8) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationX == 3 || event.getCode() == KeyCode.D && locationX == 8) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 3 && locationY == 9) {
				stage2.close();
				
				stage2 = grid4Top();
				stage2.show();
			}
			if (locationX == 8 && locationY == 9) {
				stage2.close();
				
				stage2 = grid4Middle();
				stage2.show();
			}
			if (locationX == 0 && locationY == 3) {
				stage2.close();
				
				stage2 = grid1Bottom();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 3 coming from section  4 top
	private Stage grid3Middle() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		Image floorBlockedImage = new Image("file:gr/yellow_tile_blocked.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorBlockedImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		ImageView imgFV17 = new ImageView(floorImage);
		ImageView imgFV18 = new ImageView(floorImage);
		ImageView imgFV19 = new ImageView(floorImage);
		ImageView imgFV20 = new ImageView(floorImage);
		ImageView imgFV21 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		ImageView imgWV26 = new ImageView(wallImage);
		ImageView imgWV27 = new ImageView(wallImage);
		ImageView imgWV28 = new ImageView(wallImage);
		ImageView imgWV29 = new ImageView(wallImage);
		ImageView imgWV30 = new ImageView(wallImage);
		ImageView imgWV31 = new ImageView(wallImage);
		ImageView imgWV32 = new ImageView(wallImage);
		ImageView imgWV33 = new ImageView(wallImage);
		ImageView imgWV34 = new ImageView(wallImage);
		ImageView imgWV35 = new ImageView(wallImage);
		ImageView imgWV36 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 2, 1);
			map.add(imgWV2, 2, 2);
			map.add(imgWV3, 1, 2);
			map.add(imgWV4, 0, 2);
			map.add(imgWV5, 0, 3);
			map.add(imgWV6, 0, 4);
			map.add(imgWV7, 1, 4);
			map.add(imgWV8, 2, 4);
			map.add(imgWV9, 2, 5);
			map.add(imgWV10, 2, 6);
			map.add(imgWV11, 2, 7);
			map.add(imgWV12, 2, 8);
			map.add(imgWV13, 2, 9);
			map.add(imgWV14, 4, 1);
			map.add(imgWV15, 4, 2);
			map.add(imgWV16, 5, 2);
			map.add(imgWV17, 6, 2);
			map.add(imgWV18, 7, 2);
			map.add(imgWV19, 8, 2);
			map.add(imgWV20, 4, 4);
			map.add(imgWV21, 5, 4);
			map.add(imgWV22, 6, 4);
			map.add(imgWV23, 7, 4);
			map.add(imgWV24, 8, 4);
			map.add(imgWV25, 4, 5);
			map.add(imgWV26, 4, 6);
			map.add(imgWV27, 4, 7);
			map.add(imgWV28, 4, 9);
			map.add(imgWV29, 5, 7);
			map.add(imgWV30, 6, 7);
			map.add(imgWV31, 7, 7);
			map.add(imgWV32, 8, 7);
			map.add(imgWV33, 5, 9);
			map.add(imgWV34, 6, 9);
			map.add(imgWV35, 7, 9);
			map.add(imgWV36, 8, 9);
			map.add(imgFV1, 3, 1);
			map.add(imgFV2, 3, 2);
			map.add(imgFV3, 3, 3);
			map.add(imgFV4, 3, 4);
			map.add(imgFV5, 3, 5);
			map.add(imgFV6, 3, 6);
			map.add(imgFV7, 3, 7);
			map.add(imgFV8, 3, 8);
			map.add(imgFV9, 3, 9);
			map.add(imgFV10, 2, 3);
			map.add(imgFV11, 1, 3);
			map.add(imgFV12, 4, 3);
			map.add(imgFV13, 5, 3);
			map.add(imgFV14, 6, 3);
			map.add(imgFV15, 7, 3);
			map.add(imgFV16, 8, 3);
			map.add(imgFV17, 4, 8);
			map.add(imgFV18, 5, 8);
			map.add(imgFV19, 6, 8);
			map.add(imgFV20, 7, 8);
			map.add(imgFV21, 8, 8);
			map.add(playerMove, 9, 3);
			map.setAlignment(Pos.CENTER);
			map.setPadding(new Insets(20));
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setAlignment(Pos.CENTER);
			menuBar.setPadding(new Insets(10));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 3;
		locationY = 9;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 3 && locationX != 8 || event.getCode() == KeyCode.W && locationY == 3){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationX == 3 && locationY != 1 || event.getCode() == KeyCode.A && locationX == 8 && locationY != 3) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationY == 3 && locationX != 8) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationX == 3 || event.getCode() == KeyCode.D && locationX == 8) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 3 && locationY == 9) {
				stage2.close();
				
				stage2 = grid4Top();
				stage2.show();
			}
			if (locationX == 8 && locationY == 9) {
				stage2.close();
				
				stage2 = grid4Middle();
				stage2.show();
			}
			if (locationX == 0 && locationY == 3) {
				stage2.close();
				
				stage2 = grid1Bottom();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 3 coming from section 4 middle
	private Stage grid3Bottom() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		Image floorBlockedImage = new Image("file:gr/yellow_tile_blocked.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorBlockedImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		ImageView imgFV17 = new ImageView(floorImage);
		ImageView imgFV18 = new ImageView(floorImage);
		ImageView imgFV19 = new ImageView(floorImage);
		ImageView imgFV20 = new ImageView(floorImage);
		ImageView imgFV21 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		ImageView imgWV26 = new ImageView(wallImage);
		ImageView imgWV27 = new ImageView(wallImage);
		ImageView imgWV28 = new ImageView(wallImage);
		ImageView imgWV29 = new ImageView(wallImage);
		ImageView imgWV30 = new ImageView(wallImage);
		ImageView imgWV31 = new ImageView(wallImage);
		ImageView imgWV32 = new ImageView(wallImage);
		ImageView imgWV33 = new ImageView(wallImage);
		ImageView imgWV34 = new ImageView(wallImage);
		ImageView imgWV35 = new ImageView(wallImage);
		ImageView imgWV36 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 2, 1);
			map.add(imgWV2, 2, 2);
			map.add(imgWV3, 1, 2);
			map.add(imgWV4, 0, 2);
			map.add(imgWV5, 0, 3);
			map.add(imgWV6, 0, 4);
			map.add(imgWV7, 1, 4);
			map.add(imgWV8, 2, 4);
			map.add(imgWV9, 2, 5);
			map.add(imgWV10, 2, 6);
			map.add(imgWV11, 2, 7);
			map.add(imgWV12, 2, 8);
			map.add(imgWV13, 2, 9);
			map.add(imgWV14, 4, 1);
			map.add(imgWV15, 4, 2);
			map.add(imgWV16, 5, 2);
			map.add(imgWV17, 6, 2);
			map.add(imgWV18, 7, 2);
			map.add(imgWV19, 8, 2);
			map.add(imgWV20, 4, 4);
			map.add(imgWV21, 5, 4);
			map.add(imgWV22, 6, 4);
			map.add(imgWV23, 7, 4);
			map.add(imgWV24, 8, 4);
			map.add(imgWV25, 4, 5);
			map.add(imgWV26, 4, 6);
			map.add(imgWV27, 4, 7);
			map.add(imgWV28, 4, 9);
			map.add(imgWV29, 5, 7);
			map.add(imgWV30, 6, 7);
			map.add(imgWV31, 7, 7);
			map.add(imgWV32, 8, 7);
			map.add(imgWV33, 5, 9);
			map.add(imgWV34, 6, 9);
			map.add(imgWV35, 7, 9);
			map.add(imgWV36, 8, 9);
			map.add(imgFV1, 3, 1);
			map.add(imgFV2, 3, 2);
			map.add(imgFV3, 3, 3);
			map.add(imgFV4, 3, 4);
			map.add(imgFV5, 3, 5);
			map.add(imgFV6, 3, 6);
			map.add(imgFV7, 3, 7);
			map.add(imgFV8, 3, 8);
			map.add(imgFV9, 3, 9);
			map.add(imgFV10, 2, 3);
			map.add(imgFV11, 1, 3);
			map.add(imgFV12, 4, 3);
			map.add(imgFV13, 5, 3);
			map.add(imgFV14, 6, 3);
			map.add(imgFV15, 7, 3);
			map.add(imgFV16, 8, 3);
			map.add(imgFV17, 4, 8);
			map.add(imgFV18, 5, 8);
			map.add(imgFV19, 6, 8);
			map.add(imgFV20, 7, 8);
			map.add(imgFV21, 8, 8);
			map.add(playerMove, 9, 8);
			map.setAlignment(Pos.CENTER);
			map.setPadding(new Insets(20));
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setAlignment(Pos.CENTER);
			menuBar.setPadding(new Insets(10));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 8;
		locationY = 9;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 3 && locationX != 8 || event.getCode() == KeyCode.W && locationY == 3){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationX == 3 && locationY != 1 || event.getCode() == KeyCode.A && locationX == 8 && locationY != 3) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationY == 3 && locationX != 8) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationX == 3 || event.getCode() == KeyCode.D && locationX == 8) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 3 && locationY == 9) {
				stage2.close();
				
				stage2 = grid4Top();
				stage2.show();
			}
			if (locationX == 8 && locationY == 9) {
				stage2.close();
				
				stage2 = grid4Middle();
				stage2.show();
			}
			if (locationX == 0 && locationY == 3) {
				stage2.close();
				
				stage2 = grid1Bottom();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 4 coming from section 3 middle
	private Stage grid4Top() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		Image floorBlockedImage = new Image("file:gr/yellow_tile_blocked.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorBlockedImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		ImageView imgFV17 = new ImageView(floorImage);
		ImageView imgFV18 = new ImageView(floorImage);
		ImageView imgFV19 = new ImageView(floorImage);
		ImageView imgFV20 = new ImageView(floorImage);
		ImageView imgFV21 = new ImageView(floorImage);
		ImageView imgFV22 = new ImageView(floorImage);
		ImageView imgFV23 = new ImageView(floorImage);
		ImageView imgFV24 = new ImageView(floorImage);
		ImageView imgFV25 = new ImageView(floorImage);
		ImageView imgFV26 = new ImageView(floorImage);
		ImageView imgFV27 = new ImageView(floorImage);
		ImageView imgFV28 = new ImageView(floorImage);
		ImageView imgFV29 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		ImageView imgWV26 = new ImageView(wallImage);
		ImageView imgWV27 = new ImageView(wallImage);
		ImageView imgWV28 = new ImageView(wallImage);
		ImageView imgWV29 = new ImageView(wallImage);
		ImageView imgWV30 = new ImageView(wallImage);
		ImageView imgWV31 = new ImageView(wallImage);
		ImageView imgWV32 = new ImageView(wallImage);
		ImageView imgWV33 = new ImageView(wallImage);
		ImageView imgWV34 = new ImageView(wallImage);
		ImageView imgWV35 = new ImageView(wallImage);
		ImageView imgWV36 = new ImageView(wallImage);
		ImageView imgWV37 = new ImageView(wallImage);
		ImageView imgWV38 = new ImageView(wallImage);
		ImageView imgWV39 = new ImageView(wallImage);
		ImageView imgWV40 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 1, 0);
			map.add(imgWV2, 2, 0);
			map.add(imgWV3, 3, 0);
			map.add(imgWV4, 4, 0);
			map.add(imgWV5, 6, 0);
			map.add(imgWV6, 7, 0);
			map.add(imgWV7, 8, 0);
			map.add(imgWV8, 9, 0);
			map.add(imgWV9, 10, 0);
			map.add(imgWV10, 10, 1);
			map.add(imgWV11, 1, 2);
			map.add(imgWV12, 2, 2);
			map.add(imgWV13, 3, 2);
			map.add(imgWV14, 10, 2);
			map.add(imgWV15, 3, 3);
			map.add(imgWV16, 4, 3);
			map.add(imgWV17, 5, 3);
			map.add(imgWV18, 6, 3);
			map.add(imgWV19, 8, 3);
			map.add(imgWV20, 9, 3);
			map.add(imgWV21, 10, 3);
			map.add(imgWV22, 6, 4);
			map.add(imgWV23, 8, 4);
			map.add(imgWV24, 1, 5);
			map.add(imgWV25, 2, 5);
			map.add(imgWV26, 3, 5);
			map.add(imgWV27, 4, 5);
			map.add(imgWV28, 5, 5);
			map.add(imgWV29, 6, 5);
			map.add(imgWV30, 8, 5);
			map.add(imgWV31, 8, 6);
			map.add(imgWV32, 1, 7);
			map.add(imgWV33, 2, 7);
			map.add(imgWV34, 3, 7);
			map.add(imgWV35, 4, 7);
			map.add(imgWV36, 5, 7);
			map.add(imgWV37, 6, 7);
			map.add(imgWV38, 8, 7);
			map.add(imgWV39, 6, 8);
			map.add(imgWV40, 8, 8);
			map.add(imgFV1, 1, 1);
			map.add(imgFV2, 2, 1);
			map.add(imgFV3, 3, 1);
			map.add(imgFV4, 4, 1);
			map.add(imgFV5, 5, 1);
			map.add(imgFV6, 6, 1);
			map.add(imgFV7, 7, 1);
			map.add(imgFV8, 8, 1);
			map.add(imgFV9, 9, 1);
			map.add(imgFV10, 5, 0);
			map.add(imgFV11, 4, 2);
			map.add(imgFV12, 5, 2);
			map.add(imgFV13, 6, 2);
			map.add(imgFV14, 7, 2);
			map.add(imgFV15, 8, 2);
			map.add(imgFV16, 9, 2);
			map.add(imgFV17, 7, 3);
			map.add(imgFV18, 7, 4);
			map.add(imgFV19, 7, 5);
			map.add(imgFV20, 1, 6);
			map.add(imgFV21, 2, 6);
			map.add(imgFV22, 3, 6);
			map.add(imgFV23, 4, 6);
			map.add(imgFV24, 5, 6);
			map.add(imgFV25, 6, 6);
			map.add(imgFV26, 7, 6);
			map.add(imgFV27, 7, 7);
			map.add(imgFV28, 7, 8);
			map.add(playerMove, 1, 1);
			map.setAlignment(Pos.CENTER);
			map.setPadding(new Insets(20));
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setAlignment(Pos.CENTER);
			menuBar.setPadding(new Insets(10));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 1;
		locationY = 1;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 1 && locationX != 6 || event.getCode() == KeyCode.W && locationY == 7 && locationX != 1){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationY != 4 && locationY != 7 || event.getCode() == KeyCode.A && locationX == 6 || event.getCode() == KeyCode.A && locationX == 1 || event.getCode() == KeyCode.A && locationX == 2 && locationY != 4) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 2 && locationY != 1 && locationY != 2 && locationY != 3 && locationY != 1 && locationX != 6 || event.getCode() == KeyCode.S && locationY == 7) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 7 && locationY != 9 || event.getCode() == KeyCode.D &&  locationX == 2 && locationY != 9 || event.getCode() == KeyCode.D &&  locationX == 1 && locationY != 9) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 1 && locationY == 0) {
				stage2.close();
				
				stage2 = grid3Middle();
				stage2.show();
			}
			if (locationX == 6 && locationY == 0) {
				stage2.close();
				
				stage2 = grid3Bottom();
				stage2.show();
			}
			if (locationX == 9 && locationY == 7) {
				stage2.close();
				
				stage2 = grid5();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 4 coming from section 3 bottom
	private Stage grid4Middle() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		Image floorBlockedImage = new Image("file:gr/yellow_tile_blocked.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorBlockedImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		ImageView imgFV17 = new ImageView(floorImage);
		ImageView imgFV18 = new ImageView(floorImage);
		ImageView imgFV19 = new ImageView(floorImage);
		ImageView imgFV20 = new ImageView(floorImage);
		ImageView imgFV21 = new ImageView(floorImage);
		ImageView imgFV22 = new ImageView(floorImage);
		ImageView imgFV23 = new ImageView(floorImage);
		ImageView imgFV24 = new ImageView(floorImage);
		ImageView imgFV25 = new ImageView(floorImage);
		ImageView imgFV26 = new ImageView(floorImage);
		ImageView imgFV27 = new ImageView(floorImage);
		ImageView imgFV28 = new ImageView(floorImage);
		ImageView imgFV29 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		ImageView imgWV26 = new ImageView(wallImage);
		ImageView imgWV27 = new ImageView(wallImage);
		ImageView imgWV28 = new ImageView(wallImage);
		ImageView imgWV29 = new ImageView(wallImage);
		ImageView imgWV30 = new ImageView(wallImage);
		ImageView imgWV31 = new ImageView(wallImage);
		ImageView imgWV32 = new ImageView(wallImage);
		ImageView imgWV33 = new ImageView(wallImage);
		ImageView imgWV34 = new ImageView(wallImage);
		ImageView imgWV35 = new ImageView(wallImage);
		ImageView imgWV36 = new ImageView(wallImage);
		ImageView imgWV37 = new ImageView(wallImage);
		ImageView imgWV38 = new ImageView(wallImage);
		ImageView imgWV39 = new ImageView(wallImage);
		ImageView imgWV40 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 1, 0);
			map.add(imgWV2, 2, 0);
			map.add(imgWV3, 3, 0);
			map.add(imgWV4, 4, 0);
			map.add(imgWV5, 6, 0);
			map.add(imgWV6, 7, 0);
			map.add(imgWV7, 8, 0);
			map.add(imgWV8, 9, 0);
			map.add(imgWV9, 10, 0);
			map.add(imgWV10, 10, 1);
			map.add(imgWV11, 1, 2);
			map.add(imgWV12, 2, 2);
			map.add(imgWV13, 3, 2);
			map.add(imgWV14, 10, 2);
			map.add(imgWV15, 3, 3);
			map.add(imgWV16, 4, 3);
			map.add(imgWV17, 5, 3);
			map.add(imgWV18, 6, 3);
			map.add(imgWV19, 8, 3);
			map.add(imgWV20, 9, 3);
			map.add(imgWV21, 10, 3);
			map.add(imgWV22, 6, 4);
			map.add(imgWV23, 8, 4);
			map.add(imgWV24, 1, 5);
			map.add(imgWV25, 2, 5);
			map.add(imgWV26, 3, 5);
			map.add(imgWV27, 4, 5);
			map.add(imgWV28, 5, 5);
			map.add(imgWV29, 6, 5);
			map.add(imgWV30, 8, 5);
			map.add(imgWV31, 8, 6);
			map.add(imgWV32, 1, 7);
			map.add(imgWV33, 2, 7);
			map.add(imgWV34, 3, 7);
			map.add(imgWV35, 4, 7);
			map.add(imgWV36, 5, 7);
			map.add(imgWV37, 6, 7);
			map.add(imgWV38, 8, 7);
			map.add(imgWV39, 6, 8);
			map.add(imgWV40, 8, 8);
			map.add(imgFV1, 1, 1);
			map.add(imgFV2, 2, 1);
			map.add(imgFV3, 3, 1);
			map.add(imgFV4, 4, 1);
			map.add(imgFV5, 5, 1);
			map.add(imgFV6, 6, 1);
			map.add(imgFV7, 7, 1);
			map.add(imgFV8, 8, 1);
			map.add(imgFV9, 9, 1);
			map.add(imgFV10, 5, 0);
			map.add(imgFV11, 4, 2);
			map.add(imgFV12, 5, 2);
			map.add(imgFV13, 6, 2);
			map.add(imgFV14, 7, 2);
			map.add(imgFV15, 8, 2);
			map.add(imgFV16, 9, 2);
			map.add(imgFV17, 7, 3);
			map.add(imgFV18, 7, 4);
			map.add(imgFV19, 7, 5);
			map.add(imgFV20, 1, 6);
			map.add(imgFV21, 2, 6);
			map.add(imgFV22, 3, 6);
			map.add(imgFV23, 4, 6);
			map.add(imgFV24, 5, 6);
			map.add(imgFV25, 6, 6);
			map.add(imgFV26, 7, 6);
			map.add(imgFV27, 7, 7);
			map.add(imgFV28, 7, 8);
			map.add(playerMove, 1, 6);
			map.setAlignment(Pos.CENTER);
			map.setPadding(new Insets(20));
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setAlignment(Pos.CENTER);
			menuBar.setPadding(new Insets(10));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 6;
		locationY = 1;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 1 && locationX != 6 || event.getCode() == KeyCode.W && locationY == 7 && locationX != 1){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationY != 4 && locationY != 7 || event.getCode() == KeyCode.A && locationX == 6 || event.getCode() == KeyCode.A && locationX == 1 || event.getCode() == KeyCode.A && locationX == 2 && locationY != 4) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 2 && locationY != 1 && locationY != 2 && locationY != 3 && locationY != 1 && locationX != 6 || event.getCode() == KeyCode.S && locationY == 7) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 7 && locationY != 9 || event.getCode() == KeyCode.D &&  locationX == 2 && locationY != 9 || event.getCode() == KeyCode.D &&  locationX == 1 && locationY != 9) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 1 && locationY == 0) {
				stage2.close();
				
				stage2 = grid3Middle();
				stage2.show();
			}
			if (locationX == 6 && locationY == 0) {
				stage2.close();
				
				stage2 = grid3Bottom();
				stage2.show();
			}
			if (locationX == 9 && locationY == 7) {
				stage2.close();
				
				stage2 = grid5();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 4 coming from section 5
	private Stage grid4Bottom() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");
		Image floorBlockedImage = new Image("file:gr/yellow_tile_blocked.png");
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorBlockedImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		ImageView imgFV17 = new ImageView(floorImage);
		ImageView imgFV18 = new ImageView(floorImage);
		ImageView imgFV19 = new ImageView(floorImage);
		ImageView imgFV20 = new ImageView(floorImage);
		ImageView imgFV21 = new ImageView(floorImage);
		ImageView imgFV22 = new ImageView(floorImage);
		ImageView imgFV23 = new ImageView(floorImage);
		ImageView imgFV24 = new ImageView(floorImage);
		ImageView imgFV25 = new ImageView(floorImage);
		ImageView imgFV26 = new ImageView(floorImage);
		ImageView imgFV27 = new ImageView(floorImage);
		ImageView imgFV28 = new ImageView(floorImage);
		ImageView imgFV29 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		ImageView imgWV26 = new ImageView(wallImage);
		ImageView imgWV27 = new ImageView(wallImage);
		ImageView imgWV28 = new ImageView(wallImage);
		ImageView imgWV29 = new ImageView(wallImage);
		ImageView imgWV30 = new ImageView(wallImage);
		ImageView imgWV31 = new ImageView(wallImage);
		ImageView imgWV32 = new ImageView(wallImage);
		ImageView imgWV33 = new ImageView(wallImage);
		ImageView imgWV34 = new ImageView(wallImage);
		ImageView imgWV35 = new ImageView(wallImage);
		ImageView imgWV36 = new ImageView(wallImage);
		ImageView imgWV37 = new ImageView(wallImage);
		ImageView imgWV38 = new ImageView(wallImage);
		ImageView imgWV39 = new ImageView(wallImage);
		ImageView imgWV40 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 1, 0);
			map.add(imgWV2, 2, 0);
			map.add(imgWV3, 3, 0);
			map.add(imgWV4, 4, 0);
			map.add(imgWV5, 6, 0);
			map.add(imgWV6, 7, 0);
			map.add(imgWV7, 8, 0);
			map.add(imgWV8, 9, 0);
			map.add(imgWV9, 10, 0);
			map.add(imgWV10, 10, 1);
			map.add(imgWV11, 1, 2);
			map.add(imgWV12, 2, 2);
			map.add(imgWV13, 3, 2);
			map.add(imgWV14, 10, 2);
			map.add(imgWV15, 3, 3);
			map.add(imgWV16, 4, 3);
			map.add(imgWV17, 5, 3);
			map.add(imgWV18, 6, 3);
			map.add(imgWV19, 8, 3);
			map.add(imgWV20, 9, 3);
			map.add(imgWV21, 10, 3);
			map.add(imgWV22, 6, 4);
			map.add(imgWV23, 8, 4);
			map.add(imgWV24, 1, 5);
			map.add(imgWV25, 2, 5);
			map.add(imgWV26, 3, 5);
			map.add(imgWV27, 4, 5);
			map.add(imgWV28, 5, 5);
			map.add(imgWV29, 6, 5);
			map.add(imgWV30, 8, 5);
			map.add(imgWV31, 8, 6);
			map.add(imgWV32, 1, 7);
			map.add(imgWV33, 2, 7);
			map.add(imgWV34, 3, 7);
			map.add(imgWV35, 4, 7);
			map.add(imgWV36, 5, 7);
			map.add(imgWV37, 6, 7);
			map.add(imgWV38, 8, 7);
			map.add(imgWV39, 6, 8);
			map.add(imgWV40, 8, 8);
			map.add(imgFV1, 1, 1);
			map.add(imgFV2, 2, 1);
			map.add(imgFV3, 3, 1);
			map.add(imgFV4, 4, 1);
			map.add(imgFV5, 5, 1);
			map.add(imgFV6, 6, 1);
			map.add(imgFV7, 7, 1);
			map.add(imgFV8, 8, 1);
			map.add(imgFV9, 9, 1);
			map.add(imgFV10, 5, 0);
			map.add(imgFV11, 4, 2);
			map.add(imgFV12, 5, 2);
			map.add(imgFV13, 6, 2);
			map.add(imgFV14, 7, 2);
			map.add(imgFV15, 8, 2);
			map.add(imgFV16, 9, 2);
			map.add(imgFV17, 7, 3);
			map.add(imgFV18, 7, 4);
			map.add(imgFV19, 7, 5);
			map.add(imgFV20, 1, 6);
			map.add(imgFV21, 2, 6);
			map.add(imgFV22, 3, 6);
			map.add(imgFV23, 4, 6);
			map.add(imgFV24, 5, 6);
			map.add(imgFV25, 6, 6);
			map.add(imgFV26, 7, 6);
			map.add(imgFV27, 7, 7);
			map.add(imgFV28, 7, 8);
			map.add(playerMove, 7, 8);
			map.setAlignment(Pos.CENTER);
			map.setPadding(new Insets(20));
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setAlignment(Pos.CENTER);
			menuBar.setPadding(new Insets(10));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 8;
		locationY = 7;
		
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.W && locationX != 1 && locationX != 6 || event.getCode() == KeyCode.W && locationY == 7 && locationX != 1){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationY != 4 && locationY != 7 || event.getCode() == KeyCode.A && locationX == 6 || event.getCode() == KeyCode.A && locationX == 1 || event.getCode() == KeyCode.A && locationX == 2 && locationY != 4) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 2 && locationY != 1 && locationY != 2 && locationY != 3 && locationY != 1 && locationX != 6 || event.getCode() == KeyCode.S && locationY == 7) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 7 && locationY != 9 || event.getCode() == KeyCode.D &&  locationX == 2 && locationY != 9 || event.getCode() == KeyCode.D &&  locationX == 1 && locationY != 9) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 1 && locationY == 0) {
				stage2.close();
				
				stage2 = grid3Middle();
				stage2.show();
			}
			if (locationX == 6 && locationY == 0) {
				stage2.close();
				
				stage2 = grid3Bottom();
				stage2.show();
			}
			if (locationX == 9 && locationY == 7) {
				stage2.close();
				
				stage2 = grid5();
				stage2.show();
			}
			if (combatRNG < 6) {
				combat();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	//Method for the main game, section 5
	private Stage grid5() {
		stage2 = new Stage();
		
		playerClass = playerClassChoosen();
		playerName = playerNameChoosen();
		
		Image fighterImage = new Image("file:gr/fighterPlayerGame.png");
		Image rogueImage = new Image("file:gr/roguePlayerGame.png");
		Image mageImage = new Image("file:gr/magePlayerGame.png");
		ImageView playerView = new ImageView();
		if (playerClass.equals("Fighter")) {
			playerView.setImage(fighterImage);
		}
		else if (playerClass.equals("Rogue")) {
			playerView.setImage(rogueImage);
		}
		else {
			playerView.setImage(mageImage);
		}
		Pane playerMove = new Pane(playerView);
		
		//Images for the floor and walls
		Image wallImage = new Image("file:gr/green_tile.png");
		Image floorImage = new Image("file:gr/yellow_tile.png");

		//Images and Views for houses
		Image beggarImg = new Image("file:gr/beggarNPC.png");
		Image dogImg = new Image("file:gr/dogNPC.png");
		Image oldManImg = new Image("file:gr/oldManNPC.png");
		Image peasantImg = new Image("file:gr/peasentNPC.png");
		
		ImageView beggarView = new ImageView(beggarImg);
		ImageView dogView = new ImageView(dogImg);
		ImageView oldManView = new ImageView(oldManImg);
		ImageView peasantView = new ImageView(peasantImg);
		
		//Imageviews for the floors
		ImageView imgFV1 = new ImageView(floorImage);
		ImageView imgFV2 = new ImageView(floorImage);
		ImageView imgFV3 = new ImageView(floorImage);
		ImageView imgFV4 = new ImageView(floorImage);
		ImageView imgFV5 = new ImageView(floorImage);
		ImageView imgFV6 = new ImageView(floorImage);
		ImageView imgFV7 = new ImageView(floorImage);
		ImageView imgFV8 = new ImageView(floorImage);
		ImageView imgFV9 = new ImageView(floorImage);
		ImageView imgFV10 = new ImageView(floorImage);
		ImageView imgFV11 = new ImageView(floorImage);
		ImageView imgFV12 = new ImageView(floorImage);
		ImageView imgFV13 = new ImageView(floorImage);
		ImageView imgFV14 = new ImageView(floorImage);
		ImageView imgFV15 = new ImageView(floorImage);
		ImageView imgFV16 = new ImageView(floorImage);
		ImageView imgFV17 = new ImageView(floorImage);
		ImageView imgFV18 = new ImageView(floorImage);
		ImageView imgFV19 = new ImageView(floorImage);
		ImageView imgFV20 = new ImageView(floorImage);
		ImageView imgFV21 = new ImageView(floorImage);
		ImageView imgFV22 = new ImageView(floorImage);
		ImageView imgFV23 = new ImageView(floorImage);
		ImageView imgFV24 = new ImageView(floorImage);
		ImageView imgFV25 = new ImageView(floorImage);
		ImageView imgFV26 = new ImageView(floorImage);
		ImageView imgFV27 = new ImageView(floorImage);
		ImageView imgFV28 = new ImageView(floorImage);
		ImageView imgFV29 = new ImageView(floorImage);
		ImageView imgFV30 = new ImageView(floorImage);
		ImageView imgFV31 = new ImageView(floorImage);
		ImageView imgFV32 = new ImageView(floorImage);
		ImageView imgFV33 = new ImageView(floorImage);
		ImageView imgFV34 = new ImageView(floorImage);
		ImageView imgFV35 = new ImageView(floorImage);
		ImageView imgFV36 = new ImageView(floorImage);
		ImageView imgFV37 = new ImageView(floorImage);
		ImageView imgFV38 = new ImageView(floorImage);
		ImageView imgFV39 = new ImageView(floorImage);
		ImageView imgFV40 = new ImageView(floorImage);
		ImageView imgFV41 = new ImageView(floorImage);
		ImageView imgFV42 = new ImageView(floorImage);
		ImageView imgFV43 = new ImageView(floorImage);
		ImageView imgFV44 = new ImageView(floorImage);
		ImageView imgFV45 = new ImageView(floorImage);
		ImageView imgFV46 = new ImageView(floorImage);
		ImageView imgFV47 = new ImageView(floorImage);
		ImageView imgFV48 = new ImageView(floorImage);
		ImageView imgFV49 = new ImageView(floorImage);
		ImageView imgFV50 = new ImageView(floorImage);
		ImageView imgFV51 = new ImageView(floorImage);
		ImageView imgFV52 = new ImageView(floorImage);
		ImageView imgFV53 = new ImageView(floorImage);
		ImageView imgFV54 = new ImageView(floorImage);
		ImageView imgFV55 = new ImageView(floorImage);
		
		//Imageviews for the walls
		ImageView imgWV1 = new ImageView(wallImage);
		ImageView imgWV2 = new ImageView(wallImage);
		ImageView imgWV3 = new ImageView(wallImage);
		ImageView imgWV4 = new ImageView(wallImage);
		ImageView imgWV5 = new ImageView(wallImage);
		ImageView imgWV6 = new ImageView(wallImage);
		ImageView imgWV7 = new ImageView(wallImage);
		ImageView imgWV8 = new ImageView(wallImage);
		ImageView imgWV9 = new ImageView(wallImage);
		ImageView imgWV10 = new ImageView(wallImage);
		ImageView imgWV11 = new ImageView(wallImage);
		ImageView imgWV12 = new ImageView(wallImage);
		ImageView imgWV13 = new ImageView(wallImage);
		ImageView imgWV14 = new ImageView(wallImage);
		ImageView imgWV15 = new ImageView(wallImage);
		ImageView imgWV16 = new ImageView(wallImage);
		ImageView imgWV17 = new ImageView(wallImage);
		ImageView imgWV18 = new ImageView(wallImage);
		ImageView imgWV19 = new ImageView(wallImage);
		ImageView imgWV20 = new ImageView(wallImage);
		ImageView imgWV21 = new ImageView(wallImage);
		ImageView imgWV22 = new ImageView(wallImage);
		ImageView imgWV23 = new ImageView(wallImage);
		ImageView imgWV24 = new ImageView(wallImage);
		ImageView imgWV25 = new ImageView(wallImage);
		ImageView imgWV26 = new ImageView(wallImage);
		ImageView imgWV27 = new ImageView(wallImage);
		ImageView imgWV28 = new ImageView(wallImage);
		ImageView imgWV29 = new ImageView(wallImage);
		ImageView imgWV30 = new ImageView(wallImage);
		ImageView imgWV31 = new ImageView(wallImage);
		ImageView imgWV32 = new ImageView(wallImage);
		ImageView imgWV33 = new ImageView(wallImage);
		ImageView imgWV34 = new ImageView(wallImage);
		
		//First number is column, the second is the row. The map that the player will traverse
		GridPane map = new GridPane();
			map.add(imgWV1, 0, 1);
			map.add(imgWV2, 1, 1);
			map.add(imgWV3, 2, 1);
			map.add(imgWV4, 3, 1);
			map.add(imgWV5, 4, 1);
			map.add(imgWV6, 5, 1);
			map.add(imgWV7, 6, 1);
			map.add(imgWV8, 7, 1);
			map.add(imgWV9, 9, 1);
			map.add(imgWV10, 10, 1);
			map.add(imgWV11, 0, 2);
			map.add(imgWV12, 0, 3);
			map.add(imgWV13, 0, 4);
			map.add(imgWV14, 0, 5);
			map.add(imgWV15, 0, 6);
			map.add(imgWV16, 0, 7);
			map.add(imgWV17, 0, 8);
			map.add(imgWV18, 1, 8);
			map.add(imgWV19, 2, 8);
			map.add(imgWV20, 3, 8);
			map.add(imgWV21, 4, 8);
			map.add(imgWV22, 5, 8);
			map.add(imgWV23, 6, 8);
			map.add(imgWV24, 7, 8);
			map.add(imgWV25, 9, 8);
			map.add(imgWV26, 10, 1);
			map.add(imgWV27, 10, 2);
			map.add(imgWV28, 10, 3);
			map.add(imgWV29, 10, 4);
			map.add(imgWV30, 10, 5);
			map.add(imgWV31, 10, 6);
			map.add(imgWV32, 10, 7);
			map.add(imgWV33, 10, 8);
			map.add(imgWV34, 8, 8);
			map.add(imgFV1, 1, 2);
			map.add(imgFV2, 2, 2);
			map.add(imgFV3, 3, 2);
			map.add(imgFV4, 4, 2);
			map.add(imgFV5, 5, 2);
			map.add(imgFV6, 6, 2);
			map.add(imgFV7, 7, 2);
			map.add(imgFV8, 8, 2);
			map.add(imgFV9, 9, 2);
			map.add(imgFV10, 8, 1);
			map.add(imgFV11, 1, 3);
			map.add(imgFV12, 2, 3);
			map.add(imgFV13, 3, 3);
			map.add(imgFV14, 4, 3);
			map.add(imgFV15, 5, 3);
			map.add(imgFV16, 6, 3);
			map.add(imgFV17, 7, 3);
			map.add(imgFV18, 8, 3);
			map.add(imgFV19, 9, 3);
			map.add(imgFV20, 1, 4);
			map.add(imgFV21, 2, 4);
			map.add(imgFV22, 3, 4);
			map.add(imgFV23, 4, 4);
			map.add(imgFV24, 5, 4);
			map.add(imgFV25, 6, 4);
			map.add(imgFV26, 7, 4);
			map.add(imgFV27, 8, 4);
			map.add(imgFV28, 9, 4);
			map.add(imgFV29, 1, 5);
			map.add(imgFV30, 2, 5);
			map.add(imgFV31, 3, 5);
			map.add(imgFV32, 4, 5);
			map.add(imgFV33, 5, 5);
			map.add(imgFV34, 6, 5);
			map.add(imgFV35, 7, 5);
			map.add(imgFV36, 8, 5);
			map.add(imgFV37, 9, 5);
			map.add(imgFV38, 1, 6);
			map.add(imgFV39, 2, 6);
			map.add(imgFV40, 3, 6);
			map.add(imgFV41, 4, 6);
			map.add(imgFV42, 5, 6);
			map.add(imgFV43, 6, 6);
			map.add(imgFV44, 7, 6);
			map.add(imgFV45, 8, 6);
			map.add(imgFV46, 9, 6);
			map.add(imgFV47, 1, 7);
			map.add(imgFV48, 2, 7);
			map.add(imgFV49, 3, 7);
			map.add(imgFV50, 4, 7);
			map.add(imgFV51, 5, 7);
			map.add(imgFV52, 6, 7);
			map.add(imgFV53, 7, 7);
			map.add(imgFV54, 8, 7);
			map.add(imgFV55, 9, 7);
			map.add(beggarView, 1, 4);
			map.add(dogView, 9, 3);
			map.add(oldManView, 6, 6);
			map.add(peasantView, 5, 2);
			map.add(playerMove, 8, 1);
			map.setAlignment(Pos.CENTER);
			map.setPadding(new Insets(20));
			
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setAlignment(Pos.CENTER);
			menuBar.setPadding(new Insets(10));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		VBox gameV = new VBox(map, menuBar);
			gameV.setAlignment(Pos.CENTER);
			gameV.setStyle("-fx-background-color: black;");
		
		Scene gameS = new Scene(gameV);
		
		locationX = 1;
		locationY = 8;
	
		//First number is column, the second is the row. The map that the player will traverse
		gameS.setOnKeyPressed(event -> {
			int combatRNG = rng(100) + 1;
			
			if (event.getCode() == KeyCode.Q && locationX == 5 && locationY == 1 || event.getCode() == KeyCode.Q && locationX == 4 && locationY == 2) {
					int talk = 1;
					switch (talk) {
							case 1:
								JOptionPane.showMessageDialog(null, "Hey, a new person. Haven't seen many of you around.");
								talk++;
							break;
							case 2:
								JOptionPane.showMessageDialog(null, "Why are you talking to me? You're probably worse than that dog.");
								talk++;
							break;
							case 3:
								JOptionPane.showMessageDialog(null, "Leave me alone!");
								talk++;
							break;
							case 4:
								JOptionPane.showMessageDialog(null, "........'It seems like he doesn't want to talk to you.'");
							break;
							default:
					}
				}
				
				if (event.getCode() == KeyCode.Q && locationX == 3 && locationY == 8 || event.getCode() == KeyCode.Q && locationX == 3 && locationY == 9) {
					int talk = 1;
					switch (talk) {
							case 1:
								JOptionPane.showMessageDialog(null, "*WOOF*");
								talk++;
							break;
							case 2:
								JOptionPane.showMessageDialog(null, "*Rarf*");
								talk++;
							break;
							case 3:
								JOptionPane.showMessageDialog(null, "Unless you have any treats, leave me alone.");
							break;
							default:
					}
				}
				
				if (event.getCode() == KeyCode.Q && locationX == 5 && locationY == 6 || event.getCode() == KeyCode.Q && locationX == 4 && locationY == 2) {
						int talk = 1;
						switch (talk) {
								case 1:
									JOptionPane.showMessageDialog(null, "Oh, a newcomer! Don't see many of you make it this far.");
									talk++;
								break;
								case 2:
									JOptionPane.showMessageDialog(null, "You know I've been here a long time. I put in here during the King's father's reign.");
									talk++;
								break;
								case 3:
									JOptionPane.showMessageDialog(null, "If you really want to survive out there you need to be tough. If I was 20 years younger, I could give you a run for your money.");
								break;
								default:
						}
					}
					
				if (event.getCode() == KeyCode.Q && locationX == 2 && locationY == 6 || event.getCode() == KeyCode.Q && locationX == 3 && locationY == 5) {
					int talk = 1;
					switch (talk) {
							case 1:
								JOptionPane.showMessageDialog(null, "It's downright terrible to make anything grow down here. All I get our weeds.");
								talk++;
							break;
							case 2:
								JOptionPane.showMessageDialog(null, "I stand around and talk, but I have work to do.");
								talk++;
							break;
							case 3:
								JOptionPane.showMessageDialog(null, "Please leave me to my crops.");
							break;
							default:
					}
				}
			
			if (event.getCode() == KeyCode.W && locationX != 1 && locationY == 8){
				map.getChildren().remove(playerMove);
				locationX = locationX-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.A && locationY != 1) {
				map.getChildren().remove(playerMove);
				locationY = locationY-1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.S && locationX != 7) {
				map.getChildren().remove(playerMove);
				locationX = locationX+1;
				map.add(playerMove, locationY, locationX);
			}
			if (event.getCode() == KeyCode.D && locationY != 9) {
				map.getChildren().remove(playerMove);
				locationY = locationY+1;
				map.add(playerMove, locationY, locationX);
			}
			if (locationX == 0 && locationY == 8) {
				stage2.close();
				stage2 = grid4Bottom();
				stage2.show();
			}
		});
		
		stage2.setScene(gameS);
		stage2.setTitle("The Savage Labyrinth - The Labyrinth");
		return stage2;
	}
	
	public final void menu() {
		stage3 = new Stage();
		
		Button newGame = new Button("New Game");
			newGame.setStyle("-fx-background-color: orange;");
			newGame.setOnAction(en -> {
				stage3.close();
				stage2.close();
				characterCreator1();
			});
		Button loadGame = new Button("Load Game");
			loadGame.setStyle("-fx-background-color: orange;");
			loadGame.setOnAction(el -> {
				load();
			});
		Button settings = new Button("Settings");
			settings.setStyle("-fx-background-color: orange;");
			settings.setOnAction(es -> {
				JOptionPane.showMessageDialog(null, "You think I would code settings into this game? For what?");
				stage3.close();
			});
		Button exitGame = new Button("Exit Game");
			exitGame.setStyle("-fx-background-color: orange;");
			exitGame.setOnAction(eg -> {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit without saving?");
				
				if (dialogResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				else {
					JOptionPane.showMessageDialog(null, "Close this window!");
				}
			});
		Button backToGame = new Button("Back");
			backToGame.setStyle("-fx-background-color: orange;");
			backToGame.setOnAction(bg -> {
				stage3.close();
			});
		
		VBox menuBox = new VBox(30, newGame, loadGame, settings, exitGame, backToGame);
			menuBox.setAlignment(Pos.CENTER);
			menuBox.setStyle("-fx-background-color: black;");
		
		Scene menuScene = new Scene(menuBox);
		
		stage3.setScene(menuScene);
		stage3.setTitle("The Savage Labyrinth - Menu");
		stage3.show();
	}
	
	//Enemy Stats
	private String enemyName;
	private double enemyHP;
	private double enemyMagicka;
	private double enemyAttack;
	private double enemyDefense;
	private double enemyXP;
	private double enemyGold;
	private double weapon;
	
	public final void combat() {
		stage3 = new Stage();
		
		playerLevel = 1;
		hp = 100;
		playerXP = 0;
		totalGold = 0;
		
		TextArea combatMessages = new TextArea();
			combatMessages.setWrapText(true);
			combatMessages.setEditable(false);
			combatMessages.setStyle("-fx-max-height: 50px; text-area-background: black; -fx-text-fill: orange;");
			
		int enemy = rng(3) + 1;
		
		//Image for enemies Level 1+
		Image goblinWarriorImg = new Image("file:gr/goblinWarriorEnemy.png");
		Image goblinThiefImg = new Image("file:gr/goblinThiefEnemy.png");
		Image skeletonImg = new Image("file:gr/skeletonEnemy.png");
		
		//Image for enemies Level 5+
		Image goblinBerserkerImg = new Image("file:gr/goblinBerserkerEnemy.png");
		Image goblinShamanImg = new Image("file:gr/goblinShamanEnemy.png");
		Image turtleImg = new Image("file:gr/turtleEnemy.png");
		
		//Image for enemies Level 10+
		Image wraithImg = new Image("file:gr/wraithEnemy.png");
		Image darkWizardImg = new Image("file:gr/darkWizardEnemy.png");
		
		//ImageView for the enemy
		ImageView mainEnemy = new ImageView();
			mainEnemy.setFitWidth(200);
		
		if (playerLevel < 6) {
			switch (enemy) {
				case 1: 
						mainEnemy.setImage(goblinWarriorImg);
						enemyHP = 15;
						enemyMagicka = 10;
						enemyAttack = 2;
						enemyDefense = 0.95;
						enemyXP = 30;
						enemyGold = 10;
						enemyName = "Goblin Warrior";
						combatMessages.setText("A goblin warrior runs at you through the darkness! He lets out a war cry as he prepares to strike! \n");
					break;
					
				case 2: 
						mainEnemy.setImage(goblinThiefImg);
						enemyHP = 10;
						enemyMagicka = 10;
						enemyAttack = 1;
						enemyDefense = 0.90;
						enemyXP = 40;
						enemyGold = 15;
						enemyName = "Goblin Thief";
						combatMessages.setText("You have been beset upon by a goblin thief! He will not let you past! \n");
					break;
					
				case 3: 
						mainEnemy.setImage(skeletonImg);
						enemyHP = 20;
						enemyMagicka = 10;
						enemyAttack = 2;
						enemyDefense = 0.85;
						enemyXP = 45;
						enemyGold = 20;
						enemyName = "Skeleton";
						combatMessages.setText("A creature of the undead forms before your eyes! It's decrepit bones creak as it enters a battle stance. \n");
					break;
					
				default:
			}
		}
		else if (playerLevel > 0 && playerLevel < 5) {
			switch (enemy) {
				case 1: 
						mainEnemy.setImage(goblinBerserkerImg);
						enemyHP = 35;
						enemyMagicka = 10;
						enemyAttack = 5;
						enemyDefense = 0.65;
						enemyXP = 70;
						enemyGold = 40;
						enemyName = "Goblin Berserker";
						combatMessages.setText("A dragging of metal on stone is heard. A goblin berserker rushs towards you with his sword raised aboved his head! \n");
					break;
					
				case 2: 
						mainEnemy.setImage(goblinShamanImg);
						enemyHP = 25;
						enemyMagicka = 30;
						enemyAttack = 4;
						enemyDefense = 0.7;
						enemyXP = 60;
						enemyGold = 30;
						enemyName = "Goblin Shaman";
						combatMessages.setText("Muttering in an unknown language is eminating from the dark. A goblin shaman strides fowards, twirling his staff in incantation. \n");
					break;
					
				case 3: 
						mainEnemy.setImage(turtleImg);
						enemyHP = 30;
						enemyMagicka = 10;
						enemyAttack = 5;
						enemyDefense = 0.5;
						enemyXP = 80;
						enemyGold = 35;
						enemyName = "Giant Turtle";
						combatMessages.setText("You feel as if there is a giant walking down the hall. A giant turtle crawls out of the void. \n");
					break;
					
				default:
			}
		}
		else {
			switch (enemy) {
				case 1: 
						mainEnemy.setImage(wraithImg);
						enemyHP = 50;
						enemyMagicka = 20;
						enemyAttack = 10;
						enemyDefense = 0.25;
						enemyName = "Wraith";
						enemyXP = 100;
						enemyGold = 60;
						combatMessages.setText("You feel a tingling sensation go down your spine as you hear what sounds like chains dragging along the ground. A ghostly shriek is heard as the Wraith floats toward you! \n");
					break;
					
				case 2: 
						mainEnemy.setImage(darkWizardImg);
						enemyHP = 60;
						enemyMagicka = 50;
						enemyAttack = 12;
						enemyDefense = 0.35;
						enemyName = "Dark Wizard";
						enemyXP = 120;
						enemyGold = 70;
						combatMessages.setText("You hear a sinister chuckle from the inky black. A ball of fire flies past your face, you feel a searing heat as a Dark Wizard steps out from the darkness! \n");
					break;
					
				case 3: 
						enemy = rng(2) + 1;
					break;
					
				default:
			}
		}
		
		double playerDmg1 = 0;
		
		if (playerClass == "Fighter" || playerClass == "Rogue") {
			playerDmg1 = weaponChoosen();
		}
		else {
			playerDmg1 = spellChoosen();
		}
		final double playerDefense = inventory();
		final double playerDmg = playerDmg1;
			
		//Combat!!!
		Button attackBtn = new Button("Attack");
		attackBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) {
					int hitChance = rng(100) + 1;
					if (hitChance > 10) {
						combatMessages.appendText("You attack the " + enemyName + ". \n");
						double hit = playerDmg * enemyDefense;
						combatMessages.appendText("You have done " + hit + " damage. \n");
						enemyHP = enemyHP - hit;

						combatMessages.appendText("The " + enemyName + " attacks! \n");
						double enemyHit = enemyAttack * playerDefense;
						hp = hp - enemyHit;
						combatMessages.appendText("You have taken " + enemyHit + " points of damage! \n");
						combatMessages.appendText("Your health is now at " + hp + " points! \n");
					}
					else {
						combatMessages.appendText("You have missed! \n");
						combatMessages.appendText("The " + enemyName + " attacks! \n");
						double enemyHit = enemyAttack * playerDefense;
						hp = hp - enemyHit;
						combatMessages.appendText("You have taken " + enemyHit + " points of damage! \n");
						combatMessages.appendText("Your health is now at " + hp + " points! \n");
					}
					
					if (enemyHP <= 0) {
						JOptionPane.showMessageDialog(null, "You have successfully killed the " + enemyName + "! You have regained all of your health.");
						hp = 100;
						playerXP = playerXP + enemyXP;
						JOptionPane.showMessageDialog(null, "You have gained " + enemyXP + " XP!");
						if (playerXP == 500) {
							JOptionPane.showMessageDialog(null, "You have leveled up! You are now level " + playerLevel + "!");
						}
						stage3.close();
					}
				}
			});
		attackBtn.setStyle("-fx-background-color: black; -fx-text-fill: orange;");
		
	Button fleeBtn = new Button("Flee");
		fleeBtn.setOnAction(event -> {
			int fleeChance = rng(100) + 1;
			if (fleeChance > 15) {
				combatMessages.appendText("You tried to flee but have failed! \n");
				combatMessages.appendText("The " + enemyName + " attacks! \n");
				double enemyHit = enemyAttack * playerDefense;
				hp = hp - enemyHit;
				combatMessages.appendText("You have taken " + enemyHit + " points of damage! \n");
				combatMessages.appendText("Your health is now at " + hp + " points! \n");
			}
			else {
				JOptionPane.showMessageDialog(null, "You have successfully fled the battle!");
				stage3.close();
			}
		});
		fleeBtn.setStyle("-fx-background-color: black; -fx-text-fill: orange;");
		
		
		
		//Images for menu bar buttons
		Image menuImg = new Image("file:gr/menuButton.png");
		Image attackImg = new Image("file:gr/attackButton.png");
		Image magicImg = new Image("file:gr/magicButton.png");
		Image invtImg = new Image("file:gr/inventoryButton.png");
		Image saveImg = new Image("file:gr/saveButton.png");
		//Imageviews for menu bar buttons
		ImageView menuView = new ImageView(menuImg);
		ImageView attackView = new ImageView(attackImg);
		ImageView magicView = new ImageView(magicImg);
		ImageView invtView = new ImageView(invtImg);
		ImageView saveView = new ImageView(saveImg);
			
		Button menu = new Button();
			menu.setOnAction(e -> {
				menu();
			});
			menu.setGraphic(menuView);
			
		Button attack = new Button();
			attack.setOnAction(e -> {
				phyAttack();
			});
			attack.setGraphic(attackView);
			
		Button magic = new Button();
			magic.setOnAction(e -> {
				magicAttack();
			});
			magic.setGraphic(magicView);
			
		Button invt = new Button();
			invt.setOnAction(e -> {
				inventory();
			});
			invt.setGraphic(invtView);
			
		Button save = new Button();
			save.setOnAction(e -> {
				save();
			});
			save.setGraphic(saveView);
			
		HBox menuBar = new HBox(20, menu, attack, magic, invt, save);
			menuBar.setPadding(new Insets(40));
			menuBar.setStyle("-fx-background-color: #EBCFA7;");
			
		HBox enemyView = new HBox(mainEnemy);
		
		HBox interactBtns = new HBox(200, attackBtn, fleeBtn);
			interactBtns.setAlignment(Pos.CENTER);
	
		VBox combatBox = new VBox(mainEnemy, interactBtns, combatMessages, menuBar);
			combatBox.setAlignment(Pos.CENTER);
			combatBox.setStyle("-fx-background-image: url('gr/green_tile.png')");
			
		Scene combatScene  = new Scene(combatBox);
			combatScene.getStylesheets().add("the_Savage_CSS.css");
		
		stage3.setScene(combatScene);
		stage3.setTitle("The Savage Labyrinth - The Labyrinth - Engaged In Combat");
		stage3.show();
	}
	
	//Physical method to fight with weapons
	public final double phyAttack() {
		stage3 = new Stage();
		
		playerClass = playerClassChoosen();
		
		int c = 0;
		
		int i = 1;
		
		//Inventory for weapons for Fighter
		String inventoryFighterWeaponsNames[] = new String[i];
			inventoryFighterWeaponsNames[0] = "Iron Short Sword";
		double inventoryFighterWeaponsStats[] = new double[i];
			inventoryFighterWeaponsStats[0] = 3;	
			
		//Inventory for weapons for Rogue
		String inventoryRogueWeaponsNames[] = new String[i];
			inventoryRogueWeaponsNames[0] = "Iron Dagger";
		double inventoryRogueWeaponsStats[] = new double[i];
			inventoryRogueWeaponsStats[0] = 3;	
	
		Label equipLabel = new Label("Enter item number to equip");
			equipLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: orange;");
		TextField equipTF = new TextField();
			equipTF.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
		TextArea inventory = new TextArea();
			inventory.setStyle("text-area-background: black; -fx-text-fill: orange;");
			inventory.setEditable(false);
			inventory.setText("Item # \t\t Name \t\t Damage \n");

		if (playerClass.equals("Fighter")) {
			weapon = inventoryFighterWeaponsStats[0];
			do {
				String names = inventoryFighterWeaponsNames[c];
				
				double dmg = inventoryFighterWeaponsStats[c];
				
				inventory.appendText(c + "\t\t\t" + names + "\t\t" + dmg + "\n");
				c++;
			} while (i > c);
		}	
		else {
			do {
				weapon = inventoryRogueWeaponsStats[0];
				String names = inventoryRogueWeaponsNames[c];
				
				double dmg = inventoryRogueWeaponsStats[c];
				
				inventory.appendText(c + "\t\t\t" + names + "\t\t" + dmg + "\n");
				c++;
			} while (i > c);
		}
		
		Button backToGame = new Button("Back");
			backToGame.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
			backToGame.setOnAction(bg -> {
				stage3.close();
			});
		
		Button equipItem = new Button("Equip");
			equipItem.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
			equipItem.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override public void handle(MouseEvent ev) {
						if (playerClass.equals("Fighter")) {
							weapon = inventoryFighterWeaponsStats[Integer.parseInt(equipTF.getText())];
						}
						else {
							weapon = inventoryRogueWeaponsStats[Integer.parseInt(equipTF.getText())];
						}
					}
				});
			
		VBox btns = new VBox(20, backToGame, equipItem, equipLabel, equipTF);
		
		HBox weaponInvtBox = new HBox(50, inventory, btns);
			weaponInvtBox.setAlignment(Pos.CENTER);
			weaponInvtBox.setPadding(new Insets(40));
		
		Scene weaponInvtScene = new Scene(weaponInvtBox);
			weaponInvtScene.getStylesheets().add("the_Savage_CSS.css");
		
		stage3.setScene(weaponInvtScene);
		stage3.setTitle("The Savage Labyrinth - Weapons");
		stage3.show();
		return weapon;
	}
	
	//Method for getting the weapon item
	public final double weaponChoosen() {
		double weapon = phyAttack();
		stage3.close();
		return weapon;
	}
	
	private double spell;
	
	//Magic method to fight with spells
	public final double magicAttack() {
		stage3 = new Stage();
		
		int c = 0;
		
		int i = 3;
		
		//Inventory for spells
		String spellNames[] = new String[i];
			spellNames[0] = "Flare";
			spellNames[1] = "Freeze";
			spellNames[2] = "Shock";

		double spellStats[] = new double[i];
			spellStats[0] = 5;
			spellStats[1] = 5;
			spellStats[2] = 5;
			
		spell = spellStats[0];
		
		Label equipLabel = new Label("Enter item number to equip");
			equipLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: orange;");
		TextField equipTF = new TextField();
			equipTF.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
		TextArea inventory = new TextArea();
			inventory.setStyle("text-area-background: black; -fx-text-fill: orange;");
			inventory.setEditable(false);
			inventory.setText("Item # \t\t Name \t\t Damage \n");

		do {
			String names = spellNames[c];
			
			double dmg = spellStats[c];
			
			inventory.appendText(c + "\t\t\t" + names + "\t\t" + dmg + "\n");
			c++;
		} while (i > c);
		
		Button backToGame = new Button("Back");
			backToGame.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
			backToGame.setOnAction(bg -> {
				stage3.close();
			});
			
		Button equipItem = new Button("Equip");
			equipItem.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
			equipItem.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override public void handle(MouseEvent ev) {
						spell = spellStats[Integer.parseInt(equipTF.getText())];
					}
				});
			
		VBox btns = new VBox(20, backToGame, equipItem, equipLabel, equipTF);
		
		HBox weaponInvtBox = new HBox(50, inventory, btns);
			weaponInvtBox.setAlignment(Pos.CENTER);
			weaponInvtBox.setPadding(new Insets(40));
		
		Scene spellInvtScene = new Scene(weaponInvtBox);
			spellInvtScene.getStylesheets().add("the_Savage_CSS.css");
		
		stage3.setScene(spellInvtScene);
		stage3.setTitle("The Savage Labyrinth - Spells");
		stage3.show();
		return spell;
	}
	
	//Method for getting the weapon item
	public final double spellChoosen() {
		double spell = magicAttack();
		stage3.close();
		return spell;
	}
	
	//Inventory method to check the inventory with the button
	public final double inventory() {
		stage3 = new Stage();
		
		int i = 3;
		
		int c = 0;
			
		//Inventory for clothes
		String clothesNames[] = new String[i];
			clothesNames[0] = "Prisoner Shoes";
			clothesNames[1] = "Prisoner Pants";
			clothesNames[2] = "Prisoner Tunic";

		double clothesStats[] = new double[i];
			clothesStats[0] = 1;
			clothesStats[1] = 1;
			clothesStats[2] = 1;
			
		double defense = clothesStats[0] + clothesStats[1] + clothesStats[2];
		
		Label equipLabel = new Label("Enter item number to equip");
			equipLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: orange;");
		TextField equipTF = new TextField();
			equipTF.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
		TextArea inventory = new TextArea();
			inventory.setStyle("text-area-background: black; -fx-text-fill: orange;");
			inventory.setEditable(false);
			inventory.setText("Item # \t\t Name \t\t Damage \n");

		do {
			String names = clothesNames[c];
			
			double dmg = clothesStats[c];
			
			inventory.appendText(c + "\t\t\t" + names + "\t\t" + dmg + "\n");
			c++;
		} while (i > c);
		
		Button backToGame = new Button("Back");
			backToGame.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
			backToGame.setOnAction(bg -> {
				stage3.close();
			});
			
		Button equipItem = new Button("Equip");
			equipItem.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
			equipItem.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override public void handle(MouseEvent ev) {
						spell = clothesStats[Integer.parseInt(equipTF.getText())];
					}
				});
			
		VBox btns = new VBox(20, backToGame, equipItem, equipLabel, equipTF);
		
		HBox clothesInvtBox = new HBox(50, inventory, btns);
			clothesInvtBox.setAlignment(Pos.CENTER);
			clothesInvtBox.setPadding(new Insets(40));
		
		Scene clothesInvtScene = new Scene(clothesInvtBox);
			clothesInvtScene.getStylesheets().add("the_Savage_CSS.css");
		
		stage3.setScene(clothesInvtScene);
		stage3.setTitle("The Savage Labyrinth - Inventory");
		stage3.show();
		return defense;
	}
	
	public void playerStats() {
		//Player stats
		hp = hp;
		magicka = magicka;
		defense = defense;
		totalGold = totalGold;
		playerLevel = playerLevel;
		playerXP = playerXP;
		
		//Skill stats
		blade = blade;
		mysticism = mysticism;
		mercantile = mercantile;
		heavyArmor = heavyArmor;
		destruction = destruction;
		speechcraft = speechcraft;
		athletics = athletics;
		handToHand = handToHand;
		restoration = restoration;			
		lightArmor = lightArmor;
		marksman = marksman;
	}
	
	//Random number generator
	public static int rng(int num) {
		Random rand = new Random();
		
		return rand.nextInt(num);
	}
}