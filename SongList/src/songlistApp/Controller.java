package songlistApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Controller {
	
	@FXML ListView<String> listView; 
	
	@FXML Button add;
	@FXML Button edit;
	@FXML Button delete;
	@FXML Button applyEdit;
	@FXML Button addSong;
	@FXML Button cancel;
	
	@FXML TextField songField;
	@FXML TextField artistField;
	@FXML TextField albumField;
	@FXML TextField yearField;
	
	@FXML Text songText;
	@FXML Text artistText;
	@FXML Text albumText;
	@FXML Text yearText;
	
	private ArrayList<Song> songList;
	private ObservableList<String> obsList;
	private String saStr; // song+artist string concatenation
	
	//FUNCTION FOR TAKING DATA FROM JSON AND FILLING UP songList
	public void fillSongList() {
		//do stuff
		//fill using gson.FromJson()
		
		//this is temp code
		songList = new ArrayList<Song>();
		
		Song one = new Song("(You're) Having My Baby","Paul Anka/Odia Coates ","oneone","1974");
        Song two = new Song("I Write The Songs","Barry Manilow","twotwo","1975");
        Song three = new Song("Billy, Don't Be A Hero","Bo Donaldson & the Heywoods","threethree","1974");
        Song four = new Song("Ballad of the Green Berets","Sgt. Barry Sadler","fourfour","1966");
        
        songList.add(one);
        songList.add(two);
        songList.add(three);
        songList.add(four);
		
		return;
	}
	
	// START
	public void start(Stage mainStage) {                
	
		fillSongList();
		
		// create an ObservableList from an ArrayList  
		obsList = FXCollections.observableArrayList(                               
				songList.get(0).getName() + " by " + songList.get(0).getArtist(),                               
				songList.get(1).getName() + " by " + songList.get(1).getArtist(),
				songList.get(2).getName() + " by " + songList.get(2).getArtist(),
				songList.get(3).getName() + " by " + songList.get(3).getArtist()); 

		listView.setItems(obsList);

		// select the first item if there is something to select
		if(!listView.getSelectionModel().isEmpty()) {
			listView.getSelectionModel().select(0);
		}
		
	    // set listener for the items
	    listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> setStr());
	}
	
	
	// ADD
	public void addPress(ActionEvent e) {
		Button b = (Button)e.getSource();
		
		if (b == add) {
			showMenu(b);
			setAllFields(null);
		}
	}
	
	public void addSongPress(ActionEvent e) {
		Button b = (Button)e.getSource();
		Song tempSong;
		String songlist_sa;
		String temp_sa;
		
		if (b == addSong) {
			
			if(songField.getText().isEmpty()) {
				System.out.println("Error: Song name was left blank. Please write in a song name to add the song into the list.");
				return;
			}else if(artistField.getText().isEmpty()) {
				System.out.println("Error: Artist was left blank. Please write in an artist to add the song into the list.");
				return;
			}else {
				tempSong = new Song(songField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
			}
			
			
			if(songList.isEmpty()) {
				songList.add(tempSong);
			}else {
				temp_sa = tempSong.getName() + tempSong.getArtist();
				
				for(int i = 0; i < songList.size(); i++) {
					songlist_sa = songList.get(i).getName() + songList.get(i).getName();
					if(temp_sa.compareTo(songlist_sa) == 0) {
						System.out.println("Error: Song already exists. Please add a new song.");
						return;
					}
				}
				
				songList.add(tempSong);
			}
			//sort
			Collections.sort(songList, Song.songComparator);
			
			hideMenu();
		}
	}
	
	
	// DELETE
	public void deletePress(ActionEvent e) {
		Button b = (Button)e.getSource();
		String tempSong;
		
		if (b == delete) {
			setAllFields(null);
			
			if(listView.getSelectionModel().getSelectedItem().isEmpty()) {
				return;
			}else {
				tempSong = listView.getSelectionModel().getSelectedItem();
				
				//do stuff
			}
		}
	}
	
	
	// EDIT
	public void editPress(ActionEvent e) {
		Button b = (Button)e.getSource();
		String songTemp;
		
		if (b == edit) {
			showMenu(b);
			
			if(!listView.getSelectionModel().getSelectedItem().isEmpty()) {
				songTemp = listView.getSelectionModel().getSelectedItem();
				
				for(int i = 0; i < songList.size(); i++) {
					if(songTemp.compareTo(songList.get(i).getName() + " by " + songList.get(i).getArtist()) == 0) {
						songField.setText(songList.get(i).getName());
						artistField.setText(songList.get(i).getArtist());
						albumField.setText(songList.get(i).getAlbum());
						yearField.setText(songList.get(i).getYear());
					}
				}
			}else {
				System.out.println("Error: Select a song.");
			}
		}
		
		return;
	}
	
	public void applyEditPress(ActionEvent e) {
		Button b = (Button)e.getSource();
		if (b == applyEdit) {
			//do stuff
			
			hideMenu();
		}
	}
	
	
	// CANCEL
	public void cancelPress(ActionEvent e) {
		Button b = (Button)e.getSource();
		if (b == cancel) {
			hideMenu();
		}
	}
	
	
	// HELPER METHODS
	private void showMenu(Button b) {
		songField.setVisible(true);
		songField.setDisable(false);
		songText.setVisible(true);
		songText.setDisable(false);
		artistField.setVisible(true);
		artistField.setDisable(false);
		artistText.setVisible(true);
		artistText.setDisable(false);
		albumField.setVisible(true);
		albumField.setDisable(false);
		albumText.setVisible(true);
		albumText.setDisable(false);
		yearField.setVisible(true);
		yearField.setDisable(false);
		yearText.setVisible(true);
		yearText.setDisable(false);
		cancel.setVisible(true);
		cancel.setDisable(false);
		
		if(b == edit) {
			if(addSong.isVisible()) {
				addSong.setVisible(false);
				addSong.setDisable(true);
			}

			applyEdit.setVisible(true);
			applyEdit.setDisable(false);

		}else if(b == add) {
			if(applyEdit.isVisible()) {
				applyEdit.setVisible(false);
				applyEdit.setDisable(true);
			}

			addSong.setVisible(true);
			addSong.setDisable(false);
		}
	}
	
	private void hideMenu() {
		songField.setVisible(false);
		songField.setDisable(true);
		songText.setVisible(false);
		songText.setDisable(true);
		artistField.setVisible(false);
		artistField.setDisable(true);
		artistText.setVisible(false);
		artistText.setDisable(true);
		albumField.setVisible(false);
		albumField.setDisable(true);
		albumText.setVisible(false);
		albumText.setDisable(true);
		yearField.setVisible(false);
		yearField.setDisable(true);
		yearText.setVisible(false);
		yearText.setDisable(true);
		cancel.setVisible(false);
		cancel.setDisable(true);
		
		if(applyEdit.isVisible()) {
			applyEdit.setVisible(false);
			applyEdit.setDisable(true);
		}
		if(addSong.isVisible()) {
			addSong.setVisible(false);
			addSong.setDisable(true);
		}
	}
	
	private void setAllFields(String str) {
		songField.setText(str);
		artistField.setText(str);
		albumField.setText(str);
		yearField.setText(str);
	}
	
	private void setStr() {saStr = listView.getSelectionModel().getSelectedItem();}
}
