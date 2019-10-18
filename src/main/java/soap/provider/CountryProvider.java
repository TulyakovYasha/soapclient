package soap.provider;



import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class CountryProvider {

    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public String getCountry(String name) {

        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.spring.guides.gs_producing_web_service");
        marshaller.setSchema(new ClassPathResource("countries.xsd"));
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);

        GetCountryRequest getCountryRequest = new GetCountryRequest();
        getCountryRequest.setName(name);
        GetCountryResponse getCountryResponse = (GetCountryResponse) webServiceTemplate.marshalSendAndReceive(prop.getProperty("soap.server.url"), getCountryRequest);
        builder.append("Country : ")
                .append(getCountryResponse.getCountry().getName())
                .append("\n")
                .append("Capital : ")
                .append(getCountryResponse.getCountry().getCapital())
                .append("\n")
                .append("Population : ")
                .append(getCountryResponse.getCountry().getPopulation())
                .append("\n")
                .append("Currency : ")
                .append(getCountryResponse.getCountry().getCurrency());
        return builder.toString();
    }

    public void setDefaultUri(String defaultUri) {
        webServiceTemplate.setDefaultUri(defaultUri);
    }
}
