package com.danram.user.repository;

import com.danram.user.domain.DeletedMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedMemberRepository extends JpaRepository<DeletedMember, Long> {
}
