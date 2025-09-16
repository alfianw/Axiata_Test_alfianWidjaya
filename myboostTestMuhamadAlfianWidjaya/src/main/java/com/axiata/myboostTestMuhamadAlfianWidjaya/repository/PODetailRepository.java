/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.repository;

import com.axiata.myboostTestMuhamadAlfianWidjaya.model.PODetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hp
 */
public interface PODetailRepository extends JpaRepository<PODetail, Integer> {

    List<PODetail> findByPoHeaderId(Integer poHeaderId);
}
