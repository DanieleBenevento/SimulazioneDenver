/**
 * Sample Skeleton for 'Crimes.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import it.polito.tdp.model.Distretto;
import it.polito.tdp.model.EdgeAndCount;
import it.polito.tdp.model.Model;
import it.polito.tdp.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CrimesController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {
    	
    	if(this.boxAnno.getValue()!=null) {
    	txtResult.clear();
    	model.creaGrafo(boxAnno.getValue());
    	
    	for(Distretto d:model.getGrafo().vertexSet()) {
    		txtResult.appendText("Distretto "+d.getId()+": ");
    	for(EdgeAndCount e : model.getEdgeAndCount(model.getGrafo(), d)) {
    		txtResult.appendText(""+Graphs.getOppositeVertex(model.getGrafo(),e.getEdgee(),d)+"-");
    	}
    	txtResult.appendText("\n");
    	}
    	
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	try {
    		txtResult.clear();
    	if(this.boxAnno.getValue()!=null && this.boxMese.getValue()!=null) {
    		model.creaGrafo(boxAnno.getValue());
    		switch(this.boxMese.getValue()) {
    		
    		case 2: 
    		if(this.boxGiorno.getValue()>28) {
    			txtResult.appendText("Giorno non presente nel mese\n");
    			return;
    		}
    		else {
    			if(Integer.parseInt(this.txtN.getText())>0 && Integer.parseInt(this.txtN.getText())<10 ) {
    				Simulatore sim=new Simulatore(Integer.parseInt(this.txtN.getText()), boxAnno.getValue(), boxMese.getValue(), boxGiorno.getValue());
    				sim.init();
    				sim.run();
    				txtResult.appendText("Totale eventi "+sim.getTOT_EVENTI()+", ben gestiti "+sim.getBEN_GESTITI()+", mal gestiti "+sim.getMAL_GESTITI());
    			}
    			else {
    				txtResult.appendText("Valore di n fuori range. Devi inserire una valore tra 1 e 10\n");
    			}
    		}
    		break;
    		case 4:
    		if(this.boxGiorno.getValue()>30) {
    			txtResult.appendText("Giorno non presente nel mese\n");
    			return;
    		}
    		else {
    			if(Integer.parseInt(this.txtN.getText())>0 && Integer.parseInt(this.txtN.getText())<10 ) {
    				Simulatore sim=new Simulatore(Integer.parseInt(this.txtN.getText()), boxAnno.getValue(), boxMese.getValue(), boxGiorno.getValue());
    				sim.init();
    				sim.run();
    				txtResult.appendText("Totale eventi "+sim.getTOT_EVENTI()+", ben gestiti "+sim.getBEN_GESTITI()+", mal gestiti "+sim.getMAL_GESTITI());
    			}
    			else {
    				txtResult.appendText("Valore di n fuori range. Devi inserire una valore tra 1 e 10\n");
    			}
    		}
    		break;
    		case 6:
    		if(this.boxGiorno.getValue()>30) {
    			txtResult.appendText("Giorno non presente nel mese\n");
    			return;
    		}
    		else {
    			if(Integer.parseInt(this.txtN.getText())>0 && Integer.parseInt(this.txtN.getText())<10 ) {
    				Simulatore sim=new Simulatore(Integer.parseInt(this.txtN.getText()), boxAnno.getValue(), boxMese.getValue(), boxGiorno.getValue());
    				sim.init();
    				sim.run();
    				txtResult.appendText("Totale eventi "+sim.getTOT_EVENTI()+", ben gestiti "+sim.getBEN_GESTITI()+", mal gestiti "+sim.getMAL_GESTITI());
    			}
    			else {
    				txtResult.appendText("Valore di n fuori range. Devi inserire una valore tra 1 e 10\n");
    			}
    		}
    		break;
    		case 9:
    		if(this.boxGiorno.getValue()>30) {
    			txtResult.appendText("Giorno non presente nel mese\n");
    			return;
    		}
    		else {
    			if(Integer.parseInt(this.txtN.getText())>0 && Integer.parseInt(this.txtN.getText())<10 ) {
    				Simulatore sim=new Simulatore(Integer.parseInt(this.txtN.getText()), boxAnno.getValue(), boxMese.getValue(), boxGiorno.getValue());
    				sim.init();
    				sim.run();
    				txtResult.appendText("Totale eventi "+sim.getTOT_EVENTI()+", ben gestiti "+sim.getBEN_GESTITI()+", mal gestiti "+sim.getMAL_GESTITI());
    			}
    			else {
    				txtResult.appendText("Valore di n fuori range. Devi inserire una valore tra 1 e 10\n");
    			}
    		}
    		break;
    		
    		default:
    			if(Integer.parseInt(this.txtN.getText())>0 && Integer.parseInt(this.txtN.getText())<10 ) {
    				Simulatore sim=new Simulatore(Integer.parseInt(this.txtN.getText()), boxAnno.getValue(), boxMese.getValue(), boxGiorno.getValue());
    				sim.init();
    				sim.run();
    				txtResult.appendText("Totale eventi "+sim.getTOT_EVENTI()+", ben gestiti "+sim.getBEN_GESTITI()+", mal gestiti "+sim.getMAL_GESTITI());
    			}
    			else {
    				txtResult.appendText("Valore di n fuori range. Devi inserire una valore tra 1 e 10\n");
    			}
    		
    		}
    	}
    	}catch(NumberFormatException nfe) {
    		txtResult.appendText("Formato non valido nel campo N. Inserisci un numero in quel campo.\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxAnno.getItems().addAll(model.getAllYears());
    	for(int i=1;i<13;i++) {
    		this.boxMese.getItems().add(i);
    }
    	for(int j=1;j<32;j++) {
    	this.boxGiorno.getItems().add(j);
    	}
    	}
    
    
}