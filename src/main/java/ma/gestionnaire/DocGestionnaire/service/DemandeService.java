package ma.gestionnaire.DocGestionnaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.gestionnaire.DocGestionnaire.entities.Demande;
import ma.gestionnaire.DocGestionnaire.repositories.DemandeRepository;

@Service
public class DemandeService {

    @Autowired
    DemandeRepository demandeRepository;

    public Demande save(Demande demande) {
        return demandeRepository.save(demande);
    }

    public List<Demande> getAll() {
        return demandeRepository.findAll();
    }

    public void delete(Long id) {
        demandeRepository.deleteById(id);
    }

    public Demande getById(Long id) {
        return demandeRepository.findById(id).get();
    }

}
