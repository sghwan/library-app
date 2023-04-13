package com.group.libraryapp.tmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final PeopleRepository peopleRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TestController(PeopleRepository peopleRepository, TeamRepository teamRepository) {
        this.peopleRepository = peopleRepository;
        this.teamRepository = teamRepository;
    }

    /**
     * 아래 둘다 의존 관계가 설정이 되었다 영속성 전이가 이루어 지는 이유는 의존 관계가 설정이 되었을 때
     * 필드가 존재하지 않으면 em.persist
     * 필드가 존재하면 변경 감지
     * 일단 Transaction 안에서 영속성 전이도 일어난다는 것을 무조건 인지하고 있자.
     *
     * 궁금하던 사항에서 cascade없으면 insert 문도 안나감감
     * 없는 것을 의존관계로 맞추려다 보니까 insert 문으로 강제로 만들어 주는것 같다. (cascade 있을 때만)
     * */
    @PostMapping("/test1")
    @Transactional //cascade transactional 안에서 근데 왜 부모가 전이를 받을까?
    public String test1(@RequestBody CreateRequest request) {
        Team byName = teamRepository.findByName(request.getTeamName());
        byName.addPerson("byName1");

        return "ok";
    }

    /**
     * 여기는 변경감지를 사용하는 것이라 cascade없어도 transaction 안이므로 update문 나감
     */
    @PostMapping("/test2")
    @Transactional //cascade transactional 안에서 근데 왜 부모가 전이를 받을까?
    public String test2(@RequestBody CreateRequest request) {
        Person byName1 = peopleRepository.findByName(request.getUsername());
        Team byName = teamRepository.findByName(request.getTeamName());
        byName.addPerson(byName1);

        return "ok";
    }
}
