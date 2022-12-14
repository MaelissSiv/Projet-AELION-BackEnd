package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Poe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoeRepository extends JpaRepository<Poe,Integer> {
}
