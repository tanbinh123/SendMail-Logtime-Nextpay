//package com.example.emaillogtime.controller;
//
//import com.example.emaillogtime.dto.EntriesTimeDTO;
//import com.example.emaillogtime.reposiotry.EntriesTimeDtoRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@ComponentScan
//@RestController
//@RequestMapping(value = "config/v1")
//@Slf4j
//public class ControllerEntriesDTO {
//
//    @Autowired
//    private EntriesTimeDtoRepository entriesTimeDtoRepository;
//
//    @PostMapping()
//    public ResponseEntity<EntriesTimeDTO> createEntriesTimeDTO (@RequestBody EntriesTimeDTO entriesTimeDTO) {
//        return ResponseEntity.status(HttpStatus.OK).body(entriesTimeDtoRepository.save(entriesTimeDTO));
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<EntriesTimeDTO> updateEntriesTimeDTO (@PathVariable(name = "id") Long entriesTimeDtoId,
//                                                     @RequestBody(required = false) EntriesTimeDTO entriesTimeDTO) {
//        Optional<EntriesTimeDTO> entriesTimeDTO1 = entriesTimeDtoRepository.findById(entriesTimeDtoId);
//        if (entriesTimeDTO1.isPresent()){
//            entriesTimeDTO1.get().setTimeWeekDto(entriesTimeDTO.getTimeWeekDto());
//            log.info("ok ");
//        } else {
//            log.info("khong ton tai id");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(entriesTimeDtoRepository.save(entriesTimeDTO1.get()));
//    }
//}
