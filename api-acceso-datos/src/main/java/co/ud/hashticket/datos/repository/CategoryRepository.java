package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long>, CrudRepository<CategoryEntity, Long> {
}
