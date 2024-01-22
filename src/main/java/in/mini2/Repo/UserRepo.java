package in.mini2.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.mini2.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByEmailAndRandompass(String email,String randomPass);
	
	public User findByEmail(String email);
	
	public User findByRandompass(String randompass);

	public User findByEmailAndPwd(String email, String pass);
	
}
