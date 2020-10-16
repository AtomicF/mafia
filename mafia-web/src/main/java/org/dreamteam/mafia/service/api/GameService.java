package org.dreamteam.mafia.service.api;

import org.dreamteam.mafia.dto.CharacterDTO;
import org.dreamteam.mafia.exceptions.GameIsOverException;
import org.dreamteam.mafia.exceptions.GameNotStartedException;
import org.dreamteam.mafia.exceptions.IllegalMoveException;
import org.dreamteam.mafia.model.Character;
import org.dreamteam.mafia.model.*;

import java.util.List;

/**
 * Интерфейс сервиса, обеспечивающего ведение самой игры.
 */
public interface GameService {

    /**
     * Находит игру, идущую в указанной комнате
     *
     * @param room - комната
     * @return - игра, идущая в комнате
     * @throws GameNotStartedException - если игра в комнате еще не началась
     */
    Game getGameInRoom(Room room) throws GameNotStartedException;

    /**
     * Возвращает список всех персонажей в игре
     *
     * @param game - игра
     * @return - список персонажей
     */
    List<Character> getCharactersInGame(Game game);

    /**
     * Возвращает список всех сообщений в чате игры
     *
     * @param game - игра
     * @return - список сообщений
     */
    List<Message> getMessageLog(Game game);

    /**
     * Переводит игру в следующую фазу
     *
     * @param game - игра
     * @throws GameIsOverException - если игра уже окончена
     */
    void advancePhase(Game game) throws GameIsOverException;

    /**
     * Выдвигает персонажа на голосование
     *
     * @param user         - выдвигающий игрок
     * @param characterDTO - выдвигаемый персонаж
     * @throws IllegalMoveException - если выдвижение нарушает правила игры
     */
    void nominateCharacter(User user, CharacterDTO characterDTO) throws IllegalMoveException;

    /**
     * Голосует против персонажа
     *
     * @param user         - голосующий игрок
     * @param characterDTO - голосуемый против персонаж
     * @throws IllegalMoveException - если голосование нарушает правила игры
     */
    void voteCharacter(User user, CharacterDTO characterDTO) throws IllegalMoveException;
}
