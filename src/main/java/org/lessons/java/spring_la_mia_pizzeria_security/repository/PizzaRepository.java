package org.lessons.java.spring_la_mia_pizzeria_security.repository;

import org.lessons.java.spring_la_mia_pizzeria_security.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    // Ricerca per nome o descrizione
    List<Pizza> findByNomeContainingIgnoreCaseOrDescrizioneContainingIgnoreCase(String nome, String descrizione);
}