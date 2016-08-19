package com.leoman.stadium.dao;

        import com.leoman.common.dao.IBaseJpaRepository;
        import com.leoman.stadium.entity.Stadium;
        import com.leoman.stadium.entity.StadiumSub;
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

    //被预定总数
    @Query("SELECT COUNT(a) FROM Reserve a WHERE a.stadium.id = ?1")
    public Integer stadiumNum(Long id);

    //当前预定没使用的数(正在组队)
    @Query("SELECT COUNT(a) FROM Reserve a WHERE a.stadium.id = ?1 AND a.status='0'")
    public Integer availableStadiumNum(Long id);

    //球场积累金额
    @Query("SELECT SUM(a.price) FROM Reserve a WHERE a.stadium.id = ?1")
    public Integer accumulatedAmount(Long id);

}
