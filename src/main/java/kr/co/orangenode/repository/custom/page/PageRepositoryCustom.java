package kr.co.orangenode.repository.custom.page;

import kr.co.orangenode.entity.page.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PageRepositoryCustom {

    public List<Page> findPagesByPartnerUid(String uid);
}
