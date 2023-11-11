package com.example.hycare.Service;

import com.example.hycare.Repository.DiagnosisRepository;
import com.example.hycare.dto.DiagnosisDto;
import com.example.hycare.entity.Diagnosis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    public void saveDiagnosis(DiagnosisDto diagnosisDto) {

        // Dto -> Entity
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagLink(diagnosisDto.getDiagLink());
        diagnosis.setConsultationSheet(diagnosisDto.getConsultationSheet());

        /**
         * id 임의 생성
         * 추후에 WebRTC UUID로 바꾸어주어야함*/
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "HHmmss"; //HHmmss 형태로 변환
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        diagnosis.setDiagId(formatter.format(today));

        // DB에 저장
        diagnosisRepository.save(diagnosis);
    }

    public DiagnosisDto findData(Long id) {
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
