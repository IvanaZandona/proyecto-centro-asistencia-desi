package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import accesoDatos.IRecetaRepo;

@Service
public class RecetaServiceImpl implements RecetaService{
	
	private final IRecetaRepo recetaRepo;
	
	@Autowired
	public RecetaServiceImpl(IRecetaRepo recetaRepo) {
		this.recetaRepo = recetaRepo;
		}
	
	public String crearReceta() {
		return null;
		
	}
}
              