package com.kmsocialapp.chat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDao extends JpaRepository<Chat,Long> {
}
