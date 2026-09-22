package com.testedesoftware.atividade.jacoco.junit;

import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    public boolean temConflito(Disciplina a, Disciplina b) {
        if (!a.getDia().equals(b.getDia())) {
            return false;
        }
        if (a.getHoraFim() <= b.getHoraInicio()) {
            return false;
        }
        if (b.getHoraFim() <= a.getHoraInicio()) {
            return false;
        }
        return true;
    }
}
