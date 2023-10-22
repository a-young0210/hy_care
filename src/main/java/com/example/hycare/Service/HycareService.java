package com.example.hycare.Service;

import com.example.hycare.dto.HycareDto;
import com.example.hycare.Repository.HycareRepository;
import com.example.hycare.entity.Hycare;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HycareService {
    private final HycareRepository hycareRepository;

    public void saveHycare(HycareDto hycareDto) {

        // Dto -> Entity
        Hycare hycare = new Hycare();
        hycare.setDiagText(hycareDto.getDiagText());
        hycare.setSheetImg(hycareDto.getSheetImg());

        // DB에 저장
        hycareRepository.save(hycare);
    }

    public HycareDto findData(Long id) {
        Optional<Hycare> hycare = hycareRepository.findById(id);

        HycareDto hycareDto = HycareDto.builder()
                .id(hycare.get().getId())
                .diagText(hycare.get().getDiagText())
                .sheetImg(hycare.get().getSheetImg())
                .sttTime(hycare.get().getSttTime())
                .EndTime(hycare.get().getEndTime())
                .build();

        return hycareDto;
    }
}
