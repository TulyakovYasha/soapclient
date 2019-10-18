package soap.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soap.repository.CountryRepository;

@RestController
public class CountryClientEndpoint {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/getCountry")
    public String getCountry(@RequestParam("name") String name) {
        return countryRepository.getCountry(name);
    }


}
