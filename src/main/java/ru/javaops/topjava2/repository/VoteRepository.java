package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    Optional<Vote> getVoteById(Long id);

    @Query("SELECT v FROM Vote v WHERE FORMATDATETIME(registered,'yyyy-MM-dd')  = ?1 and v.user = ?2")
    Optional<Vote> getVoteByDateAndUser(LocalDate date, User user);

    @Modifying
    @Query("UPDATE Vote v set v.user = ?1, v.dateTime = ?2, v.restaurant = ?3 WHERE v.id = ?4")
    Vote updateVote(User user, LocalDateTime dateTime, Restaurant restaurant, Integer id);
}