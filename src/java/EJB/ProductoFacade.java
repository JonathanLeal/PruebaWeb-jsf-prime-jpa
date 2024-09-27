package EJB;

import entity.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "PruebaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> findByNombre(String nombre) {
        return em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre", Producto.class)
                 .setParameter("nombre", "%" + nombre + "%")
                 .getResultList();
    }
}
