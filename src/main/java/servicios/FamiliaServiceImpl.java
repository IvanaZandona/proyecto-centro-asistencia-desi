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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Familia getByNroFamilia(Integer integer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByNroFamilia(Integer nroFamilia) {
		// TODO Auto-generated method stub
		
	}
	
}
