package com.example.TimeHarmony.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TimeHarmony.dtos.AccessHistory;
import com.example.TimeHarmony.dtos.Favorites;
import com.example.TimeHarmony.dtos.WatchInCart;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;

public interface MemberRepository extends JpaRepository<Members, UUID> {
        @Query("select m from Members m where m.member_id = ?1")
        Optional<Members> getMemberById(String member_id);

        @Query("select m from Members m where m.user_log_info = ?1")
        Optional<Members> getMemberbyUserLogInfo(Users user);

        @Query("select m from Members m where m.email = :email")
        List<Members> getMemberbyEmail(@Param("email") String email);

        @Query(value = "select * from Access_History h where h.member_id = :id", nativeQuery = true)
        List<AccessHistory> getAllAccessHistoriesFromMember(@Param("id") UUID id);

        @Query(value = "select * from Favorites f where f.member_id = :id", nativeQuery = true)
        List<Favorites> getFavoritesFromMember(@Param("id") UUID id);

        @Modifying
        @Transactional
        @Query(value = "insert Favorites(member_id, watch_id) values (:m_id, :w_id)", nativeQuery = true)
        void insertFavorites(@Param("m_id") UUID m_id, @Param("w_id") String w_id);

        @Modifying
        @Transactional
        @Query(value = "delete Favorites where watch_id = :w_id and member_id = :m_id", nativeQuery = true)
        void deleteFavorites(@Param("w_id") String w_id, @Param("m_id") UUID m_id);

        @Modifying
        @Transactional
        @Query(value = "insert Access_History(member_id, url, access_time) values (:id , :url , :access_time)", nativeQuery = true)
        void insertAccessHistory(@Param("id") UUID id, @Param("url") String url,
                        @Param("access_time") Timestamp access_time);

        @Modifying
        @Transactional
        @Query("update Members m set m.last_login_date = :date where m.member_id = :id")
        void updateLastLoginDate(@Param("date") Timestamp date, @Param("id") UUID id);

        @Modifying
        @Transactional
        @Query("update Members m set m.last_logout_date = :date where m.member_id = :id")
        void updateLastLogoutDate(@Param("date") Timestamp date, @Param("id") UUID id);

        @Modifying
        @Transactional
        @Query("update Members m set m.is_active = :status where m.member_id = :id")
        void updateActiveStatus(@Param("status") int status, @Param("id") UUID id);

        @Modifying
        @Transactional
        @Query("update Members m set m.email = :email where m.member_id = :id")
        void updateMemberEmail(@Param("email") String email, @Param("id") UUID id);

        @Modifying
        @Transactional
        @Query("update Members m set m.member_image = :url where m.member_id = :id")
        void updateMemberImage(@Param("url") String url, @Param("id") UUID id);

        @Modifying
        @Transactional
        @Query(value = "insert into Watches_In_Cart([watch_id], [cart_id], order_id, [checked], [add_date], [state]) values (:wid, :cid, :oid, :checked, :date, :state)", nativeQuery = true)
        void addToCart(@Param("wid") String wid, @Param("cid") String cid, @Param("oid") String oid,
                        @Param("checked") Integer checked, @Param("date") Timestamp date,
                        @Param("state") Integer state);

        @Query(value = "select * from Watches_In_Cart where cart_id = :cid", nativeQuery = true)
        List<WatchInCart> getCart(@Param("cid") String cid);

        @Modifying
        @Transactional
        @Query(value = "delete [dbo].[Watches_In_Cart] where cart_id = :cid and watch_id = :wid", nativeQuery = true)
        void deleteWatchInCart(@Param("cid") String cid, @Param("wid") String wid);

        @Query(value = "select watch_id from Watches_In_Cart where cart_id = :cid", nativeQuery = true)
        List<String> getWatchesInCart(@Param("cid") String cid);

    @Query(value = "select m from Members m where m.cart_id = ?1")
    Optional<Members> getMemberByCartId(String cid) ; 
}
