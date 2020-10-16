package org.dreamteam.mafia.service.api;

import org.dreamteam.mafia.dto.RoomDTO;
import org.dreamteam.mafia.exceptions.NoSuchRoomException;
import org.dreamteam.mafia.exceptions.NotEnoughRightsException;
import org.dreamteam.mafia.model.Room;
import org.dreamteam.mafia.model.User;

import java.util.List;

/**
 * Интерфейс с ервиса, обслуживающего систему комнат
 */
public interface RoomService {

    /**
     * Находит комнату по описанию, переданному из интерфейса
     *
     * @param roomDTO - описание комнаты, полученное из интерфейса
     * @return - найденная комната
     * @throws NoSuchRoomException - если описываемая комната не существует
     */
    Room getRoomFromDTO(RoomDTO roomDTO) throws NoSuchRoomException;

    /**
     * Возвращает комнату, в которой находится указанный пользователь
     *
     * @param user - пользователь, комнату которого нужно найти
     * @return - найденная комната
     * @throws NoSuchRoomException - если пользователь не находится в комнате
     */
    Room getUsersRoom(User user) throws NoSuchRoomException;

    /**
     * Возвращает администратора заданной комнаты
     *
     * @param room - комната
     * @return - пользователь - администратор комнаты.
     */
    User getRoomAdmin(Room room);

    /**
     * Создает новую комнату
     *
     * @param roomDTO - описание комнаты, полученние из интерфейса
     * @return - созданная комната
     */
    Room createRoom(RoomDTO roomDTO);

    /**
     * Проверяет является ли заданная комната приватной
     *
     * @param room - описание комнаты, полученние из интерфейса
     * @return - true, если комната приватна, false - иначе
     */
    boolean isRoomPrivate(Room room);

    /**
     * Пытается добавить пользователя в приватную комнату
     *
     * @param user         - пользователь
     * @param room         - комната
     * @param roomPassword - пароль от комнаты
     * @return -  true, если удалось добавить пользователя в комнату, false -иначе
     */
    boolean joinRoom(User user, Room room, String roomPassword);

    /**
     * Пытается добавить пользователя в публичную комнату
     *
     * @param user - пользователь
     * @param room - комната
     * @return -  true, если удалось добавить пользователя в комнату, false -иначе
     */
    boolean joinRoom(User user, Room room);

    /**
     * Возвращает все незаполненные (доступные для присоединения) комнаты в приложении
     *
     * @return - список незаполненных комнат
     */
    List<Room> getNonFullRooms();

    /**
     * Возвращает список пользователей внутри комнат
     *
     * @param room - комната
     * @return - список пользователей в комнате
     */
    List<User> getUsersInRoom(Room room);

    /**
     * Пытается убрать пользователя из комнаты
     *
     * @param admin  - пользователь, запросивший изгнанине
     * @param target - изгоняемый пользователь
     * @throws NoSuchRoomException      - если оба пользователя не находятся в одной и той же комнате
     * @throws NotEnoughRightsException - если запросивший пользователь не является администратором своей комнаты
     */
    void kickUser(User admin, User target) throws NoSuchRoomException, NotEnoughRightsException;

    /**
     * Проверяет заполнена ли комната
     *
     * @param room - комната для проверки
     * @return - true, если комната заполнена, false - иначе
     */
    boolean isRoomFull(Room room);

    /**
     * Подтверждает\отменяет готовность пользователя для начала игры в комнате, в которой он сейчас находится
     *
     * @param user  - пользователь
     * @param ready - состояния, на которое нужно изменить готовность
     * @throws NoSuchRoomException - если данный пользователь не находится сейчас в комнате
     */
    void setReady(User user, boolean ready) throws NoSuchRoomException;

    /**
     * Проверяет готовы ли все пользователи  в комнате для начала игры
     *
     * @param room - комната для проверки
     * @return - true, если все пользователи в комнате готовы, false - иначе
     */
    boolean isRoomReady(Room room);

    /**
     * Запускает игру в комнате
     *
     * @param user - игрок, запускающий игру (должен быть адмнистратором комнаты)
     * @throws NoSuchRoomException      - если данный пользователь не находится сейчас в комнате
     * @throws NotEnoughRightsException - если данный пользователь не является администратором своей комнаты
     */
    void startGame(User user) throws NoSuchRoomException, NotEnoughRightsException;
}
