/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.CountyDAO;
import br.ufrj.macae.tic.modelo.dao.HabitatDAO;
import br.ufrj.macae.tic.modelo.dao.MunicipalityDAO;
import br.ufrj.macae.tic.modelo.dao.PersonDAO;
import br.ufrj.macae.tic.modelo.dao.SampleDAO;
import br.ufrj.macae.tic.modelo.dao.SampleProtocolDAO;
import br.ufrj.macae.tic.modelo.dao.SizeUnitDAO;
import br.ufrj.macae.tic.modelo.dao.SubprojectDAO;
import br.ufrj.macae.tic.modelo.dao.WaterBodyDAO;
import br.ufrj.macae.tic.persistence.entity.County;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Habitat;
import br.ufrj.macae.tic.persistence.entity.Municipality;
import br.ufrj.macae.tic.persistence.entity.Occurrence;
import br.ufrj.macae.tic.persistence.entity.People;
import br.ufrj.macae.tic.persistence.entity.Person;
import br.ufrj.macae.tic.persistence.entity.SamplingProtocol;
import br.ufrj.macae.tic.persistence.entity.SizeUnit;
import br.ufrj.macae.tic.persistence.entity.Subproject;
import br.ufrj.macae.tic.persistence.entity.WaterBody;
import br.ufrj.macae.tic.util.OperacoesArquivos;
import br.ufrj.macae.tic.util.Util;


@Named
@SessionScoped
@ManagedBean
public class SampleMB implements Serializable {	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8023212851823352562L;

	@Inject
	private Event event;

	private Event eventAux;

	@Inject
	private Event eventSave;

	@Inject
	private SampleDAO sampleDAO;

	@Inject
	private SampleProtocolDAO samplingProtocolDAO;

	@Inject
	private CountyDAO countyDAO; 

	@Inject
	private  MunicipalityDAO municipalityDAO; 

	@Inject
	private HabitatDAO habitatDAO;

	@Inject
	private WaterBodyDAO waterBodyDAO;

	@Inject
	private SizeUnitDAO sizeUnitDAO;

	@Inject
	private SubprojectDAO subprojectDAO;		


	private List<SamplingProtocol>  samplingProtocolList;	
	private List<SizeUnit> sizeUnitList;	
	private List<County>  countyList;		
	private List<Municipality> municipalityList;	
	private List<Habitat> habitatList;			
	private List<WaterBody> waterBodyList;
	private List<Subproject> subProjectList;

	private List<Event> eventList;


	private List<Event> eventListSelect;

	private Date initialDate;
	private Date finalDate;

	private List<Occurrence> occurrenceList;

	private String caminhoDownload;
	private String arqName;

