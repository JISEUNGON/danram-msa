package com.danram.party.repository;

import com.danram.party.domain.Party;
import com.danram.party.domain.PartyMember;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PartyMemberRepository extends JpaRepository<PartyMember,Long> {

    @Query("select pm from PartyMember pm where pm.memberId = :memberId and " +
            "pm.deletedAt is null")
    public Slice<PartyMember> findByMemberId(Long memberId, Pageable pageable);

    @Query("select pm from PartyMember pm where pm.memberId = :memberId and " +
            "pm.party = :party and " +
            "pm.deletedAt is null")
    public Optional<PartyMember> findByMemberIdAndParty(@Param("memberId") Long memberId,@Param("party") Party party);
    public List<PartyMember> findByParty(Party party);
    public Optional<PartyMember> findByPartyAndMemberId(Party party, Long memberId);
}
