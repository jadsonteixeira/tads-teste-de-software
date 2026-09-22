package com.testedesoftware.atividade.jacoco.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatriculaServiceTest {

    private MatriculaService matriculaService;

    @BeforeEach
    void setUp() {
        // Nao precisamos subir o contexto Spring: e um teste de unidade puro.
        matriculaService = new MatriculaService();
    }

    @Test
    @DisplayName("Dias diferentes nunca tem conflito, mesmo com horarios iguais")
    void diasDiferentesNaoDeveTerConflito() {
        Disciplina a = new Disciplina("SEGUNDA", 8.0, 10.0);
        Disciplina b = new Disciplina("TERCA", 8.0, 10.0);

        assertFalse(matriculaService.temConflito(a, b));
    }

    @Test
    @DisplayName("Mesmo dia, horarios identicos: deve ter conflito")
    void mesmoDiaHorariosIdenticosDeveTerConflito() {
        Disciplina a = new Disciplina("SEGUNDA", 8.0, 10.0);
        Disciplina b = new Disciplina("SEGUNDA", 8.0, 10.0);

        assertTrue(matriculaService.temConflito(a, b));
    }

    @Test
    @DisplayName("Mesmo dia, B comeca no meio de A: deve ter conflito")
    void mesmoDiaSobreposicaoParcialNoInicioDeBDeveTerConflito() {
        Disciplina a = new Disciplina("SEGUNDA", 8.0, 10.0);
        Disciplina b = new Disciplina("SEGUNDA", 9.0, 11.0);

        assertTrue(matriculaService.temConflito(a, b));
    }

    @Test
    @DisplayName("Mesmo dia, A comeca no meio de B: deve ter conflito")
    void mesmoDiaSobreposicaoParcialNoInicioDeADeveTerConflito() {
        Disciplina a = new Disciplina("SEGUNDA", 9.0, 11.0);
        Disciplina b = new Disciplina("SEGUNDA", 8.0, 10.0);

        assertTrue(matriculaService.temConflito(a, b));
    }

    @Test
    @DisplayName("Mesmo dia, uma disciplina totalmente contida na outra: deve ter conflito")
    void mesmoDiaUmaContidaNaOutraDeveTerConflito() {
        Disciplina a = new Disciplina("QUARTA", 8.0, 12.0);
        Disciplina b = new Disciplina("QUARTA", 9.0, 10.0);

        assertTrue(matriculaService.temConflito(a, b));
    }

    @Test
    @DisplayName("Mesmo dia, A termina exatamente quando B comeca: NAO deve ter conflito (limite do 1o if)")
    void mesmoDiaAdjacentesAAntesDeBNaoDeveTerConflito() {
        Disciplina a = new Disciplina("QUINTA", 8.0, 10.0);
        Disciplina b = new Disciplina("QUINTA", 10.0, 12.0);

        assertFalse(matriculaService.temConflito(a, b));
    }

    @Test
    @DisplayName("Mesmo dia, B termina exatamente quando A comeca: NAO deve ter conflito (limite do 2o if)")
    void mesmoDiaAdjacentesBAntesDeANaoDeveTerConflito() {
        Disciplina a = new Disciplina("SEXTA", 10.0, 12.0);
        Disciplina b = new Disciplina("SEXTA", 8.0, 10.0);

        assertFalse(matriculaService.temConflito(a, b));
    }

    @Test
    @DisplayName("Mesmo dia, sem nenhuma sobreposicao (folga entre os horarios): NAO deve ter conflito")
    void mesmoDiaSemSobreposicaoNaoDeveTerConflito() {
        Disciplina a = new Disciplina("SABADO", 8.0, 9.0);
        Disciplina b = new Disciplina("SABADO", 14.0, 16.0);

        assertFalse(matriculaService.temConflito(a, b));
    }

    @Test
    @DisplayName("O resultado deve ser o mesmo trocando a ordem dos parametros (A,B) e (B,A)")
    void ordemDosParametrosInvertidaDeveManterOMesmoResultado() {
        Disciplina a = new Disciplina("SEGUNDA", 8.0, 10.0);
        Disciplina b = new Disciplina("SEGUNDA", 9.0, 11.0);

        assertTrue(matriculaService.temConflito(a, b));
        assertTrue(matriculaService.temConflito(b, a));
    }
}
