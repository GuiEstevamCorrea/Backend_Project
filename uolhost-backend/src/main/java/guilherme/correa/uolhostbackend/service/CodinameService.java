package guilherme.correa.uolhostbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class CodinameService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    private List<String> avangersCodinameList = new ArrayList<>();
    private List<String> justiceLeagueList = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void loadJsonData(){

        try{

            String codinameResposnse = restTemplate.getForObject(environment.getProperty("avangers"), String.class);
            JsonNode jsonNode = objectMapper.readTree(codinameResposnse);

            ArrayNode avangers = (ArrayNode) jsonNode.get("vingadores");

            for (JsonNode item : avangers) {
                JsonNode codinameNode = item.get("codinome");
                if (codinameNode != null && !codinameNode.isNull()) {
                    this.avangersCodinameList.add(codinameNode.asText());
                } else {
                    // Adicione logs ou imprima para diagnosticar o problema
                    System.out.println("Valor nulo ou ausente para 'codiname': " + item);
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void loadXmlData(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(environment.getProperty("justice.league"));

            NodeList codinameList = document.getElementsByTagName("codinome");

            for (int i = 0; i < codinameList.getLength(); i++){
                Element codinameElement = (Element) codinameList.item(i);
                String codiname = codinameElement.getTextContent();
                this.justiceLeagueList.add(codiname);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }



}