	private boolean first;
	private boolean last;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}


	public List<Event> getEventList() {
		return eventList;
	}


	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}


	public Event getEventAux() {
		return eventAux;
	}


	public void setEventAux(Event eventAux) {
		this.eventAux = eventAux;
	}


	public List<Subproject> getSubProjectList() {
		return subProjectList;
	}


	public void setSubProjectList(List<Subproject> subProjectList) {
		this.subProjectList = subProjectList;
	}



	public List<SamplingProtocol> getSamplingProtocolList() {
		return samplingProtocolList;
	}


	public void setSamplingProtocolList(List<SamplingProtocol> samplingProtocolList) {
		this.samplingProtocolList = samplingProtocolList;
	}


	public List<SizeUnit> getSizeUnitList() {
		return sizeUnitList;
	}


	public void setSizeUnitList(List<SizeUnit> sizeUnitList) {
		this.sizeUnitList = sizeUnitList;
	}


	public List<County> getCountyList() {
		return countyList;
	}


	public void setCountyList(List<County> countyList) {
		this.countyList = countyList;
	}


	public List<Municipality> getMunicipalityList() {
		return municipalityList;
	}


	public void setMunicipalityList(List<Municipality> municipalityList) {
		this.municipalityList = municipalityList;
	}


	public List<Habitat> getHabitatList() {
		return habitatList;
	}


	public void setHabitatList(List<Habitat> habitatList) {
		this.habitatList = habitatList;
	}


	public List<WaterBody> getWaterBodyList() {
		return waterBodyList;
	}


	public void setWaterBodyList(List<WaterBody> waterBodyList) {
		this.waterBodyList = waterBodyList;
	}

	public Event getEventSave() {
		return eventSave;
	}


	public void setEventSave(Event eventSave) {
		this.eventSave = eventSave;
	}


	public Date getFinalDate() {
		return finalDate;
	}


	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}



	public List<Occurrence> getOccurrenceList() {
		return occurrenceList;
	}


	public void setOccurrenceList(List<Occurrence> occurrenceList) {
		this.occurrenceList = occurrenceList;
	}

	public List<Event> getEventListSelect() {
		return eventListSelect;
	}

	public void setEventListSelect(List<Event> eventListSelect) {
		this.eventListSelect = eventListSelect;
	}

	public String getCaminhoDownload() {
		return caminhoDownload;
	}

	public void setCaminhoDownload(String caminhoDownload) {
		this.caminhoDownload = caminhoDownload;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	@PostConstruct
	public void init() {

		//eventList = sampleDAO.pesquisa();

		subProjectList = subprojectDAO.pesquisa();
		iniciarListas();

	}

	/*
	 * Abre a modal de sub projeto
	 */
	public String openSubProject() {

		subProjectList = subprojectDAO.pesquisa();

		return "/researcher/sample/subproject";

	}

	/*
	 * Abre a tela de coleta
	 */
	public String open() {	   

		Subproject subproject = subprojectDAO.recupera(event.getSubproject().getId());

		event.setSubproject(subproject);
		eventAux = new Event();
		eventAux.setSubproject(subproject);

		eventList = sampleDAO.getEventOrderByDate(event);

		return "listSample";
	}


	/*
	 * Volta a tela de coleta
	 */
	public String back() {


		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("subprojectId");

		Long id = Long.parseLong(p);	   

		Subproject subproject = subprojectDAO.recupera(id);

		event.setSubproject(subproject);
		eventAux = new Event();
		eventAux.setSubproject(subproject);

		eventList = sampleDAO.getEventOrderByDate(event);

		return "listSample";
	}	

	/*
	 * Volta da tela de ocorrência para a de coleta
	 */
	public void backOfOccurrance() {

		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("subprojectId");

		Long id = Long.parseLong(p);		   

		Subproject subproject = subprojectDAO.recupera(id);

		event.setSubproject(subproject);
		eventAux = new Event();
		eventAux.setSubproject(subproject);

		eventList = sampleDAO.getEventOrderByDate(event);

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		try {
			ec.redirect("../sample/listSample.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		//return "./researcher/sample/listSample";
	}		

	private void iniciarListas() {

		samplingProtocolList = samplingProtocolDAO.pesquisa();		
		sizeUnitList = sizeUnitDAO.pesquisa();		
		countyList = countyDAO.pesquisa();			
		municipalityList = municipalityDAO.pesquisa();		
		habitatList = habitatDAO.pesquisa();				
		waterBodyList = waterBodyDAO.getWaterBodyOrder();
	}

	/*
	 * Edita os dados de coleta
	 */
	public void edit() {

		try {			 


			sampleDAO.atualiza(event);

			eventList = sampleDAO.pesquisa();


			Mensagem.adicionarMensagemSucesso("Evento atualizado com sucesso!");

			//open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_ATUALIZAR);

		}
	}

	/*
	 * Deteta os dados de evento de coleta
	 */
	public void delete() {

		try {			 

			eventAux = new Event();
			eventAux.setId(event.getId());

			sampleDAO.apaga(eventAux);

			Mensagem.adicionarMensagemSucesso("Evento removido com sucesso!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_EXCLUIR);

		}
	}

	public void searchEvent() {	   

		eventList = sampleDAO.getEventByDate(initialDate, finalDate, event.getSubproject().getId());
	}


	public void relatorio2() {

		occurrenceList = new ArrayList<Occurrence>();
		if(eventListSelect == null || eventListSelect.isEmpty()) {
			Mensagem.adicionarMensagemErro("Selecione ao menos um evento de coleta..");
			return;
		}

		for(Event e : eventListSelect) {
			occurrenceList.addAll(e.getOccurrences());
		}

		if(occurrenceList.isEmpty()) {
			Mensagem.adicionarMensagemErro("Não existem ocorrências cadastradas para o evento de coleta cadastrado.");		   
		}
	}

	/*
	 * Cria os arquivos a serem exportados para o sibbr.
	 * São criados os seguintes arquivos: event.txt, occurrence.txt e o arquivo eml.xml.
	 * Esses arquivos são compactados junto com o arquivo meta.xml, sendo assim eles estão
	 * preparados para serem exportados para o sibbr
	 *
	 */
	public StreamedContent exportarSibbr() {

		StreamedContent file = null;
		try {		   

			if(eventListSelect == null || eventListSelect.isEmpty()) {		
				throw new RuntimeException("Selecione ao menos um evento de coleta.");
			}

			String dir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "sibbr";
			String entrada = dir + "/exportacao/";


			BufferedWriter event = new BufferedWriter(new FileWriter(entrada + "event.txt"));
			BufferedWriter occurrence = new BufferedWriter(new FileWriter(entrada + "occurrence.txt"));



			event.write("Id,eventID,eventDate,samplingProtocol,sampleSizeValue,sampleSizeUnit,samplingEffort,habitat \n");
			occurrence.write("id,eventID,occurenceID,recordedBy,IdentifiedBy,individualCount,organismQuantity \n");
			String sEvent;
			String sOccurrence;
			String nomeArquivo = "";
			for(Event e : eventListSelect) {

				sEvent = e.getId() + "," + e.getEventId() + "," + e.getEventDate() + "," + e.getSamplingProtocol().getSamplingProtocol() + "," + e.getSampleSizeValue() + "," 
						+ e.getSizeUnit().getSizeUnit() + "," + e.getSampleEffort() + "," + e.getWaterBody().getHabitat().getHabitat() + "\n";

				//nova linha
				event.write(sEvent);

				for(Occurrence o : e.getOccurrences()) {
					sOccurrence = o.getId() + "," + e.getEventId() + ",br:sibbr:peld:rlac:" + e.getEventId() + ":" + o.getId() + "," + o.getRecorder().getPersonGivenName() + " " + o.getRecorder().getPersonSurName() + "," 
							+ o.getIdentifier().getPersonGivenName() + " " + o.getIdentifier().getPersonSurName() + "," + o.getIndividualCount() + ","
							+ o.getOrganismQuantity() + "\n";

					//nova linha
					occurrence.write(sOccurrence);
				}

				nomeArquivo = "dwc-peld-rlac-ictiologia-" + e.getSubproject().getSubprojectTitle() + ".zip";
			}

			event.close();
			occurrence.close();


			createEmlArq(eventList.get(0).getSubproject(), entrada);

			String[] files = new String[4];
			files[0] = "event.txt";
			files[1] = "occurrence.txt";
			files[2] = "eml.xml";
			files[3] = "meta.xml";



			zipFiles(entrada, dir , files, nomeArquivo); 

			caminhoDownload = dir + "/" + nomeArquivo;
			arqName = nomeArquivo;

			Mensagem.adicionarMensagemSucesso("Arquivo gerado com sucesso.");

			//FacesContext.getCurrentInstance().getExternalContext().redirect("file://" + entrada + nomeArquivo);



		} catch (RuntimeException e) {
			Mensagem.adicionarMensagemErro(e.getMessage());
		} catch (IOException e) {
			Mensagem.adicionarMensagemErro("Ocorreu um erro na exportação dos arquivo devida a falta da pasta contendo os metadados.");
		} catch(Exception e){
			e.printStackTrace();
			Mensagem.adicionarMensagemErro("Ocorreu um erro ao exportar o arquivo.");
		}

		return file;

		//eventList.clear();
	}

	/*
	 * Cria o arquivo eml.xml a ser exportado para o sibbbr
	 */
	private void createEmlArq(Subproject sb, String caminho) {

		PersonDAO pDAO = new PersonDAO();

		StringBuffer eml = new StringBuffer();

		eml.append("<eml:eml ").append("xmlns:eml='eml://ecoinformatics.org/eml-2.1.1' ");
		eml.append("xmlns:dc='http://purl.org/dc/terms/'");
		eml.append(" xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'");
		eml.append(" xsi:schemaLocation='eml://ecoinformatics.org/eml-2.1.1").append(" http://rs.gbif.org/schema/eml-gbif-profile/1.1/eml.xsd' ");
		eml.append(" packageId='https://ipt.sibbr.gov.br/peld/resource?id=dwc-peld-rlac-ictiologia-" + sb.getSubprojectTitle() + "/v1.0' system='http://gbif.org' scope='system' xml:lang='eng'> ");
		eml.append("<dataset>");
		eml.append("<title xml:lang='").append(sb.getMetadataLanguage()).append("'>").append(sb.getSubprojectTitle()).append("</title>");

		eml.append("<creator>");
		insertPersonData(eml, pDAO.recupera(sb.getSubprojectCoordinatorId()) );
		eml.append("</creator>");

		eml.append("<metadataProvider>");
		insertPersonData(eml, pDAO.recupera(sb.getMetadataProviderId()) );
		eml.append("</metadataProvider>");

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = formato.format(new Date());		
		eml.append("<pubDate>").append(dataFormatada).append("</pubDate>");
		eml.append("<language>").append(sb.getDataLanguage()).append("</language>");

		eml.append("<abstract>");
		eml.append("<para>").append(sb.getSubProjectDescription()).append("</para>");
		eml.append("</abstract>");

		eml.append("<keywordSet>");
		eml.append("<keyword>").append(sb.getKeywords()).append("</keyword>");
		eml.append("</keywordSet>");

		eml.append("<intellectualRights>");
		eml.append("<para>").append(sb.getLicence()).append("</para>");
		eml.append("</intellectualRights>");

		eml.append("<coverage>");
		eml.append("<geographicCoverage>");
		eml.append("<geographicDescription>").append(sb.getGeographicalDescription()).append("</geographicDescription>");	   	   
		eml.append("<boundingCoordinates>");
		eml.append("<westBoundingCoordinate>").append(sb.getWest()).append("</westBoundingCoordinate>");
		eml.append("<eastBoundingCoordinate>").append(sb.getEast()).append("</eastBoundingCoordinate>");
		eml.append("<southBoundingCoordinate>").append(sb.getSouth()).append("</southBoundingCoordinate>");
		eml.append("<northBoundingCoordinate>").append(sb.getNorth()).append("</northBoundingCoordinate>");
		eml.append("</boundingCoordinates>");	   
		eml.append("</geographicCoverage>");

		dataFormatada = formato.format(sb.getInicialDateTemporal());
		eml.append("<temporalCoverage>");
		eml.append("<rangeOfDates>");
		eml.append("<beginDate>");
		eml.append("<calendarDate>").append(dataFormatada).append("</calendarDate>");
		eml.append("</beginDate>");

		dataFormatada = formato.format(sb.getEndDateTemporal());
		eml.append("<endDate>");
		eml.append("<calendarDate>").append(sb.getEndDateTemporal()).append("</calendarDate>");
		eml.append("</endDate>");		   
		eml.append("</rangeOfDates>");
		eml.append("</temporalCoverage>");
		eml.append("</coverage>");  

		eml.append("<maintenance>"); 
		eml.append("<description>");
		eml.append("<para></para>");
		eml.append("</description>");
		eml.append("<maintenanceUpdateFrequency>unkown</maintenanceUpdateFrequency>");
		eml.append("</maintenance>");

		eml.append("<contact>");
		insertPersonData(eml, pDAO.recupera(sb.getContactId()) );
		eml.append("</contact>");

		eml.append("<methods>");
		eml.append("<methodStep>");
		eml.append("<description>");
		eml.append("<para>").append(sb.getStepDescription()).append("</para>");
		eml.append("</description>");
		eml.append("</methodStep>");
		eml.append("<sampling>");
		eml.append("<studyExtent>");
		eml.append("<description>");
		eml.append("<para>").append(sb.getStudyExtendy()).append("</para>");
		eml.append("</description>");
		eml.append("</studyExtent>");
		eml.append("<samplingDescription>");
		eml.append("<para>").append(sb.getSampleDescription()).append("</para>");
		eml.append("</samplingDescription>");
		eml.append("</sampling>");
		eml.append("</methods>");
		eml.append("</dataset>");


		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		eml.append("<additionalMetadata>");
		eml.append("<metadata>");
		eml.append("<gbif>");
		eml.append("<dateStamp>").append(timestamp).append("</dateStamp>");
		eml.append("<hierarchyLevel>dataset</hierarchyLevel>");
		eml.append("<citation>").append("Luiz Florentino Borges E (2017): dwc-peld-rlac-ictiologia-" + sb.getSubprojectTitle() + "v1.0. Sistema de Informação sobre a Biodiversidade Brasileira - SiBBr. Dataset/Samplingevent. https://ipt.sibbr.gov.br/peld/resource?r=dwc-peld-rlac-ictiologia-"+sb.getSubprojectTitle()+"&amp;v=1.0").append("</citation>"); 
		eml.append("<dc:replaces>https://ipt.sibbr.gov.br/peld/resource?id=dwc-peld-rlac-ictiologia-" + sb.getSubprojectTitle() + "/v1.0.xml</dc:replaces>");	
		eml.append("</gbif>");
		eml.append("</metadata>");
		eml.append("</additionalMetadata>");

		eml.append("</eml:eml>");


		BufferedWriter emlArq;	 
		try {
			emlArq = new BufferedWriter(new FileWriter(caminho + "eml.xml"));
			emlArq.write(eml.toString());
			emlArq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
	
	/*
	 * Esse método faz a compactação de arquivos
	 */
	public void zipFiles(String dirImp, String dirTmp, String[] filenames, String outFilename) {
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];
		try {
			// Create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(dirTmp + "/" + outFilename));
			// Compress the files
			for (int i = 0; i < filenames.length; i++) {
				FileInputStream in = new FileInputStream(dirImp + "/" + filenames[i]);
				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(filenames[i]));
				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// Complete the entry
				out.closeEntry();
				in.close();
			}
			// Complete the ZIP file
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			Mensagem.adicionarMensagemErro("Ocorreu um erro ao zippar o arquivo.");
		}
	}


	/*
	 * Emite o relatório de coleta
	 */
	public void relatorio() {

		occurrenceList = new ArrayList<Occurrence>();
		if(eventListSelect == null || eventListSelect.isEmpty()) {
			Mensagem.adicionarMensagemErro("Selecione ao menos um evento de coleta..");
			return;
		}

		for(Event e : eventListSelect) {
			occurrenceList.addAll(e.getOccurrences());
		}

		if(occurrenceList.isEmpty()) {
			Mensagem.adicionarMensagemErro("Não existem ocorrências cadastradas para o evento de coleta cadastrado.");		   
		}
	}

	public void getAll() {	   
		eventList = sampleDAO.pesquisa();
	}

	/*
	 * Verifica se possue mais eventos a serem visualizados na modal de edição e se estiver
	 * mostra o próximo.
	 */
	public void next() {

		try {

			int t = eventList.size() - 1;
			Event l = eventList.get(t);

			long id = event.getId();

			boolean achei = false;
			//last = true;
			for(Event e : eventList) {

				if(achei) {
					event = e;				   
					break;
				}

				if(e.getId() == id) {
					achei = true;
				}
			}

			verificarPosicao();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/*
	 * Verifica se possue mais eventos a serem visualizados na modal de edição e se estiver
	 * mostra o anterior.
	 */
	public void previous() {

		try {

			//Event p = eventList.get(0);

			long id = event.getId();
			long idAnterior = 0; 
			boolean achei = false;
			//first = true;
			for(Event e : eventList) {



				if(e.getId() == id) {
					achei = true;
					if(idAnterior != 0) {
						event = sampleDAO.recupera(idAnterior);
					} else {
						event = e;
					}

					break;
				}

				if(!achei) {
					idAnterior = e.getId();
				} 

			}

			verificarPosicao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Faz o download do arquivo a ser exportado para o sibbr
	 */
	public void download() {
		OperacoesArquivos.downloadFile(arqName, caminhoDownload ,"zip",FacesContext.getCurrentInstance());
	}

	/*
	 * Na tela de edição de coleta verifique se o item é o último ou o primeiro, com 
	 * o objetivo de retirar o botão que permitir avançar ou retroceder para outro evento
	 * de coleta
	 */
	public void verificarPosicao(Event event) {

		Event p = eventList.get(0);
		if( event.getId() == p.getId() ) {
			first = true;
			last = false;
			return;
		} else {
			first = true;
		}

		int pos = eventList.size() - 1;
		p = eventList.get(pos);

		if( event.getId() == p.getId() ) {
			last = true;
			first = false;
		} else {
			last = true;
		}
	}

	/*
	 * Na tela de edição de coleta verifique se o item é o último ou o primeiro, com 
	 * o objetivo de retirar o botão que permitir avançar ou retroceder para outro evento
	 * de coleta
	 */
	public void verificarPosicao() {

		Event p = eventList.get(0);
		if( event.getId() == p.getId() ) {
			first = true;
			last = false;
			return;
		} else {
			first = true;
		}

		int pos = eventList.size() - 1;
		p = eventList.get(pos);

		if( event.getId() == p.getId() ) {
			last = true;
			first = false;
		} else {
			last = true;
		}
	}
	
	/*
	 * Insere os dados dos envolvidos no projeto no arquivo eml.xml
	 */
	private void insertPersonData(StringBuffer eml, Person p) {
		eml.append("<individualName>");
		eml.append("<givenName>").append(p.getPersonGivenName()).append("</givenName>");
		eml.append("<surName>").append(p.getPersonSurName()).append("</surName>");	   
		eml.append("</individualName>");
		eml.append("<organizationName>").append(p.getOrganization().getOrganizationName()).append("</organizationName>");
		eml.append("<positionName>").append(p.getPosition().getPositionName()).append("</positionName>");	   
		eml.append("<address>");
		eml.append("<deliveryPoint>Rua São José do Barreto, 764</deliveryPoint>");
		eml.append("<city>Macaé</city>");
		eml.append("<administrativeArea>Rio de Janeiro</administrativeArea>");
		eml.append("<postalCode>27910-970</postalCode>");
		eml.append("<country>BR</country>");
		eml.append("</address>");
		eml.append("<phone>55-022-2141-3958</phone>");
		eml.append("<electronicMailAddress>").append(p.getPersonEmail()).append("</electronicMailAddress>");
		eml.append("<onlineUrl>").append(p.getPersonEmail()).append("</onlineUrl>");

	}
}
