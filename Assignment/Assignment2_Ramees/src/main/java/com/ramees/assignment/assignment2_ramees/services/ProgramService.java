package com.ramees.assignment.assignment2_ramees.services;

import com.ramees.assignment.assignment2_ramees.Repositories.ProgramRepository;
import com.ramees.assignment.assignment2_ramees.models.Program;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProgramService.class);

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Program findByProgramCode(String programCode) {
        logger.info("Fetching program {}", programCode);
        return programRepository.findByProgramCode(programCode);
    }

    public void addProgram(Program program) {
        logger.info("Adding a new program: {}", program.getProgramCode());
        programRepository.save(program);
    }

    public void deleteProgram(Program program) {
        logger.info("Deleting program: {}", program.getProgramCode());
        program.setStatus("INACTIVE");
        programRepository.delete(program);
    }

    public List<Program> findAll() {
        logger.info("Fetching all programs");
        return programRepository.findAll();
    }

    public List<Program> findAllActive() {
        logger.info("Fetching all active programs");
        return programRepository.findAllByStatus("ACTIVE");
    }
}
