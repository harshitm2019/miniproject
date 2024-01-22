package in.mini2.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import in.mini2.Model.City;

@Repository
public interface CityRepo extends JpaRepository<City, String>{

	@Query("SELECT c.cityname FROM City c WHERE c.state.statename = :stateName")
    List<String> findCityNamesByStateName(@Param("stateName") String stateName);
	
}
