package kr.co.orangenode.repository.custom.cs;

import kr.co.orangenode.entity.cs.CsEntity;

import java.util.List;

public interface CsRepositoryCustom {
    List<CsEntity> selectCs(String CateName);
}
