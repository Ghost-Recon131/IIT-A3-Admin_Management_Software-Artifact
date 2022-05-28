package rmit.iit.a3.Logs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmit.iit.a3.Logs.model.Log;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findAll();

    List<Log> getByLogID(Long logID);

}
