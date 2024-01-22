package in.mini2.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.mini2.Model.State;

@Repository
public interface StateRepo extends JpaRepository<State, String> {

	@Query("SELECT s.statename FROM State s WHERE s.country.countryname = :countryName")
    List<String> findStateNamesByCountryName(@Param("countryName") String countryName);
	
	
	
}
	
