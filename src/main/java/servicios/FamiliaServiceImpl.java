package servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accesoDatos.IFamiliaRepo;
import java.util.List;
import entidades.Familia;
import excepciones.Excepcion;

@Service
public class FamiliaServiceImpl implements FamiliaService {

	@Autowired
    private IFamiliaRepo familiaRepo;
	
	@Override
	public List<Familia> getAll() {
		return familiaRepo.findAll();
	}

	@Override
	public Familia getById(Integer id) {
		return familiaRepo.findById(id).orElse(null); //podrías lanzar una excepción si no se encuentra
	}

	@Override
	public void save(Familia familia) throws Excepcion {
		// Validación de ejemplo: no guardar si nombre es vacío
		if (familia.getNombre() == null || familia.getNombre().isBlank()) {
			throw new Excepcion("El nombre de la familia es obligatorio.");
		}
		familiaRepo.save(familia);
	}

	@Override
	public void deleteById(Integer id) {
		familiaRepo.deleteById(id);
	}

	@Override
	public List<Familia> filter(String nombre) {
		//return familiaRepo.buscarPorNombre(nombre);
		return familiaRepo.findByNombreContaining(nombre);
	}

	@Override
	public Familia getByNroFamilia(Integer nroFamilia) {
		 return familiaRepo.findById(nroFamilia).orElse(null); 
	}

	@Override
	public void deleteByNroFamilia(Integer nroFamilia) {
		familiaRepo.deleteById(nroFamilia);
	}

	@Override
	public List<Familia> findAll() {
		return familiaRepo.findAll();
	}
	
}
