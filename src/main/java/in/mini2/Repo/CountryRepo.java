package in.mini2.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.mini2.Model.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, String>{

}
