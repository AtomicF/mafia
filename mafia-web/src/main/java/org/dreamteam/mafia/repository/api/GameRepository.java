package org.dreamteam.mafia.repository.api;

import org.dreamteam.mafia.dao.GameDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data репозиторий для обеспечения CRUD доступа к играм
 */
@Repository
public interface GameRepository extends CrudRepository<GameDAO, Long> {
}
