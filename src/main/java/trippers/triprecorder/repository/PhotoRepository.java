package trippers.triprecorder.repository;

import org.springframework.data.repository.CrudRepository;
import trippers.triprecorder.dto.PhotoDto;

public interface PhotoRepository extends CrudRepository<PhotoDto, Long> {

}
