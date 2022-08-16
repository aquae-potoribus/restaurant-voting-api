package ru.javaops.topjava2.web.myControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.UserRepository;
import ru.javaops.topjava2.repository.VoteRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
// TODO: cache only most requested data!
public class VoteRestController {

    static final String REST_URL = "/api/vote";

    @Autowired
    protected VoteRepository repository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    private static final Integer END_VOTING_HOURS = 11;
    private static final Integer END_VOTING_MINUTES = 0;

    @GetMapping(REST_URL)
    List<Vote> all() {
        log.info("get  all{}");

        return repository.findAll();
    }

    // Single item
    @GetMapping(REST_URL + "/{id}")
    Vote one(@PathVariable Integer id) {
        log.info("get {}", id);

        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(value = REST_URL + "/createArray", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> createArray(@RequestBody List<Vote> votes) {
        log.info("createArray {}", votes);

        return repository.saveAll(votes);
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote createOrUpdate(@RequestBody VoteTo vote) {
        Vote newVote = new Vote(restaurantRepository.getById(vote.getRestaurantId()), userRepository.getById(vote.getUserId()), LocalDateTime.now());
        log.info("create " + newVote);
        Optional<Vote> oldVoteOptional = repository.getVoteByDateAndUser(LocalDate.now(), newVote.getUser());

        if (oldVoteOptional.isPresent()) {
            Vote oldValue = oldVoteOptional.get();
            if (oldValue.getDateTime().toLocalTime().isAfter(LocalTime.of(END_VOTING_HOURS, END_VOTING_MINUTES))) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_ACCEPTABLE, "Votes are not accepted after " + END_VOTING_HOURS + ":" + END_VOTING_MINUTES);
            }
            return repository.updateVote(newVote.getUser(), newVote.getDateTime(), newVote.getRestaurant(), newVote.getId());
        }
        return repository.save(newVote);

    }

    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Vote update(@RequestBody Vote vote, @PathVariable Integer id) {
        log.info("update {}", vote);
        vote.setId(id);
        return repository.save(vote);
    }

    @DeleteMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer id) {
        log.info("delete {}", id);
        repository.delete(id);
    }
}