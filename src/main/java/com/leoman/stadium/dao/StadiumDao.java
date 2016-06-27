package com.leoman.stadium.dao;

        import com.leoman.common.dao.IBaseJpaRepository;
        import com.leoman.stadium.entity.Stadium;
        import com.leoman.user.entity.User;
        import org.springframework.data.jpa.repository.Query;

        import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface StadiumDao extends IBaseJpaRepository<Stadium> {

    public Stadium findById(Long id);

    @Query("SELECT a FROM User a WHERE a.userId=(SELECT b.stadiumUserId FROM Stadium b WHERE b.id=?1)")
    public List<User> findByNickName(Long id);
}
