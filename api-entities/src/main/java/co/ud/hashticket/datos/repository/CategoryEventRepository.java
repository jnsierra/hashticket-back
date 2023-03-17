package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.CategoryEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryEventRepository extends JpaRepository<CategoryEventEntity, Long>, CrudRepository<CategoryEventEntity, Long> {
}
