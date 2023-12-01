package com.example.hycare.Service;

import com.example.hycare.Repository.DiagnosisRepository;
import com.example.hycare.dto.DiagnosisDto;
import com.example.hycare.entity.Diagnosis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    public String makeDiagnosis(DiagnosisDto diagnosisDto) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagId(diagnosisDto.getDiagId());
        diagnosis.setDoctorName(diagnosisDto.getDoctorName());

        // DB에 저장
        Diagnosis diagnosis1 = diagnosisRepository.save(diagnosis);

        return diagnosis1.getDiagId();
    }

    public void saveDiagnosis(DiagnosisDto diagnosisDto, String uuid) {

        // Dto -> Entity
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagId(uuid);
        diagnosis.setDiagLink(diagnosisDto.getDiagLink());
        diagnosis.setPatientName(diagnosisDto.getPatientName());
        diagnosis.setConsultationSheet(diagnosisDto.getConsultationSheet());

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        diagnosis.setDiagTime(timestamp);

        // DB에 저장
        diagnosisRepository.save(diagnosis);
    }

    public DiagnosisDto findDiagnosis() {
        DiagnosisDto diagnosisDto = new DiagnosisDto();
        Diagnosis diagnosis = diagnosisRepository.findDiagnosis();
        if(diagnosis != null) {
            diagnosisDto = DiagnosisDto.builder()
                    .diagId(diagnosis.getDiagId())
                    .diagLink(diagnosis.getDiagLink())
                    .consultationSheet(diagnosis.getConsultationSheet())
                    .diagTime(diagnosis.getDiagTime())
                    .doctorName(diagnosis.getDoctorName())
                    .patientName(diagnosis.getPatientName())
                    .build();

        }
        return diagnosisDto;
    }

    public DiagnosisDto findData(String id) {
        Optional<Diagnosis> diagnosis = diagnosisRepository.findById(id);

        if(!diagnosis.isEmpty()) {
            DiagnosisDto hycareDto = DiagnosisDto.builder()
                    .diagId(diagnosis.get().getDiagId())
                    .diagLink(diagnosis.get().getDiagLink())
                    .consultationSheet(diagnosis.get().getConsultationSheet())
                    .diagTime(diagnosis.get().getDiagTime())
                    .doctorName(diagnosis.get().getDoctorName())
                    .patientName(diagnosis.get().getPatientName())
                    .build();
            return hycareDto;
        }

        return null;
    }
}
