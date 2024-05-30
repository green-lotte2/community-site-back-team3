package kr.co.orangenode.repository.Custom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface ProjectRepositoryCustom {

    public List<Tuple> selectKanban(int proNo);
}
