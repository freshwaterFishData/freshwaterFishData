package br.ufrj.macae.tic.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.ufrj.macae.tic.modelo.dao.SampleDAO;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Occurrence;

public class Teste {
	
	public static void main(String args[]) {
		
		 try {
		        BufferedWriter event = new BufferedWriter(new FileWriter("event.txt"));
		        BufferedWriter occurrence = new BufferedWriter(new FileWriter("occurrence.txt"));
		        
		        SampleDAO sampleDAO = new SampleDAO();
		        
		        List<Event> eventList = sampleDAO.pesquisa();
		        
		        
		        event.write("Id,eventID,eventDate,samplingProtocol,sampleSizeValue,sampleSizeUnit,samplingEffort,habitat \n");
		        occurrence.write("id,eventID,occurenceID,recordedBy,IdentifiedBy,individualCount,organismQuantity \n");
		        String sEvent;
		        String sOccurrence;
		        for(Event e : eventList) {
		        	
		        	sEvent = e.getId() + "," + e.getId() + "," + e.getEventDate() + "," + e.getSamplingProtocol().getSamplingProtocol() + "," + e.getSampleSizeValue() + "," 
		        	+ e.getSizeUnit().getSizeUnit() + "," + e.getSampleEffort() + "," + e.getWaterBody().getHabitat().getHabitat() + "\n";
		        	
		        	//nova linha
		        	event.write(sEvent);
		        	
		        	for(Occurrence o : e.getOccurrences()) {
		        		sOccurrence = o.getId() + "," + e.getId() + "," + o.getId() + "," + o.getRecorder().getPersonGivenName() + " " + o.getRecorder().getPersonSurName() + "," 
		        				+ o.getIdentifier().getPersonGivenName() + " " + o.getIdentifier().getPersonSurName() + "," + o.getIndividualCount() + ","
		        				+ o.getOrganismQuantity() + "\n";
		        		
		        		//nova linha
			        	occurrence.write(sOccurrence);
		        	}
		        }
		        
		        event.close();
		        occurrence.close();
		    }
		    catch(IOException e){
		        // possiveis erros são tratados aqui
		    	e.printStackTrace();
		    }
		    System.out.println("Acabei de escrever no arquivo");
		    System.exit(0);
		
	}

}
