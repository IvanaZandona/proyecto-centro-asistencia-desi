package accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entidades.Asistido;

public interface IAsistidoRepo extends JpaRepository<Asistido, Long>{

}
