package com.example.hycare.Service;

import com.example.hycare.Repository.DiagnosisRepository;
import com.example.hycare.dto.DiagnosisDto;
import com.example.hycare.entity.Diagnosis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    public void saveDiagnosis(DiagnosisDto diagnosisDto, String uuid) {

        // Dto -> Entity
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagId(uuid);
        diagnosis.setDiagLink(diagnosisDto.getDiagLink());
        diagnosis.setConsultationSheet(diagnosisDto.getConsultationSheet());

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        diagnosis.setDiagTime(timestamp);

        // DB에 저장
        diagnosisRepository.save(diagnosis);
    }

    public DiagnosisDto findData(String id) {
        Optional<Diagnosis> diagnosis = diagnosisRepository.findById(id);

        DiagnosisDto hycareDto = DiagnosisDto.builder()
                .diagId(diagnosis.get().getDiagId())
                .diagLink(diagnosis.get().getDiagLink())
                .consultationSheet(diagnosis.get().getConsultationSheet())
                .diagTime(diagnosis.get().getDiagTime())
                .build();

        return hycareDto;
    }
}
