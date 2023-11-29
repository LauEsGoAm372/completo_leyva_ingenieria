package com.example.app_gestion_estudiantil.service;

import aj.org.objectweb.asm.Opcodes;
import com.example.app_gestion_estudiantil.entity.Archivo;
import com.example.app_gestion_estudiantil.entity.Foro;
import com.example.app_gestion_estudiantil.entity.Representante;
import com.example.app_gestion_estudiantil.repository.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class RepresentanteService {

    @Autowired
    private RepresentanteRepository representanterepository;

    @Autowired
    private ArchivoService ArchivoService;

    public void guardarRepresentante( String categoria,String firstname, String description, MultipartFile archivo) throws IOException, ParseException {

        Representante representante = Representante.builder()
                .categoria(categoria)
                .firstname(firstname)
                .description(description)
                .votos(0)
                .build();

        Representante r = representanterepository.save(representante);

        String url2 = null;
        if (archivo != null && !archivo.isEmpty()) {
            Archivo archivoGuardado = ArchivoService.leerFoto(archivo);
            System.out.println(archivoGuardado);
            if (archivoGuardado != null) {
                url2 = archivoGuardado.getNombre_archivo();
                r.addFile(url2);
                System.out.println(url2);
            }
        }
        System.out.println(url2+"final");
        representanterepository.save(r);

    }

    public void votar(Long id, Long repre) throws IOException, ParseException {

        List<Representante> lista = getall();
        int fea = 0;

        for (int i = 0; i < lista.size(); i++) {
            List<Long> votantes = lista.get(i).getVotantes();
            if (!votantes.isEmpty()) {
                for (int j = 0; j < votantes.size(); j++) {
                    if (id.equals(votantes.get(j))) {
                        fea = 1;
                        break;
                    }
                    if (fea == 1) {
                        break;
                    }
                }
            }
        }

        if (fea == 0) {
            Optional<Representante> r = getRepresentante(repre);
            if (r.isPresent()) {
                Representante r2 = r.get();
                r2.setVotos(r2.getVotos() + 1);
                r2.addVotante(id);
                representanterepository.save(r2);
            } else {
                throw new IllegalArgumentException("Representante no encontrado");
            }
        } else {
            throw new IllegalArgumentException("Ya votó");
        }
    }


    public Optional<Representante> getRepresentante(Long id) {
        return representanterepository.findById(id);

    }


    public List<Representante> getall(){
        return (List<Representante>) representanterepository.findAll();
    }

    public void calificar(Long repre, String nota) throws IOException, ParseException {
        System.out.println(nota);
        Optional<Representante> r = getRepresentante(repre);;
        if (r.isPresent()) {
            Representante r2 = r.get();
            r2.addNota(nota);
            representanterepository.save(r2);
        } else {
            throw new IllegalArgumentException("Representante no encontrado");
        }
    }

}