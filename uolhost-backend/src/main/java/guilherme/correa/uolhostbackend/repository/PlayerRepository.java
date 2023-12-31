package guilherme.correa.uolhostbackend.repository;

import guilherme.correa.uolhostbackend.model.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {
}
