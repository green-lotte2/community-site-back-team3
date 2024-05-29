package kr.co.orangenode.service.user;

import kr.co.orangenode.dto.terms.TermsDTO;
import kr.co.orangenode.entity.terms.Terms;
import kr.co.orangenode.repository.user.TermsRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TermsService {

    private final TermsRepository repository;
    private final ModelMapper modelMapper;

    public List<TermsDTO> getAllTerms() {
        List<Terms> termsList = repository.findAll();
        return termsList.stream()
                .map(terms -> modelMapper.map(terms, TermsDTO.class))
                .collect(Collectors.toList());
    }
}
