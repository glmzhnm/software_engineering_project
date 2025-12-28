package com.galymzhan.finalspring.service.impl;

import com.galymzhan.finalspring.dto.NationDto;
import com.galymzhan.finalspring.entity.NationEntity;
import com.galymzhan.finalspring.mapper.NationMapper;
import com.galymzhan.finalspring.repository.NationRepo;
import com.galymzhan.finalspring.service.NationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NationServiceImpl implements NationService {
    private final NationMapper nationMapper;
    private final NationRepo nationRepo;

    @Override
    public List<NationDto> getAll(){
        return nationMapper.toDtoList(nationRepo.findAll());
    }

    @Override
    public NationDto getById(Long id) {
        NationEntity nation = nationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nation not found with id: " + id));
        return nationMapper.toDto(nation);
    }

    @Override
    @Transactional
    public NationDto createNation(NationDto nationDto) {
        return nationMapper.toDto(nationRepo.save(nationMapper.toEntity(nationDto)));
    }

    @Override
    @Transactional
    public NationDto updateNation(Long id, NationDto nationDto) {
        // ИСПРАВЛЕНО: Защита от NullPointerException
        NationEntity updateNationEntity = nationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nation not found with id: " + id));

        if (nationDto != null) {
            updateNationEntity.setName(nationDto.getNameDto());
            updateNationEntity.setDescription(nationDto.getDescriptionDto());
        }

        return nationMapper.toDto(nationRepo.save(updateNationEntity));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if(nationRepo.existsById(id)) {
            nationRepo.deleteById(id);
            return true;
        }
        return false;
    }
}