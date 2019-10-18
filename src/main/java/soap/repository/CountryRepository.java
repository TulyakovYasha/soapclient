package soap.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soap.provider.CountryProvider;

@Repository
public class CountryRepository {

    @Autowired
    CountryProvider provider;

    public String getCountry(String name) {
        return provider.getCountry(name);
    }

}
