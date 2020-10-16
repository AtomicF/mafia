package org.dreamteam.mafia.repository.api;

import org.dreamteam.mafia.dao.CharacterDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data репозиторий для обеспечения CRUD доступа к персонажам
 */
@Repository
public interface CharacterRepository extends CrudRepository<CharacterDAO, Long> {

}
